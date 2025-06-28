package com.luis.gestionusuarios;

import org.springframework.data.jpa.repository.JpaRepository;

// <Usuario, Long> = Clase que maneja + tipo de su ID
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
