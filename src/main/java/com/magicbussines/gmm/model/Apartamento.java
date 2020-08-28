package com.magicbussines.gmm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Apartamento {

	@Id
	@NotNull
	private int nroApto;
	
	@Column
	private String notas;
	
	@Column
	private boolean venta;
	
	@Column
	private boolean alquilable;
	
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaPropietario propietario;

	public int getNroApto() {
		return nroApto;
	}

	public void setNroApto(int nroApto) {
		this.nroApto = nroApto;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public boolean isVenta() {
		return venta;
	}

	public void setVenta(boolean venta) {
		this.venta = venta;
	}

	public PersonaPropietario getPropietario() {
		return propietario;
	}

	public void setPropietario(PersonaPropietario propietario) {
		this.propietario = propietario;
	}
	

	public boolean isAlquilable() {
		return alquilable;
	}

	public void setAlquilable(boolean alquilable) {
		this.alquilable = alquilable;
	}

	public Apartamento(@NotNull int nroApto, String notas, boolean venta, PersonaPropietario propietario, boolean alquilable) {
		super();
		this.nroApto = nroApto;
		this.notas = notas;
		this.venta = venta;
		this.propietario = propietario;
		this.alquilable = alquilable;
	}

	public Apartamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
