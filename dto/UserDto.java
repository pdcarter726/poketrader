package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO with information for a user
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	/**
	 * User's ID
	 */
	private Long userId;
	
	/**
	 * User's username
	 */
	private String username;
	
	/**
	 * User's first name
	 */
	private String firstName;
	
	/**
	 * User's last name
	 */
	private String lastName;
	
	/**
	 * User's role
	 */
	private String role;
}
