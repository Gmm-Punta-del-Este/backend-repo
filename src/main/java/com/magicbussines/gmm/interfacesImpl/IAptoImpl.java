package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.repos.RepositoryAptos;
@Service
public class IAptoImpl implements IApto {
	
	@Autowired
	private RepositoryAptos _repo;

	@Override
	public Iterable<Apartamento> List() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<Apartamento> Entity(Integer id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public Apartamento Save(Apartamento obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
	}

	@Override
	public Iterable<Apartamento> listaApartamentosByPropietario(String documento) {
		// TODO Auto-generated method stub
		return _repo.findListByDoc(documento);
	}

	@Override
	public void update(Apartamento obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Apartamento> listaApartamentosAlquilables(boolean alquilable) {
		// TODO Auto-generated method stub
		return _repo.findListAlquilable(alquilable);
	}

	@Override
	public Iterable<Apartamento> listaApartamentosVendibles(boolean vendibles) {
		// TODO Auto-generated method stub
		return _repo.findListVendible(vendibles);
	}

}
