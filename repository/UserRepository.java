package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for users
 * 
 * @author Peter Carter
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user from their username
     * @param username username of user to find
     * @return The User with the specified username
     */
	Optional<User> findByUsername(String username);

    /**
     * Determines if a user with the specified username exists
     * @param username username to search for
     * @return true if a user has the specified username, else false
     */
	boolean existsByUsername(String username);
}
