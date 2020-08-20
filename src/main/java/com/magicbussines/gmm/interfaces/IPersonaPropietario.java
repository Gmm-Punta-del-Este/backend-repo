package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.PersonaPropietario;

public interface IPersonaPropietario {
	public Iterable<PersonaPropietario> List();
	public Iterable<PersonaPropietario> ListarActivos();
	public Iterable<PersonaPropietario> ListarInactivos();
	public Optional<PersonaPropietario> Entity(String id);
	public PersonaPropietario savePropietario(PersonaPropietario obj); //save funciona para la primera vez o para update
	public void Delete(String id);
	
}

