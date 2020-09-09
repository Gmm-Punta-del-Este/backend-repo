package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IContactoPropietario;
import com.magicbussines.gmm.model.ContactoPropietario;
import com.magicbussines.gmm.repos.RepositoryContactoPropietario;
@Service
public class IContactoPropietarioImpl implements IContactoPropietario{

	@Autowired
	private RepositoryContactoPropietario _repo;
	
	@Override
	public Iterable<ContactoPropietario> listarTodo() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<ContactoPropietario> Entity(String id) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(id);
	}

	@Override
	public ContactoPropietario Save(ContactoPropietario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContactoPropietario Save2(ContactoPropietario obj) {
		// TODO Auto-generated method stub
		_repo.insertManopla(obj.getNombre(), obj.getApellido(), obj.getUsuario().getDocumento(), obj.getEmail(), obj.getTelefono());
		return _repo.findByDocumento(obj.getUsuario().getDocumento()).get();
	}

	@Override
	public ContactoPropietario ContactoByDocumento(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(documento).get();
	}

	@Override
	public boolean existeContacto(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(documento).isPresent();
	}

}
