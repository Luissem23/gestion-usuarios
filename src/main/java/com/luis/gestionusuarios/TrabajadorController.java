package com.luis.gestionusuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {
        List<Trabajador> trabajadores = trabajadorRepository.findAll();
        model.addAttribute("trabajadores", trabajadores);
        return "lista"; 
    }

    @GetMapping("/trabajadores/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("trabajador", new Trabajador());
        return "formulario";
    }

    @PostMapping("/trabajadores/guardar")
    public String guardarTrabajador(@ModelAttribute Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
        return "redirect:/trabajadores";
    }

    @GetMapping("/trabajadores/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Trabajador trabajador = trabajadorRepository.findById(id).orElse(null);
        model.addAttribute("trabajador", trabajador);
        return "formulario";
    }

    @GetMapping("/trabajadores/eliminar/{id}")
    public String eliminarTrabajador(@PathVariable Long id) {
        trabajadorRepository.deleteById(id);
        return "redirect:/trabajadores";
    }
}
