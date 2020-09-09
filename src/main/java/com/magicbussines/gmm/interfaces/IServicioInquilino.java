package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.Nota;
import com.magicbussines.gmm.model.ServicioInquilino;

public interface IServicioInquilino {
	public Iterable<ServicioInquilino> List();
	public Optional<ServicioInquilino> Entity(Integer id);
	public ServicioInquilino Save(ServicioInquilino obj); //save funciona para la primera vez o para update
	public void Delete(Integer id);
	public boolean existe(Integer id);
//	public Iterable<ServicioInquilino> listaNotasByLogin(String login);
//	public void update(Nota obj);
	

}
