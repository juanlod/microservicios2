package com.jaunos.app.productos.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaunos.app.productos.entity.Producto;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
