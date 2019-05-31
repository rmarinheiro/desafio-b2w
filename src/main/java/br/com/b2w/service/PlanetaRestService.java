package br.com.b2w.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.dto.ResultDTO;



@Component("planetaRestService")
public class PlanetaRestService {
	
	@Value( "${url.swapi}" )
	private String urlSwapi;
	
	private RestTemplate restTemplate;
	
	private HttpEntity<String> entity;
	
	public PlanetaRestService() {
		
	}
	
	public ResultDTO findByName(String name) {
		ResponseEntity<ResultDTO> resultDTO = restTemplate.exchange(urlSwapi.concat("?search=").concat(name), HttpMethod.GET, entity, ResultDTO.class);
		
		return resultDTO.getBody();
	}

}
