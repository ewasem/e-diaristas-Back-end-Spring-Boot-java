package br.com.ewapps.ediaristas.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ewapps.ediaristas.models.Diarista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaristasPagedResponse {
    
    private List<Diarista> diaristas;

    @JsonProperty("quantidade_diaristas")
    private Long quantidadeDeDiaristas;

}
