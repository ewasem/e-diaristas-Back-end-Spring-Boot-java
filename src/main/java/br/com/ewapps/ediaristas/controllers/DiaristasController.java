package br.com.ewapps.ediaristas.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ewapps.ediaristas.models.Diarista;
import br.com.ewapps.ediaristas.repositories.DiaristaRepositoty;
import br.com.ewapps.ediaristas.services.FileService;
import br.com.ewapps.ediaristas.services.ViaCepService;
import br.com.ewapps.ediaristas.validators.CepValidator;

@Controller
@RequestMapping("/admin/diaristas")
public class DiaristasController {
    
    @Autowired
    private DiaristaRepositoty repositoty;

    @Autowired
    private FileService fileService;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private CepValidator cepValidator;

    @InitBinder("diarista")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(cepValidator);
    }

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
    public String cadstrar(@RequestParam MultipartFile imagem, @Valid Diarista diarista, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        }

        var filename = fileService.salvar(imagem);
        diarista.setFoto(filename);

        var cep = diarista.getCep();
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);
        repositoty.save(diarista);
        return "redirect:/admin/diaristas";
        
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/diaristas/form");
        modelAndView.addObject("diarista", repositoty.getById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@RequestParam MultipartFile imagem, @PathVariable Long id, @Valid Diarista diarista, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "admin/diaristas/form";
        }

        var diaristaAtual = repositoty.getById(id);
        if (imagem.isEmpty()) {
            diarista.setFoto(diaristaAtual.getFoto());
        } else {
            var filename = fileService.salvar(imagem);
            diarista.setFoto(filename);
        }
        var cep = diarista.getCep();
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();
        diarista.setCodigoIbge(codigoIbge);
        repositoty.save(diarista);
        return "redirect:/admin/diaristas";
        
        
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repositoty.deleteById(id);

        return "redirect:/admin/diaristas";
    }
}
