package com.magicbussines.gmm.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance

@MappedSuperclass
public abstract class Inhouse { 
	
	// >> I.ATRIBUTOS
	
	//se debe generar trigger cuando se haga una accion sobre un Inhouse

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="America/Montevideo")
	protected LocalDateTime timestamp;
	
	//Notes about in-house
	@Column
	protected String comentario;
	
		// enumerado por tipo de usuario sobre el inhouse
		// revisar que no me gusta pariente, don't like it
	private enum TipoPersona{
			Inquilino,
			Propietario
		};
	@Column
	@NotNull
	protected TipoPersona tipoPersona;
	
	// FK with apartment
	@NotNull
	@ManyToOne
	@JoinColumn(name="nroApto")
	protected Apartamento apto;

	@Column
	protected String documento;
	
	// >> F.ATRIBUTOS
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Apartamento getApto() {
		return apto;
	}

	public void setApto(Apartamento apto) {
		this.apto = apto;
	}
	
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Inhouse(int id,String documento, LocalDateTime timestamp, String comentario, TipoPersona tipoPersona, Apartamento apto) {
		super();
		this.id = id;
		this.documento = documento;
		this.timestamp = timestamp;
		this.comentario = comentario;
		this.tipoPersona = tipoPersona;
		this.apto = apto;
	}

	public Inhouse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}

