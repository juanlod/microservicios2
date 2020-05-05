package com.jaunos.app.oauth.model;

import java.util.List;

public class Usuario {

	private Long id;

	private String username;

	private Boolean enabled;

	private String password;

	private String nombre;

	private String apellido;

	private String email;

	private List<Role> roles;
	
	private Integer intentos;

	public Usuario(Long id, String username, Boolean enabled, String password, String nombre, String apellido,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.enabled = enabled;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public Usuario() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}
	
	
}
