package com.aequilibrium.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aequilibrium.domain.AbstractTransformer;
import com.aequilibrium.dto.request.TransformerRequestDto;
import com.aequilibrium.dto.response.BattleResponseDto;
import com.aequilibrium.dto.response.TransformerResponseDto;
import com.aequilibrium.exception.DuplicateItemException;
import com.aequilibrium.exception.ErrorCodes;
import com.aequilibrium.exception.NotFoundException;
import com.aequilibrium.factory.TransformerFactory;
import com.aequilibrium.repository.TransformerRepository;

@Lazy
@Service
public class TransformerService {

	private TransformerRepository transfomerRepository;
	private BattleService battleService;

	@Autowired
	public TransformerService(final TransformerRepository transfomerRepository, final BattleService battleService) {
		this.transfomerRepository = transfomerRepository;
		this.battleService = battleService; 
	}

	public List<TransformerResponseDto> findAllTransformers(final Pageable pageable) {
		final Page<AbstractTransformer> transformers = this.transfomerRepository.findAll(pageable);
		return transformers.stream().map(c -> c.buildTransformerResponseDto()).collect(Collectors.toList());
	}

	public TransformerResponseDto findById(final Integer transformerId) {
		this.validateTransformerNotFound(transformerId);
		final Optional<AbstractTransformer> transformer = this.transfomerRepository.findById(transformerId);
		return transformer.get().buildTransformerResponseDto();
	}

	public AbstractTransformer findByName(final String name) {
		Optional<AbstractTransformer> transformer = this.transfomerRepository.findByName(name);
		return transformer.get();
	}

	private void validateDuplicateTransformerByName(final String name) {
		this.transfomerRepository.findByName(name).ifPresent(c -> {
			throw new DuplicateItemException(ErrorCodes.VALIDATE_RECORD_DUPLICATE_ITEM, new Object[] { "name" });
		});
	}

	private void validateTransformerNotFound(final Integer transformerId) {
		this.transfomerRepository.findById(transformerId).orElseThrow(() -> new NotFoundException(ErrorCodes.VALIDATE_NOT_FOUND_ITEM, new Object[] { "Transformer" }));
	}

	public TransformerResponseDto save(final TransformerRequestDto transformerDto) {
		this.validateDuplicateTransformerByName(transformerDto.getName());

		AbstractTransformer transformer = TransformerFactory.getTransformer(transformerDto); 
		
		final AbstractTransformer transformerSaved = this.transfomerRepository.save( transformer );

		return transformerSaved.buildTransformerResponseDto(); 
	}

	public void delete(final Integer transformerId) {
		validateTransformerNotFound(transformerId);
		this.transfomerRepository.deleteById(transformerId);
	}

	public TransformerResponseDto replaceTransformer(final Integer transformerId, final TransformerRequestDto transformerDto) {
		this.validateTransformerNotFound(transformerId);

		Optional<AbstractTransformer> transformerOptinal = this.transfomerRepository.findById(transformerId);

		AbstractTransformer oldTransformer = transformerOptinal.get();

		BeanUtils.copyProperties(transformerDto, oldTransformer, "id");

		AbstractTransformer transformerReplaced = this.transfomerRepository.save(oldTransformer);

		return transformerReplaced.buildTransformerResponseDto();

	}

	public void modifiesTransformer(final Integer transformerId, final TransformerRequestDto transformerDto) {
		this.validateTransformerNotFound(transformerId);

		Optional<AbstractTransformer> transformerOptinal = this.transfomerRepository.findById(transformerId);
		transformerOptinal.ifPresent(c -> {

			String name = transformerDto.getName();
			if (!StringUtils.isEmpty(name)) {
				c.setName(name);
			}
			
			Integer strength = transformerDto.getStrength();
			if (!StringUtils.isEmpty(strength)) {
				c.setStrength(strength);
			}
			
			Integer intelligence = transformerDto.getIntelligence();
			if (!StringUtils.isEmpty(intelligence)) {
				c.setIntelligence(intelligence);
			}
			
			Integer speed = transformerDto.getSpeed();
			if ( !StringUtils.isEmpty(speed) ) {
				c.setSpeed(speed);
			}
			
			Integer endurance = transformerDto.getEndurance();
			if ( !StringUtils.isEmpty(endurance) ) {
				c.setEndurance(endurance); 
			}

			this.transfomerRepository.save(c);
		});
	}

	public BattleResponseDto battle(final List<Integer> transformersId) {
		final List<AbstractTransformer> transformers = this.transfomerRepository.findAllById(transformersId);
		return this.battleService.startBattle(transformers); 
	}
	
}
