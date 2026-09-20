package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO with information to log in a user
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDto {
	
	/**
	 * Username to login with
	 */
	private String username;
	
	/**
	 * Password
	 */
	private String password;
	
}
