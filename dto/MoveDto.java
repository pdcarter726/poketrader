package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO with information for a move
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MoveDto {
	
	/**
	 * ID of the move
	 */
	private Long moveId;
	
	/**
	 * Move name
	 */
	private String name;
	
	/**
	 * Damage done by move
	 */
	private Integer damage;
	
	/**
	 * Move description
	 */
	private String description;
	
	/**
	 * Move element
	 */
	private String element;
	
	/**
	 * Move energy
	 */
	private Integer energyAmnt;
}
