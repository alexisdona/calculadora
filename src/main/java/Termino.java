import java.util.ArrayList;

public class Termino {

    private Operador operador;
    private double operando;

    public Termino(double operando, Operador operador) {
        this.operando = operando;
        this.operador = operador;
    }

    public double getOperando() {
        return operando;
    }

    public void setOperando(double operando) {
        this.operando = operando;
    }

    public Operador getOperador() {
        return operador;
    }
    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public static Operador parsearOperador(char operador) {
        switch (operador) {
            case '+':
                return Operador.SUMA;
            case '-':
                return Operador.RESTA;
            case '*':
                return Operador.MULTIPLICACION;
            case '/':
                return Operador.DIVISION;
            default: return Operador.NADA;
        }

    }

    //
    public static ArrayList<Termino> armarTerminos(String expresion) {
        ArrayList<Termino> terminos = new ArrayList<Termino>();
        Operador operador;
        int indice = 0;
        String numero = "";
        while (indice < expresion.length()) {
            int subIndice = indice;
            while (subIndice<expresion.length() && Character.isDigit(expresion.charAt(subIndice))) {
                numero = numero + expresion.charAt(subIndice);
                subIndice++;
            }
            if (subIndice<expresion.length()) {
                  operador = parsearOperador(expresion.charAt(subIndice));
            }
            else{
                  operador = Operador.NADA;
            }
            Termino termino = new Termino(Double.parseDouble(numero),operador );
            terminos.add(termino);
            numero="";
            indice = subIndice+1;
        }

        return terminos;
    }


}





