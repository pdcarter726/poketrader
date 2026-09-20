package edu.ncsu.csc440.poketrader.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO with information regarding a trade
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TradeDto {
        
	/**
	 * ID of the trade
	 */
	private Long tradeId;
	
	/**
	 * Time of the trade
	 */
	private LocalDateTime tradeTime;
	
	/**
	 * ID of initiator
	 */
    private Long initiatorUserId;
    
    /**
     * Username of initiator
     */
    private String initiatorUsername;
    
    /**
     * ID of receiver
     */
    private Long receiverUserId;
    
    /**
     * Username of receiver
     */
    private String receiverUsername;
    
    /**
     * Card from initiator
     */
    private Long initiatorCardId;
    
    /**
     * Card from receiver
     */
    private Long receiverCardId;
    
    /**
     * Status of trade
     */
    private String status;
    
    /**
     * Card from initiator
     */
    private CardDto initiatorCard;
    
    /**
     * Card from receiver
     */
    private CardDto receiverCard;
}
