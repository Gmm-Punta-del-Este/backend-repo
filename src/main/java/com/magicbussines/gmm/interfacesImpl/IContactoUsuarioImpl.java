package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IContactoUsuario;
import com.magicbussines.gmm.model.ContactoUsuario;
import com.magicbussines.gmm.repos.RepositoryContactoUsuario;

@Service
public class IContactoUsuarioImpl implements IContactoUsuario {

	@Autowired
	private RepositoryContactoUsuario _repo;
	
	@Override
	public Iterable<ContactoUsuario> listarTodo() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<ContactoUsuario> Entity(String id) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(id);
	}

	@Override
	public ContactoUsuario Save(ContactoUsuario obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}
	@Override
	public ContactoUsuario Save2(ContactoUsuario obj) {
		// TODO Auto-generated method stub
		_repo.insertManopla(obj.getNombre(), obj.getApellido(), obj.getUsuario().getDocumento(), obj.getEmail(), obj.getTelefono());
		return _repo.findByDocumento(obj.getUsuario().getDocumento()).get();
	}

	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		//_repo.delete(id);
	}

	@Override
	public ContactoUsuario ContactoByDocumento(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(documento).get();
	}

	@Override
	public boolean existeContacto(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(documento).isPresent();
	}

}
