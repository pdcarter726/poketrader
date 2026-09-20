package edu.ncsu.csc440.poketrader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class represents a purchase transaction where a user buys/sells a card
 * It is named CardTrasaction to avoid conflict with java.sql.transaction
 * 
 * Hibernate is used for database connections
 * 
 * Lombok is used for constructors and getters/setters
 * 
 * @author Peter Carter
 * 
 */
@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CardTransaction {

    /**
     * This is the transaction ID. It is auto generated.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private Long transactionId;

    /**
     * This is the time the transaction occurs.
     */
	@Column(name = "TransactionTime")
    private LocalDateTime transactionTime;

    /**
     * This is the user ID of the seller, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "SellerUserID", nullable = false)
    private User sellerUser;

    /**
     * This is the user ID of the buyer, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "BuyerUserID", nullable = false)
    private User buyerUser;

    /**
     * This is the ID of the card being purchased, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "CardID", nullable = true)
    private Card card;

    /**
     * This is the price of the card being purchased
     */
	@Column(name = "Price")
    private BigDecimal price;
}
