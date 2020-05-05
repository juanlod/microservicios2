package com.jaunos.app.oauth.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jaunos.app.oauth.model.Usuario;
import com.jaunos.app.oauth.services.IUsuarioService;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);

		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
		tracer.currentSpan().tag("usuario", "El usuario ".concat(usuario.getUsername().concat(" se ha logueado correctamente")));

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el Login: " + exception.getMessage();
		System.out.println(mensaje);
		log.info(mensaje);
		
		StringBuilder error = new StringBuilder();
		error.append(mensaje);

		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			log.info(String.format("Los intentos actuales son: ".concat(usuario.getIntentos().toString())));
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info(String.format("Los intentos actuales son: ".concat(usuario.getIntentos().toString())));

			error.append(" - Los intentos actuales son: ".concat(usuario.getIntentos().toString()));
			
			
			
			if (usuario.getIntentos() >= 3) {
				String errorIntentos = "El usuario".concat(usuario.getUsername().toString()).concat(" ha sido deshabilitado por máximos intentos");
				usuario.setIntentos(0);
				log.error(String.format("El usuario".concat(usuario.getUsername().toString()).concat(" ha sido deshabilitado por máximos intentos")));
				usuario.setIntentos(0);
				error.append(errorIntentos);
				usuario.setEnabled(false);
			}

			usuarioService.update(usuario, usuario.getId());
			
			tracer.currentSpan().tag("error.mensaje", error.toString());

		} catch (FeignException e) {
			log.error(String.format("El usuario ".concat(authentication.getName().toString()).concat(" no existe en el sistema")));
		}

	}

}
