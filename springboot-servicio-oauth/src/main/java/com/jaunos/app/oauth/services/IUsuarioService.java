package com.jaunos.app.oauth.services;

import com.jaunos.app.oauth.model.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public Usuario update(Usuario usuario, Long id);
}
