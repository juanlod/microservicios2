package com.jaunos.app.productos.services;

import java.util.List;

import com.jaunos.app.productos.entity.Producto;

public interface ProductoService {

	public List<Producto> findAll();

	public Producto findById(Long id);

	public Producto save(Producto producto);

	public void deleteById(Long id);

}
