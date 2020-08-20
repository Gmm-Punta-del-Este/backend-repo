package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IPersonaInquilino;
import com.magicbussines.gmm.interfaces.IPersonaPropietario;
import com.magicbussines.gmm.model.PersonaInquilino;
import com.magicbussines.gmm.model.PersonaPropietario;
import com.magicbussines.gmm.repos.RepositoryPersonaInquilino;
import com.magicbussines.gmm.repos.RepositoryPersonaPropietario;

@Service
public class IPersonaInquilinoImp implements IPersonaInquilino {

	@Autowired
	private RepositoryPersonaInquilino _repo;
	
	@Override
	public Iterable<PersonaInquilino> List() {
		// TODO Auto-generated method stub
		return (Iterable<PersonaInquilino>)_repo.findAll();
	}

	@Override
	public Optional<PersonaInquilino> Entity(String id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public PersonaInquilino Save(PersonaInquilino obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}

	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
	}

	@Override
	public Iterable<PersonaInquilino> ListarActivos() {
		// TODO Auto-generated method stub
		return _repo.findAllActive();
	}

	@Override
	public Iterable<PersonaInquilino> ListarInactivos() {
		// TODO Auto-generated method stub
		return _repo.findAllInactive();
	}


}
