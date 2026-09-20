package edu.ncsu.csc440.poketrader.service;

import edu.ncsu.csc440.poketrader.dto.CardDto;
import edu.ncsu.csc440.poketrader.dto.TradeDto;
import edu.ncsu.csc440.poketrader.entity.Card;
import edu.ncsu.csc440.poketrader.entity.Trade;
import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.repository.CardRepository;
import edu.ncsu.csc440.poketrader.repository.TradeRepository;
import edu.ncsu.csc440.poketrader.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Carter
 */
@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public TradeService(TradeRepository tradeRepository,
                        UserRepository userRepository,
                        CardRepository cardRepository) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    /** Return all trades where the user is initiator or receiver. */
    public List<TradeDto> getUserTrades(Long userId) {
        List<Trade> trades = new ArrayList<>();
        trades.addAll(tradeRepository.findByInitiatorUser_UserId(userId));
        trades.addAll(tradeRepository.findByReceiverUser_UserId(userId));

        List<TradeDto> dtos = new ArrayList<>();
        for (Trade trade : trades) {
            dtos.add(toDto(trade));
        }
        return dtos;
    }

    /** Create a new trade request with status=Request and tradeTime=now. */
    public TradeDto createTrade(Long initiatorId, Long receiverId,
                                Long initiatorCardId, Long receiverCardId) {

        if (initiatorId == null || receiverId == null || initiatorCardId == null || receiverCardId == null) {
            throw new IllegalArgumentException("Missing required trade fields");
        }
        User initiator = userRepository.findById(initiatorId)
                .orElseThrow(() -> new IllegalArgumentException("Initiator or receiver does not exist"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Initiator or receiver does not exist"));
        Card initiatorCard = cardRepository.findById(initiatorCardId)
                .orElseThrow(() -> new IllegalArgumentException("Initiator or receiver card does not exist"));
        Card receiverCard = cardRepository.findById(receiverCardId)
                .orElseThrow(() -> new IllegalArgumentException("Initiator or receiver card does not exist"));

        if (initiator.getUserId().equals(receiver.getUserId())) {
            throw new IllegalArgumentException("Initiator and receiver must be different users");
        }
        if (initiatorCard.getCardId().equals(receiverCard.getCardId())) {
            throw new IllegalArgumentException("Initiator and receiver cards must be different");
        }

        Trade trade = new Trade();
        trade.setTradeTime(LocalDateTime.now());
        trade.setInitiatorUser(initiator);
        trade.setReceiverUser(receiver);
        trade.setInitiatorCard(initiatorCard);
        trade.setReceiverCard(receiverCard);
        trade.setStatus(Trade.TradeStatus.Request);

        return toDto(tradeRepository.save(trade));
    }

    /** Accept or deny a pending trade (receiver only). */
    public TradeDto respondTrade(Long tradeId, Long userId, Trade.TradeStatus newStatus) {

        User user = userRepository.findById(userId).orElse(null);
        if (tradeId == null || user == null || newStatus == null) {
            throw new IllegalArgumentException("Missing required response fields");
        }

        Trade trade = tradeRepository.findById(tradeId).orElse(null);
        if (trade == null) {
            throw new IllegalArgumentException("Trade does not exist");
        }
        if (trade.getStatus() != Trade.TradeStatus.Request) {
            throw new IllegalArgumentException("Trade has been responded to already");
        }
        if (!user.getUserId().equals(trade.getReceiverUser().getUserId())) {
            throw new IllegalArgumentException("Only receiver can respond to this trade");
        }

        trade.setStatus(newStatus);
        return toDto(tradeRepository.save(trade));
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private TradeDto toDto(Trade trade) {
        return new TradeDto(
                trade.getTradeId(),
                trade.getTradeTime(),
                trade.getInitiatorUser().getUserId(),
                trade.getInitiatorUser().getUsername(),
                trade.getReceiverUser().getUserId(),
                trade.getReceiverUser().getUsername(),
                trade.getInitiatorCard().getCardId(),
                trade.getReceiverCard().getCardId(),
                trade.getStatus() == null ? null : trade.getStatus().name(),
                toCardDto(trade.getInitiatorCard()),
                toCardDto(trade.getReceiverCard())
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
