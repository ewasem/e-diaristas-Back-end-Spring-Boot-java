package br.com.ewapps.ediaristas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ewapps.ediaristas.models.Diarista;

public interface DiaristaRepositoty extends JpaRepository<Diarista, Long> {
    Page<Diarista> findByCodigoIbge(String codigoIbge, Pageable pageable);
}
