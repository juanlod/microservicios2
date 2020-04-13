package com.jaunos.app.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.jaunos.app.usuarios.entity.Usuario;

// Path donde se va a exportar el repositorio
@RepositoryRestResource(path="usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);

	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerUsuarioPorUsername(String username);

}
