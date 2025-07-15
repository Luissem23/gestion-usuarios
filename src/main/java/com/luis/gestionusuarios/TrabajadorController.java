package com.luis.gestionusuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    // Mostrar lista
    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {
        List<Trabajador> trabajadores = trabajadorRepository.findAll();
        model.addAttribute("trabajadores", trabajadores);
        return "lista"; 
    }

    // Mostrar formulario para nuevo
    @GetMapping("/trabajadores/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("trabajador", new Trabajador());
        return "formulario";
    }

    // Guardar trabajador (crear o editar)
    @PostMapping("/trabajadores/guardar")
    public String guardarTrabajador(@ModelAttribute Trabajador trabajador,
                                    RedirectAttributes redirectAttributes) {
        trabajadorRepository.save(trabajador);
        redirectAttributes.addFlashAttribute("mensaje", "✅ Trabajador guardado exitosamente.");
        return "redirect:/trabajadores";
    }

    // Mostrar formulario de edición
    @GetMapping("/trabajadores/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Trabajador trabajador = trabajadorRepository.findById(id).orElse(null);
        model.addAttribute("trabajador", trabajador);
        return "formulario";
    }

    // Eliminar trabajador
    @GetMapping("/trabajadores/eliminar/{id}")
    public String eliminarTrabajador(@PathVariable Long id,
                                     RedirectAttributes redirectAttributes) {
        trabajadorRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "🗑️ Trabajador eliminado correctamente.");
        return "redirect:/trabajadores";
    }

    // Buscar por nombre o DNI
    @GetMapping("/trabajadores/buscar")
    public String buscarTrabajadores(@RequestParam("filtro") String filtro, Model model) {
        List<Trabajador> trabajadoresFiltrados = trabajadorRepository.buscarPorNombreODni(filtro);
        model.addAttribute("trabajadores", trabajadoresFiltrados);
        return "lista";
    }
}
