package com.aequilibrium.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Transformer Response")
public class TransformerResponseDto {
	
	@ApiModelProperty(example = "15")
	private Integer id;
	
	@ApiModelProperty(example = "2")
	private Integer strength;
	
	@ApiModelProperty(example = "4")
	private Integer intelligence;
	
	@ApiModelProperty(example = "5")
	private Integer speed;
	
	@ApiModelProperty(example = "8")
	private Integer endurance;
	
	@ApiModelProperty(example = "9")
	private Integer rank;
	
	@ApiModelProperty(example = "1")
	private Integer courage;
	
	@ApiModelProperty(example = "2")
	private Integer firepower;
	
	@ApiModelProperty(example = "7")
	private Integer skill;
	
	@ApiModelProperty(example = "Soundwave")
	private String name; 
	
	@ApiModelProperty(example = "AUTOBOT, DECEPTICON")
	private String team;
	
}
