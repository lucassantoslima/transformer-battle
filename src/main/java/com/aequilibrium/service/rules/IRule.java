package com.aequilibrium.service.rules;

import com.aequilibrium.domain.Fight;

public interface IRule {

	boolean evaluate(Fight fight, Rules rule);

	Fight getResult();
	
}
