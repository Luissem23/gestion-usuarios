package com.luis.gestionusuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    // Cuenta los trabajadores por estado ("Activo" o "Inactivo")
    long countByEstado(String estado);

    // Cuenta los proyectos únicos, ignorando nulos y vacíos
    @Query("SELECT COUNT(DISTINCT t.proyectoAsignado) FROM Trabajador t WHERE t.proyectoAsignado IS NOT NULL AND t.proyectoAsignado <> ''")
    long countDistinctProyectos();

    // 🔍 Búsqueda por nombre o DNI
    @Query("SELECT t FROM Trabajador t WHERE t.nombreCompleto LIKE %:filtro% OR t.dni LIKE %:filtro%")
    List<Trabajador> buscarPorNombreODni(@Param("filtro") String filtro);
}
