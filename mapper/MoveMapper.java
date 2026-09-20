package edu.ncsu.csc440.poketrader.mapper;

import org.mapstruct.Mapper;

import edu.ncsu.csc440.poketrader.dto.MoveDto;
import edu.ncsu.csc440.poketrader.entity.Move;

/**
 * Helper to map between Move entities and DTOs
 * 
 * @author Peter Carter
 */
@Mapper(componentModel = "spring")
public interface MoveMapper {
	
	/**
	 * Maps a Move to a MoveDto
	 * @param move Move to map
	 * @return MoveDto representation of the move
	 */
	public MoveDto toDto(Move move);
	
	/**
	 * Maps a MoveDto to a move
	 * @param dto MoveDto to map
	 * @return Move from MoveDto
	 */
	public Move toEntity(MoveDto dto);

}
