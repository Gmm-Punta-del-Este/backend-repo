package com.magicbussines.gmm.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.magicbussines.gmm.common.DTOUsuario;
import com.magicbussines.gmm.common.MapperPersona;
//import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;

@RestController
@RequestMapping("/persona")
public class ControllerPersona {

	@Autowired
	private IPersonaPropietario _propietario;
	@Autowired
	private IPersonaInquilino _inqulino;
	@Autowired
	private IPersonaUsuario _usuario;
	//@Autowired
	//private IContactoUsuario _contactoUsuario;
	@Autowired
	private ObjectMapper obj;
	@Autowired
	private MapperPersona _map;
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ================================= SECCION DEL CONTROLADOR PARA PERSONA_PROPIETARIO =================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	//lista todos los prop
	@GetMapping("/propietario/listar")
	public ResponseEntity<Object> PropietarioList() {
		List<PersonaPropietario> propietarios = (List<PersonaPropietario>) _propietario.List();
		if(propietarios.isEmpty()) {
			return new ResponseEntity<Object>("FALLO", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(propietarios,HttpStatus.OK);
	}
	
	//consulta del propietario por id
	@GetMapping("/propietario/{id}")
	public ResponseEntity<Object> Propietario(@PathVariable(value = "id") String id) {
		Optional<PersonaPropietario> propietario = _propietario.Entity(id);
		if(propietario.isEmpty()) {
			return new ResponseEntity<Object>("No existe propietario con documento", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(propietario,HttpStatus.OK);
	}
	
	//inserta un propietario
	@PostMapping("/propietario/")
	public ResponseEntity<Object> savePropietario(@Valid @RequestBody PersonaPropietario data){
		if (_propietario.Entity(data.getDocumento()).isPresent()){
			return new ResponseEntity<Object>("No se puede poner dos usuarios con el mismo id: "+data.getDocumento(), HttpStatus.NOT_FOUND);
		}
		try {
			PersonaPropietario nuevaPersona = new PersonaPropietario();
			nuevaPersona = _propietario.savePropietario(data);
			return new ResponseEntity<Object>(nuevaPersona, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	
	
	//deletea un propietario
	@DeleteMapping("/propietario/{id}")
	public ResponseEntity<Object> deletePropietario(@PathVariable(value = "id") String id){
		try {
			PersonaPropietario pp = _propietario.Entity(id).get();
			if (pp == null){
				return new ResponseEntity<Object>("No existe el Propietario con "+id, HttpStatus.NOT_FOUND);
			}
			_propietario.Delete(id);
			return new ResponseEntity<Object>(pp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// =================================== SECCION DEL CONTROLADOR PARA PERSONA_INQULINO ===================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/inquilino/")
	public ResponseEntity<Object> InquilinoList() {
		List<PersonaInquilino> inquilinos = (List<PersonaInquilino>) _inqulino.List();
		if(inquilinos.isEmpty()) {
			return new ResponseEntity<Object>("FALLO", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(inquilinos,HttpStatus.OK);
	}
	
	//
	@GetMapping("/inquilino/{id}")
	public ResponseEntity<Object> inquiUnit(@PathVariable(value = "id") String id) {
		Optional<PersonaInquilino> inquilino = _inqulino.Entity(id);
		if(inquilino.isEmpty()) {
			return new ResponseEntity<Object>("No existe inquilino con documento", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(inquilino,HttpStatus.OK);
	}
	
	@PostMapping("/inquilino/")
	public ResponseEntity<Object> saveInquilino(@Valid @RequestBody PersonaInquilino data){
		try {
			if (!_inqulino.Entity(data.getDocumento()).isEmpty()){
				return new ResponseEntity<Object>("No se puede poner dos usuarios con el mismo id: "+data.getDocumento(), HttpStatus.NOT_FOUND);
			}
			PersonaInquilino nuevaInquilino = new PersonaInquilino();
			 nuevaInquilino = _inqulino.Save(data);
			return new ResponseEntity<Object>(nuevaInquilino, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@DeleteMapping("/inquilino/{id}")
	public ResponseEntity<Object> deleteInquilino(@PathVariable(value = "id") String id){
		try {
			PersonaInquilino pp = _inqulino.Entity(id).get();
			if (pp == null){
				return new ResponseEntity<Object>("No existe el Propietario con "+id, HttpStatus.NOT_FOUND);
			}
			_inqulino.Delete(id);
			return new ResponseEntity<Object>(pp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// =================================== SECCION DEL CONTROLADOR PARA PERSONA_USUARIO ====================================== 
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	//--GETTERS 
	@GetMapping("/usuario/listar")
	public ResponseEntity<Object> UsuarioList() {
		List<PersonaUsuario> usuarios = (List<PersonaUsuario>) _usuario.ListarActivos();
		if(usuarios.isEmpty()) {
			return new ResponseEntity<Object>("No hay usuarios registrados", HttpStatus.NO_CONTENT);
		}
		
		List<DTOUsuario> dtoUsers = new ArrayList<DTOUsuario>();
		
		for (PersonaUsuario usuarioList : usuarios) {
			dtoUsers.add(_map.UsuarioToDTO(usuarioList));
		}
		return new ResponseEntity<Object>(dtoUsers,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/usuario/activos")
	public ResponseEntity<Object> UsuarioListaActivos() {
		List<PersonaUsuario> usuarios = (List<PersonaUsuario>) _usuario.ListarActivos();
		if(usuarios.isEmpty()) {
			return new ResponseEntity<Object>("No hay usuarios registrados", HttpStatus.NO_CONTENT);
		}
		List<DTOUsuario> dtoUsers = new ArrayList<DTOUsuario>();
		
		for (PersonaUsuario usuarioList : usuarios) {
			dtoUsers.add(_map.UsuarioToDTO(usuarioList));
		}
		return new ResponseEntity<Object>(dtoUsers,HttpStatus.OK);
	}
	
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************
		
	@GetMapping("/usuario/inactivos")
	public ResponseEntity<Object> UsuarioListaInactivos() {
		List<PersonaUsuario> usuarios = (List<PersonaUsuario>) _usuario.ListarInactivos();
		
		if(usuarios.isEmpty()) {
			return new ResponseEntity<Object>("No hay usuarios registrados", HttpStatus.NO_CONTENT);
		}
		
		List<DTOUsuario> dtoUsers = new ArrayList<DTOUsuario>();
		for (PersonaUsuario usuarioList : usuarios) {
			dtoUsers.add(_map.UsuarioToDTO(usuarioList));
		}
		return new ResponseEntity<Object>(dtoUsers,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
		
	//futuro peque;o cambio a ser recibido por JSON por seguridad
	@GetMapping("/usuario/{username}")
	public ResponseEntity<Object> userUser(@PathVariable(value = "username") String username) throws JsonParseException, JsonMappingException, IOException {
		//String id = data.get("user").get("documento").asText();
		//String status = data.get("user").get("status").asText();
		try {
			Optional<PersonaUsuario> usuario = _usuario.UserByLogin(username);
			if(usuario.isEmpty()) {
				return new ResponseEntity<Object>("No existe usuario con el documento "+username, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				DTOUsuario dtoUser = _map.UsuarioToDTO(usuario.get());
				return new ResponseEntity<Object>(dtoUser,HttpStatus.OK);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//GET BY DOCUMENTO
	@GetMapping("/usuario/{documento}")
	public ResponseEntity<Object> userUseruSER(@PathVariable(value = "documento") String documento) throws JsonParseException, JsonMappingException, IOException {
		//String id = data.get("user").get("documento").asText();
		//String status = data.get("user").get("status").asText();
		try {
			Optional<PersonaUsuario> usuario = _usuario.UserById(documento);
			if(usuario.isEmpty()) {
				return new ResponseEntity<Object>("No existe usuario con el documento "+documento, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				DTOUsuario dtoUser = _map.UsuarioToDTO(usuario.get());
				return new ResponseEntity<Object>(dtoUser,HttpStatus.OK);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/usuario/{username}/{password}")
	public ResponseEntity<Object> logim(@PathVariable(value = "username") String username,@PathVariable(value = "password") String password) throws JsonParseException, JsonMappingException, IOException {
		try {
			Optional<PersonaUsuario> usuario = _usuario.UserByCredenciales(username, password);
			if(usuario.isEmpty()) {
				return new ResponseEntity<Object>("El usuario o el password estan incorrectos", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				DTOUsuario dtoUser = _map.UsuarioToDTO(usuario.get());
				return new ResponseEntity<Object>(dtoUser,HttpStatus.OK);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * 
	 * 
	@GetMapping("/usuario/contacto/{documento}")
	public ResponseEntity<Object> userContacto(@PathVariable(value = "documento") String documento) throws JsonParseException, JsonMappingException, IOException {
		//String id = data.get("user").get("documento").asText();
		//String status = data.get("user").get("status").asText();
		try {
			Optional<ContactoUsuario> usuario = _contactoUsuario.Entity(documento);
			if(usuario.isEmpty()) {
				return new ResponseEntity<Object>("No existe usuario con el documento "+documento, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				
				//CREO UN JSON Y LE AGREGO NODOS				
				//JsonNode dtoUser = obj.createObjectNode();
				//((ObjectNode) dtoUser).putPOJO("User", usuario);
				//((ObjectNode) dtoUser).putPOJO("UserDTO", _map.UsuarioToDTO(usuario.get()));
				//DTOUsuario dtoUser = _map.UsuarioToDTO(usuario.get());
				return new ResponseEntity<Object>(usuario,HttpStatus.OK);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*
	*
	*
	*/

	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@PostMapping("/usuario")
	public ResponseEntity<Object> saveUsuario(@Valid @RequestBody PersonaUsuario data){
		try {
			if (_usuario.isUserActiveId(data.getDocumento())){
				return new ResponseEntity<Object>("No se puede poner dos usuarios con el mismo id: "+data.getDocumento(), HttpStatus.NOT_FOUND);
			}
			PersonaUsuario nuevoUsuario = new PersonaUsuario();
			nuevoUsuario = _usuario.Save(data);
			return new ResponseEntity<Object>(nuevoUsuario, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	//How is the best way to reactivate a user?
	@PutMapping("/usuario/estado")
	public ResponseEntity<Object> reactivateUser(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException {
		try {
			if (_usuario.isUserActiveId(data.get("documento_usuario").asText())) {
				if (data.get("flag").asText().contentEquals("reactivar")) {
					try {
						_usuario.ReactivateUser(data.get("documento_usuario").asText());// ACCION
						return new ResponseEntity<Object>("Usuario con documento: "+data.get("documento_usuario").asText()+" activado correctamente",HttpStatus.OK);
						//IF USUARIO HA SIDO REACTIVADO
					} catch (Exception e) {
						// TODO: handle exception
						//ERROR
						return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else if (data.get("flag").asText().contentEquals("desactivar"))
				{ 
					_usuario.DesactivateUser(data.get("documento_usuario").asText()); // ACCION
					return new ResponseEntity<Object>("Usuario con documento: "+data.get("documento_usuario").asText()+" desactivado correctamente",HttpStatus.OK);
					//IF USUARIO HA SIDO DESACTIVADO
				} else {
					//ELSE COMANDO EQUIVADO
					return new ResponseEntity<Object>("Solo dispone de las opciones 'desactivar' y 'reactivar'",HttpStatus.BAD_REQUEST);
				}
			}
			else {
				return new ResponseEntity<Object>("No existe usuario con el documento: "+data.get("documento_usuario").asText(),HttpStatus.BAD_REQUEST);
				//ELSE NO EXISTE USUARIO
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
		
	
	@DeleteMapping("/usuario/{login}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "login") String login) throws JsonParseException, JsonMappingException, IOException {
		String id = login;
		try {
			PersonaUsuario user = _usuario.UserByLogin(login).get();
			if (user == null){
				return new ResponseEntity<Object>("No existe el usuario con "+id, HttpStatus.NOT_FOUND);
			} else
			{
				_usuario.Delete(user.getDocumento());
			}
			return new ResponseEntity<Object>(_map.UsuarioToDTO(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	
}