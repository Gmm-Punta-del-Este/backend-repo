package com.magicbussines.gmm.interfacesImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IPersonaUsuario;
import com.magicbussines.gmm.model.PersonaUsuario;
import com.magicbussines.gmm.repos.RepositoryPersonaUsuario;
@Service
public class IPersonaUsuarioImp implements IPersonaUsuario {

	@Autowired
	private RepositoryPersonaUsuario _repo;
	
	@Override
	public Iterable<PersonaUsuario> ListarTodos() {
		// TODO Auto-generated method stub
		return (Iterable<PersonaUsuario>)_repo.findAll();
	}

	@Override
	public Optional<PersonaUsuario> UserByCredenciales(String login, String password) {
		// TODO Auto-generated method stub
		return _repo.findByLoginPassword(login, password);
	}

	@Override
	public Optional<PersonaUsuario> UserById(String documento) {
		// TODO Auto-generated method stub
		return _repo.findById(documento);
	}

	@Override
	public PersonaUsuario Save(PersonaUsuario obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}

	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
	}

	@Override
	public void DesactivateUser(String id) {
		// TODO Auto-generated method stub
		_repo.desactivarUser(id);
		//Logic deleted for user
	}

	@Override
	public void ReactivateUser(String id) {
		// TODO Auto-generated method stub
		_repo.reactUser(id);	
	}

	@Override
	public Iterable<PersonaUsuario> ListarActivos() {
		// TODO Auto-generated method stub
		return _repo.findAllActive();
	}

	@Override
	public Iterable<PersonaUsuario> ListarInactivos() {
		// TODO Auto-generated method stub
		return _repo.findAllInactive();
	}

	@Override
	public boolean isUserActiveId(String documento) {
		// TODO Auto-generated method stub
		return _repo.isActivedId(documento).isPresent();
	}

	@Override
	public boolean isUserActiveCredenciales(String login, String password) {
		// TODO Auto-generated method stub
		return _repo.isActivedCredenciales(login, password).isPresent();
	}
	@Override
	public boolean isUserActiveLogin(String login) {
		// TODO Auto-generated method stub
		return _repo.isActivedLogin(login).isPresent();
	}

	@Override
	public Optional<PersonaUsuario> UserByLogin(String login) {
		// TODO Auto-generated method stub
		return _repo.findByLogin(login);
	}
	
	

}
