package edu.ncsu.csc440.poketrader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents a trade of cards between two users
 * 
 * Hibernate is used for database connections
 * Lombok is used for constructors and getters/setters
 * 
 * @author Peter Carter
 */
@Entity
@Table(name = "trade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trade {

    /**
     * This is the ID of the trade. It is auto generated
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TradeID")
    private Long tradeId;

    /**
     * This is the time the trade takes place
     */
	@Column(name = "TradeTime")
    private LocalDateTime tradeTime;

    /**
     * This is the user ID of the initiator of the trade, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "InitiatorUserID", nullable = false)
    private User initiatorUser;

    /**
     * This is the user ID of the recipient of the trade, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "ReceiverUserID", nullable = false)
    private User receiverUser;

    /**
     * This is the ID of the card offered by the initiator, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "InitiatorCardID", nullable = false)
    private Card initiatorCard;

    /**
     * This is the ID of the card offered by the reciever, it cannot be null
     */
	@ManyToOne
    @JoinColumn(name = "ReceiverCardID", nullable = false)
    private Card receiverCard;

    /**
     * Each trade has a status
     */
	@Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private TradeStatus status;

    /**
     * This enum represents the available statuses of a trade
     */
	public enum TradeStatus { Request, Accepted, Denied, Blocked }
}
