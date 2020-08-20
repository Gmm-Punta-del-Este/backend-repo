package com.magicbussines.gmm.model;

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
}
