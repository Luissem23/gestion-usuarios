package com.luis.gestionusuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final TrabajadorRepository trabajadorRepository;

    @Autowired
    public LoginController(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Renderiza login.html
    }

    @GetMapping("/")
    public String inicio(Model model) {
        long total = trabajadorRepository.count();
        long activos = trabajadorRepository.countByEstado("Activo");
        long inactivos = trabajadorRepository.countByEstado("Inactivo");
        long proyectos = trabajadorRepository.countDistinctProyectos();

        model.addAttribute("total", total);
        model.addAttribute("activos", activos);
        model.addAttribute("inactivos", inactivos);
        model.addAttribute("proyectos", proyectos);

        return "inicio"; // Renderiza inicio.html
    }
}
