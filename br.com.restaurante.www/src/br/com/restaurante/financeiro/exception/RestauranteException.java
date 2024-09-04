package br.com.restaurante.financeiro.exception;

public class RestauranteException extends Exception {
    public RestauranteException(String message) {
        super(message);
    }

    public RestauranteException(String message, Throwable cause) {
        super(message, cause);
    }
}
