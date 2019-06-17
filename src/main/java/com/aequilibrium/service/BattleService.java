package com.aequilibrium.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.aequilibrium.domain.AbstractTransformer;
import com.aequilibrium.domain.Autobot;
import com.aequilibrium.domain.Battle;
import com.aequilibrium.domain.Decepticon;
import com.aequilibrium.domain.Fight;
import com.aequilibrium.dto.response.BattleResponseDto;
import com.aequilibrium.enuns.FightStatus;
import com.aequilibrium.enuns.TeamType;
import com.aequilibrium.exception.ErrorCodes;
import com.aequilibrium.exception.GenericException;
import com.aequilibrium.exception.InputDataException;

@Lazy
@Service
public class BattleService {
	
	private FightService fightService;

	public BattleService(FightService fightService) {
		this.fightService = fightService; 
	}
	
	public BattleResponseDto startBattle(final List<AbstractTransformer> transformers) {  
		
		this.validateInputData(transformers); 
		
		final Map<TeamType, List<AbstractTransformer>> splitedTeams = this.splitTeams(transformers);  
		
		this.validateTeams(splitedTeams);

		Battle battle = new Battle();
		
		battle.setTeamAutobot(splitedTeams.get(TeamType.AUTOBOT));  
		battle.setTeamDecepticon(splitedTeams.get(TeamType.DECEPTICON));
		
		int numberOfBattles = this.findNumberOfBattles(battle.getTeamAutobot(), battle.getTeamDecepticon());
		  
		for (int i = 0; i < numberOfBattles; i++) {
			battle.getFights().add(this.fightService.startFight( ((Autobot) battle.getTeamAutobot().get(i)), ( (Decepticon) battle.getTeamDecepticon().get(i))));
		}
		
		this.winnerTeam(battle); 
		
		this.checkSurvivors(battle); 
		
		return battle.buildBattleResponseDto();
	}
	
	private Map<TeamType, List<AbstractTransformer>> splitTeams(final List<AbstractTransformer> allTransformers ) {  
		Map<TeamType, List<AbstractTransformer>> teamsSplitted = new HashMap<TeamType, List<AbstractTransformer>>();
		
		for (TeamType teamType : TeamType.values()) {
			teamsSplitted.put(teamType, transformersByTeam(teamType, allTransformers)); 
		}
		
		return teamsSplitted; 
	}
	
	private void validateInputData(final List<AbstractTransformer> transformers) {
		if( transformers.isEmpty() || transformers.size() < 1 ) {
			throw new InputDataException(ErrorCodes.VALIDATE_FIELD_MIN_LENGTH, new Object[] { "2" });
		}
	}
	
	private void validateTeams(Map<TeamType, List<AbstractTransformer>> splitedTeams) {
		Arrays.asList(TeamType.values()).stream().forEach(t -> { 
			if(splitedTeams.get(t).isEmpty()) {throw new GenericException(ErrorCodes.VALIDATE_TEAM_NOT_INFORMED, new Object[] {t.getDescription()} );} 
		});
	}

	private List<AbstractTransformer> transformersByTeam(final TeamType team,  final List<AbstractTransformer> transformers) {
		return transformers.stream().filter(t -> t != null && t.checkTeam(team))  
				.sorted(Comparator.comparing(AbstractTransformer::getRank).reversed())
				.collect(Collectors.toList()); 
	}
	
	public int findNumberOfBattles(final List<AbstractTransformer> teamAutobot, final List<AbstractTransformer> teamDecepticon) {
		return Math.min(teamAutobot.size(), teamDecepticon.size());
	}
	
	private void checkSurvivors(final Battle battle) {
		if( battle.getWinnerTeam() == null ) {
			return;
		}
		
		if (  !battle.getWinnerTeam().equals(TeamType.AUTOBOT) && battle.getTeamAutobot().size() > battle.getFights().size() ) {
			for (int i = battle.getFights().size(); i < battle.getTeamAutobot().size(); i++) {
				battle.getSurvivors().add(battle.getTeamAutobot().get(i));
			}
		} 
		
		if ( !battle.getWinnerTeam().equals(TeamType.DECEPTICON) && battle.getTeamDecepticon().size() > battle.getFights().size() ) {
			for (int i = battle.getFights().size(); i < battle.getTeamDecepticon().size(); i++) {
				battle.getSurvivors().add(battle.getTeamDecepticon().get(i)); 
			}
		}
	}
	
	private void winnerTeam(final Battle battle) {

		int autobotponts = 0;
		int decepticonponts = 0;
		
		for (Fight fight : battle.getFights()) {
			
			if( fight.getStatus().equals(FightStatus.WINNER) ) {
				if(fight.getWinner().equals(TeamType.AUTOBOT)) {
					autobotponts++;
				}
				
				if(fight.getWinner().equals(TeamType.DECEPTICON)) {
					decepticonponts++;
				}
			}
		}
		
		if(autobotponts > decepticonponts) {
			battle.setWinnerTeam(TeamType.AUTOBOT);
		}  else if( decepticonponts > autobotponts ) {
			battle.setWinnerTeam(TeamType.DECEPTICON);
		} else {
			battle.setWinnerTeam(null); 
		}
		
		
		
	}

}
