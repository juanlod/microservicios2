package com.jaunos.app.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;

	// Otra manera de inyección. Mejor cuando solo hay 1 elemento que inyectar
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				/* ORDEN CORRECTO */
				// Permitir la ruta del microservicio
				.antMatchers("/api/security/oauth/**")
				// Tipos de permisos
				// Ruta publica
				.permitAll()
				// Ruta de productos e items y el listado de usuarios
				.antMatchers(HttpMethod.GET, "/api/productos/listar", "/api/items/listar", "/api/usuarios/usuarios")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/api/productos/ver/{id}", "/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/usuarios/usuarios/{id}")
				.hasAnyRole("ADMIN", "USER")
				// Simplificada
				.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN").anyRequest()
				.authenticated().and().cors().configurationSource(corsConfigurationSource());
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

	// Configuración de CORs
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		// Configurar el origen
		corsConfig.addAllowedOrigin("*");
		// Configurar varios orígenes
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		// Configurar método de acceso. Options lo utiliza oauth2 por debajo
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		// Configurar credenciales
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		// Enviar configuración de cors a la url
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Registrar la configuración
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	// Filtro de CORS a nivel de aplicación
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = 
				new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		// Configurar como prioridad alta
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	/**
	 * Transforma el accesToken en JwtTokenStore
	 * 
	 * @return JwtTokenStore
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Devuelve un JwsAccesTokenConverter a partir de un codigo secreto
	 * 
	 * @return JwtAccessTokenConverter'
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
		return tokenConverter;
	}
}
