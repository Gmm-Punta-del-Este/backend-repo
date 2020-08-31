package com.magicbussines.gmm.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicbussines.gmm.common.Helpers;
import com.magicbussines.gmm.common.MapperNota;
import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.interfaces.IConsumo;
import com.magicbussines.gmm.interfaces.INotas;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.model.Consumo;

@RestController
@RequestMapping("/consumo")
public class ControllerConsumo {
	@Autowired
	private IConsumo _consu;
	@Autowired
	private IPersonaUsuario _usuario;
	@Autowired
	private IApto _apto;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private Helpers helper;
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	@GetMapping("/listar")
	public ResponseEntity<Object> listarConsumos(){
		List<Consumo> consumitos = (List<Consumo>) _consu.List();
		return new ResponseEntity<Object>(consumitos,HttpStatus.OK);		
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	@GetMapping("/apto/{apto}")
	public ResponseEntity<Object> listarByApto(@PathVariable(name = "apto") int apto){
		
		try {
			List<Consumo> consumitos = (List<Consumo>) _consu.listaConsumosByApto(apto);
			return new ResponseEntity<Object>(consumitos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("El numero de apartamento que ingreso es incorrecto o no esta registrado en el sistema, Numero Apto: "+apto, HttpStatus.NOT_FOUND);
		}
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

		// ACA VA EL QUE LLEVA FILTO DE TIEMPO
		@GetMapping("/fecha")
		 public ResponseEntity<Object> processDate
		 		(@RequestParam(value = "inicio", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime inicio,
	 		   @RequestParam(value = "fin", required = false)@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fin,
	 		   @RequestParam(value = "apto", required = false)int apto) throws JsonParseException, JsonMappingException, IOException {
			 
				try {
					List<Consumo> listConsumitos = (List<Consumo>) _consu.listaConsumosByApto(apto);
					
					List<Consumo> listaRetorno = new ArrayList<Consumo>();
					
					for (Consumo consumoDato : listConsumitos) {
						if(helper.checkRange(consumoDato.getFechaInicio(), inicio, fin) && helper.checkRange(consumoDato.getFechaFinal(), inicio, fin)) {
							listaRetorno.add(consumoDato);
						}
					}
					return new ResponseEntity<Object>(listaRetorno,HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<Object>("El numero de apartamento que ingreso es incorrecto o no esta registrado en el sistema, Numero Apto: "+apto+" o ingreso las fechas de manera incorrecta", HttpStatus.NOT_FOUND);
				}
					 
		 }
		
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	@PostMapping("/")
	public ResponseEntity<Object> addconsumo(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
		
		try {
			
			String login = data.get("user").get("login").asText();
			String password = data.get("user").get("password").asText();
			
			if (_usuario.isUserActiveCredenciales(login, password)) {
				Consumo newConsumo = new Consumo();
				newConsumo = objectMapper.readValue(data.get("consumo").toString(),Consumo.class);
				Apartamento apto = _apto.Entity(data.get("nroApto").asInt()).get();
				try {
					newConsumo.setNroApto(apto);
				} catch (Exception e) {
					return new ResponseEntity<Object>("El apartamento ha sido ingresado incorrectamente o ha sido ingreso erroneamente",HttpStatus.NOT_FOUND);
				}
			
				newConsumo  = _consu.Save(newConsumo);
				return new ResponseEntity<Object>(newConsumo, HttpStatus.OK);
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

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNota(@PathVariable(value = "id") Integer id){
		try {
			Consumo consum = _consu.Entity(id).get();
			if (consum == null){
				return new ResponseEntity<Object>("Apto con el id:  "+id, HttpStatus.NOT_FOUND);
			}
			_consu.Delete(id);
			return new ResponseEntity<Object>(consum, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
