package com.magicbussines.gmm.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;

public class ContactoPropietarioPK implements Serializable {
	
	private String telefono;
	    
		private PersonaPropietario usuario;

		public String getId() {
			return telefono;
		}

		public void setId(String telefono) {
			this.telefono = telefono;
		}

		public PersonaPropietario getUsuario() {
			return usuario;
		}

		public void setUsuario(PersonaPropietario usuario) {
			this.usuario = usuario;
		}

		public ContactoPropietarioPK() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ContactoPropietarioPK(String telefono, PersonaPropietario usuario) {
			super();
			this.telefono = telefono;
			this.usuario = usuario;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
			result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ContactoPropietarioPK other = (ContactoPropietarioPK) obj;
			if (telefono == null) {
				if (other.telefono != null)
					return false;
			} else if (!telefono.equals(other.telefono))
				return false;
			if (usuario == null) {
				if (other.usuario != null)
					return false;
			} else if (!usuario.equals(other.usuario))
				return false;
			return true;
		}

		
		
		
}
