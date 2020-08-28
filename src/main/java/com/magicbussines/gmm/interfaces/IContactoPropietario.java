package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.ContactoPropietario;

public interface IContactoPropietario {
	public Iterable<ContactoPropietario> listarTodo();
	public Optional<ContactoPropietario> Entity(String id);
	public ContactoPropietario Save(ContactoPropietario obj); //save funciona para la primera vez o para update
	public void Delete(String id);
}
