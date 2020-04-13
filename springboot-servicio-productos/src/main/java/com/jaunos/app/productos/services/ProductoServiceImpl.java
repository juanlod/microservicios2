package com.jaunos.app.productos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaunos.app.productos.entity.Producto;
import com.jaunos.app.productos.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository repository;

	@Override
	public List<Producto> findAll() {
		return repository.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Producto save(Producto producto) {
		return repository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}
}
