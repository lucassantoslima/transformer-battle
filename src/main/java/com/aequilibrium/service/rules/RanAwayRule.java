package com.aequilibrium.service.rules;

import com.aequilibrium.domain.Fight;
import com.aequilibrium.enuns.FightStatus;
import com.aequilibrium.enuns.TeamType;

public class RanAwayRule implements IRule {
	
	private Fight result;

	@Override
	public boolean evaluate(Fight fight, Rules rule) {
		boolean evalResult = false;
		
		if (rule == Rules.RUNAWAY) {
			this.result = fight;
			
			int resultCorauge = Math.abs(fight.getAutobot().getCourage() - fight.getDecepticon().getCourage());
			int resultStrenght = Math.abs(fight.getAutobot().getStrength() - fight.getDecepticon().getStrength());
			
			if( resultCorauge >= 4 && resultStrenght >= 3 ) {
				this.result.setWinner(fight.getAutobot().getCourage() > fight.getDecepticon().getCourage() ? TeamType.AUTOBOT : TeamType.DECEPTICON);
				this.result.setStatus(FightStatus.WINNER); 
			}
			evalResult = true;
		}
		
		return evalResult;
	}

	@Override
	public Fight getResult() {
		return result;
	}

}
