package com.magicbussines.gmm.repos;

import java.util.Optional;


import javax.persistence.IdClass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import com.magicbussines.gmm.model.Persona;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;


public interface RepositoryPersonaPropietario extends CrudRepository<PersonaPropietario, String>{

	@Query(nativeQuery = true, value ="select * from persona_propietario where deleted = 0")
	public Iterable<PersonaPropietario> findAllActive();
	
	@Query(nativeQuery = true, value ="select * from persona_propietario where deleted = 1")
	public Iterable<PersonaPropietario> findAllInactive();
}
