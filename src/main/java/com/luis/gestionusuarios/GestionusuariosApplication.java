package com.luis.gestionusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class GestionusuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionusuariosApplication.class, args);

		// Abre el navegador automáticamente
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8080/usuarios"));
		} catch (Exception e) {
			System.out.println("No se pudo abrir el navegador automáticamente: " + e.getMessage());
		}
	}
}
