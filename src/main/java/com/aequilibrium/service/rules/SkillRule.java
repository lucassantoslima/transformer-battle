package com.aequilibrium.service.rules;

import com.aequilibrium.domain.Fight;
import com.aequilibrium.enuns.FightStatus;
import com.aequilibrium.enuns.TeamType;

public class SkillRule implements IRule {

	private Fight result;

	@Override
	public boolean evaluate(Fight fight, Rules rule) {
		boolean evalResult = false; 
		if (rule == Rules.SKILL) {
			this.result = fight;
			
			int resultSkill = Math.abs(fight.getAutobot().getSkill() - fight.getDecepticon().getSkill());
			
			if( resultSkill >= 3 ) {
				this.result.setWinner( fight.getAutobot().getSkill() > fight.getDecepticon().getSkill() ? TeamType.AUTOBOT : TeamType.DECEPTICON );
				this.result.setStatus(FightStatus.WINNER); 
			} else if(resultSkill == 0) {
				this.result.setWinner( null );
				this.result.setStatus(FightStatus.TIE); 
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
