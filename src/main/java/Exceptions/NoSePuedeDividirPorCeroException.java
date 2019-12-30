package Exceptions;

public class NoSePuedeDividirPorCeroException extends RuntimeException {
    public NoSePuedeDividirPorCeroException(String mensaje){
        super(mensaje);
    }
}
