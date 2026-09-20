package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for card moves
 * 
 * @author Peter Carter
 */
public interface MoveRepository extends JpaRepository<Move, Long> {
	
}
