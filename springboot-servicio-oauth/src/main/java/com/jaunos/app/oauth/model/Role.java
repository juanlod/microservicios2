package com.jaunos.app.oauth.model;

import java.util.List;

public class Role {

	private Long id;

	private String nombre;

	private List<Usuario> usuarios;

	public Role(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
