package com.magicbussines.gmm.interfacesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbussines.gmm.interfaces.IApto;
import com.magicbussines.gmm.model.Apartamento;
import com.magicbussines.gmm.repos.RepositoryConsumo;
@Service
public class IConsumoImpl implements IConsumo {
	
	@Autowired
	private RepositoryConsumo _repo;


}
