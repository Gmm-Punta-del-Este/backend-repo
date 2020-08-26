package com.magicbussines.gmm.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaUsuario;

public interface RepositoryContactoPropietario extends CrudRepository<ContactoPropietario,Integer> {
	
	@Query(nativeQuery = true, value ="select * from contacto_usuario where documento =?1")
	public Optional<ContactoPropietario> findByDocumento(String documento);
}
