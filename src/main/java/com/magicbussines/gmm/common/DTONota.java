package com.magicbussines.gmm.common;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DTONota {

	private int id;
	private String titulo;
	

	private String texto;
	

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	private LocalDateTime createdOn;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	private LocalDateTime modifiedOn;
	
	private DTOUsuario usuario;

	public DTONota(int id, String titulo, String texto, LocalDateTime createdOn, LocalDateTime modifiedOn, DTOUsuario usuario) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.createdOn = createdOn;
		this.usuario = usuario;
		this.setId(id);
	}

	public DTONota() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public DTOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(DTOUsuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
	
}
