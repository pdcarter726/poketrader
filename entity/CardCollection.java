package edu.ncsu.csc440.poketrader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class represents a collection in the database. It is named CardCollection to avoid
 * conflict with the java.util.collection class.
 * 
 * Hibernate uses @Entity and @Table for database connection
 * 
 * Lombok is used for constructors and getters/setters
 * 
 * @author Peter Carter
 */
@Entity
@Table(name = "collection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CardCollection {

    /**
     * This is the ID of the collection, and it is auto generated.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CollectionID")
    private Long collectionId;

    /**
     * This joins the Collection with a User in a many collections to one user relationship
     * This cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    /**
     * This is the collection's name, it cannot be null
     */
	@Column(name = "Name", nullable = false)
    private String name;

    /**
     * This is a description of the collection
     */
	@Column(name = "Description")
    private String description;
}
