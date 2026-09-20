package edu.ncsu.csc440.poketrader.service;

import edu.ncsu.csc440.poketrader.config.Roles.UserRoles;
import edu.ncsu.csc440.poketrader.dto.CardDto;
import edu.ncsu.csc440.poketrader.dto.CreateCardDto;
import edu.ncsu.csc440.poketrader.dto.MoveDto;
import edu.ncsu.csc440.poketrader.entity.Card;
import edu.ncsu.csc440.poketrader.entity.Move;
import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.repository.CardMoveRepository;
import edu.ncsu.csc440.poketrader.repository.CardRepository;
import edu.ncsu.csc440.poketrader.repository.TradeRepository;
import edu.ncsu.csc440.poketrader.repository.TransactionRepository;
import edu.ncsu.csc440.poketrader.repository.UserCardRepository;
import edu.ncsu.csc440.poketrader.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Carter
 */
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardMoveRepository cardMoveRepository;
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;
    private final TradeRepository tradeRepository;
    private final TransactionRepository transactionRepository;

    public CardService(CardRepository cardRepository, CardMoveRepository cardMoveRepository,
                       UserRepository userRepository, UserCardRepository userCardRepository,
                       TradeRepository tradeRepository, TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.cardMoveRepository = cardMoveRepository;
        this.userRepository = userRepository;
        this.userCardRepository = userCardRepository;
        this.tradeRepository = tradeRepository;
        this.transactionRepository = transactionRepository;
    }

    private void requireAdmin(HttpSession session) {
        Object userIdAttr = session.getAttribute("userId");
        if (!(userIdAttr instanceof Long userId)) {
            throw new IllegalStateException("Not authenticated");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null || user.getRole() != UserRoles.Admin) {
            throw new SecurityException("Admin access required");
        }
    }

    /**
     * Return all cards, optionally filtered by name and/or primaryType.
     * Trainer/Energy cards (null PrimaryType) are included regardless of type filter
     * unless the filter is "Trainer" or "Energy" (resolved via category field).
     */
    public List<CardDto> getCards(String name, String type) {
        List<Card> cards = cardRepository.findAll();
        String nameFilter = null;
        String typeFilter = null;

        if (name != null && !name.isBlank()) {
            nameFilter = name.toLowerCase();
        }
        if (type != null && !type.isBlank()) {
            typeFilter = type.toLowerCase();
        }

        List<CardDto> dtos = new ArrayList<>();
        for (Card card : cards) {
            String category;
            if (card.getPrimaryType() != null) {
                category = "Pokemon";
            } else if (card.getName() != null && card.getName().toLowerCase().contains("energy")) {
                category = "Energy";
            } else {
                category = "Trainer";
            }

            boolean include = true;
            if (nameFilter != null) {
                if (card.getName() == null || !card.getName().toLowerCase().contains(nameFilter)) {
                    include = false;
                }
            }
            if (include && typeFilter != null) {
                if ("trainer".equals(typeFilter) || "energy".equals(typeFilter)) {
                    if (!typeFilter.equals(category.toLowerCase())) {
                        include = false;
                    }
                } else if (card.getPrimaryType() == null || !typeFilter.equals(card.getPrimaryType().toLowerCase())) {
                    include = false;
                }
            }

            if (include) {
                dtos.add(new CardDto(
                        card.getCardId(),
                        card.getName(),
                        card.getPrimaryType(),
                        card.getSecondaryType(),
                        card.getGrade(),
                        card.getHp(),
                        card.getCardSet(),
                        category,
                        null
                ));
            }
        }
        return dtos;
    }

    /** Return a single card with its moves populated. */
    public CardDto getCardById(Long cardId) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card == null) {
            throw new IllegalArgumentException("Card not found");
        }

        List<MoveDto> moveDtos = new ArrayList<>();
        if (card.getMoves() != null) {
            for (Move move : card.getMoves()) {
                moveDtos.add(new MoveDto(
                        move.getMoveId(),
                        move.getName(),
                        move.getDamage(),
                        move.getDescription(),
                        move.getElement(),
                        move.getEnergyAmnt()
                ));
            }
        }

        String category;
        if (card.getPrimaryType() != null) {
            category = "Pokemon";
        } else if (card.getName() != null && card.getName().toLowerCase().contains("energy")) {
            category = "Energy";
        } else {
            category = "Trainer";
        }

        return new CardDto(
                card.getCardId(),
                card.getName(),
                card.getPrimaryType(),
                card.getSecondaryType(),
                card.getGrade(),
                card.getHp(),
                card.getCardSet(),
                category,
                moveDtos
        );
    }

    /** Admin only: create a new card. */
    public CardDto createCard(CreateCardDto request, HttpSession session) {
        requireAdmin(session);

        if (request == null || request.getName() == null || request.getName().isBlank()
                || request.getCardSet() == null || request.getCardSet().isBlank()) {
            throw new IllegalArgumentException("Name and cardSet are required");
        }

        Card card = new Card();
        card.setName(request.getName());
        card.setPrimaryType(request.getPrimaryType());
        card.setSecondaryType(request.getSecondaryType());
        card.setGrade(request.getGrade());
        card.setHp(request.getHp());
        card.setCardSet(request.getCardSet());

        Card saved = cardRepository.save(card);

        String category;
        if (saved.getPrimaryType() != null) {
            category = "Pokemon";
        } else if (saved.getName().toLowerCase().contains("energy")) {
            category = "Energy";
        } else {
            category = "Trainer";
        }

        return new CardDto(saved.getCardId(), saved.getName(), saved.getPrimaryType(),
                saved.getSecondaryType(), saved.getGrade(), saved.getHp(),
                saved.getCardSet(), category, null);
    }

    /** Admin only: delete a card by ID, cascading through all FK dependents. */
    public void deleteCard(Long cardId, HttpSession session) {
        requireAdmin(session);

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));

        // 1. Remove move associations (card_move rows)
        card.setMoves(new ArrayList<>());
        cardRepository.save(card);

        // 2. Delete user ownership rows
        userCardRepository.deleteAll(userCardRepository.findByCard_CardId(cardId));

        // 3. Delete trades that reference this card
        tradeRepository.deleteAll(
                tradeRepository.findByInitiatorCard_CardIdOrReceiverCard_CardId(cardId, cardId));

        // 4. Null out card reference in historical transactions (preserves purchase history)
        transactionRepository.clearCardReferences(cardId);

        // 5. Delete the card itself
        cardRepository.deleteById(cardId);
    }
}
