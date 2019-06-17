package com.aequilibrium.domain;

import com.aequilibrium.enuns.FightStatus;
import com.aequilibrium.enuns.TeamType;

import lombok.Data;

@Data
public class Fight {

	private Autobot autobot;

	private Decepticon decepticon;

	private TeamType winner;
	
	private FightStatus status;
	
	public boolean isTie() {
		return status != null && status.equals(FightStatus.TIE); 
	}
	
	public boolean hasWinner() {
		return status != null && status.equals(FightStatus.WINNER); 
	}

}
