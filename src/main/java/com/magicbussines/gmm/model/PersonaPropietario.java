package com.magicbussines.gmm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class PersonaPropietario extends Persona {

	@Column
	private String direccion;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
}
