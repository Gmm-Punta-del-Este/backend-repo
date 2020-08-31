package com.magicbussines.gmm.interfaces;

import java.util.Optional;

import com.magicbussines.gmm.model.Consumo;

public interface IConsumo {
	public Iterable<Consumo> List();
	public Optional<Consumo> Entity(Integer id);
	public Consumo Save(Consumo obj); //save funciona para la primera vez o para update
	public void Delete(Integer id);
	public Iterable<Consumo>  listaConsumosByPropietario(String documento);
	public Iterable<Consumo>  listaConsumosByApto(int idApto);
	public void update(Consumo obj);
}
