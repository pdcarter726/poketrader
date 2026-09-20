package edu.ncsu.csc440.poketrader.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO with information regarding a buy/sell transaction
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {
        
	/**
	 * ID of the transaction
	 */
	private Long transactionId;
	
	/**
	 * Time of the transaction
	 */
	private LocalDateTime transactionTime;
	
	/**
	 * ID of the seller
	 */
	private Long sellerUserId;
	
	/**
	 * ID of the user
	 */
	private Long buyerUserId;
	
	/**
	 * ID of the card
	 */
	private Long cardId;
	
	/**
	 * Price of the card
	 */
	private BigDecimal price;
	
	/**
	 * DTO of the card
	 */
	private CardDto card;
}
