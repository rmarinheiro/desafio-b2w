package br.com.b2w.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.b2w.domain.Planet;

@Repository
public interface PlanetaRepository extends MongoRepository<Planet, String> {
	
	List<Planet> findByNomeContaining(String nome);

}
