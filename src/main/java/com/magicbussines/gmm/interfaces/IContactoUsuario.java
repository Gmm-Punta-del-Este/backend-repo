package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.ContactoUsuario;

public interface IContactoUsuario {
	public Iterable<ContactoUsuario> listarTodo();
	public Optional<ContactoUsuario> Entity(String id);
	public ContactoUsuario Save(ContactoUsuario obj); //save funciona para la primera vez o para update
	public ContactoUsuario Save2(ContactoUsuario obj);
	public ContactoUsuario ContactoByDocumento(String documento);
	public boolean existeContacto(String documento);
	public void Delete(String id);
}
