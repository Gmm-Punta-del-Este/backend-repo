package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.INotas;
import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.repos.RepositoryNotas;

@Service
public class INotasImpl implements INotas {

	
	@Autowired
	private RepositoryNotas _repo;
	
	@Override
	public Iterable<Nota> List() {
		// TODO Auto-generated method stub
		return _repo.findAll();
	}

	@Override
	public Optional<Nota> Entity(Integer id) {
		// TODO Auto-generated method stub
		return _repo.findById(id);
	}

	@Override
	public Nota Save(Nota obj) {
		// TODO Auto-generated method stub
		return _repo.save(obj);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_repo.deleteById(id);
	}


	@Override
	public Iterable<Nota> listaNotasByLogin(String login) {
		// TODO Auto-generated method stub
		return _repo.searchNoteByLogin(login);
	}

}
