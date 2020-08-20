package com.magicbussines.gmm.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import com.magicbussines.gmm.model.PersonaUsuario;

//@RepositoryDefinition(domainClass = Persona.class, idClass = Persona.class)
public interface RepositoryPersonaUsuario extends CrudRepository<PersonaUsuario, String>{

	// -------------------------- 
	// -------- FINDING --------- 
	// --------------------------
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where login =?1")
	public Optional<PersonaUsuario> findByLogin(String login);
	
	//ACTIVOS
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where login =?1 and password =?2 and created_on = deleted")
	public Optional<PersonaUsuario> findByDocumentoActivo(String login, String password);
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where deleted = created_on")
	public Iterable<PersonaUsuario> findAllActive();
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where login =?1 and password =?2 and created_on = deleted")
	public Optional<PersonaUsuario> findByLoginPasswordActivo(String login, String password);
	
	//INACTIVO
	@Query(nativeQuery = true, value ="select * from persona_usuario where login =?1 and password =?2")
	public Optional<PersonaUsuario> findByLoginPassword(String login, String password);
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where deleted <> created_on")
	public Iterable<PersonaUsuario> findAllInactive();
	
	
	// -------------------------- 
	// -------- UPDATING -------- 
	// --------------------------

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value ="update persona_usuario set deleted = created_on where documento =?1")
	public void reactUser(String documento);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value ="update persona_usuario set deleted = now() where documento =?1")
	public void desactivarUser(String documento);
	
	// -------------------------- 
	// -------- EXISTING -------- 
	// --------------------------
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where deleted = created_on and documento = ?1")
	public Optional<PersonaUsuario> isActivedId(String documento);
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where deleted = created_on and login = ?1 and password = ?2")
	public Optional<PersonaUsuario> isActivedCredenciales(String login, String password);
	
	@Query(nativeQuery = true, value ="select * from persona_usuario where deleted = created_on and login = ?1")
	public Optional<PersonaUsuario> isActivedLogin(String login);
}

