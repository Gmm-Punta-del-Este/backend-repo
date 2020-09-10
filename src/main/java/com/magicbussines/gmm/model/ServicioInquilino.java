package com.magicbussines.gmm.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ServicioInquilino extends Servicio {
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaInquilino inquilino;

	public PersonaInquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(PersonaInquilino inquilino) {
		this.inquilino = inquilino;
	}

	public ServicioInquilino() {
		super();
		// TODO Auto-generated constructor stub
		this.hecho = null;
		this.solicitado = LocalDateTime.now();
	}

	public ServicioInquilino(int id, String nombre, @NotNull LocalDateTime solicitado, @NotNull LocalDateTime hecho,
			float costo, String notas) {
		super(id, nombre, solicitado, hecho, costo, notas);
		// TODO Auto-generated constructor stub
	}
	
	

	
}
