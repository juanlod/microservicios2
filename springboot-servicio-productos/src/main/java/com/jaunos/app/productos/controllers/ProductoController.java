package com.jaunos.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jaunos.app.productos.entity.Producto;
import com.jaunos.app.productos.services.ProductoService;

@RestController
public class ProductoController {

//	@Autowired
//	private Environment environment;
//	
//	@Autowired
//	private ProductoService productoService;
//
//	@GetMapping("/listar")
//	public List<Producto> obtenerProductos() {
//		return productoService.findAll().stream().map(producto -> {
//			producto.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
//			return producto;
//		}).collect(Collectors.toList());
//	}
//	
//	@GetMapping("/ver/{id}")
//	public Producto obtenerProducto(@PathVariable Long id) {
//		Producto producto = productoService.findById(id);
//		producto.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
//		return producto;
//	}

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private ProductoService productoService;

	@GetMapping("/listar")
	public List<Producto> obtenerProductos() {
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto obtenerProducto(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(port);
//		boolean ok = false;
//		
//		if (ok == false) {
//			throw new Exception("No se puede cargar el producto");
//		}
//		
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return producto;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto producto2 = productoService.findById(id);
		producto2.setNombre(producto.getNombre());
		producto2.setPrecio(producto.getPrecio());

		return productoService.save(producto2);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
