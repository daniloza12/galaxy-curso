package pe.edu.galaxy.galaxy.java.arq.ms.ms_infraestructure_server_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class MsInfraestructureServerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInfraestructureServerAdminApplication.class, args);
	}

}
