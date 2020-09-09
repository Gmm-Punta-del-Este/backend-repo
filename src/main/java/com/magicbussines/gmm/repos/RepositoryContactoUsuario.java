package com.magicbussines.gmm.repos;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaUsuario;

public interface RepositoryContactoUsuario extends CrudRepository<ContactoUsuario,Integer> {
	
	@Query(nativeQuery = true, value ="select * from contacto_usuario where documento = :doc")
	public Optional<ContactoUsuario> findByDocumento(@Param("doc") String documento);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value ="insert into contacto_usuario (nombre,apellido,documento,email,telefono) values (:nom, :ape, :doc,:email,:tel)")
	public void insertManopla(@Param("nom") String nombre, @Param("ape") String apellido, @Param("doc")String documento, @Param("email")String email, @Param("tel") String telefono);	
}
