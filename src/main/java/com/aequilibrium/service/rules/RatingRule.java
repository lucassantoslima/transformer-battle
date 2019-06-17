package com.aequilibrium.service.rules;

import com.aequilibrium.domain.Fight;
import com.aequilibrium.enuns.FightStatus;

public class RatingRule implements IRule {

	private Fight result;

	@Override
	public boolean evaluate(Fight fight, Rules rule) {
		boolean evalResult = false;
		
		if (rule == Rules.RATING) {
			this.result = fight;
			
			if( fight.getAutobot().getRating() > fight.getDecepticon().getRating() ){
				this.result.setWinner( fight.getAutobot().getTeam() ); 
				this.result.setStatus(FightStatus.WINNER); 
			} else if( fight.getDecepticon().getRating() > fight.getAutobot().getRating()){
				this.result.setWinner( fight.getDecepticon().getTeam() ); 
				this.result.setStatus(FightStatus.WINNER);  
			} else if (fight.getAutobot().getRating() == fight.getDecepticon().getRating()) {
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
