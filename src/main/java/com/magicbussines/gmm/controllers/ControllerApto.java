package com.magicbussines.gmm.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.magicbussines.gmm.common.DTONota;
import com.magicbussines.gmm.common.MapperNota;
import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.interfaces.INotas;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.PersonaUsuario;

@RestController
@RequestMapping("/apto")
public class ControllerApto {
	
	@Autowired
	private INotas _nota;
	@Autowired
	private IApto _apto;
	@Autowired
	private IPersonaUsuario _usuario;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MapperNota _mapper;
			
	//List todas las NOTAS del SISTEMA		
	@GetMapping("/listar")
	public ResponseEntity<Object> notaLista() {
		List<Apartamento> notas = (List<Apartamento>) _apto.List();
		
		if(notas.isEmpty()) {
			return new ResponseEntity<Object>("No hay notas en el sistema", HttpStatus.NOT_FOUND);
		}
//		List<DTONota> notasDto = new ArrayList<DTONota>();
//		for (Nota notaAux : notas) {
//			notasDto.add(_mapper.NotaToDTO(notaAux));
//		}
		return new ResponseEntity<Object>(notas,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	//NOT QUERY HAHAH
	//List todas las NOTAS del SISTEMA		
		@GetMapping("/listar/alquilables")
		public ResponseEntity<Object> notaListas() {
//QUERY
//			@GetMapping("/listar/alquilables")
//			public ResponseEntity<Object> notaListas(@RequestParam(value = "booleanito") boolean alqui) {
			
			List<Apartamento> notas = (List<Apartamento>) _apto.listaApartamentosAlquilables(true);
			if(notas.isEmpty()) {
					return new ResponseEntity<Object>("No hay apartamentos disponibles para ALQUILER.", HttpStatus.NOT_FOUND);
				}else{
					return new ResponseEntity<Object>(notas,HttpStatus.OK);
				}
//			List<DTONota> notasDto = new ArrayList<DTONota>();
//			for (Nota notaAux : notas) {
//				notasDto.add(_mapper.NotaToDTO(notaAux));
//			}
		
		}
		
		// ***********************************************************************************************************************
		// ***********************************************************************************************************************
		//NOT QUERY HAHAH
		//List todas las NOTAS del SISTEMA		
			@GetMapping("/listar/vendibles")
			public ResponseEntity<Object> aptoVendible() {
				
//QUERY
//@GetMapping("/listar/alquilables")
//public ResponseEntity<Object> notaListas(@RequestParam(value = "booleanito") boolean alqui) {
				
				List<Apartamento> notas = (List<Apartamento>) _apto.listaApartamentosVendibles(true);
					if(notas.isEmpty()) {
						return new ResponseEntity<Object>("No hay apartamentos disponibles para VENTA.", HttpStatus.NOT_FOUND);
					}else {
						return new ResponseEntity<Object>(notas,HttpStatus.OK);
					}
				
//				List<DTONota> notasDto = new ArrayList<DTONota>();
//				for (Nota notaAux : notas) {
//					notasDto.add(_mapper.NotaToDTO(notaAux));
//				}
			}
			
			// ***********************************************************************************************************************
			// ***********************************************************************************************************************
	
	
	//RETORNA LAS NOTAS DE {Documento} usuario.
	@GetMapping("/listar/propietario/{documento}")
	public ResponseEntity<Object> notaListaByUser(@PathVariable(value = "documento") String documento) {
			
			List<Apartamento> aptos = (List<Apartamento>) _apto.listaApartamentosByPropietario(documento);
			if(aptos.isEmpty()) {
				return new ResponseEntity<Object>("No hay apartamentos en el sistema para el Propietario con documento nro: "+documento, HttpStatus.NOT_FOUND);
			}
			
//			List<DTONota> notasDto = new ArrayList<DTONota>();
//			for (Nota notaAux : notas) {
//				notasDto.add(_mapper.NotaToDTO(notaAux));
//			}
			
			return new ResponseEntity<Object>(aptos,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	//RETORNA LAS NOTAS DE {Documento} usuario.
	@GetMapping("/{nroapto}")
	public ResponseEntity<Object> aptoByNro(@PathVariable(value = "nroapto") int nroapto) {
			try {
				Apartamento aptos = _apto.Entity(nroapto).get();
						
//				List<DTONota> notasDto = new ArrayList<DTONota>();
//				for (Nota notaAux : notas) {
//					notasDto.add(_mapper.NotaToDTO(notaAux));
//				}
				
				return new ResponseEntity<Object>(aptos,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<Object>("El numero de apartamento que ingreso es incorrecto o no esta registrado en el sistema, Numero Apto: "+nroapto, HttpStatus.NOT_FOUND);
			}
			
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	@PostMapping("/")
	public ResponseEntity<Object> saveNota(@RequestBody JsonNode data) throws JsonParseException, JsonMappingException, IOException{
		
		try {
			String login = data.get("user").get("login").asText();
			String password = data.get("user").get("password").asText();
			if (_usuario.isUserActiveCredenciales(login, password)) {
				
				Apartamento nuevoApto = new Apartamento();
				nuevoApto = objectMapper.readValue(data.get("apartamento").toString(),Apartamento.class);
				
				nuevoApto  = _apto.Save(nuevoApto);
				return new ResponseEntity<Object>(nuevoApto, HttpStatus.OK);
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

	
	// ------------------- EDITAR
	// ------------------- APTO / PARA HACER
//	
//	@PutMapping("/")
//	public ResponseEntity<Object> modifyNota(@RequestBody Nota nota) throws JsonParseException, JsonMappingException, IOException{
//		_nota.update(nota);
//		Nota notita = _nota.Entity(nota.getId()).get();
//		return new ResponseEntity<Object>(_mapper.NotaToDTO(notita),HttpStatus.OK);
//	}
	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
		
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNota(@PathVariable(value = "id") Integer id){
		try {
			Apartamento apto = _apto.Entity(id).get();
			if (apto == null){
				return new ResponseEntity<Object>("apto con el id:  "+id, HttpStatus.NOT_FOUND);
			}
			_apto.Delete(id);
			return new ResponseEntity<Object>(apto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}


/*  
List<DTONota> notasDto = new ArrayList<DTONota>();
	for (Nota notaAux : notas) {
		notasDto.add(_mapper.NotaToDTO(notaAux));
	}
*/
