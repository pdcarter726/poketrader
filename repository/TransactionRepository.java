package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository interface for transactions
 * 
 * @author Peter Carter
 */
public interface TransactionRepository extends JpaRepository<CardTransaction, Long> {

    /**
     * Finds transactions where the specified user was the seller
     * @param userId ID of the user
     * @return List of transactions where the seller was the specified user
     */
	List<CardTransaction> findBySellerUser_UserId(Long userId);

	/**
     * Finds transactions where the specified user was the buyer
     * @param userId ID of the user
     * @return List of transactions where the buyer was the specified user
     */
	List<CardTransaction> findByBuyerUser_UserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE CardTransaction t SET t.card = null WHERE t.card.cardId = :cardId")
    void clearCardReferences(@Param("cardId") Long cardId);
}
