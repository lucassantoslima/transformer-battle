package com.aequilibrium.resource;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aequilibrium.dto.request.TransformerRequestDto;
import com.aequilibrium.dto.response.BattleResponseDto;
import com.aequilibrium.dto.response.TransformerResponseDto;
import com.aequilibrium.events.CreatedResourceEvent;
import com.aequilibrium.service.TransformerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(TransformerResource.TRANSFORMERS)
@Api(tags = "Transformer")
public class TransformerResource implements Serializable {

	static final String TRANSFORMERS = "/transformers"; 

	private static final long serialVersionUID = 1L;
	
	private TransformerService transformerService;
	private ApplicationEventPublisher publisher;

	@Autowired
	public TransformerResource(final TransformerService transformerService, final ApplicationEventPublisher publisher) {
		this.transformerService = transformerService;
		this.publisher = publisher;
	}
	
	@ApiOperation(value = "List all Transformers / containers (with essential data, enough for displaying a table)", response = TransformerResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Search results matching criteria", response = TransformerResponseDto.class) })
	@GetMapping
	public List<TransformerResponseDto> findAllTransformers(final Pageable pageable) { 
		return transformerService.findAllTransformers(pageable);
	}
	
	@ApiOperation(value = "List all data about a Transformer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A single transformer", response = TransformerResponseDto.class),
							@ApiResponse(code = 404, message = "Transformer with this ID not found") })
	@GetMapping("{transformerId}")
	public ResponseEntity<TransformerResponseDto> findById(@PathVariable("transformerId") final Integer transformerId) { 
		final TransformerResponseDto customerFound = this.transformerService.findById(transformerId);
		return new ResponseEntity<TransformerResponseDto>(customerFound, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create new Transformer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Transformer created"),
							@ApiResponse(code = 400, message = "Invalid input, object invalid"),
							@ApiResponse(code = 409, message = "An existing transformer with (name) already exists") })
	@PostMapping
	public ResponseEntity<TransformerResponseDto> create(@Valid @RequestBody final TransformerRequestDto transformerDto, final HttpServletResponse response) {
		final TransformerResponseDto transformerSaved = this.transformerService.save(transformerDto);
		publisher.publishEvent(new CreatedResourceEvent(this, response, transformerSaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(transformerSaved); 
	}
	
	@ApiOperation(value = "Replaces a Transformer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transformer replaced"),
							@ApiResponse(code = 400, message = "Invalid input, object invalid"),
							@ApiResponse(code = 404, message = "The Transformer (or any of supplied IDs) is not found") })
	@PutMapping("{transformerId}")
	public ResponseEntity<TransformerResponseDto> replace(@PathVariable("transformerId") final Integer transformerId, @RequestBody final TransformerRequestDto transformerDto) {
		final TransformerResponseDto customerUpdated = this.transformerService.replaceTransformer(transformerId, transformerDto);
		return new ResponseEntity<TransformerResponseDto>(customerUpdated, HttpStatus.OK); 
	}

	@ApiOperation(value = "Deletes a Transformer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer deleted"),
							@ApiResponse(code = 404, message = "The customer is not found") })
	@DeleteMapping("{transformerId}")
	public ResponseEntity<Void> delete(@PathVariable("transformerId") final Integer transformerId) {
		this.transformerService.delete(transformerId); 
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Modifies a Transformer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transformer modified"),
							@ApiResponse(code = 400, message = "Invalid input, object invalid"),
							@ApiResponse(code = 404, message = "The Transformer (or any of supplied IDs) is not found") })
	@PatchMapping("{transformerId}")
	public ResponseEntity<Void> modifies(@PathVariable("transformerId") @NotNull final Integer transformerId, @RequestBody final TransformerRequestDto transformerDto) {
		this.transformerService.modifiesTransformer(transformerId, transformerDto); 
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Battle")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Battle is done"),
			@ApiResponse(code = 400, message = "Invalid input, object invalid"),
			@ApiResponse(code = 404, message = "The Transformer (or any of supplied IDs) is not found") })
	@PostMapping("{transformersId}")
	public ResponseEntity<BattleResponseDto> battle(@Valid @Size(min = 2) @Min(2) @PathVariable("transformersId") final List<Integer> transformersId) {
		BattleResponseDto battle = this.transformerService.battle(transformersId);   
		return new ResponseEntity<BattleResponseDto>(battle, HttpStatus.OK); 
	}

}
