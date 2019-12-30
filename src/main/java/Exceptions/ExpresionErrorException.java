package Exceptions;

public class ExpresionErrorException extends RuntimeException {
    public ExpresionErrorException(String mensaje) {
        super(mensaje);
    }
}
