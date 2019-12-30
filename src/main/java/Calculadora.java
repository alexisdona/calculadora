import Exceptions.ExpresionErrorException;
import Exceptions.ExpresionVaciaException;
import Exceptions.NoSePuedeDividirPorCeroException;

import java.util.ArrayList;
import java.util.Stack;

public class Calculadora {
    private String expresionMatematica;
    private ArrayList<Termino> terminos;

    //Evaluo solo que la sintaxis sea correcta al principio o al fin. No evaluo por ejemplo que haya dos simbolos seguidos en el
    //medio de la expresión
    public void evaluarExpresion(String expresion) {
        if (expresion.isEmpty()) {
            throw new ExpresionVaciaException("Debe ingresar una expresión matemática para evaluar");
        }

        //Si el primer caracter es un "*" o un "/" o el último caracter es un operador arrojo error
        if ((expresion.charAt(0)=='*')||
            (expresion.charAt(0)=='/')||
                (!Character.isDigit(expresion.charAt(expresion.length()-1)))) {
            throw new ExpresionErrorException("La expresión informada no pude ser evaluada. Su sintáxis no es correcta");
        }

    }

    //Este método junta los términos de dos operaciones que se pueden realizar
    public Termino operarTerminos(Termino termino1,Termino termino2) {
        if (termino1==null) { return termino2;}
        if (termino2==null) { return termino1;}

        double resultado = this.operar(termino1.getOperando(),termino1.getOperador(),termino2.getOperando());
        Termino tResultado = new Termino(resultado,termino2.getOperador());

        return tResultado;
    }

    public String getExpresionMatematica() {
        return expresionMatematica;
    }

    public void setExpresionMatematica(String expresionMatematica) {
        this.expresionMatematica = expresionMatematica;
    }


    public static int prioridadOperador(Operador operador) {
        switch (operador) {
            case SUMA: return 1;
            case RESTA: return 1;
            case MULTIPLICACION: return 2;
            case DIVISION: return 2;
            case NADA: return 0;
        }
        return 0;
    }
    public void colapsarTope(Operador operadorTope, Stack<Double> pilaDeNumeros, Stack<Operador> PilaDeOperadores) {
        while (PilaDeOperadores.size() > 0 && pilaDeNumeros.size() > 1) {

            if (prioridadOperador(operadorTope) <= prioridadOperador(PilaDeOperadores.peek())) {
                double segundoOperando = pilaDeNumeros.pop();
                double primerOperando = pilaDeNumeros.pop();
                Operador operador = PilaDeOperadores.pop();
                double resultado = operar(primerOperando, operador, segundoOperando);
                pilaDeNumeros.push(resultado);
            } else {
                break;
            }
        }
    }

    public double ejecutar() {
        Stack<Double> pilaDeNumeros = new Stack<Double>();
        Stack<Operador> pilaDeOperadores = new Stack<Operador>();
        evaluarExpresion(this.expresionMatematica);
        ArrayList<Termino> terminos = Termino.armarTerminos(this.expresionMatematica);

        for (int i = 0; i < terminos.size(); i++) {

                /* Get number and push. */
                double operando = terminos.get(i).getOperando();
                pilaDeNumeros.push(operando);
                Operador operador = terminos.get(i).getOperador();


                /* Get operator, collapse top as needed, push operator. */

                colapsarTope(operador, pilaDeNumeros, pilaDeOperadores);
                pilaDeOperadores.push(operador);



        }

    return pilaDeNumeros.peek();
    }
    public double operar(double operando1,Operador operador,double operando2) {

        switch (operador) {
            case SUMA: return operando1+operando2;
            case RESTA: return operando1-operando2;
            case MULTIPLICACION: return operando1*operando2;
            case DIVISION: if (operando2==0) {
                throw new NoSePuedeDividirPorCeroException("No se puede dividir por cero. Revise su expresión");
            }
            else {
                return operando1/operando2;
            }
            default: return operando1;
        }

    }

}


