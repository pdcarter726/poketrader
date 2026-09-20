package edu.ncsu.csc440.poketrader.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.repository.UserRepository;

import java.util.Set;

/**
 * Supports finding and logging in a user by username or email.
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	// TODO: Update to match Poketrader setup

	/** Link to userRepository */
    private UserRepository userRepository;

    /**
     * Returns UserDetails for the user associated with the username or email address.
     * @param username username to search for
     * @return UserDetails object representing the user.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist with the given username."));

        Set<GrantedAuthority> authorities =  Set.of(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPasswordHash(),
                authorities
        );
    }
}
