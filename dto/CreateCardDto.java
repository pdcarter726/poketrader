package edu.ncsu.csc440.poketrader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCardDto {
    private String name;
    private String primaryType;
    private String secondaryType;
    private Integer grade;
    private Integer hp;
    private String cardSet;
}
