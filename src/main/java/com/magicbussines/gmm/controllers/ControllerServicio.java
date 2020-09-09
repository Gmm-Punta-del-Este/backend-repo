package com.magicbussines.gmm.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicbussines.gmm.common.Helpers;
import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.interfaces.IConsumo;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.interfaces.IServicioApartamento;
import com.magicbussines.gmm.interfaces.IServicioInquilino;
import com.magicbussines.gmm.interfaces.IServicioPropietario;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.ServicioApartamento;
import com.magicbussines.gmm.model.ServicioInquilino;
import com.magicbussines.gmm.model.ServicioPropietario;
@CrossOrigin
@RestController
@RequestMapping("/servicio")
public class ControllerServicio {
	@Autowired
	private IConsumo _consu;
	@Autowired
	private IApto _apto;
	@Autowired
	private IServicioApartamento _serapto;
	@Autowired
	private IServicioInquilino _serinqui;
	@Autowired
	private IServicioPropietario _serprop;
	@Autowired
	private IPersonaPropietario _prop;
	@Autowired
	private IPersonaInquilino _inqui;
	@Autowired
	private IPersonaUsuario _usuario;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private Helpers helper;
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	// ======================================== SERVICIO_PROPIETARIO
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/propietario/listar")
	public ResponseEntity<Object> listarConsumos(){
		List<ServicioPropietario> consumitos = (List<ServicioPropietario>) _serprop.List();
		return new ResponseEntity<Object>(consumitos,HttpStatus.OK);		
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

//	@GetMapping("/apto/{apto}")
//	public ResponseEntity<Object> listarByApto(@PathVariable(name = "apto") int apto){
//		
//		try {
//			List<Consumo> consumitos = (List<Consumo>) _consu.listaConsumosByApto(apto);
//			return new ResponseEntity<Object>(consumitos, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Object>("El numero de apartamento que ingreso es incorrecto o no esta registrado en el sistema, Numero Apto: "+apto, HttpStatus.NOT_FOUND);
//		}
//	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

		// ACA VA EL QUE LLEVA FILTO DE TIEMPO
		@GetMapping("/propietario/fecha")
		 public ResponseEntity<Object> processDate
		 		(@RequestParam(value = "inicio", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime inicio,
	 		   @RequestParam(value = "fin", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fin,
	 		   @RequestParam(value = "doc", required = false)int doc) throws JsonParseException, JsonMappingException, IOException {
			 
				try {
					List<ServicioPropietario> consumitos = (List<ServicioPropietario>) _serprop.List();
					
					List<ServicioPropietario> listaRetorno = new ArrayList<ServicioPropietario>();
					
					for (ServicioPropietario consumoDato : consumitos) {
						if(helper.checkRange(consumoDato.getHecho(), inicio, fin)) {
							listaRetorno.add(consumoDato);
						}
					}
					return new ResponseEntity<Object>(listaRetorno,HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<Object>("El documento ingresado : "+doc+" no se encuentra en el sistema o esta ingresado erroneamente", HttpStatus.NOT_FOUND);
				}
					 
		 }
		
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	@PostMapping("/propietario/")
	public ResponseEntity<Object> addconsumo(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
		
		try {
			
			String login = data.get("user").get("login").asText();
			String password = data.get("user").get("password").asText();
			
			if (_usuario.isUserActiveCredenciales(login, password)) {
				ServicioPropietario newServicio = new ServicioPropietario();
				newServicio = objectMapper.readValue(data.get("servicio_propietario").toString(),ServicioPropietario.class);
				PersonaPropietario prop = _prop.Entity(data.get("propietario").asText()).get();
				try {
					newServicio.setPropietario(prop);
				} catch (Exception e) {
					return new ResponseEntity<Object>("El documento o el servicio han sido agregados INCORRECTAMENTE",HttpStatus.NOT_FOUND);
				}
			
				newServicio  = _serprop.Save(newServicio);
				return new ResponseEntity<Object>(newServicio, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<Object>("El usuario con el cual desea ingresar un nuevo esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ***********************************************************************************************************************
			// ***********************************************************************************************************************
			@PutMapping("/propietario/hecho")
			public ResponseEntity<Object> hecho3(@RequestParam(value = "fecha", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fecha,
					@RequestParam(value = "id_servicio", required = false)int id) throws JsonParseException, JsonMappingException, IOException {
				
				try {
					if(_serprop.existe(id)) {
						ServicioPropietario servicio = _serprop.Entity(id).get();
						servicio.setHecho(fecha);
						servicio = _serprop.Save(servicio);
						return new ResponseEntity<Object>(servicio,HttpStatus.OK);
					}else {
						return new ResponseEntity<Object>("El servicio ingresado no existe, pruebe nuevamente con otro.",HttpStatus.NOT_FOUND);
					}
				} catch (Exception e) {
					return new ResponseEntity<Object>("Alguno de los datos ingresados fueron erroneos",HttpStatus.BAD_REQUEST);
				}
			}
	
	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	@DeleteMapping("/propietario/{id}")
	public ResponseEntity<Object> deleteNota(@PathVariable(value = "id") Integer id){
		try {
			ServicioPropietario consum = _serprop.Entity(id).get();
			if (consum == null){
				return new ResponseEntity<Object>("Servicio_prop con el id:  "+id, HttpStatus.NOT_FOUND);
			}
			_consu.Delete(id);
			return new ResponseEntity<Object>(consum, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************
		// ======================================== SERVICIO_INQUILINO
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************
		
		@GetMapping("/inquilino/listar")
		public ResponseEntity<Object> listarConsumos2(){
			List<ServicioInquilino> consumitos = (List<ServicioInquilino>) _serinqui.List();
			return new ResponseEntity<Object>(consumitos,HttpStatus.OK);		
		}
		
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************

//		@GetMapping("/apto/{apto}")
//		public ResponseEntity<Object> listarByApto(@PathVariable(name = "apto") int apto){
//			
//			try {
//				List<Consumo> consumitos = (List<Consumo>) _consu.listaConsumosByApto(apto);
//				return new ResponseEntity<Object>(consumitos, HttpStatus.OK);
//			} catch (Exception e) {
//				return new ResponseEntity<Object>("El numero de apartamento que ingreso es incorrecto o no esta registrado en el sistema, Numero Apto: "+apto, HttpStatus.NOT_FOUND);
//			}
//		}
		
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************

			// ACA VA EL QUE LLEVA FILTO DE TIEMPO
			@GetMapping("/inquilino/fecha")
			 public ResponseEntity<Object> processDate2
			 		(@RequestParam(value = "inicio", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime inicio,
		 		   @RequestParam(value = "fin", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fin,
		 		   @RequestParam(value = "doc", required = false)int doc) throws JsonParseException, JsonMappingException, IOException {
				 
					try {
						List<ServicioInquilino> consumitos = (List<ServicioInquilino>) _serinqui.List();
						
						List<ServicioInquilino> listaRetorno = new ArrayList<ServicioInquilino>();
						
						for (ServicioInquilino consumoDato : consumitos) {
							if(helper.checkRange(consumoDato.getHecho(), inicio, fin)) {
								listaRetorno.add(consumoDato);
							}
						}
						return new ResponseEntity<Object>(listaRetorno,HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<Object>("El documento ingresado : "+doc+" no se encuentra en el sistema o esta ingresado erroneamente", HttpStatus.NOT_FOUND);
					}
						 
			 }
			
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************

		
		@PostMapping("/inquilino/")
		public ResponseEntity<Object> addconsumo2(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
			
			try {
				
				String login = data.get("user").get("login").asText();
				String password = data.get("user").get("password").asText();
				
				if (_usuario.isUserActiveCredenciales(login, password)) {
					ServicioInquilino newServicio = new ServicioInquilino();
					newServicio = objectMapper.readValue(data.get("servicio_inquilino").toString(),ServicioInquilino.class);
					PersonaInquilino inqui = _inqui.Entity(data.get("inquilino").asText()).get();
					try {
						newServicio.setInquilino(inqui);
					} catch (Exception e) {
						return new ResponseEntity<Object>("El documento o el servicio han sido agregados INCORRECTAMENTE",HttpStatus.NOT_FOUND);
					}
				
					newServicio  = _serinqui.Save(newServicio);
					return new ResponseEntity<Object>(newServicio, HttpStatus.OK);
				} else
				{
					return new ResponseEntity<Object>("El usuario con el cual desea ingresar un nuevo esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
				}
			} catch(Exception e) {
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************
		@PutMapping("/inquilino/hecho")
		public ResponseEntity<Object> hecho2(@RequestParam(value = "fecha", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fecha,
				@RequestParam(value = "id_servicio", required = false)int id) throws JsonParseException, JsonMappingException, IOException {
			
			try {
				if(_serinqui.existe(id)) {
					ServicioInquilino servicio = _serinqui.Entity(id).get();
					servicio.setHecho(fecha);
					servicio = _serinqui.Save(servicio);
					return new ResponseEntity<Object>(servicio,HttpStatus.OK);
				}else {
					return new ResponseEntity<Object>("El servicio ingresado no existe, pruebe nuevamente con otro.",HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				return new ResponseEntity<Object>("Alguno de los datos ingresados fueron erroneos",HttpStatus.BAD_REQUEST);
			}
		}
		
		
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************

		
		@DeleteMapping("/inquilino/{id}")
		public ResponseEntity<Object> deleteNota2(@PathVariable(value = "id") Integer id){
			try {
				ServicioInquilino consum = _serinqui.Entity(id).get();
				if (consum == null){
					return new ResponseEntity<Object>("Servicio_inqui con el id:  "+id, HttpStatus.NOT_FOUND);
				}
				_consu.Delete(id);
				return new ResponseEntity<Object>(consum, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************
				// ======================================== SERVICIO_APARTAMENTO
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************
				
				@GetMapping("/apartamento/listar")
				public ResponseEntity<Object> listarConsumos3(){
					List<ServicioApartamento> consumitos = (List<ServicioApartamento>) _serapto.List();
					return new ResponseEntity<Object>(consumitos,HttpStatus.OK);		
				}
				
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************

//				@GetMapping("/apto/{apto}")
//				public ResponseEntity<Object> listarByApto(@PathVariable(name = "apto") int apto){
//					
//					try {
//						List<Consumo> consumitos = (List<Consumo>) _consu.listaConsumosByApto(apto);
//						return new ResponseEntity<Object>(consumitos, HttpStatus.OK);
//					} catch (Exception e) {
//						return new ResponseEntity<Object>("El numero de apartamento que ingreso es incorrecto o no esta registrado en el sistema, Numero Apto: "+apto, HttpStatus.NOT_FOUND);
//					}
//				}
				
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************

					// ACA VA EL QUE LLEVA FILTO DE TIEMPO
					@GetMapping("/apartamento/fecha")
					 public ResponseEntity<Object> proc2essDate2
					 		(@RequestParam(value = "inicio", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime inicio,
				 		   @RequestParam(value = "fin", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fin,
				 		   @RequestParam(value = "doc", required = false)int doc) throws JsonParseException, JsonMappingException, IOException {
						 
							try {
								List<ServicioApartamento> consumitos = (List<ServicioApartamento>) _serapto.List();
								
								List<ServicioApartamento> listaRetorno = new ArrayList<ServicioApartamento>();
								
								for (ServicioApartamento consumoDato : consumitos) {
									if(helper.checkRange(consumoDato.getHecho(), inicio, fin)) {
										listaRetorno.add(consumoDato);
									}
								}
								return new ResponseEntity<Object>(listaRetorno,HttpStatus.OK);
							} catch (Exception e) {
								return new ResponseEntity<Object>("El documento ingresado : "+doc+" no se encuentra en el sistema o esta ingresado erroneamente", HttpStatus.NOT_FOUND);
							}
								 
					 }
					
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************

				
				@PostMapping("/apartamento/")
				public ResponseEntity<Object> addconsumo3(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
					
					try {
						
						String login = data.get("user").get("login").asText();
						String password = data.get("user").get("password").asText();
						
						if (_usuario.isUserActiveCredenciales(login, password)) {
							ServicioApartamento newServicio = new ServicioApartamento();
							newServicio = objectMapper.readValue(data.get("servicio_apartamento").toString(),ServicioApartamento.class);
							Apartamento apto = _apto.Entity(data.get("apto").asInt()).get();
							try {
								newServicio.setApto(apto);
							} catch (Exception e) {
								return new ResponseEntity<Object>("El documento o el servicio han sido agregados INCORRECTAMENTE",HttpStatus.NOT_FOUND);
							}
						
							newServicio  = _serapto.Save(newServicio);
							return new ResponseEntity<Object>(newServicio, HttpStatus.OK);
						} else
						{
							return new ResponseEntity<Object>("El usuario con el cual desea ingresar un nuevo esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
						}
					} catch(Exception e) {
						return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
				
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************
				@PutMapping("/apartamento/hecho")
				public ResponseEntity<Object> hecho(@RequestParam(value = "fecha", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fecha,
						@RequestParam(value = "id_servicio", required = false)int id) throws JsonParseException, JsonMappingException, IOException {
					
					try {
						if(_serapto.existe(id)) {
							ServicioApartamento servicio = _serapto.Entity(id).get();
							servicio.setHecho(fecha);
							servicio = _serapto.Save(servicio);
							return new ResponseEntity<Object>(servicio,HttpStatus.OK);
						}else {
							return new ResponseEntity<Object>("El servicio ingresado no existe, pruebe nuevamente con otro.",HttpStatus.NOT_FOUND);
						}
					} catch (Exception e) {
						return new ResponseEntity<Object>("Alguno de los datos ingresados fueron erroneos",HttpStatus.BAD_REQUEST);
					}
				}
				// ***********************************************************************************************************************
				// ***********************************************************************************************************************

				
				@DeleteMapping("/apartamento/{id}")
				public ResponseEntity<Object> deleteNota22(@PathVariable(value = "id") Integer id){
					try {
						ServicioApartamento consum = _serapto.Entity(id).get();
						if (consum == null){
							return new ResponseEntity<Object>("Servicio_inqui con el id:  "+id, HttpStatus.NOT_FOUND);
						}
						_consu.Delete(id);
						return new ResponseEntity<Object>(consum, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
				}		
	
}
