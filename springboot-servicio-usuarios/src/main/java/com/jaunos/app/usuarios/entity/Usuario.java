package com.jaunos.app.usuarios.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private Boolean enabled;

	@Column
	private String password;

	@Column
	private String nombre;

	@Column
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	// Personalización
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"),
			// 1 usuario no puede tener rol repetido
			uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
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
		// TODO Auto-generated constructor stub
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 7849077512000661568L;

}
