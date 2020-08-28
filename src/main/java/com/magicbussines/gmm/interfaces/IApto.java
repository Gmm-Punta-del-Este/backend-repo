package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.Apartamento;

public interface IApto {
	public Iterable<Apartamento> List();
	public Optional<Apartamento> Entity(Integer id);
	public Apartamento Save(Apartamento obj); //save funciona para la primera vez o para update
	public void Delete(Integer id);
	public Iterable<Apartamento>  listaApartamentosByPropietario(String documento);
	public Iterable<Apartamento>  listaApartamentosAlquilables(boolean alquilable);
	public Iterable<Apartamento>  listaApartamentosVendibles(boolean vendibles);
	public void update(Apartamento obj);
	
	

}
