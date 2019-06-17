package com.aequilibrium.builder;

import com.aequilibrium.dto.request.TransformerRequestDto;
import com.aequilibrium.enuns.TeamType;

public class BuildTransformer {
	
	public static TransformerRequestDto createPredakingRequestDto() {
		return TransformerRequestDto.builder()
								 .strength(10)
								 .intelligence(10)
								 .speed(10)
								 .endurance(10)
								 .rank(10)
								 .courage(10)
								 .firepower(10)
								 .skill(10)
								 .name("Predaking")
								 .team(TeamType.DECEPTICON)
								 .build();
	}
	
	public static TransformerRequestDto createSoundwaveRequestDto() {
		return TransformerRequestDto.builder()
								 .strength(8)
								 .intelligence(9)
								 .speed(2)
								 .endurance(6)
								 .rank(7)
								 .courage(5)
								 .firepower(6)
								 .skill(10)
								 .name("Soundwave")
								 .team(TeamType.DECEPTICON)
								 .build();
	}
	
	public static TransformerRequestDto createJazzRequestDto() {
		return TransformerRequestDto.builder()
								 .strength(4)
								 .intelligence(4)
								 .speed(4)
								 .endurance(4)
								 .rank(4)
								 .courage(4)
								 .firepower(4)
								 .skill(4)
								 .name("Jazz")
								 .team(TeamType.DECEPTICON)
								 .build();
	}
	
	public static TransformerRequestDto createBluestreakRequestDto() {
		return TransformerRequestDto.builder()
								 .strength(6)
								 .intelligence(6)
								 .speed(7)
								 .endurance(9)
								 .rank(5)
								 .courage(2)
								 .firepower(9)
								 .skill(7)
								 .name("Bluestreak")
								 .team(TeamType.AUTOBOT)
								 .build();
	}
	
	public static TransformerRequestDto createHubcapRequestDto() {
		return TransformerRequestDto.builder()
								 .strength(4)
								 .intelligence(4)
								 .speed(4)
								 .endurance(4)
								 .rank(4)
								 .courage(4)
								 .firepower(4) 
								 .skill(4)
								 .name("Hubcap") 
								 .team(TeamType.AUTOBOT)
								 .build();
	}
	
	public static TransformerRequestDto createOptimusPrimeRequestDto() {
		return TransformerRequestDto.builder()
								 .strength(10)
								 .intelligence(10)
								 .speed(10) 
								 .endurance(10)
								 .rank(10)
								 .courage(10)
								 .firepower(10) 
								 .skill(10)
								 .name("Optimus Prime")
								 .team(TeamType.AUTOBOT)
								 .build();
	}

}
