package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for trades
 * 
 * @author Peter Carter
 */
public interface TradeRepository extends JpaRepository<Trade, Long> {

    /**
     * Finds the trades initiated by the specified user
     * @param userId ID of the user that initiated the trades
     * @return List of trades initiated by the specified user
     */
	List<Trade> findByInitiatorUser_UserId(Long userId);

    /**
     * Finds trades received by a specified user
     * @param userId ID of the user that received trades
     * @return List of trades received by the specified user
     */
	List<Trade> findByReceiverUser_UserId(Long userId);

    
	/**
	 * Lists trades received by a user with a certain status
	 * @param userId ID of the user
	 * @param status Current status of the trade
	 * @return Trades received by the specified user with the specified status
	 */
	List<Trade> findByReceiverUser_UserIdAndStatus(Long userId, Trade.TradeStatus status);
	
	/**
	 * Lists trades initiated by a user with a certain status
	 * @param userId ID of the user
	 * @param status Current status of the trade
	 * @return Trades initiated by the specified user with the specified status
	 */
	List<Trade> findByInitiatorUser_UserIdAndStatus(Long userId, Trade.TradeStatus status);

    List<Trade> findByInitiatorCard_CardIdOrReceiverCard_CardId(Long initiatorCardId, Long receiverCardId);
}


