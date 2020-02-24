package com.jaunos.app.cloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringbootServicioCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioCloudConfigServerApplication.class, args);
	}

}
