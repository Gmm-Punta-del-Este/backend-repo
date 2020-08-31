package com.magicbussines.gmm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Consumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="nroApto")
	private Apartamento nroApto;
	
	@Column
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	private LocalDateTime fechaInicio;
	
	@Column
	private float tomaInicial;
	
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	private LocalDateTime fechaFinal;
	
	@Column
	private float tomaFinal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Apartamento getNroApto() {
		return nroApto;
	}

	public void setNroApto(Apartamento nroApto) {
		this.nroApto = nroApto;
	}

	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public float getTomaInicial() {
		return tomaInicial;
	}

	public void setTomaInicial(float tomaInicial) {
		this.tomaInicial = tomaInicial;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public float getTomaFinal() {
		return tomaFinal;
	}

	public void setTomaFinal(float tomaFinal) {
		this.tomaFinal = tomaFinal;
	}
	
	
	

	public Consumo(int id, @NotNull Apartamento nroApto, @NotNull LocalDateTime fechaInicio, float tomaInicial,
			@NotNull LocalDateTime fechaFinal, float tomaFinal) {
		super();
		this.id = id;
		this.nroApto = nroApto;
		this.fechaInicio = fechaInicio;
		this.tomaInicial = tomaInicial;
		this.fechaFinal = fechaFinal;
		this.tomaFinal = tomaFinal;
	}

	public Consumo() {
		super();
		// TODO Auto-generated constructor stub
		this.fechaInicio = LocalDateTime.now();
		this.fechaFinal = null;
	}

		
}
