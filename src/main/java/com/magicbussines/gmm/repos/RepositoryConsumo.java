package com.magicbussines.gmm.repos;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magicbussines.gmm.model.Consumo;
import com.magicbussines.gmm.model.Nota;

public interface RepositoryConsumo extends CrudRepository<Consumo, Integer> {
		
//	@Query(nativeQuery = true, value = "select * from nota where login =?1")
//	public Iterable<Nota> searchNoteByLogin(String login);
//
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true, value = "update nota set titulo = ?1, texto = ?2, modified = ?3 where id = ?4")
//	public int Update(String titulo, String texto, LocalDateTime modified, int id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM consumo as c, apartamento as a, persona_propietario as pp WHERE c.nro_apto = a.nro_apto AND a.documento = pp.documento AND a.documento = ?1")
	public Iterable<Consumo> findByPropietario(String documento);
	
	@Query(nativeQuery = true, value = "SELECT * FROM consumo as c, apartamento as a WHERE c.nro_apto = a.nro_apto AND a.nro_apto = ?1")
	public Iterable<Consumo> findAllByApto(int nroapto);

}
