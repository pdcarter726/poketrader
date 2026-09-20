package edu.ncsu.csc440.poketrader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the relationship between users and cards. A user can have multiple of the same card
 * so a quantity field is necessary, so this requires its own entity class.
 * 
 * Uses Hibernate for database connections
 * Uses Lombok for constructors and getters/setters
 * 
 * @author Peter Carter
 */
@Entity
@Table(name = "user_card", uniqueConstraints = @UniqueConstraint(columnNames = {"UserID", "CardID", "CollectionID"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCard {

    /**
     * Id of the relationship, auto generated
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserCardID")
    private Long userCardId;

    /**
     * Id of the card owner, cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    /**
     * Id of the owned card, cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "CardID", nullable = false)
    private Card card;

    /**
     * Id of the collection the card belongs to
     */
	@ManyToOne
    @JoinColumn(name = "CollectionID", nullable = false)
    private CardCollection collection;

    /**
     * Quantity of the card owned by the user
     */
	@Column(name = "Quantity")
    private Integer quantity;
}
