package br.com.ewapps.ediaristas.models;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.ewapps.ediaristas.converters.CepConverter;
import br.com.ewapps.ediaristas.converters.CpfConverter;
import br.com.ewapps.ediaristas.converters.TelefoneConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Diarista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @NotNull
    @Size(min = 11, max = 14)
    @CPF
    @Column(nullable = false, unique = true, length = 11)
    @Convert(converter = CpfConverter.class)
    private String cpf;

    @NotNull
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 11, max = 15)
    @Column(nullable = false, length = 11)
    @Convert(converter = TelefoneConverter.class)
    private String telefone;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String logradouro;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String numero;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String bairro;

    @Column(nullable = true)
    private String complemento;

    @NotNull
    @Size(min = 8, max = 9)
    @Column(nullable = false, length = 8)
    @Convert(converter = CepConverter.class)
    private String cep;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(nullable = false, length = 2)
    private String estado;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String codigoIbge;

}
