package com.aequilibrium.service.rules;

import com.aequilibrium.domain.AbstractTransformer;
import com.aequilibrium.enuns.FightStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

	private AbstractTransformer winner;
	private FightStatus status;
	
}
