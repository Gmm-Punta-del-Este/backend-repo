package com.magicbussines.gmm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.magicbussines.gmm.model.pk.*;

@Entity
@IdClass(ContactoInquilinoPK.class)
public class ContactoInquilino extends  Contacto {
	
	@Id
	@Column(name = "telefono")
    private String telefono;
	
	@Id
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaInquilino usuario;

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public PersonaInquilino getUsuario() {
		return usuario;
	}

	public void setUsuario(PersonaInquilino usuario) {
		this.usuario = usuario;
	}

	public ContactoInquilino() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ContactoInquilino [telefono=" + telefono + ", usuario=" + usuario + ", email=" + email + ", nombre="
				+ nombre + ", apellido=" + apellido + ", getTelefono()=" + getTelefono() + ", getUsuario()="
				+ getUsuario() + ", getEmail()=" + getEmail() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
