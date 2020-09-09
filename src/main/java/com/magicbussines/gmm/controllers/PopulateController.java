package com.magicbussines.gmm.controllers;

import java.io.IOException;
import java.util.Map;

import org.aspectj.weaver.patterns.PerClause;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.magicbussines.gmm.common.Helpers;
import com.magicbussines.gmm.common.MapperPersona;
import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.interfaces.IConsumo;
import com.magicbussines.gmm.interfaces.IContactoInquilino;
import com.magicbussines.gmm.interfaces.IContactoPropietario;
import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.interfaces.INotas;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.model.Consumo;
import com.magicbussines.gmm.model.ContactoInquilino;
import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;

import com.magicbussines.gmm.controllers.*;
@CrossOrigin
@Controller
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
	@Autowired
	private INotas _nota;
	@Autowired IConsumo _consumo;
	//@Autowired
	//private IContactoUsuario _contactoUsuario;
	@Autowired
	private ObjectMapper obj;
	@Autowired
	private MapperPersona _map;
	@Autowired
	private Helpers _help;
	@Autowired
	private IApto _apto;
	
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------

		@RequestMapping("/")
	    @ResponseBody
		public ModelAndView testeo() throws JsonParseException, JsonMappingException, IOException {
			//variables
							
				String resultado = "";
				String fileName = "populateBD";
				String fieldName = "";
				//CAMBIAR ESTO , IR A LA VENTANA DE WINDOWS, CTRL+C, CTRL+V. :3 (Automaticamente se pega con las barras bien, tranca)
				//System.out.println("Present Project Directory : "+ );
				
				String path = System.getProperty("user.dir")+"\\TesteoFake";
				System.out.println(path);
				JSONArray arryCargado = new JSONArray();
				JSONArray arryCargado2 = new JSONArray();
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
				// ------------------------------------------------
				// ----------------------- NOTAS
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== NOTAS");

			
				fieldName = "notas";
				arryCargado = _help.populateTable(path,fileName,fieldName);
				arryCargado2 = _help.populateTable(path, fileName, "usuarios");
				System.out.println("Cantidad de NOTAS para agregar: "+arryCargado.size());				
					
				//	System.out.println(notas);
					
					for (int i = 0; i < arryCargado2.size(); i++) {
						for (int j = 0; j < arryCargado.size(); j++) {
							//JsonNode rootNode = obj.createObjectNode().put(fieldName, v);
							
							Nota nuevaNota = new Nota();
							nuevaNota = obj.readValue(arryCargado.get(j).toString(),Nota.class);
							PersonaUsuario user = obj.readValue(arryCargado2.get(i).toString(), PersonaUsuario.class);  
							nuevaNota.setUsuario((_usuario.UserByCredenciales(user.getLogin(),user.getPassword()).get()));
							nuevaNota = _nota.Save(nuevaNota);
							System.out.println(">> Notas agregadas al usuario con USERNAME: "+user.getLogin()+"satisfactoriamente.");

						}
					}

				resultado = resultado + "NOTAS DEL SISTEMA: DONE\n";
				//-------------------------------------------------------------------------------------------------------
				//-------------------------------------------------------------------------------------------------------
				// ------------------------------------------------
				// ----------------------- NOTAS
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== APTOS");
				
				fieldName = "aptos";
				arryCargado = _help.populateTable(path,fileName,fieldName);
				System.out.println("Cantidad de APTOS para agregar: "+arryCargado.size());
				
				for ( Object object : arryCargado) {
					
					Apartamento con = obj.readValue(object.toString(), Apartamento.class);
					JSONObject jsonObject = (JSONObject) object; //PARSEO DE OBJETO CHOTO A JSON
					String nroUsu = jsonObject.get("documento").toString(); //con esto obtengo lo necesario
					PersonaPropietario prop =  _propietario.Entity(nroUsu).get();
					con.setPropietario(prop);
					_apto.Save(con);
					System.out.println(">> Ha sigo agregado apartamento NRO: "+con.getNroApto()+" al PROPIETARIE: "+prop.getNombre()+" "+prop.getApellido1());
					
				}
				System.out.println(">> LOS APARTAMENTO TIENEN {ID} CORRELATIVO 1 TO 100.");
				
				resultado = resultado + "APARTAMENTOS CON PROPIETARIO: DONE\n";
				//-------------------------------------------------------------------------------------------------------
				//-------------------------------------------------------------------------------------------------------
				// ------------------------------------------------
				// ----------------------- NOTAS
				// ------------------------------------------------
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("+++++++++++++++++++++++++++++++++++++ ");
				System.out.println("=================== CARGA");
				System.out.println("=================== DE");			
				System.out.println("=================== CONSUMOS");
				
				fieldName = "consumos";
				arryCargado = _help.populateTable(path,fileName,fieldName);
				System.out.println("Cantidad de CONSUMOS para agregar: "+arryCargado.size());
				int i = 1;
				for ( Object object : arryCargado) {
										
					Consumo con = new Consumo();
					con = obj.readValue(object.toString(), Consumo.class);
					Apartamento prop =  _apto.Entity(i).get();
					con.setNroApto(prop);
					_consumo.Save(con);
					i++;
					System.out.println(">> Ha sigo agregado CONSUMO para el Apartamento NRO: "+con.getNroApto().getNroApto());
					
				}
				
				resultado = resultado + "CONSUMO PARA APARTAMENTOS: DONE\n";
				
				resultado = resultado + "\n";
				resultado = resultado + "POPULATE TERMINADO";
				resultado = resultado + "<a class=\"myButtonIndex\" href=\"http://localhost:8080/\">BACK TO HOME</a>";
				System.out.println();
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-= PROGRAMA DE POPULATE, FINALIZADO. PAGO 2, COUNTERSPELL, IZI PA =-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				//return new ResponseEntity<Object>(resultado,HttpStatus.CREATED);
				ModelAndView modelAndView = new ModelAndView();
			    modelAndView.setViewName("index.html");
			    return modelAndView;
			
		}
}
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------------------
	