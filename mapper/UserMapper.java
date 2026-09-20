package edu.ncsu.csc440.poketrader.mapper;

import org.mapstruct.Mapper;

import edu.ncsu.csc440.poketrader.dto.UserDto;
import edu.ncsu.csc440.poketrader.entity.User;

/**
 * Helper to map between Card collections and DTOs
 * 
 * @author Peter Carter
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
	
	/**
	 * Converts a collection to a collection DTO
	 * @param collection collection to convert
	 * @return CollectionDto representation of the collection
	 */
	public UserDto toDto(User user);
	
	/**
	 * Converts a collection Dto to a collection entity
	 * @param dto DTO to get information from
	 * @return Card collection entity
	 */
	public User toEntity(UserDto dto);

}
