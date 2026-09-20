package edu.ncsu.csc440.poketrader.repository;

import edu.ncsu.csc440.poketrader.entity.CardCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for card collections uses JpaRepository
 * 
 * @author Peter Carter
 */
public interface CardCollectionRepository extends JpaRepository<CardCollection, Long> {

    List<CardCollection> findByUser_UserId(Long userId);
}
