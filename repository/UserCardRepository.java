package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for relationship between user and cards
 * 
 * @author Peter Carter
 */
public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    /**
     * Finds cards owned by the specified user
     * @param userId the User
     * @return List of cards owned by the user
     */
	List<UserCard> findByUser_UserId(Long userId);

    /**
     * Finds cards in a specific collection
     * @param collectionId ID of the collection
     * @return List of cards in the collection
     */
	List<UserCard> findByCollection_CollectionId(Long collectionId);

    /**
     * Finds a specific card owned by a user
     * @param userId ID of the user
     * @param cardId ID of the card
     * @return The relationship of the specified card and user
     */
	Optional<UserCard> findByUser_UserIdAndCard_CardId(Long userId, Long cardId);

    /**
     * Determines if a user owns a card
     * @param userId User ID
     * @param cardId Card ID
     * @return true if a relationship exists between the specified user and card, else false
     */
	boolean existsByUser_UserIdAndCard_CardId(Long userId, Long cardId);

    boolean existsByUser_UserIdAndCard_CardIdAndCollection_CollectionId(Long userId, Long cardId, Long collectionId);

    List<UserCard> findByCard_CardId(Long cardId);
}
