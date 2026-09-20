package edu.ncsu.csc440.poketrader.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for a card
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardDto {
	
        /**
         * Id of the card
         */
		private Long cardId;
		
		/**
		 * Name of the card
		 */
        private String name;
        
        /**
         * Primary type of the card
         */       
        private String primaryType;
        
        /**
         * Secondary type of the card
         */
        private String secondaryType;
        
        /**
         * Grade of the card
         */
        private Integer grade;
        
        /**
         * HP of the card
         */
        private Integer hp;
        
        /**
         * Set the card is from
         */
        private String cardSet;
        
        /**
         * Card category
         */
        private String category;
        
        /**
         * List of moves made by the card
         */
        private List<MoveDto> moves;
}
