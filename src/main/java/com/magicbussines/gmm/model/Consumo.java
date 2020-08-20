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
	private LocalDateTime consumoInicial;
	
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	private LocalDateTime consumoFinal;

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

	public LocalDateTime getConsumoInicial() {
		return consumoInicial;
	}

	public void setConsumoInicial(LocalDateTime consumoInicial) {
		this.consumoInicial = consumoInicial;
	}

	public LocalDateTime getConsumoFinal() {
		return consumoFinal;
	}

	public void setConsumoFinal(LocalDateTime consumoFinal) {
		this.consumoFinal = consumoFinal;
	}

	public Consumo(int id, @NotNull Apartamento nroApto, @NotNull LocalDateTime consumoInicial,
			LocalDateTime consumoFinal) {
		super();
		this.id = id;
		this.nroApto = nroApto;
		this.consumoInicial = consumoInicial;
		this.consumoFinal = consumoFinal;
	}

	public Consumo() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
