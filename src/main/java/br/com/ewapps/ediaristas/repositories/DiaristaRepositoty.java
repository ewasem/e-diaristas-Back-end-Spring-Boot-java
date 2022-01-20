package br.com.ewapps.ediaristas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ewapps.ediaristas.models.Diarista;

public interface DiaristaRepositoty extends JpaRepository<Diarista, Long> {
    
}
