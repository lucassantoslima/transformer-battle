package com.aequilibrium.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.aequilibrium.LucaslimaAequilibriumTechassignmentApplication;
import com.aequilibrium.builder.BuildTransformer;
import com.aequilibrium.conf.H2JpaConfig;
import com.aequilibrium.dto.request.TransformerRequestDto;
import com.aequilibrium.dto.response.BattleResponseDto;
import com.aequilibrium.dto.response.TransformerResponseDto;
import com.aequilibrium.exception.ErrorMessage;
import com.aequilibrium.util.JsonUtilImpl;


@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = { LucaslimaAequilibriumTechassignmentApplication.class,	H2JpaConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransformerResourceTest {
	
	@Autowired
	private TransformerResource resource;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort 
	private int port;
	
	private static final String RESOURCE = TransformerResource.TRANSFORMERS;
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(resource).isNotNull(); 
	}
	
	@Test
	public void shouldCreateTransformer() {
		
		TransformerRequestDto requestDto = BuildTransformer.createSoundwaveRequestDto();

		String jsonRequested = new JsonUtilImpl().fromObject(requestDto);
	    
		ResponseEntity<TransformerResponseDto> responseEntity = this.restTemplate.postForEntity(RESOURCE, getPostRequestHeaders(jsonRequested), TransformerResponseDto.class);
		TransformerResponseDto client = responseEntity.getBody(); 

		assertEquals(requestDto.getName(), client.getName());
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("http://localhost:"+ port +"/api/transformers/1", responseEntity.getHeaders().getLocation().toString());
	}
	
	@Test
	public void shouldUpdateTransformer() {
		TransformerResponseDto createdTransformer = this.createTransformer(BuildTransformer.createSoundwaveRequestDto());
		
	    URI build = UriComponentsBuilder.fromUriString(RESOURCE+"/{transformerId}").build(createdTransformer.getId());  
	    
	    TransformerRequestDto updatedTransformer = BuildTransformer.createSoundwaveRequestDto();
	    
	    updatedTransformer.setName("Name Modified"); 

		String jsonRequested = new JsonUtilImpl().fromObject(updatedTransformer);
	    
		ResponseEntity<TransformerResponseDto> responseEntity = this.restTemplate.exchange(build, HttpMethod.PUT, getPostRequestHeaders(jsonRequested), TransformerResponseDto.class);
		TransformerResponseDto client = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Name Modified", client.getName());
	}
	
	@Test
	public void updatePartialTransformer() {
		String requestJson = "{\"name\":\"PartialTransformer\"}"; 
		
		TransformerResponseDto optimusPrime = createTransformer(BuildTransformer.createSoundwaveRequestDto());
		
	    URI build = UriComponentsBuilder.fromUriString(RESOURCE+"/{transformerId}").build(optimusPrime.getId());  
	    
		ResponseEntity<TransformerResponseDto> responseEntity = this.restTemplate.exchange(build, HttpMethod.PATCH, getPostRequestHeaders(requestJson), TransformerResponseDto.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void shouldDeleteATransformer() {  
		TransformerResponseDto optimusPrime = createTransformer(BuildTransformer.createJazzRequestDto()); 
		
		URI build = UriComponentsBuilder.fromUriString(RESOURCE + "/{transformerId}").build(optimusPrime.getId()); 
		
		this.restTemplate.delete(build);
		    
		URI buildSearch = UriComponentsBuilder.fromUriString(RESOURCE + "/{transformerId}").build(optimusPrime.getId()); 
			
		ResponseEntity<TransformerResponseDto> responseEntity = this.restTemplate.exchange(buildSearch, HttpMethod.GET, getPostRequestHeaders(null), TransformerResponseDto.class);
		
		assertNull(responseEntity.getBody().getTeam()); 
	}
	
	@Test
	public void shouldWinBattleDecepticons() {
		TransformerResponseDto soundwave = createTransformer(BuildTransformer.createSoundwaveRequestDto());
		TransformerResponseDto bluestreak = createTransformer(BuildTransformer.createBluestreakRequestDto());
		TransformerResponseDto hubcap = createTransformer(BuildTransformer.createHubcapRequestDto());
		
		URI build = UriComponentsBuilder.fromUriString(RESOURCE + "/{transformerId}").build(soundwave.getId() + "," + bluestreak.getId() + "," + hubcap.getId());  
		
		ResponseEntity<BattleResponseDto> responseEntity = this.restTemplate.postForEntity(build, getPostRequestHeaders(null), BattleResponseDto.class);
		BattleResponseDto body = responseEntity.getBody();
		
		assertNotNull(body); 
		assertEquals("1 battle", body.getNumberOfBattle());
		assertThat(body.getWinningTeam()).contains("Soundwave");  
		assertThat(body.getSurvivorsFromLosingTeam()).contains("Hubcap");  
	}
	
	@Test
	public void shouldTieBattle() {
		TransformerResponseDto jazz = createTransformer(BuildTransformer.createJazzRequestDto());
		TransformerResponseDto hubcap = createTransformer(BuildTransformer.createHubcapRequestDto());
		
		URI build = UriComponentsBuilder.fromUriString(RESOURCE + "/{transformerId}").build(jazz.getId() + "," + hubcap.getId());  
		
		ResponseEntity<BattleResponseDto> responseEntity = this.restTemplate.postForEntity(build, getPostRequestHeaders(null), BattleResponseDto.class);
		BattleResponseDto body = responseEntity.getBody();
		
		assertNotNull(body); 
		assertThat(body.getWinningTeam()).contains("There is no");  
		assertThat(body.getSurvivorsFromLosingTeam()).contains("There is no");  
	}
	
	@Test
	public void shouldDestroyedAllCompetitors() {
		TransformerResponseDto predaking = createTransformer(BuildTransformer.createPredakingRequestDto());
		TransformerResponseDto optimusPrime = createTransformer(BuildTransformer.createOptimusPrimeRequestDto());
		
		URI build = UriComponentsBuilder.fromUriString(RESOURCE + "/{transformerId}").build(predaking.getId() + "," + optimusPrime.getId());  
		
		ResponseEntity<ErrorMessage> responseEntity = this.restTemplate.postForEntity(build, getPostRequestHeaders(null), ErrorMessage.class);
		ErrorMessage body = responseEntity.getBody();
		
		assertNotNull(body); 
		assertThat(body.getMessage().contains("The game immediately ends with all competitors destroyed.")); 
	}
	
	private TransformerResponseDto createTransformer(TransformerRequestDto transformer) {
		String jsonRequested = new JsonUtilImpl().fromObject(transformer);
	    
		ResponseEntity<TransformerResponseDto> responseEntity = this.restTemplate.postForEntity(RESOURCE, getPostRequestHeaders(jsonRequested), TransformerResponseDto.class);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		return responseEntity.getBody();
	} 
	 
	public TransformerResponseDto searchById(final Integer id) {
	    URI build = UriComponentsBuilder.fromUriString(RESOURCE + "/{transformerId}").build(id); 
		
	    ResponseEntity<TransformerResponseDto> responseEntity = this.restTemplate.exchange(build, HttpMethod.GET, getPostRequestHeaders(null), TransformerResponseDto.class);
	    
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    return responseEntity.getBody();
	}
	
	private HttpEntity<?> getPostRequestHeaders(String jsonPostBody) {
		List<MediaType> acceptTypes = new ArrayList<>(); 
        acceptTypes.add(MediaType.APPLICATION_JSON_UTF8);  

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        reqHeaders.setAccept(acceptTypes); 

        return new HttpEntity<Object>(jsonPostBody, reqHeaders); 
	} 
	
	@Before
	public void setup() {
	    restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}
	
}
