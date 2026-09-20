package edu.ncsu.csc440.poketrader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc440.poketrader.entity.Move;

/**
 * Repository interface for card moves
 * 
 * @author Peter Carter
 */
public interface CardMoveRepository extends JpaRepository<Move, Long> {

}
