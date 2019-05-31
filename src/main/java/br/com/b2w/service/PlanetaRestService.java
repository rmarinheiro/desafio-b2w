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
		      //Erro 403
				restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
		        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		        entity = new HttpEntity<String>("parameters", headers);
		
	}
	
	public ResultDTO findByName(String name) {
		ResponseEntity<ResultDTO> resultDTO = restTemplate.exchange(urlSwapi.concat("?search=").concat(name), HttpMethod.GET, entity, ResultDTO.class);
		
		return resultDTO.getBody();
	}

}
