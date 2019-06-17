package com.aequilibrium.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aequilibrium.dto.response.BattleResponseDto;
import com.aequilibrium.enuns.TeamType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Battle {
	
	private List<Fight> fights;
	
	private List<AbstractTransformer> survivors;
	
	private List<AbstractTransformer> teamAutobot;
	
	private List<AbstractTransformer> teamDecepticon;
	
	private TeamType winnerTeam;
	
	public BattleResponseDto buildBattleResponseDto() {
		return BattleResponseDto.builder()
				.numberOfBattle(getFights().size() + " battle") 
				.winningTeam(getWinningTeamDescription())
				.survivorsFromLosingTeam(getSurvivorsDescription()) 
				.build();   
	}

	private String getSurvivorsDescription() {
		
		if( getSurvivors().isEmpty() ) {
			return "There is no survivor";
		}
		
		StringBuilder sb = new StringBuilder("Survivors from the losing team ");
		
		sb.append("(");
		sb.append(getSurvivors().get(0).getTeam().getDescription());
		sb.append("): ");
		
		List<String> survivors = getSurvivors().stream().map(s -> s.getName()).collect(Collectors.toList());
		
		survivors.forEach(sb::append);
		
		return sb.toString();
		
	}

	private String getWinningTeamDescription() {
		
		if( getWinnerTeam() == null ) {
			return "There is no winner";
		}
		
		StringBuilder sb = new StringBuilder("Winning team ");
		
		sb.append("(");
		sb.append(getWinnerTeam().getDescription());
		sb.append("): ");
		
		List<String> winners = getFights().stream().map(t -> getWinnerTeam().equals(TeamType.AUTOBOT) ? t.getAutobot().getName() : t.getDecepticon().getName()).collect(Collectors.toList());
		
		winners.forEach(sb::append);
		
		return sb.toString();
	}
	
	public List<Fight> getFights() {
		if( this.fights == null ) {
			this.fights = new ArrayList<>();
		}
		return this.fights;
	}
	
	public List<AbstractTransformer> getSurvivors() {
		if( this.survivors == null ) {
			this.survivors = new ArrayList<>();
		}
		return this.survivors;
	}
	
	
}
