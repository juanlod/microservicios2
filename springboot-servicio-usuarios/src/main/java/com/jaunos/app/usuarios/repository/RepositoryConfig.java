package com.jaunos.app.usuarios.repository;

import javax.annotation.security.RolesAllowed;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.jaunos.app.usuarios.entity.Role;
import com.jaunos.app.usuarios.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		config.exposeIdsFor(Usuario.class, Role.class);

	}

}
