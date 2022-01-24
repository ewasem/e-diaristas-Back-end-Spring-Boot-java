package br.com.ewapps.ediaristas.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.ewapps.ediaristas.dtos.ViaCepResponse;
import br.com.ewapps.ediaristas.exceptions.CepInvalidoException;
import br.com.ewapps.ediaristas.exceptions.CepNaoEncontradoException;

@Service
public class ViaCepService {
    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        var url = "https://viacep.com.br/ws/" + cep + "/json/";

        var clienteHttp = new RestTemplate();
        ResponseEntity<ViaCepResponse> response;

        try {
            response = clienteHttp.getForEntity(url, ViaCepResponse.class);
        } catch (HttpClientErrorException.BadRequest.BadRequest e){
            throw new CepInvalidoException("CEP Inválido!");
        }

        if (response.getBody().getCep() == null) {
            throw new CepNaoEncontradoException("CEP não encontrado!");
        }
        
        return response.getBody();
    }
}
