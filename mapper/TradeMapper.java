package edu.ncsu.csc440.poketrader.mapper;

import org.mapstruct.Mapper;

import edu.ncsu.csc440.poketrader.dto.TradeDto;
import edu.ncsu.csc440.poketrader.entity.Trade;

/**
 * Helper to map between Trade entities and DTOs
 * 
 * @author Peter Carter
 */
@Mapper(componentModel = "spring")
public interface TradeMapper {
	
	/**
	 * Maps a Trade to a DTO
	 * @param trade Trade to map
	 * @return DTO representation of the trade
	 */
	public TradeDto toDto(Trade trade);
	
	/**
	 * Maps a Trade DTO to entity
	 * @param dto DTO to map
	 * @return Trade represented by the DTO
	 */
	public Trade toEntity(TradeDto dto);

}
