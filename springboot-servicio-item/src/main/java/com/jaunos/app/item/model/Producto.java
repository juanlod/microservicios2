package com.jaunos.app.item.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class Producto {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String nombre;

	@Getter
	@Setter
	private Double precio;

	@Getter
	@Setter
	private Date createAt;

	@Getter
	@Setter
	private Integer port;
	
	public Producto() {
		super();
	}

	public Producto(Long id, String nombre, Double precio, Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
	}

}
