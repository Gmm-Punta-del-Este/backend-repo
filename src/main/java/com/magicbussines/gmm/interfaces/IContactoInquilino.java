package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.ContactoInquilino;

public interface IContactoInquilino {
	public Iterable<ContactoInquilino> listarTodo();
	public Optional<ContactoInquilino> Entity(String id);
	public ContactoInquilino Save(ContactoInquilino obj); //save funciona para la primera vez o para update
	public void Delete(String id);
}
