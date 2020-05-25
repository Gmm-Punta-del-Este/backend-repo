package com.magicbussines.gmm.repos;

import java.util.Optional;


import javax.persistence.IdClass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import com.magicbussines.gmm.model.Persona;
import com.magicbussines.gmm.model.PersonaPropietario;

//@RepositoryDefinition(domainClass = Persona.class, idClass = Persona.class)
public abstract interface RepositoryPersonaPropietario extends CrudRepository<PersonaPropietario, String>{

}