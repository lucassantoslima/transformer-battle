package com.aequilibrium.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aequilibrium.enuns.TeamType;

import lombok.Builder;

@Entity
@DiscriminatorValue(value = TeamType.Values.AUTOBOT)  
public class Autobot extends AbstractTransformer  {
	
	private static final long serialVersionUID = 1L;
	
	public Autobot() {
	}
	
	@Builder
	public Autobot(Integer id, String name, Integer strength, Integer intelligence, Integer speed, Integer endurance,
			Integer rank, Integer courage, Integer firepower, Integer skill, TeamType team) {
		super(id, name, strength, intelligence, speed, endurance, rank, courage, firepower, skill, team);
	}


}
