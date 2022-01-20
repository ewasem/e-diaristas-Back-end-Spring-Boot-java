package br.com.ewapps.ediaristas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ewapps.ediaristas.models.Diarista;
import br.com.ewapps.ediaristas.repositories.DiaristaRepositoty;

@Controller
@RequestMapping("/admin/diaristas")
public class DiaristasController {
    
    @Autowired
    private DiaristaRepositoty repositoty;

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/diaristas/form");
        modelAndView.addObject("diarista", new Diarista());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadstrar(Diarista diarista) {
        repositoty.save(diarista);
        return "redirect:/admin/diaristas/cadastrar";
    }
}