package com.aequilibrium.service.rules;

import com.aequilibrium.domain.AbstractTransformer;
import com.aequilibrium.domain.Fight;
import com.aequilibrium.enuns.FightStatus;
import com.aequilibrium.exception.ErrorCodes;
import com.aequilibrium.exception.GameDestroyedException;

public class NameRule implements IRule {

	private static final String PREDAKING = "Predaking";
	private static final String OPTIMUS_PRIME = "Optimus Prime";  
	
	private Fight result;

	@Override
	public boolean evaluate(Fight fight, Rules rule) {
		boolean evalResult = false;
		if (rule == Rules.NAME) {
			this.result = fight;
			
			if( (isOptimusPrime(fight.getAutobot()) || isPredaking(fight.getAutobot())) && (isOptimusPrime(fight.getDecepticon()) || isPredaking(fight.getDecepticon())) ) {
				throw new GameDestroyedException(ErrorCodes.VALIDATE_GAME_END_COMPETITORS_DESTROYED, new Object[] { fight.getAutobot().getName(), fight.getDecepticon().getName() });
		    } else if( isOptimusPrime(fight.getAutobot()) || isPredaking(fight.getAutobot())  ) {  
		    	this.result.setWinner(fight.getAutobot().getTeam());
				this.result.setStatus(FightStatus.WINNER); 
			} else if( isOptimusPrime(fight.getDecepticon()) || isPredaking(fight.getDecepticon())  ) { 
				this.result.setWinner(fight.getDecepticon().getTeam());
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
	
	private boolean isOptimusPrime(AbstractTransformer transformer) {
		return transformer.getName().trim().equalsIgnoreCase(OPTIMUS_PRIME);
	}
	
	private boolean isPredaking(AbstractTransformer transformer) {
		return transformer.getName().trim().equalsIgnoreCase(PREDAKING);
	}

}
