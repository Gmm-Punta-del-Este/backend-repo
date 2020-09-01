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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicbussines.gmm.common.DTONota;
import com.magicbussines.gmm.common.MapperNota;
import com.magicbussines.gmm.interfaces.INotas;
import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.PersonaUsuario;
@CrossOrigin
@RestController
@RequestMapping("/nota")
public class ControllerNotas {
	
	@Autowired
	private INotas _nota;
	@Autowired
	private IPersonaUsuario _usuario;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MapperNota _mapper;
			
	//List todas las NOTAS del SISTEMA		
	@GetMapping("/listar")
	public ResponseEntity<Object> notaLista() {
		List<Nota> notas = (List<Nota>) _nota.List();
		
		if(notas.isEmpty()) {
			return new ResponseEntity<Object>("No hay notas en el sistema", HttpStatus.NOT_FOUND);
		}
		List<DTONota> notasDto = new ArrayList<DTONota>();
		for (Nota notaAux : notas) {
			notasDto.add(_mapper.NotaToDTO(notaAux));
		}
		return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	//RETORNA LAS NOTAS DE {Documento} usuario.
	@GetMapping("/usuario/{username}")
	public ResponseEntity<Object> notaListaByUser(@PathVariable(value = "username") String username) {
			
			List<Nota> notas = (List<Nota>) _nota.listaNotasByLogin(username);
			if(notas.isEmpty()) {
				return new ResponseEntity<Object>("No hay notas en el sistema para el usuario con el ID: "+username, HttpStatus.NOT_FOUND);
			}
			
			List<DTONota> notasDto = new ArrayList<DTONota>();
			for (Nota notaAux : notas) {
				notasDto.add(_mapper.NotaToDTO(notaAux));
			}
			
			return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Object> notaListaByTitulo(@PathVariable(value = "titulo") String titulo) {
		List<Nota> notas = (List<Nota>) _nota.List();
		if(notas.isEmpty()) {
			return new ResponseEntity<Object>("No hay notas en el sistema", HttpStatus.NOT_FOUND);
		}
		
		List<DTONota> notasDto = new ArrayList<DTONota>();
		for (Nota notaAux : notas) {
			
			String titulito = notaAux.getTitulo();
			String tituloMayus = titulito.toLowerCase();
			String tituloMinus = titulito.toUpperCase();
			if (tituloMayus.contains(titulo) || tituloMinus.contains(titulo) || titulito.contains(titulo) ) {
				notasDto.add(_mapper.NotaToDTO(notaAux));
			}	
		}
		if (notasDto.isEmpty()) {
			return new ResponseEntity<Object>("No hay notas en el sistema que contenga en su titulo la/s palabra/s: "+titulo, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/texto/{texto}")
	public ResponseEntity<Object> notaListaByTexto(@PathVariable(value = "texto") String texto) {
		List<Nota> notas = (List<Nota>) _nota.List();
		if(notas.isEmpty()) {
			return new ResponseEntity<Object>("No hay notas en el sistema", HttpStatus.NOT_FOUND);
		}
		
		List<DTONota> notasDto = new ArrayList<DTONota>();
		for (Nota notaAux : notas) {
			
			//contains or containsEquals
			String textito = notaAux.getTexto();
			String textoMinus = textito.toLowerCase();
			String textoMayus = textito.toUpperCase();
			if (textito.contains(texto) || textoMayus.contains(texto) || textoMinus.contains(texto)) {
				notasDto.add(_mapper.NotaToDTO(notaAux));
			}	
		}
		if (notasDto.isEmpty()) {
			return new ResponseEntity<Object>("No hay notas en el sistema que contenga en su texto la/s palabra/s: "+texto, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/fecha/{fecha}")
	public ResponseEntity<Object> notaListaByFecha(@PathVariable(value = "fecha") String fecha) {
		
		if (GenericValidator.isDate(fecha,"yyyy-MM-dd", true)) {
		
			List<Nota> notas = (List<Nota>) _nota.List();
			if(notas.isEmpty()) {
				return new ResponseEntity<Object>("No hay notas en el sistema", HttpStatus.NOT_FOUND);
			}
			
			
			List<DTONota> notasDto = new ArrayList<DTONota>();
			for (Nota notaAux : notas) {
				
				//contains or containsEquals
				if ((notaAux.getCreatedOn().toLocalDate().toString()).equals(fecha)) {
					notasDto.add(_mapper.NotaToDTO(notaAux));
				}	
			}
			if (notasDto.isEmpty()) {
				return new ResponseEntity<Object>("No hay notas en el sistema para la fecha: "+fecha, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Object>("El patron de la fecha es incorrecta, debe ingresar yyyy-MM-dd", HttpStatus.CONFLICT);
		}
	}
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
	
	@GetMapping("/modificacion/{fecha}")
	public ResponseEntity<Object> notaListaByFechaModificacion(@PathVariable(value = "fecha") String fecha) {
		
		if (GenericValidator.isDate(fecha,"yyyy-MM-dd", true)) {
		
			List<Nota> notas = (List<Nota>) _nota.List();
			if(notas.isEmpty()) {
				return new ResponseEntity<Object>("No hay notas en el sistema", HttpStatus.NOT_FOUND);
			}
			
			
			List<DTONota> notasDto = new ArrayList<DTONota>();
			for (Nota notaAux : notas) {
				
				//contains or containsEquals
				if ((notaAux.getCreatedOn().toLocalDate().toString()).equals(fecha)) {
					notasDto.add(_mapper.NotaToDTO(notaAux));
				}	
			}
			if (notasDto.isEmpty()) {
				return new ResponseEntity<Object>("No hay notas en el sistema para la fecha: "+fecha, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(notasDto,HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Object>("El patron de la fecha es incorrecta, debe ingresar yyyy-MM-dd", HttpStatus.CONFLICT);
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
				
				Nota nuevaNota = new Nota();
				//nuevaNota = objectMapper.readValue(data.get("nota").toString(),Nota.class);
				nuevaNota.setTitulo(data.get("nota").get("titulo").asText());
				nuevaNota.setTexto(data.get("nota").get("texto").asText());
				nuevaNota.setUsuario((_usuario.UserByCredenciales(login,password).get()));
				nuevaNota = _nota.Save(nuevaNota);
				return new ResponseEntity<Object>(nuevaNota, HttpStatus.OK);
			} else
			{
				return new ResponseEntity<Object>("El usuario con el cual desea ingresar una nota esta desactivado o no existe, avise a un administrador en caso de ser necesario.",HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************

	
	// ------------------- EDITAR
	// ------------------- NOTA
	
	@PutMapping("/")
	public ResponseEntity<Object> modifyNota(@RequestBody Nota nota) throws JsonParseException, JsonMappingException, IOException{
		_nota.update(nota);
		Nota notita = _nota.Entity(nota.getId()).get();
		return new ResponseEntity<Object>(_mapper.NotaToDTO(notita),HttpStatus.OK);
	}
	
	
	// ***********************************************************************************************************************
	// ***********************************************************************************************************************
		
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNota(@PathVariable(value = "id") Integer id){
		try {
			Nota nota = _nota.Entity(id).get();
			if (nota == null){
				return new ResponseEntity<Object>("No existe el Propietario con "+id, HttpStatus.NOT_FOUND);
			}
			_nota.Delete(id);
			return new ResponseEntity<Object>(nota, HttpStatus.OK);
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
