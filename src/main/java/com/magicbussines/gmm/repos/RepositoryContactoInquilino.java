package com.magicbussines.gmm.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.magicbussines.gmm.model.ContactoInquilino;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaUsuario;

public interface RepositoryContactoInquilino extends CrudRepository<ContactoInquilino,String> {
	
	@Query(nativeQuery = true, value ="select * from contacto_inquilino where documento =?1")
	public Optional<ContactoInquilino> findByDocumento(String documento);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value ="insert into contacto_inquilino (nombre,apellido,documento,email,telefono) values (:nom, :ape, :doc,:email,:tel)")
	public void insertManopla(@Param("nom") String nombre, @Param("ape") String apellido, @Param("doc")String documento, @Param("email")String email, @Param("tel") String telefono);
}
