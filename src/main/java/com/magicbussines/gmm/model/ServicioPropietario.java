package com.magicbussines.gmm.model;

import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ServicioPropietario extends Servicio{
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaPropietario propietario;

	public PersonaPropietario getPropietario() {
		return propietario;
	}

	public void setPropietario(PersonaPropietario propietario) {
		this.propietario = propietario;
	}

	public ServicioPropietario() {
		super();
		// TODO Auto-generated constructor stub
		this.solicitado = LocalDateTime.now();
		this.hecho = null;
	}

	public ServicioPropietario(int id, String nombre, @NotNull LocalDateTime solicitado, @NotNull LocalDateTime hecho,
			float costo, String notas) {
		super(id, nombre, solicitado, hecho, costo, notas);
		// TODO Auto-generated constructor stub
	}
	
	
}
