package com.luis.gestionusuarios;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class GestionusuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionusuariosApplication.class, args);

		// Abre el navegador automáticamente en /login
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8083/login"));
		} catch (Exception e) {
			System.out.println("No se pudo abrir el navegador automáticamente: " + e.getMessage());
		}
	}

	@Bean
	CommandLineRunner crearUsuariosIniciales(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder) {
		return args -> {
			if (usuarioRepository.findAll().isEmpty()) {
				Usuario supervisor = new Usuario();
				supervisor.setUsername("supervisor");
				supervisor.setPassword(encoder.encode("12345"));
				supervisor.setRol("SUPERVISOR");

				Usuario trabajador = new Usuario();
				trabajador.setUsername("trabajador");
				trabajador.setPassword(encoder.encode("12345"));
				trabajador.setRol("TRABAJADOR");

				usuarioRepository.save(supervisor);
				usuarioRepository.save(trabajador);

				System.out.println("✅ Usuarios iniciales creados.");
			}
		};
	}
}
