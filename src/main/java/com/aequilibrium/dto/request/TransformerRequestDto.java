package com.aequilibrium.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.aequilibrium.domain.Autobot;
import com.aequilibrium.domain.Decepticon;
import com.aequilibrium.enuns.TeamType;
import com.aequilibrium.exception.ErrorCodes;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@ApiModel(value = "Transformer Request")
public class TransformerRequestDto {

	@ApiModelProperty(example = "Bumblebee", value = "Transformer name")
	@NotBlank(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED) 
	private String name;
	
	@ApiModelProperty(example = "5", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	@NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
	private Integer strength;
	
	@ApiModelProperty(example = "2", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	@NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
	private Integer intelligence;
	
	@ApiModelProperty(example = "8", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	@NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
	private Integer speed;
	
	@ApiModelProperty(example = "7", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	@NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
	private Integer endurance;
	
	@ApiModelProperty(example = "7", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	@NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
	private Integer rank;
	
	@ApiModelProperty(example = "6", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	@NotNull(message = ErrorCodes.REQUIRED_FIELD_NOT_INFORMED)
	private Integer firepower;
	
	@ApiModelProperty(example = "4", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	private Integer skill;
	
	@ApiModelProperty(example = "10", value = "ranked from 1 to 10")
	@Range(min = 1, max = 10, message = ErrorCodes.VALIDATE_FIELD_INVALID_RANGE)
	private Integer courage;
	
	@ApiModelProperty(dataType = "string", allowableValues = "AUTOBOT, DECEPTICON", value = "description", notes = "notes")
	private TeamType team;
	
	public Autobot buildAutobot() {
		return Autobot.builder()
							.name(getName())
							.strength(getStrength()) 
							.speed(getSpeed())
							.courage(getCourage())
							.endurance(getEndurance())
							.rank(getRank()) 
							.firepower(getFirepower())
							.intelligence(getIntelligence())
							.skill(getSkill())
							.endurance(getEndurance()) 	
							.team(getTeam())
 							.build();
	}
	
	public Decepticon buildDecepticon() {
		return Decepticon.builder()
							.name(getName())
							.strength(getStrength()) 
							.speed(getSpeed())
							.courage(getCourage())
							.endurance(getEndurance())
							.rank(getRank()) 
							.firepower(getFirepower())
							.intelligence(getIntelligence())
							.skill(getSkill())
							.endurance(getEndurance()) 	
							.team(getTeam())
							.build();
	}
	
}
