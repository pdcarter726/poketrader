package edu.ncsu.csc440.poketrader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a move available on a card.
 * 
 * Hibernate is used for database connection
 * 
 * Lombok is used for constructors, getters and setters
 * 
 * @author Peter Carter
 */
@Entity
@Table(name = "move")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Move {

    /**
     * Each move has a unique ID that is auto generated
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MoveID")
    private Long moveId;

    /**
     * The name of the move
     */
	@Column(name = "Name")
    private String name;

    /**
     * Amount of damage done by the move
     */
	@Column(name = "Damage")
    private Integer damage;

    /**
     * Description of the move
     */
	@Column(name = "Description")
    private String description;

    /**
     * Element type of the move
     */
	@Column(name = "Element")
    private String element;

    /**
     * Amount of energy it takes to use the move
     */
	@Column(name = "EnergyAmnt")
    private Integer energyAmnt;
}
