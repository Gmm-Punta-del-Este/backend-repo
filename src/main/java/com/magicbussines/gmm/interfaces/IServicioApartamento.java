package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.ServicioApartamento;

public interface IServicioApartamento {
	public Iterable<ServicioApartamento> List();
	public Optional<ServicioApartamento> Entity(Integer id);
	public ServicioApartamento Save(ServicioApartamento obj); //save funciona para la primera vez o para update
	public void Delete(Integer id);
	public boolean existe(Integer id);
//	public Iterable<Nota> listaNotasByLogin(String login);
//	public void update(ServicioApartamento obj);
	

}
