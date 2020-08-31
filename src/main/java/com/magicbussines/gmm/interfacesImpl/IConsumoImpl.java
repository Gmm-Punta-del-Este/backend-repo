package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.interfaces.IConsumo;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.model.Consumo;
import com.magicbussines.gmm.repos.RepositoryConsumo;
@Service
public class IConsumoImpl implements IConsumo {
	
	@Autowired
	private RepositoryConsumo _repo;

	@Override
	public Iterable<Consumo> List() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<Consumo> Entity(Integer id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public Consumo Save(Consumo obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
		
	}

	@Override
	public Iterable<Consumo> listaConsumosByPropietario(String documento) {
		// TODO Auto-generated method stub
		return _repo.findByPropietario(documento);
		
	}

	@Override
	public Iterable<Consumo> listaConsumosByApto(int idApto) {
		// TODO Auto-generated method stub
		return _repo.findAllByApto(idApto);
	}

	@Override
	public void update(Consumo obj) {
		// TODO Auto-generated method stub
		
	}


}
