package br.com.ewapps.ediaristas.exceptions;

public class CepNaoEncontradoException extends RuntimeException {
    public CepNaoEncontradoException(String message) {
        super(message);
    }
}
