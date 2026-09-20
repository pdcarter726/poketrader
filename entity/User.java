package edu.ncsu.csc440.poketrader.entity;

import edu.ncsu.csc440.poketrader.config.Roles.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a user in the system.
 * 
 * Hibernate is used for database connection.
 * Lombok is used for constructors and getters/setters
 * 
 * @author Peter Carter
 */
@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    /**
     * The ID of the user, it is auto generated
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userId;

    /**
     * The user's role in the system (User or Admin)
     */
	@Enumerated(EnumType.STRING)
    @Column(name = "Role", nullable = false)
    private UserRoles role;

    /**
     * User's first name
     */
	@Column(name = "FirstName", nullable = false)
    private String firstName;

    /**
     * User's last name
     */
	@Column(name = "LastName", nullable = false)
    private String lastName;

    /**
     * User's username
     */
	@Column(name = "Username", nullable = false, unique = true)
    private String username;

    /**
     * A user's password, stored as a hash
     */
	@Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    /**
     * The salt for the user's password
     */
	@Column(name = "PasswordSalt")
    private String passwordSalt;

}
