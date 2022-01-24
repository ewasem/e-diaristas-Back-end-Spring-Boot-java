package br.com.ewapps.ediaristas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ewapps.ediaristas.dtos.ViaCepResponse;
import br.com.ewapps.ediaristas.models.Diarista;
import br.com.ewapps.ediaristas.repositories.DiaristaRepositoty;
import br.com.ewapps.ediaristas.services.ViaCepService;

@RestController
@RequestMapping("/api/diaristas-cidade")
public class DiaristaRestController {

    @Autowired
    private DiaristaRepositoty repositoty;

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping
    public List<Diarista> buscaDiaristasPorCep(@RequestParam String cep) {
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();

        return repositoty.findByCodigoIbge(codigoIbge);
    }
    
}
