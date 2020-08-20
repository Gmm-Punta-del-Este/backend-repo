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
	
}
