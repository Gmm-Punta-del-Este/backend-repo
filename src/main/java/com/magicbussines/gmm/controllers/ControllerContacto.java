package com.magicbussines.gmm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magicbussines.gmm.common.DTONota;
import com.magicbussines.gmm.interfaces.IContactoInquilino;
import com.magicbussines.gmm.interfaces.IContactoPropietario;
import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.Nota;
@CrossOrigin
@RestController
@RequestMapping("/contacto")
public class ControllerContacto {

	@Autowired 
	private IContactoInquilino _inqui;
	@Autowired 
	private IContactoPropietario _propi;
	@Autowired 
	private IContactoUsuario _usu;
	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ================================= SECCION DEL CONTROLADOR PARA PERSONA_PROPIETARIO =================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/propietario/listar")
	public ResponseEntity<Object> contactos() {
		List<ContactoPropietario> cp = (List<ContactoPropietario>) _propi.listarTodo();
		
		if(cp.isEmpty()) {
			return new ResponseEntity<Object>("No hay contactos de propietaarios", HttpStatus.NOT_FOUND);
		}
//		List<DTONota> notasDto = new ArrayList<DTONota>();
//		for (Nota notaAux : notas) {
//			notasDto.add(_mapper.NotaToDTO(notaAux));
//		}
		return new ResponseEntity<Object>("coso",HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ================================= SECCION DEL CONTROLADOR PARA PERSONA_INQULINO =================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ================================= SECCION DEL CONTROLADOR PARA PERSONA_USUARIO =================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/usuario/listar")
	public ResponseEntity<Object> contactosUsu() {
		List<ContactoUsuario> cp = (List<ContactoUsuario>) _usu.listarTodo();
		
		if(cp.isEmpty()) {
			return new ResponseEntity<Object>("No hay contactos de propietaarios", HttpStatus.NOT_FOUND);
		}
//		List<DTONota> notasDto = new ArrayList<DTONota>();
//		for (Nota notaAux : notas) {
//			notasDto.add(_mapper.NotaToDTO(notaAux));
//		}
		return new ResponseEntity<Object>(cp,HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Object> contactoasdsUsu(@PathVariable(name="id")String id) {
		ContactoUsuario cp =  _usu.Entity(id).get();
		
		
		return new ResponseEntity<Object>(cp,HttpStatus.OK);
	}
}
