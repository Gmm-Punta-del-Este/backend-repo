package com.magicbussines.gmm.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	
}
