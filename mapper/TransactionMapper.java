package edu.ncsu.csc440.poketrader.mapper;

import org.mapstruct.Mapper;

import edu.ncsu.csc440.poketrader.dto.TransactionDto;
import edu.ncsu.csc440.poketrader.entity.CardTransaction;

/**
 * Helper to map between Card transactions and their DTOs
 * 
 * @author Peter Carter
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {
	
	/**
	 * Maps a transaction to dto
	 * @param transaction transaction to map
	 * @return DTO of the transaction
	 */
	public TransactionDto toDto(CardTransaction transaction);
	
	/**
	 * Maps a transaction dto to entity
	 * @param dto DTO to map
	 * @return Transaction entity of the DTO
	 */
	public CardTransaction toEntity(TransactionDto dto);

}
