package com.magicbussines.gmm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ApartamentoActividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaUsuario usuario;
	
	@Column
	private String acompaniante;
	
	@Column
	private String nombreEntra;
	
	@Column
	private String documentoEntra;
	
	@Column
	private String motivo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PersonaUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(PersonaUsuario usuario) {
		this.usuario = usuario;
	}

	public String getAcompaniante() {
		return acompaniante;
	}

	public void setAcompaniante(String acompaniante) {
		this.acompaniante = acompaniante;
	}

	public String getNombreEntra() {
		return nombreEntra;
	}

	public void setNombreEntra(String nombreEntra) {
		this.nombreEntra = nombreEntra;
	}

	public String getDocumentoEntra() {
		return documentoEntra;
	}

	public void setDocumentoEntra(String documentoEntra) {
		this.documentoEntra = documentoEntra;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public ApartamentoActividad(int id, PersonaUsuario usuario, String acompaniante, String nombreEntra,
			String documentoEntra, String motivo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.acompaniante = acompaniante;
		this.nombreEntra = nombreEntra;
		this.documentoEntra = documentoEntra;
		this.motivo = motivo;
	}

	public ApartamentoActividad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	// firma digital a posterior :3
	
	
}
