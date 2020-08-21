package com.magicbussines.gmm.common;

import com.magicbussines.gmm.model.Nota;

import org.springframework.stereotype.Service;

import com.magicbussines.gmm.common.MapperPersona;
@Service
public class MapperNota {

	@Autowired
	private MapperPersona _mp;

	public DTONota NotaToDTO(Nota nota) {

		DTONota dtoNota = new DTONota();
			dtoNota.setTexto(nota.getTexto());
			dtoNota.setTitulo(nota.getTitulo());
			dtoNota.setCreatedOn(nota.getCreatedOn());
			dtoNota.setUsuario(_mp.UsuarioToDTO(nota.getUsuario()));
			
		return dtoNota;
	}
}
