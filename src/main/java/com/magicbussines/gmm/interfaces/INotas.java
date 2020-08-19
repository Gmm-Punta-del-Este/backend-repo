package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.Nota;

public interface INotas {
	public Iterable<Nota> List();
	public Optional<Nota> Entity(Integer id);
	public Nota Save(Nota obj); //save funciona para la primera vez o para update
	public void Delete(Integer id);
	public Iterable<Nota> listaNotasByLogin(String login);
	

}
