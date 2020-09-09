package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IServicioPropietario;
import com.magicbussines.gmm.model.ServicioPropietario;
import com.magicbussines.gmm.repos.RepositoryServicioPropietario;
@Service
public class IServicioPropietarioImpl implements IServicioPropietario {

	@Autowired
	private RepositoryServicioPropietario _repo;
	
	@Override
	public Iterable<ServicioPropietario> List() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<ServicioPropietario> Entity(Integer id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public ServicioPropietario Save(ServicioPropietario obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
	}

	@Override
	public boolean existe(Integer id) {
		// TODO Auto-generated method stub
		return _repo.existsById(id);
	}

}
