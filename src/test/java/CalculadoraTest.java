import Exceptions.ExpresionErrorException;
import Exceptions.ExpresionVaciaException;
import Exceptions.NoSePuedeDividirPorCeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculadoraTest {
    private String expresion;
    private Calculadora calculadora;
    @Before
    public void iniciarTest() {
        this.expresion = "15+10*5/2";
        this.calculadora = new Calculadora();
        this.calculadora.setExpresionMatematica(expresion);
    }
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void resultadoCorrecto() {
        double resultado = this.calculadora.ejecutar();
        Assert.assertEquals(40,resultado,0);

    }
    @Test
    public void expresionInvalidaException() {
        this.calculadora.setExpresionMatematica("/15*8");
        exception.expect(ExpresionErrorException.class);
        exception.expectMessage("La expresión informada no pude ser evaluada. Su sintáxis no es correcta");
        this.calculadora.ejecutar();
    }

    @Test
    public void expresionVaciaException() {
        this.calculadora.setExpresionMatematica("");
        exception.expect(ExpresionVaciaException.class);
        exception.expectMessage("Debe ingresar una expresión matemática para evaluar");
        this.calculadora.ejecutar();
    }
    @Test
    public void divisionPorCeroException() {
        this.calculadora.setExpresionMatematica("15/0");
        exception.expect(NoSePuedeDividirPorCeroException.class);
        exception.expectMessage("No se puede dividir por cero. Revise su expresión");
        this.calculadora.ejecutar();
    }
}
