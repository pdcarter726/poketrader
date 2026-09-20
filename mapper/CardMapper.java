package edu.ncsu.csc440.poketrader.mapper;

import org.mapstruct.Mapper;

import edu.ncsu.csc440.poketrader.dto.CardDto;
import edu.ncsu.csc440.poketrader.entity.Card;

/**
 * Helper to map between Cards and CardDto objects
 * 
 * @author Peter Carter
 */
@Mapper(componentModel = "spring")
public interface CardMapper {

    /**
     * Converts a Card entity to DTO
     * @param card card to convert to dto
     * @return CardDto of the card entity
     */
	CardDto toDTO(Card card);
	
	/**
	 * Converts a Card DTo to entity
	 * @param dto DTO to convert to a card
	 * @return Card representation of the DTO
	 */
    Card toEntity(CardDto dto);
}
