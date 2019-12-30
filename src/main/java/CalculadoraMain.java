import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculadoraMain {

    public static void main(String[] args) {
       String expresion = new String("1+2*5+100/10*5*8*7-1");
       Calculadora calc = new Calculadora();
       calc.setExpresionMatematica(expresion);
       System.out.println("El resultado es: "+calc.ejecutar());

    }
}

