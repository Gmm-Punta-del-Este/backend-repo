package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.repos.RepositoryPersonaPropietario;

@Service
public class IPersonaPropietarioImp implements IPersonaPropietario {

	@Autowired
	private RepositoryPersonaPropietario _repo;
	
	@Override
	public Iterable<PersonaPropietario> List() {
		// TODO Auto-generated method stub
		return (Iterable<PersonaPropietario>)_repo.findAll();
	}

	@Override
	public Optional<PersonaPropietario> Entity(String id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public PersonaPropietario savePropietario(PersonaPropietario obj) {
		// TODO Auto-generated method stub
		PersonaPropietario pp = _repo.save(obj);
		return pp;
	}

	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
	}

	@Override
	public Iterable<PersonaPropietario> ListarActivos() {
		// TODO Auto-generated method stub
		return _repo.findAllActive();
	}

	@Override
	public Iterable<PersonaPropietario> ListarInactivos() {
		// TODO Auto-generated method stub
		return _repo.findAllInactive();
	}


}
