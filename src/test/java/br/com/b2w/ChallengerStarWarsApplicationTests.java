package br.com.b2w;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b2w.domain.Planet;
import br.com.b2w.resource.PlanetaResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengerStarWarsApplicationTests {
	
	@Autowired
	private PlanetaResource planetaResource;
	
	private Planet planet;
	private List<Planet> planets;
	private ObjectMapper mapper;

	@Before
	public void inicializacao() throws JsonParseException, JsonMappingException, IOException {
		mapper = new ObjectMapper();
		planets = new ArrayList<Planet>();
		planet =  mapper.readValue(new File("src/test/resource/Geonosis.json") , Planet.class);
		planets.add(planet);
	}
	
	/**
	 * Metodo usado para testar o salvar planeta.
	 */
	@Test
	public void testSalvar() {
		planets.forEach(item -> planetaResource.insert(item));
		planets.forEach(item -> assertNotNull(item.getId())); 
	}
	
	/**
	 * Metodo de teste responsavel por testar a buscar por Nome.
	 */
	@Test
	public void testarBuscarNome() {
		planets.forEach(item -> planetaResource.insert(item));
		planets.forEach(item -> assertNotNull(planetaResource.findByName(item.getNome())));
		
	}
	
	/**
	 * Metodo  de teste responsavel por testar a busca de um planeta pelo ID
	 */
	@Test
	public void testarBuscarID() {
		
		planets.forEach(item -> planetaResource.insert(item));
		planets.forEach(item -> assertNotNull(planetaResource.findById(item.getId())));
		
	}
	
	
	/**
	 * Metodo responsavel por remover um planeta
	 */
	@Test
	public void testarRemoverPlaneta() {
		planets.forEach(item -> planetaResource.insert(item));
		planets.forEach(item -> assertNotNull(planetaResource.delete(item.getId())));
	}
	
	@Test
	public void testarBuscarTodosPlanetas() {
		assertNotNull(planetaResource.findAll());
				
	}

}
