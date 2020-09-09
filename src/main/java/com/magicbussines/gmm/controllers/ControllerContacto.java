package com.magicbussines.gmm.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicbussines.gmm.common.DTOContactoInquilino;
import com.magicbussines.gmm.common.DTOContactoPropietario;
import com.magicbussines.gmm.common.DTOContactoUsuario;
import com.magicbussines.gmm.common.DTONota;
import com.magicbussines.gmm.common.MapperContacto;
import com.magicbussines.gmm.interfaces.IContactoInquilino;
import com.magicbussines.gmm.interfaces.IContactoPropietario;
import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.model.ContactoInquilino;
import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;

import jdk.jfr.BooleanFlag;
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
	
	@Autowired
	private MapperContacto _mapContacto;
	@Autowired
	private IPersonaUsuario _usuario;
	@Autowired
	private IPersonaPropietario _propietario;
	@Autowired
	private IPersonaInquilino _inquilino;
	@Autowired
	private ObjectMapper _obj;
	
	
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
		List<DTOContactoPropietario> notasDto = new ArrayList<DTOContactoPropietario>();
		for (ContactoPropietario cpAux : cp) {
			notasDto.add(_mapContacto.PropToDTO(cpAux));
		}
		return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	@GetMapping("/propietario/listar/{id}")
	public ResponseEntity<Object> contactoaspprop(@PathVariable(name="id")String id) {
		
		if(_propi.existeContacto(id)) {
			ContactoPropietario cp =  _propi.Entity(id).get();
			DTOContactoPropietario dtoCp = _mapContacto.PropToDTO(cp);
			return new ResponseEntity<Object>(dtoCp,HttpStatus.OK);
			
		}else {
			return new ResponseEntity<Object>("NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/propietario/")
	public ResponseEntity<Object> addCProp(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
	
		try {
			String login = data.get("user").get("login").asText();
			String password = data.get("user").get("password").asText();
			if (_usuario.isUserActiveCredenciales(login, password)) {
				
				ContactoPropietario con = _obj.readValue(data.get("contacto_propietario").toString(), ContactoPropietario.class); 
				try {
				PersonaPropietario usu = _propietario.Entity(data.get("contacto_propietario").get("documento").asText()).get();
				con.setUsuario(usu);
				con = _propi.Save2(con);
			} catch (Exception e) {
				return new ResponseEntity<Object>("No existe PERSONA_PROPIETARIO a la cual asociar un contacto, por favor registre una en /persona/propietario.",HttpStatus.CONFLICT);
			}
				return new ResponseEntity<Object>(_mapContacto.PropToDTO(con),HttpStatus.OK);
			} else
			{
				return new ResponseEntity<Object>("El usuario con el cual desea ingresar un contacto esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ================================= SECCION DEL CONTROLADOR PARA PERSONA_INQULINO =================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	@GetMapping("/inquilino/listar")
	public ResponseEntity<Object> contactosInqui() {
		List<ContactoInquilino> cp = (List<ContactoInquilino>) _inqui.listarTodo();
		
		if(cp.isEmpty()) {
			return new ResponseEntity<Object>("No hay contactos de inquilinos", HttpStatus.NOT_FOUND);
		}
		List<DTOContactoInquilino> notasDto = new ArrayList<DTOContactoInquilino>();
		for (ContactoInquilino cpAux : cp) {
			notasDto.add(_mapContacto.InquiToDTO(cpAux));
		}
		return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/inquilino/listar/{id}")
	public ResponseEntity<Object> contactoasdsInqu(@PathVariable(name="id")String id) {
		if(_inqui.existeContacto(id)) {
			ContactoInquilino cp =  _inqui.Entity(id).get();
			DTOContactoInquilino dtoCp = _mapContacto.InquiToDTO(cp);
			return new ResponseEntity<Object>(dtoCp,HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@PostMapping("/inquilino/")
	public ResponseEntity<Object> addCInqu(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
	
		try {
			String login = data.get("user").get("login").asText();
			String password = data.get("user").get("password").asText();
			if (_usuario.isUserActiveCredenciales(login, password)) {
				ContactoInquilino con = _obj.readValue(data.get("contacto_inquilino").toString(), ContactoInquilino.class); 
				
				try {
					PersonaInquilino usu = _inquilino.Entity(data.get("contacto_inquilino").get("documento").asText()).get();
					con.setUsuario(usu);
					con = _inqui.Save2(con);
				} catch (Exception e) {
					return new ResponseEntity<Object>("No existe PERSONA_INQUILINO a la cual asociar un contacto.",HttpStatus.CONFLICT);
				}

				return new ResponseEntity<Object>(_mapContacto.InquiToDTO(con),HttpStatus.OK);
			} else
			{
				return new ResponseEntity<Object>("El usuario con el cual desea ingresar un contacto esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
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
		List<DTOContactoUsuario> notasDto = new ArrayList<DTOContactoUsuario>();
		for (ContactoUsuario cpAux : cp) {
			notasDto.add(_mapContacto.UsuToDTO(cpAux));
		}
		return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/usuario/listar/{id}")
	public ResponseEntity<Object> contactoasdsUsu(@PathVariable(name="id")String id) {
		if(_usu.existeContacto(id)) {
			ContactoUsuario cp =  _usu.Entity(id).get();
			DTOContactoUsuario dtoCp = _mapContacto.UsuToDTO(cp);
			return new ResponseEntity<Object>(dtoCp,HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("NOT FOUND",HttpStatus.NOT_FOUND);
		}
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@PostMapping("/usuario/")
	public ResponseEntity<Object> addCUsu(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
	
		try {
			String login = data.get("user").get("login").asText();
			String password = data.get("user").get("password").asText();
			if (_usuario.isUserActiveCredenciales(login, password)) {
				
				ContactoUsuario con = _obj.readValue(data.get("contacto_usuario").toString(), ContactoUsuario.class); 
			try {
				PersonaUsuario usu = _usuario.UserById(data.get("contacto_usuario").get("documento").asText()).get();
				con.setUsuario(usu);
				con = _usu.Save2(con);
			} catch (Exception e) {
				return new ResponseEntity<Object>("No existe PERSONA_USUARIO a la cual asociar un contacto. Por favor registe una */persona/usuario/",HttpStatus.CONFLICT);
			}
				return new ResponseEntity<Object>(_mapContacto.UsuToDTO(con),HttpStatus.OK);
			} else
			{
				return new ResponseEntity<Object>("El usuario con el cual desea ingresar un contacto esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
