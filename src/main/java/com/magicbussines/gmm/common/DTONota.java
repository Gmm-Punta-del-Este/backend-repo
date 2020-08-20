package com.magicbussines.gmm.common;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DTONota {

	private String titulo;
	

	private String texto;
	

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	private LocalDateTime createdOn;
	
	private DTOUsuario usuario;

	public DTONota(String titulo, String texto, LocalDateTime createdOn, DTOUsuario usuario) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.createdOn = createdOn;
		this.usuario = usuario;
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
	
	
}
