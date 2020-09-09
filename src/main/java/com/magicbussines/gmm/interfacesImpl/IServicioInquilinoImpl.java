package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IServicioInquilino;
import com.magicbussines.gmm.model.ServicioInquilino;
import com.magicbussines.gmm.repos.RepositoryServicioInquilino;
import com.magicbussines.gmm.repos.RepositoryServicioPropietario;
@Service
public class IServicioInquilinoImpl implements IServicioInquilino {
	@Autowired
	private RepositoryServicioInquilino _repo;
	
	@Override
	public Iterable<ServicioInquilino> List() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<ServicioInquilino> Entity(Integer id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public ServicioInquilino Save(ServicioInquilino obj) {
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
