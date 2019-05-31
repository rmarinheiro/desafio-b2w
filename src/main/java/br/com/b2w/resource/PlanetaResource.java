package br.com.b2w.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.decoder.URL;
import br.com.b2w.domain.Planet;
import br.com.b2w.service.PlanetaService;

@RestController
@RequestMapping(value = "/planets")
public class PlanetaResource {

	@Autowired
	private PlanetaService planetaService;

	/**
	 * Metodo responsavel por inserir um planeta
	 * @param planet
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Planet planet) {
		planetaService.inserir(planet);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(planet.getId()).toUri();

		return ResponseEntity.created(uri).build();// codigo 201 para objeto criado

	}
	
	/**
	 * Metodo responsavel por buscar um planeta pelo ID
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Planet> findById(@PathVariable String id){
		
		Planet planet = planetaService.findById(id);
		
		return ResponseEntity.ok(planet);// retorna um codigo 200 objeto encontrado
	}
	
	/**
	 * Metodo responsavel por listar todos os planetas
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Planet>> findAll(){
		
		List<Planet> planet = planetaService.findAll();
		
		return ResponseEntity.ok(planet);// retorna um codigo 200 objeto encontrado
	}
	
	/**
	 * Metodo responsavel por listar um planeta pelo nome
	 * @param name
	 * @return
	 */
	@RequestMapping( value ="/name" , method =  RequestMethod.GET)
	public ResponseEntity<List<Planet> > findByName( @RequestParam(value = "text" ,defaultValue = "") String name){
		
		name = URL.decode(name);
		List<Planet> planet = planetaService.findByName(name);
		
		return ResponseEntity.ok(planet);
	}
	
	/**
	 * Metodo responsavel por deletar um planeta pelo ID
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/{id}" , method =  RequestMethod.DELETE)
	public ResponseEntity<Planet> delete(@PathVariable String id){
		
		planetaService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
