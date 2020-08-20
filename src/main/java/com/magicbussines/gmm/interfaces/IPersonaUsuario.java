package com.magicbussines.gmm.interfaces;

import java.util.Optional;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.model.PersonaUsuario;

public interface IPersonaUsuario {
	public Iterable<PersonaUsuario> ListarTodos();
	
	public Iterable<PersonaUsuario> ListarActivos();
	public Iterable<PersonaUsuario> ListarInactivos();
	
	public Optional<PersonaUsuario> UserByCredenciales(String login, String password);
	public Optional<PersonaUsuario> UserById(String documento);
	public Optional<PersonaUsuario> UserByLogin(String login);
	
	public boolean isUserActiveId(String documento);
	public boolean isUserActiveCredenciales(String login, String password);
	public boolean isUserActiveLogin(String login);
	
	public PersonaUsuario Save(PersonaUsuario obj); //save funciona para la primera vez o para update
	public void Delete(String id);
	
	public void DesactivateUser(String id);
	public void ReactivateUser(String id);

	
}

