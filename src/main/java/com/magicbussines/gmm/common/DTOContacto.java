package com.magicbussines.gmm.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DTOContacto { 
	
	protected String telefono;
	protected String documento;
	protected String nombre; 
	protected String apellido;
	protected String email;
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DTOContacto(String telefono, String documento, String nombre, String apellido, String email) {
		super();
		this.telefono = telefono;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	public DTOContacto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
