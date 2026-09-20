package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO with information to register a new user
 * Lombok used for constructors/getters/setters
 * 
 * @author Peter Carter
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	
	/** 
	 * User's first name 
	 */
    private String firstName;
    
    /**
     * User's last name
     */
    private String lastName;
    
    /** 
     * User's username
     */
    private String username;
    
    /**
     *  User's password 
     */
    private String password;
}
