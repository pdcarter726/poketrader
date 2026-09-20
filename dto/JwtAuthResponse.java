package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response for authenticated and authorized user.
 * Lombok used for constructors/getters/setters
 * @author Peter Carter
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

	/** 
	 * Access token
	 */
    private String accessToken;
    
    /** 
     * Access token type 
     */
    private String tokenType = "Bearer";
    
    /**
     * Role for user with access token 
     */
    private String role;
}