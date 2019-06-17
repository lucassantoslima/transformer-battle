package com.aequilibrium.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.aequilibrium.dto.response.TransformerResponseDto;
import com.aequilibrium.enuns.TeamType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "team", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractTransformer implements ITransformer {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 8, name = "ID")
	private Integer id;

	private String name;

	private Integer strength;

	private Integer intelligence;

	private Integer speed;

	private Integer endurance;

	private Integer rank;

	private Integer courage;

	private Integer firepower;

	private Integer skill;
	
	@Column(name = "team", insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
    private TeamType team;

	public Integer getRating() {
		return strength + intelligence + speed + endurance + firepower; 
	}
	
	public TransformerResponseDto buildTransformerResponseDto() {
		return TransformerResponseDto.builder()
							.id(getId())
							.strength(getStrength())
							.intelligence(getIntelligence())
							.speed(getSpeed())
							.endurance(getEndurance())
							.rank(getRank())
							.courage(getCourage())
							.firepower(getFirepower())
							.skill(getSkill()) 
							.name(getName())
							.team(getTeam().getDescription())
							.build();
	}
	
	public TransformerResponseDto buildTransformerResponseDtoWithOutId() {
		return TransformerResponseDto.builder()
							.strength(getStrength())
							.intelligence(getIntelligence())
							.speed(getSpeed())
							.endurance(getEndurance())
							.rank(getRank())
							.courage(getCourage())
							.firepower(getFirepower())
							.skill(getSkill()) 
							.name(getName())
							.team(getTeam().getDescription())
							.build();
	}
	
	public boolean isDecepticon() {
		return checkTeam(TeamType.DECEPTICON);
	}
	
	public boolean isAutobot() {
		return checkTeam(TeamType.AUTOBOT); 
	}

	public boolean checkTeam(final TeamType team) {
		return getTeam().equals(team);
	}

	@Override
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTransformer other = (AbstractTransformer) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		return result;
	}

}
