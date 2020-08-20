package com.magicbussines.gmm.common;

public class DTOUsuario extends DTOPersona {
	
	private String tipoUsuario;
	
	private String estado;

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DTOUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
