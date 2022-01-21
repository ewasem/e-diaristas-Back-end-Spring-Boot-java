package br.com.ewapps.ediaristas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("admin/diaristas/listar");
        modelAndView.addObject("diaristas", repositoty.findAll());
        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/diaristas/form");
        modelAndView.addObject("diarista", new Diarista());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadstrar(@Valid Diarista diarista, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        } else {
            repositoty.save(diarista);
            return "redirect:/admin/diaristas";
        }
        
        
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/diaristas/form");
        modelAndView.addObject("diarista", repositoty.getById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid Diarista diarista, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        } else {
            repositoty.save(diarista);
            return "redirect:/admin/diaristas";
        }
        
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repositoty.deleteById(id);

        return "redirect:/admin/diaristas";
    }
}
