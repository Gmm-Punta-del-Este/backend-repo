package com.magicbussines.gmm.controllers;

import java.io.IOException;

import org.aspectj.weaver.patterns.PerClause;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.magicbussines.gmm.interfaces.IContactoInquilino;
import com.magicbussines.gmm.interfaces.IContactoPropietario;
import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.ContactoInquilino;
import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;
@CrossOrigin
@RestController
@RequestMapping("/populate")
public class PopulateController {
	
	@Autowired
	private IPersonaPropietario _propietario;
	@Autowired
	private IContactoPropietario _con_propietario;
	@Autowired
	private IPersonaInquilino _inqulino;
	@Autowired
	private IContactoInquilino _con_inqulino;
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
				
				String resultado = "";
				String fileName = "populateBD";
				String fieldName = "";
				//CAMBIAR ESTO , IR A LA VENTANA DE WINDOWS, CTRL+C, CTRL+V. :3 (Automaticamente se pega con las barras bien, tranca)
				String path = "F:\\Proyectos JAVA\\GMM RN\\TesteoFake";
				JSONArray arryCargado = new JSONArray();
				// ------------------------------------------------
				// ----------------------- PERSONA_USUARIOS
				// ------------------------------------------------
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== USUARIOS");

				fieldName = "usuarios";
				arryCargado = _help.populateTable(path,fileName,fieldName);
				System.out.println("Cantidad de USUARIOS para agregar: "+arryCargado.size());
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
				resultado = resultado + "PERSONA USUARIOS: DONE\n";
				
				
				// ------------------------------------------------
				// ----------------------- PERSONA_USUARIOS CONTACTO
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== CONTACTO USUARIOS");
				fieldName = "contactoUsuarios";
				arryCargado = _help.populateTable(path,fileName,fieldName);	
				System.out.println("Cantidad de contactos para agregar: "+arryCargado.size());
				for ( Object object : arryCargado) {
					
					ContactoUsuario con = obj.readValue(object.toString(), ContactoUsuario.class); 
					
					JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
					String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
					String nomUsu = jsonObject.get("nombre").toString();
					String apeUsu = jsonObject.get("nombre").toString();
					if (_con_usu.existeContacto(nroUsu)) {
						System.out.println("ERROR con el Contacto para Usuario: "+nomUsu+" "+apeUsu+" ya EXISTE");
					}else {
						PersonaUsuario persu = _usuario.UserById(nroUsu).get();
						con.setUsuario(persu);
						con = _con_usu.Save2(con);
						
						System.out.println("El contacto_usuario: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");

					}
					
				}
				resultado = resultado + "CONTACTOS USUARIOS: DONE\n";
				
				// ------------------------------------------------
				// ----------------------- PERSONA_INQUILINO
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== PERSONA INQUILINO");

			
				fieldName = "inquilinos";
				arryCargado = _help.populateTable(path,fileName,fieldName);
				System.out.println("Cantidad de INQUILINOS para agregar: "+arryCargado.size());
				for ( Object object : arryCargado) {
					PersonaInquilino usu = obj.readValue(object.toString(), PersonaInquilino.class);  
					if (_inqulino.Entity(usu.getDocumento()).isPresent()){
						System.out.println("ERROR con el INQUILNO: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
					}else {
						PersonaInquilino nuevoUsuario = new PersonaInquilino();
						nuevoUsuario = _inqulino.Save(usu);
						System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
					}
				}
				
				resultado = resultado + "PERSONA INQUILINOS: DONE\n";
				
				// ------------------------------------------------
				// ----------------------- PERSONA_INQUILINO CONTACTO
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");	
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== CONTACTO INQUILINOS");
				fieldName = "contactoInquilinos";
				arryCargado = _help.populateTable(path,fileName,fieldName);	
				System.out.println("Cantidad de contactos para agregar: "+arryCargado.size());
				for ( Object object : arryCargado) {
					ContactoInquilino con = obj.readValue(object.toString(), ContactoInquilino.class); 
					JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
					String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
					String nomUsu = jsonObject.get("nombre").toString();
					String apeUsu = jsonObject.get("apellido").toString();
					if (_con_inqulino.existeContacto(nroUsu)) {
						System.out.println("ERROR con el Contacto para Inquilino: "+nomUsu+" "+apeUsu+" ya EXISTE");
					}else {
						PersonaInquilino inqui = _inqulino.Entity(nroUsu).get();
						con.setUsuario(inqui);
						con = _con_inqulino.Save2(con);
						System.out.println("El contacto_inquilino: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");
					}
				}
				
				// ------------------------------------------------
				// ----------------------- PERSONA_PROPIETARIO
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== PERSONA PROPIETARIO");

			
				fieldName = "propietarios";
				arryCargado = _help.populateTable(path,fileName,fieldName);
				System.out.println("Cantidad de PROPIETARIOS para agregar: "+arryCargado.size());
				for ( Object object : arryCargado) {
					
					PersonaPropietario usu = obj.readValue(object.toString(), PersonaPropietario.class); 
					if (_propietario.Entity(usu.getDocumento()).isPresent()){
						System.out.println("ERROR con el PROPIETARIO: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
					}else {
						PersonaPropietario nuevoUsuario = new PersonaPropietario();
						nuevoUsuario = _propietario.savePropietario(usu);
						System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
					}
				}
				
				resultado = resultado + "PERSONA PROPIETARIOS: DONE\n";
				
				// ------------------------------------------------
				// ----------------------- PERSONA_INQUILINO CONTACTO
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");	
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== CONTACTO PROPIETARIOS");
				fieldName = "contactoPropietarios";
				arryCargado = _help.populateTable(path,fileName,fieldName);	
				System.out.println("Cantidad de contactos para agregar: "+arryCargado.size());
				for ( Object object : arryCargado) {
					ContactoPropietario con = obj.readValue(object.toString(), ContactoPropietario.class); 
					JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
					String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
					String nomUsu = jsonObject.get("nombre").toString();
					String apeUsu = jsonObject.get("apellido").toString();
					if (_con_propietario.existeContacto(nroUsu)) {
						System.out.println("ERROR con el Contacto para Inquilino: "+nomUsu+" "+apeUsu+" ya EXISTE");
					}else {
						PersonaPropietario inqui = _propietario.Entity(nroUsu).get();
						con.setUsuario(inqui);
						con = _con_propietario.Save2(con);
						System.out.println("El contacto_propietario: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");
					}
				}

				
				resultado = resultado + "CONTACTOS PROPIETARIOS: DONE\n";
				System.out.println();
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-= PROGRAMA DE POPULATE, FINALIZADO. PAGO 2, COUNTERSPELL, IZI PA =-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				return new ResponseEntity<Object>(resultado,HttpStatus.CREATED);
			} catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<Object>(e,HttpStatus.CONFLICT);
			}
			
		}
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------------------
			@GetMapping("/stress")
			public ResponseEntity<Object> testeo1() throws JsonParseException, JsonMappingException, IOException {
				//variables
				
				try {
					
					String resultado = "";
					String fileName = "populateBD2";
					String fieldName = "";
					//CAMBIAR ESTO , IR A LA VENTANA DE WINDOWS, CTRL+C, CTRL+V. :3 (Automaticamente se pega con las barras bien, tranca)
					String path = "F:\\Proyectos JAVA\\GMM RN\\TesteoFake";
					JSONArray arryCargado = new JSONArray();
					// ------------------------------------------------
					// ----------------------- PERSONA_USUARIOS
					// ------------------------------------------------
					System.out.println("=================== CARGA");
					System.out.println("=================== DE");			
					System.out.println("=================== USUARIOS");

					fieldName = "usuarios";
					arryCargado = _help.populateTable(path,fileName,fieldName);
					System.out.println("Cantidad de USUARIOS para agregar: "+arryCargado.size());
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
					resultado = resultado + "PERSONA USUARIOS: DONE\n";
					
					
					// ------------------------------------------------
					// ----------------------- PERSONA_USUARIOS CONTACTO
					// ------------------------------------------------
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("=================== CARGA");
					System.out.println("=================== DE");			
					System.out.println("=================== CONTACTO USUARIOS");
					fieldName = "contactoUsuarios";
					arryCargado = _help.populateTable(path,fileName,fieldName);	
					System.out.println("Cantidad de contactos para agregar: "+arryCargado.size());
					for ( Object object : arryCargado) {
						
						ContactoUsuario con = obj.readValue(object.toString(), ContactoUsuario.class); 
						
						JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
						String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
						String nomUsu = jsonObject.get("nombre").toString();
						String apeUsu = jsonObject.get("nombre").toString();
						if (_con_usu.existeContacto(nroUsu)) {
							System.out.println("ERROR con el Contacto para Usuario: "+nomUsu+" "+apeUsu+" ya EXISTE");
						}else {
							PersonaUsuario persu = _usuario.UserById(nroUsu).get();
							con.setUsuario(persu);
							con = _con_usu.Save2(con);
							
							System.out.println("El contacto_usuario: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");

						}
						
					}
					resultado = resultado + "CONTACTOS USUARIOS: DONE\n";
					
					// ------------------------------------------------
					// ----------------------- PERSONA_INQUILINO
					// ------------------------------------------------
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("=================== CARGA");
					System.out.println("=================== DE");			
					System.out.println("=================== PERSONA INQUILINO");

				
					fieldName = "inquilinos";
					arryCargado = _help.populateTable(path,fileName,fieldName);
					System.out.println("Cantidad de INQUILINOS para agregar: "+arryCargado.size());
					for ( Object object : arryCargado) {
						PersonaInquilino usu = obj.readValue(object.toString(), PersonaInquilino.class);  
						if (_inqulino.Entity(usu.getDocumento()).isPresent()){
							System.out.println("ERROR con el INQUILNO: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
						}else {
							PersonaInquilino nuevoUsuario = new PersonaInquilino();
							nuevoUsuario = _inqulino.Save(usu);
							System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
						}
					}
					
					resultado = resultado + "PERSONA INQUILINOS: DONE\n";
					
					// ------------------------------------------------
					// ----------------------- PERSONA_INQUILINO CONTACTO
					// ------------------------------------------------
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");	
					System.out.println("=================== CARGA");
					System.out.println("=================== DE");			
					System.out.println("=================== CONTACTO INQUILINOS");
					fieldName = "contactoInquilinos";
					arryCargado = _help.populateTable(path,fileName,fieldName);	
					System.out.println("Cantidad de contactos para agregar: "+arryCargado.size());
					for ( Object object : arryCargado) {
						ContactoInquilino con = obj.readValue(object.toString(), ContactoInquilino.class); 
						JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
						String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
						String nomUsu = jsonObject.get("nombre").toString();
						String apeUsu = jsonObject.get("apellido").toString();
						if (_con_inqulino.existeContacto(nroUsu)) {
							System.out.println("ERROR con el Contacto para Inquilino: "+nomUsu+" "+apeUsu+" ya EXISTE");
						}else {
							PersonaInquilino inqui = _inqulino.Entity(nroUsu).get();
							con.setUsuario(inqui);
							con = _con_inqulino.Save2(con);
							System.out.println("El contacto_inquilino: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");
						}
					}
					
					// ------------------------------------------------
					// ----------------------- PERSONA_PROPIETARIO
					// ------------------------------------------------
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("=================== CARGA");
					System.out.println("=================== DE");			
					System.out.println("=================== PERSONA PROPIETARIO");

				
					fieldName = "propietarios";
					arryCargado = _help.populateTable(path,fileName,fieldName);
					System.out.println("Cantidad de PROPIETARIOS para agregar: "+arryCargado.size());
					for ( Object object : arryCargado) {
						
						PersonaPropietario usu = obj.readValue(object.toString(), PersonaPropietario.class); 
						if (_propietario.Entity(usu.getDocumento()).isPresent()){
							System.out.println("ERROR con el PROPIETARIO: "+usu.getNombre()+" "+usu.getApellido1()+" ya EXISTE");
						}else {
							PersonaPropietario nuevoUsuario = new PersonaPropietario();
							nuevoUsuario = _propietario.savePropietario(usu);
							System.out.println("El usuario: "+nuevoUsuario.getNombre()+" "+nuevoUsuario.getApellido1()+" ha sigo agregado.");
						}
					}
					
					resultado = resultado + "PERSONA PROPIETARIOS: DONE\n";
					
					// ------------------------------------------------
					// ----------------------- PERSONA_INQUILINO CONTACTO
					// ------------------------------------------------
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");
					System.out.println("+++++++++++++++++++++++++++++++++++++ ");	
					System.out.println("=================== CARGA");
					System.out.println("=================== DE");			
					System.out.println("=================== CONTACTO PROPIETARIOS");
					fieldName = "contactoPropietarios";
					arryCargado = _help.populateTable(path,fileName,fieldName);	
					System.out.println("Cantidad de contactos para agregar: "+arryCargado.size());
					for ( Object object : arryCargado) {
						ContactoPropietario con = obj.readValue(object.toString(), ContactoPropietario.class); 
						JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
						String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
						String nomUsu = jsonObject.get("nombre").toString();
						String apeUsu = jsonObject.get("apellido").toString();
						if (_con_propietario.existeContacto(nroUsu)) {
							System.out.println("ERROR con el Contacto para Inquilino: "+nomUsu+" "+apeUsu+" ya EXISTE");
						}else {
							PersonaPropietario inqui = _propietario.Entity(nroUsu).get();
							con.setUsuario(inqui);
							con = _con_propietario.Save2(con);
							System.out.println("El contacto_propietario: "+con.getNombre()+" "+con.getApellido()+" ha sigo agregado.");
						}
					}

					
					resultado = resultado + "CONTACTOS PROPIETARIOS: DONE\n";
					System.out.println();
					System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
					System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
					System.out.println("=-=-= PROGRAMA DE POPULATE, FINALIZADO. PAGO 2, COUNTERSPELL, IZI PA =-=-=");
					System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
					System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
					return new ResponseEntity<Object>(resultado,HttpStatus.CREATED);
				} catch (Exception e) {
					// TODO: handle exception
					return new ResponseEntity<Object>(e,HttpStatus.CONFLICT);
				}
				
			}
}
