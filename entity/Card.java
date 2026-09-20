package edu.ncsu.csc440.poketrader.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is a java representation of a Pokemon card in the database
 * Hibernate @Entity and @Table tags are used for the connection
 * Lombok is used for constructors and getters/setters
 * 
 * @author Peter Carter
 */
@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Card {

    /**
     * Each card is an ID. This ID is auto generated.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CardID")
    private Long cardId;

    /**
     * This is the name of the card. It is a string and cannot be null
     */
	@Column(name = "Name", nullable = false)
    private String name;

    /**
     * This is the card's primary type, stored as a string.
     */
	@Column(name = "PrimaryType")
    private String primaryType;

    /**
     * This is the card's secondary type, stored as a string.
     */
	@Column(name = "SecondaryType")
    private String secondaryType;

    /**
     * This is the card's grade
     */
	@Column(name = "Grade")
    private Integer grade;

    /**
     * This is the HP of the card.
     */
	@Column(name = "HP")
    private Integer hp;

    /**
     * This is the set the card is from. It cannot be null.
     */
	@Column(name = "`Set`", nullable = false)
    private String cardSet;
	
	/**
	 * Many cards can be moved many times. This field is a representation of 
	 * the card_move table in the database. It has the CardID and the MoveID
	 * and maintains a list of the moves a card has been through.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	    name = "card_move",
	    joinColumns = @JoinColumn(name = "CardID"),
	    inverseJoinColumns = @JoinColumn(name = "MoveID")
	)
	private List<Move> moves;
}
