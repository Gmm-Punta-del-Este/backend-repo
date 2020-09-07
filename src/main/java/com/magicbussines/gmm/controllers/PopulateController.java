package com.magicbussines.gmm.controllers;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicbussines.gmm.common.Helpers;
import com.magicbussines.gmm.common.MapperPersona;
import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaUsuario;
@CrossOrigin
@RestController
@RequestMapping("/populate")
public class PopulateController {
	
	@Autowired
	private IPersonaPropietario _propietario;
	@Autowired
	private IPersonaInquilino _inqulino;
	@Autowired
	private IPersonaUsuario _usuario;
	@Autowired
	private IContactoUsuario _con_usu;
	//@Autowired
	//private IContactoUsuario _contactoUsuario;
	@Autowired
	private ObjectMapper obj;
	@Autowired
	private MapperPersona _map;
	@Autowired
	private Helpers _help;
	
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
		@GetMapping("/")
		public ResponseEntity<Object> testeo() throws JsonParseException, JsonMappingException, IOException {
			//variables
			try {
				String nameEntity = "";
				String fieldName = "";
				String path = "F:\\Proyectos JAVA\\GMM RN\\TesteoFake";
				JSONArray arryCargado = new JSONArray();
				
				// ----------------------- USUARIOS  
				//data.get("users");			
				nameEntity = "users";
				fieldName = "users";
				arryCargado = _help.populateTable(path,nameEntity,fieldName);
				for ( Object object : arryCargado) {
					PersonaUsuario usu = obj.readValue(object.toString(), PersonaUsuario.class);  
					if (_usuario.isUserActiveId(usu.getDocumento())){
						System.out.println("ERROR con el usuario: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
					}else {
						PersonaUsuario nuevoUsuario = new PersonaUsuario();
						nuevoUsuario = _usuario.Save(usu);
						System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
					}
				}
				// ----------------------- USUARIOS CONTACTO
				nameEntity = "users";
				fieldName = "contacto";
				arryCargado = _help.populateTable(path,nameEntity,fieldName);	
				for ( Object object : arryCargado) {
					System.out.println(object);
					ContactoUsuario con = obj.readValue(object.toString(), ContactoUsuario.class); 
					con = _con_usu.Save(con);
					System.out.println("El contacto_usuario: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");

				}
				
				return new ResponseEntity<Object>("UsuarioPopulate OK",HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<Object>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	
	@PostMapping("/usuarios")
	public ResponseEntity<Object> saveUsuarioMuchos(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException {
		JsonNode data1 = data.get(0).get("users");
		try {
			for (JsonNode datito : data1 ) {
				System.out.println(datito);
				PersonaUsuario usu = obj.readValue(datito.toString(), PersonaUsuario.class);				
				if (_usuario.isUserActiveId(usu.getDocumento())){
					System.out.println("ERROR con el usuario: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
				}else {
					PersonaUsuario nuevoUsuario = new PersonaUsuario();
					nuevoUsuario = _usuario.Save(usu);
					System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
				}
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			
			return new ResponseEntity<Object>("UsuarioPopulate OK",HttpStatus.CREATED);
			
		} catch(Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	
	
	@PostMapping("/inquilinos")
	public ResponseEntity<Object> saveInquilinoMuchos(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException {
		JsonNode data1 = data.get(0).get("users");
		try {
			for (JsonNode datito : data1 ) {
				System.out.println(datito);
				PersonaInquilino usu = obj.readValue(datito.toString(), PersonaInquilino.class);				
				if (_usuario.isUserActiveId(usu.getDocumento())){
					System.out.println("ERROR con el usuario: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
				}else {
					PersonaInquilino nuevoUsuario = new PersonaInquilino();
					nuevoUsuario = _inqulino.Save(usu);
					System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
				}
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			
			return new ResponseEntity<Object>("UsuarioPopulate OK",HttpStatus.CREATED);
			
		} catch(Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
