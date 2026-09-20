package edu.ncsu.csc440.poketrader.mapper;

import org.mapstruct.Mapper;

import edu.ncsu.csc440.poketrader.dto.UserCardDto;
import edu.ncsu.csc440.poketrader.entity.UserCard;

/**
 * Helper to map between UserCard entities and DTOs
 * 
 * @author Peter Carter
 */
@Mapper(componentModel = "spring")
public interface UserCardMapper {
	
	/**
	 * Maps a userCard to a Dto
	 * @param userCard UserCard to map to a dto
	 * @return UserCardDto of the UserCard
	 */
	public UserCardDto toDto(UserCard userCard);
	
	/**
	 * Maps a userCardDto to entity
	 * @param dto DTO to map to entity
	 * @return UserCard of the DTO
	 */
	public UserCard toEntity(UserCardDto dto);

}
