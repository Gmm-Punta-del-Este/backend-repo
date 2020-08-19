package com.magicbussines.gmm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

// Esta entidad almacena contactos referidos a una persona, multivalued respecto de telefono

@MappedSuperclass
public abstract class Contacto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7729165706197007639L;
	@Id
	@Column
	protected String telefono;
	@Column
	protected String email;
	@Column
	protected String nombre;
	@Column
	protected String apellido;
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Contacto(String telefono, String email, String nombre, String apellido) {
		super();
		this.telefono = telefono;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public Contacto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// GET AND SET
	
	
	
}
