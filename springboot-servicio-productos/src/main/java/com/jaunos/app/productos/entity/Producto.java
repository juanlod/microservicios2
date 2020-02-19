package com.jaunos.app.productos.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;

	@Getter @Setter private String nombre;

	@Getter @Setter private Double precio;

	@Getter
	@Setter
	@Transient
	private Integer port;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	@Getter @Setter private Date createAt;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976154884915926488L;

}
