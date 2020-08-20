package com.magicbussines.gmm.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.magicbussines.gmm.model.pk.ContactoUsuarioPK;

@Entity
@IdClass(ContactoUsuarioPK.class)
public class ContactoUsuario extends Contacto{
	
	/**
	 * 
	 */
	@Id
	@Column(name = "telefono")
    private String telefono;

	@Id
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaUsuario usuario;

	
	
}
