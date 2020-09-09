package com.magicbussines.gmm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Apartamento {

	@Id
	@NotNull
	private int nroApto;
	
	@Column
	private String notas;
	
	@Column
	private boolean libre;
	
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaPropietario documento;

	public int getNroApto() {
		return nroApto;
	}

	public void setNroApto(int nroApto) {
		this.nroApto = nroApto;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	public PersonaPropietario getPropietario() {
		return documento;
	}

	public void setPropietario(PersonaPropietario documento) {
		this.documento = documento;
	}


	public Apartamento(@NotNull int nroApto, String notas, boolean venta, PersonaPropietario documento, boolean libre) {
		super();
		this.nroApto = nroApto;
		this.notas = notas;
		this.libre = libre;
		this.documento = documento;
	}

	public Apartamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Apartamento [nroApto=" + nroApto + ", notas=" + notas + ", libre=" + libre + ", documento=" + documento
				+ ", getNroApto()=" + getNroApto() + ", getNotas()=" + getNotas() + ", isLibre()=" + isLibre()
				+ ", getPropietario()=" + getPropietario() + "]";
	}
	
	
	
	
	
}
