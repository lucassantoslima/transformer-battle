package com.aequilibrium.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.aequilibrium.domain.Autobot;
import com.aequilibrium.domain.Decepticon;
import com.aequilibrium.domain.Fight;
import com.aequilibrium.service.rules.RuleEngine;
import com.aequilibrium.service.rules.Rules;

@Lazy
@Service
public class FightService {
	
	private RuleEngine engine;
	
	public FightService(final RuleEngine engine) {
		this.engine = engine;
	}
	
	public Fight startFight(final Autobot autobot, final Decepticon decepticon) {
		
		Fight fight = new Fight();
		
		fight.setAutobot(autobot);
		fight.setDecepticon(decepticon);
		
		this.engine.process(fight, Rules.NAME);
		this.engine.process(fight, Rules.RUNAWAY); 
		this.engine.process(fight, Rules.SKILL);
		this.engine.process(fight, Rules.RATING);
		
		return fight;
	}

}
