package edu.ncsu.csc440.poketrader.service;

import edu.ncsu.csc440.poketrader.dto.CardDto;
import edu.ncsu.csc440.poketrader.dto.TransactionDto;
import edu.ncsu.csc440.poketrader.entity.Card;
import edu.ncsu.csc440.poketrader.entity.CardTransaction;
import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.repository.CardRepository;
import edu.ncsu.csc440.poketrader.repository.TransactionRepository;
import edu.ncsu.csc440.poketrader.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Carter
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    /** Return all transactions where the user is buyer or seller. */
    public List<TransactionDto> getUserTransactions(Long userId) {
        List<CardTransaction> transactions = new ArrayList<>();
        transactions.addAll(transactionRepository.findBySellerUser_UserId(userId));
        transactions.addAll(transactionRepository.findByBuyerUser_UserId(userId));

        List<TransactionDto> dtos = new ArrayList<>();
        for (CardTransaction t : transactions) {
            dtos.add(toDto(t));
        }
        return dtos;
    }

    /** Record a new buy/sell transaction. */
    public TransactionDto createTransaction(Long sellerId, Long buyerId,
                                            Long cardId, BigDecimal price) {

        if (sellerId == null || buyerId == null || cardId == null || price == null) {
            throw new IllegalArgumentException("Missing required transaction fields");
        }
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("Seller or buyer does not exist"));
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("Seller or buyer does not exist"));
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Card does not exist"));

        if (seller.getUserId().equals(buyer.getUserId())) {
            throw new IllegalArgumentException("Seller and buyer must be different users");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        CardTransaction transaction = new CardTransaction();
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setSellerUser(seller);
        transaction.setBuyerUser(buyer);
        transaction.setCard(card);
        transaction.setPrice(price);

        return toDto(transactionRepository.save(transaction));
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private TransactionDto toDto(CardTransaction t) {
        return new TransactionDto(
                t.getTransactionId(),
                t.getTransactionTime(),
                t.getSellerUser().getUserId(),
                t.getBuyerUser().getUserId(),
                t.getCard() != null ? t.getCard().getCardId() : null,
                t.getPrice(),
                toCardDto(t.getCard())
        );
    }

    private CardDto toCardDto(Card card) {
        if (card == null) return null;
        return new CardDto(
                card.getCardId(),
                card.getName(),
                card.getPrimaryType(),
                card.getSecondaryType(),
                card.getGrade(),
                card.getHp(),
                card.getCardSet(),
                category(card),
                null
        );
    }

    private String category(Card card) {
        if (card.getPrimaryType() != null) return "Pokemon";
        if (card.getName() != null && card.getName().toLowerCase().contains("energy")) return "Energy";
        return "Trainer";
    }
}
