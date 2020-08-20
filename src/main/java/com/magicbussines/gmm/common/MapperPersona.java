package com.magicbussines.gmm.common;

import org.springframework.stereotype.Service;

import com.magicbussines.gmm.model.PersonaUsuario;
@Service
public class MapperPersona {

	public DTOUsuario UsuarioToDTO(PersonaUsuario user) {
		
		if (user==null)
			return null;
		
		DTOUsuario dtoUser = new DTOUsuario();
		dtoUser.setNombre(user.getNombre());
		dtoUser.setApellido1(user.getApellido1());
		dtoUser.setApellido2(user.getApellido2());
		dtoUser.setDocumento(user.getDocumento());
		
		dtoUser.setCreatedOn(user.getCreatedOn());
		if (user.isDeleted().equals(user.getCreatedOn())) {
			dtoUser.setEstado("Activado");
		}else
		{
			dtoUser.setEstado("Desactivado");
		}
		dtoUser.setTipoUsuario(user.getTipoUsuario());
		
		return dtoUser;
	}
}
