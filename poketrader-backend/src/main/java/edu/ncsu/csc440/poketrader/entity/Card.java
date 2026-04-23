package edu.ncsu.csc440.poketrader.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a Card in the Poketrader system
 * Constructors, Getters, and Setters are managed using Lombok
 * 
 * @author Peter Carter
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
	
	private long id;
	
	private long name;
	

}
