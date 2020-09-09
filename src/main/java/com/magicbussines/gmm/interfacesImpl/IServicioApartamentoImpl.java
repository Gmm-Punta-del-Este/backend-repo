package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IServicioApartamento;
import com.magicbussines.gmm.model.ServicioApartamento;
import com.magicbussines.gmm.repos.RepositoryServicioApartamento;
@Service
public class IServicioApartamentoImpl implements IServicioApartamento {

	@Autowired
	private RepositoryServicioApartamento _repo;
	
	@Override
	public Iterable<ServicioApartamento> List() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<ServicioApartamento> Entity(Integer id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public ServicioApartamento Save(ServicioApartamento obj) {
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
