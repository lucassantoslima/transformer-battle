package com.aequilibrium.service.rules;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.aequilibrium.domain.Fight;

@ApplicationScope
@Component
public class RuleEngine {
	
	private List<IRule> rules;

	@PostConstruct
	public void init() { 
		rules = new ArrayList<>();
		rules.add(new RanAwayRule());  
		rules.add(new SkillRule());
		rules.add(new NameRule()); 
		rules.add(new RatingRule()); 
	}
	
	public Fight process(Fight fight, Rules rulesOp) {
		
		if( fight.getStatus() != null ) { 
			return fight;
		}
		
        IRule rule = rules
          .stream() 
          .filter(r -> r.evaluate(fight,rulesOp)) 
          .findFirst() 
          .orElseThrow(() -> new IllegalArgumentException("Expression does not matches any Rule"));
        return rule.getResult();
    }
}
