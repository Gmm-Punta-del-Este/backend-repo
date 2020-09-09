package com.magicbussines.gmm.common;

import org.springframework.stereotype.Component;

import com.magicbussines.gmm.model.ContactoInquilino;
import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaPropietario;

@Component
public class MapperContacto {

	public DTOContactoPropietario PropToDTO(ContactoPropietario prop) {
		DTOContactoPropietario dtoPropCont = new DTOContactoPropietario();
		dtoPropCont.documento = prop.getUsuario().getDocumento();
		dtoPropCont.apellido = prop.getApellido();
		dtoPropCont.nombre = prop.getNombre();
		dtoPropCont.email = prop.getEmail();
		dtoPropCont.telefono = prop.getTelefono();
		
		return dtoPropCont;
	}
	
	public DTOContactoInquilino InquiToDTO(ContactoInquilino prop) {
		DTOContactoInquilino dtoPropCont = new DTOContactoInquilino();
		dtoPropCont.documento = prop.getUsuario().getDocumento();
		dtoPropCont.apellido = prop.getApellido();
		dtoPropCont.nombre = prop.getNombre();
		dtoPropCont.email = prop.getEmail();
		dtoPropCont.telefono = prop.getTelefono();
		
		return dtoPropCont;
	}
	
	public DTOContactoUsuario UsuToDTO(ContactoUsuario prop) {
		DTOContactoUsuario dtoPropCont = new DTOContactoUsuario();
		dtoPropCont.documento = prop.getUsuario().getDocumento();
		dtoPropCont.apellido = prop.getApellido();
		dtoPropCont.nombre = prop.getNombre();
		dtoPropCont.email = prop.getEmail();
		dtoPropCont.telefono = prop.getTelefono();
		
		return dtoPropCont;
	}
}
