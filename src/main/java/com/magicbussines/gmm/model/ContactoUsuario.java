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

import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.interfacesImpl.IPersonaUsuarioImp;
import com.magicbussines.gmm.model.pk.ContactoUsuarioPK;

@Entity
@IdClass(ContactoUsuarioPK.class)
public class ContactoUsuario extends Contacto{
	
	@Id
	@Column(name = "telefono")
    private String telefono;

	@Id
	@ManyToOne
	@JoinColumn(name="documento")
	private PersonaUsuario usuario;

	public PersonaUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(PersonaUsuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuario2(String usuario) {
		IPersonaUsuario pu = new IPersonaUsuarioImp();
		this.usuario = pu.UserById(usuario).get();
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ContactoUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactoUsuario(String telefono, String email, String nombre, String apellido) {
		super(telefono, email, nombre, apellido);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ContactoUsuario [telefono=" + telefono + ", usuario=" + usuario + ", email=" + email + ", nombre="
				+ nombre + ", apellido=" + apellido + ", getUsuario()=" + getUsuario() + ", getTelefono()="
				+ getTelefono() + ", getEmail()=" + getEmail() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	

	
	
}
