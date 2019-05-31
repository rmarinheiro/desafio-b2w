package br.com.b2w.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.b2w.domain.Planet;
import br.com.b2w.dto.PlanetDTO;
import br.com.b2w.dto.ResultDTO;
import br.com.b2w.exception.ObjectFoundException;
import br.com.b2w.repository.PlanetaRepository;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private PlanetaRestService planetaRestService;
	
	public Planet inserir(Planet planet) {
		planetaRepository.save(planet);
		getTotalFilms(Optional.ofNullable(planet));
		
		return planet;
	}
	
	public Planet findById(String id) {
		
		Optional<Planet> planet = planetaRepository.findById(id);
		getTotalFilms(planet);
		
		return planet.orElseThrow((() -> new ObjectFoundException("Planeta Não Encontrado")));
	
	}
	
	public List<Planet> findByName(String name) {
		List<Planet> planets = planetaRepository.findByNomeContaining(name);
		planets.forEach(planet -> getTotalFilms(Optional.ofNullable(planet)));
		
		return planets;
		
	}
	
	public List<Planet> findAll(){
		
		List<Planet> planets = planetaRepository.findAll();
		planets.forEach(planet -> getTotalFilms(Optional.ofNullable(planet)));
		
		
		return planets;
	}

	private void getTotalFilms(Optional<Planet> planet) {
		planet.ifPresent(itemPlaneta ->{
			ResultDTO resultDTO = planetaRestService.findByName(itemPlaneta.getNome());
			
			if(resultDTO.getResults() != null && !resultDTO.getResults().isEmpty()){
				PlanetDTO planetaDTO = resultDTO.getResults().get(0);
				itemPlaneta.setTotalFilms(planetaDTO.getFilms().size());
				
		}
		});
		
		
	}
	
	public void delete(String id) {
		Optional<Planet> planet = planetaRepository.findById(id);
		planet.ifPresent(item -> planetaRepository.delete(item));
		
	}

	
	
	

}
