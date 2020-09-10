package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.ServicioPropietario;

public interface IServicioPropietario {
	public Iterable<ServicioPropietario> List();
	public Optional<ServicioPropietario> Entity(Integer id);
	public ServicioPropietario Save(ServicioPropietario obj); //save funciona para la primera vez o para update
	public void Delete(Integer id);
	public boolean existe(Integer id);
//	public Iterable<ServicioPropietario> listaNotasByLogin(String login);
//	public void update(Nota obj);
	

}
