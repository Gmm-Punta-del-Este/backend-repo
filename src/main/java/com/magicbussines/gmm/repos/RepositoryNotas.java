package com.magicbussines.gmm.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magicbussines.gmm.model.Nota;

public interface RepositoryNotas extends CrudRepository<Nota, Integer> {
		
	@Query(nativeQuery = true, value = "select * from nota where login =?1")
	public Iterable<Nota> searchNoteByLogin(String login);


	@Query(nativeQuery = true, value = "update nota set titulo = ?1, texto = ?2, created_on = ?3, modified = ?4 where id = ?5")
	public Nota Update(String titulo, String texto, LocalDateTime createdOn, LocalDateTime modified, int id);

}
