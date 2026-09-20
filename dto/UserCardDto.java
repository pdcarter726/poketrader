package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for representation of relationship between a user and card
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCardDto {
	
	/**
	 * ID of the relationship
	 */
	private Long userCardId;
	
	/**
	 * ID of the user
	 */
	private Long userId;
	
	/**
	 * ID of the card
	 */
	private Long cardId;
	
	/**
	 * ID of the collection
	 */
	private Long collectionId;
	
	/**
	 * Quantity of the card
	 */
	private Integer quantity;
	
	/**
	 * DTO of the card
	 */
	private CardDto card;
}
