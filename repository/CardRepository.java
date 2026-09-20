package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for cards
 * 
 * @author Peter Carter
 */
public interface CardRepository extends JpaRepository<Card, Long> {

    /**
     * Returns a list of cards with the specified name, not case sensitive
     * @param name name of card to find
     * @return List of matching cards
     */
	List<Card> findByNameContainingIgnoreCase(String name);

    /**
     * Gets the cards with the primary type specified
     * @param primaryType the type of card to get
     * @return A list of cards with the primary type
     */
	List<Card> findByPrimaryType(String primaryType);

    /**
     * Gets cards with the specified name and primary type
     * @param name Name of the card
     * @param primaryType Type of the card
     * @return Cards matching the name and primary type
     */
	List<Card> findByNameContainingIgnoreCaseAndPrimaryType(String name, String primaryType);
}
