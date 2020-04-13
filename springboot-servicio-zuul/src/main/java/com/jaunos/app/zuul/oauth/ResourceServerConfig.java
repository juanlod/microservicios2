package com.jaunos.app.zuul.oauth;

import javax.swing.text.Highlighter.HighlightPainter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/* ORDEN  CORRECTO */
		// Permitir la ruta del microservicio
		.antMatchers("/api/security/oauth/**")
		// Tipos de permisos
		// Ruta publica
		.permitAll()
		// Ruta de productos e items y el listado de usuarios
		.antMatchers(HttpMethod.GET, 
				"/api/productos/listar", 
				"/api/items/listar", 
				"/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, 
				"/api/productos/ver/{id}", 
				"/api/items/ver/{id}/cantidad/{cantidad}", 
				"/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
		// Simplificada
		.antMatchers( 
				"/api/productos/**", 
				"/api/items/**",
				"/api/usuarios/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		// Manera Completa
//		.antMatchers(HttpMethod.POST, 
//				"/api/productos/crear", 
//				"/api/items/crear",
//				"/api/usuarios/usuarios").hasRole("ADMIN")
//		.antMatchers(HttpMethod.PUT, 
//				"/api/productos/editar/{id}", 
//				"/api/items/editar/{id}",
//				"/api/usuarios/usuarios/{id}").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE, 
//				"/api/productos/editar/{id}", 
//				"/api/items/editar/{id}",
//				"/api/usuarios/usuarios/{id}").hasRole("ADMIN");
	}

	
	/**
	 * Transforma el accesToken en JwtTokenStore
	 * @return JwtTokenStore
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Devuelve un JwsAccesTokenConverter a partir de un codigo secreto
	 * @return JwtAccessTokenConverter'
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("algun_codigo_secreto_aeiou");
		return tokenConverter;
	}
}
