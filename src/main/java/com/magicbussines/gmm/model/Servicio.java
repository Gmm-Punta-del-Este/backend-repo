package com.magicbussines.gmm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class Servicio {

	// >>> ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column
	protected String nombre;
	
	@Column
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	protected LocalDateTime solicitado;
	
	@Column
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	protected LocalDateTime hecho;
	
	@Column
	protected float costo;
	
	@Column
	protected String notas;
	
	// >>> FIN ATRIBUTOS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(LocalDateTime solicitado) {
		this.solicitado = solicitado;
	}

	public LocalDateTime getHecho() {
		return hecho;
	}

	public void setHecho(LocalDateTime hecho) {
		this.hecho = hecho;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Servicio(int id, String nombre, @NotNull LocalDateTime solicitado, @NotNull LocalDateTime hecho, float costo, String notas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.solicitado = solicitado;
		this.hecho = hecho;
		this.costo = costo;
		this.notas = notas;
	}

	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//SET and GET
	
	
}
