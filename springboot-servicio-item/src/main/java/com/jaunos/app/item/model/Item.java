package com.jaunos.app.item.model;

import lombok.Getter;
import lombok.Setter;

public class Item {

	@Getter
	@Setter
	private Producto producto;

	@Getter
	@Setter
	private Integer cantidad;

	public Item() {
	}

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return cantidad.doubleValue() * producto.getPrecio();
	}
}
