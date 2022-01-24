package br.com.ewapps.ediaristas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ewapps.ediaristas.dtos.DiaristasPagedResponse;
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
    public DiaristasPagedResponse buscaDiaristasPorCep(@RequestParam String cep) {
        var endereco = viaCepService.buscarEnderecoPorCep(cep);
        var codigoIbge = endereco.getIbge();

        var pageable = PageRequest.of(0, 6);
        var diaristas = repositoty.findByCodigoIbge(codigoIbge, pageable);
        var quantidadeDeDiaristas = diaristas.getTotalElements() > 6 ? diaristas.getTotalElements() - 6 : 0;
        return new DiaristasPagedResponse(diaristas.getContent(), quantidadeDeDiaristas);
    }
    
}
