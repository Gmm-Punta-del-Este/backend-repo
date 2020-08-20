package com.magicbussines.gmm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.magicbussines.gmm.model.pk.*;

@Entity
@IdClass(ContactoPropietarioPK.class)
public class ContactoPropietario extends Contacto {
	
	@Id
	@Column(name = "telefono")
    private String telefono;
	
	@Id
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaPropietario usuario;

	
}
