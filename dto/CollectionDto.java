package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representation of a collection
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CollectionDto {
	
	/**
	 * ID of the collection
	 */
	private Long collectionId;
	
	/**
	 * ID of the collection owner
	 */
	private Long userId;
	
	/**
	 * Name of the collection
	 */
	private String name;
	
	/**
	 * Description of the collection
	 */
	private String description;


}
