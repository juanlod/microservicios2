package com.jaunos.app.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	/**
	 * Método que determina los permisos que tendran los endpoint del servidor de autorización
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// Valida las credenciales para autenticarse
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated");
	}

	/**
	 * Método que configura los clientes en el servidor de autorización
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// Registramos un cliente
		clients.inMemory()
		// Nombre aplicacion
		.withClient(env.getProperty("config.security.oauth.client.id"))
		// Passeword
		.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
		// Alcance o permisos
		.scopes("read", "write")
		// Tipo de concesión que tendra la aplicación, es decir, se determina como se va 
		// a obtener el token y se refresca el token antes de que caduque 
		.authorizedGrantTypes("password", "refresh_token")
		// Se determina el tiempo de validez del token
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// Unir accesTokenConverter con la informacion de la clase InfoAdicionalToken
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		
		// Se configura el authentication manager, el token storage (JWT) 
		// y el Access token converter que se encarga de guardar los datos en el token
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		// Añadir la info a la cadena
		.tokenEnhancer(tokenEnhancerChain);
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
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
		return tokenConverter;
	}
	
	
}
