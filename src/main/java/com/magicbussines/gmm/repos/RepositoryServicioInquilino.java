package com.magicbussines.gmm.repos;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.ServicioInquilino;

public interface RepositoryServicioInquilino extends CrudRepository<ServicioInquilino, Integer> {
//		
//	@Query(nativeQuery = true, value = "select * from nota where login =?1")
//	public Iterable<Nota> searchNoteByLogin(String login);
//
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "update nota set titulo = ?1, texto = ?2, modified = ?3 where id = ?4")
//	public int Update(String titulo, String texto, LocalDateTime modified, int id);

}
