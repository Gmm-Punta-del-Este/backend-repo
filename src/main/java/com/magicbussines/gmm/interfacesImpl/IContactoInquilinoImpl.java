package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IContactoInquilino;
import com.magicbussines.gmm.model.ContactoInquilino;
import com.magicbussines.gmm.repos.RepositoryContactoInquilino;
@Service
public class IContactoInquilinoImpl implements IContactoInquilino {
	@Autowired
	private RepositoryContactoInquilino _repo;
	@Override
	public Iterable<ContactoInquilino> listarTodo() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<ContactoInquilino> Entity(String id) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(id);
	}

	@Override
	public ContactoInquilino Save(ContactoInquilino obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
		
	}

	@Override
	public ContactoInquilino Save2(ContactoInquilino obj) {
		// TODO Auto-generated method stub
		_repo.insertManopla(obj.getNombre(), obj.getApellido(), obj.getUsuario().getDocumento(), obj.getEmail(), obj.getTelefono());
		return _repo.findByDocumento(obj.getUsuario().getDocumento()).get();
	}

	@Override
	public ContactoInquilino ContactoByDocumento(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(documento).get();
	}

	@Override
	public boolean existeContacto(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByDocumento(documento).isPresent();
	}

}
