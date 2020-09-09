package com.magicbussines.gmm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ServicioApartamento extends Servicio {

	// Esto significa que el servicio lo proporciono una persona o externa
	@Column
	private boolean interno;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="nroApto")
	private Apartamento apto;

	public boolean isInterno() {
		return interno;
	}

	public void setInterno(boolean interno) {
		this.interno = interno;
	}

	public Apartamento getApto() {
		return apto;
	}

	public void setApto(Apartamento apto) {
		this.apto = apto;
	}

	public ServicioApartamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServicioApartamento(int id, String nombre, @NotNull LocalDateTime solicitado, @NotNull LocalDateTime hecho,
			float costo, String notas) {
		super(id, nombre, solicitado, hecho, costo, notas);
		// TODO Auto-generated constructor stub
	}
	
}
