package com.aequilibrium.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Battle Response")
public class BattleResponseDto {
	
	private String numberOfBattle;
	
	private String winningTeam;
	
	private String survivorsFromLosingTeam;
	
	

}
