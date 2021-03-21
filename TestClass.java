import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestClass {

    public Calculator engine(int wordSize) {
        return new Calculator(wordSize);
    }

    @Test
    public void PushAndPopTest() {
        Calculator calc = engine(8);
        calc.push("2");
        assertEquals("2", calc.pop());
    }

    @Test
    public void PeekTest() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        assertEquals("3", calc.peek(1));

    }

    @Test
    public void SizeTest() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        assertEquals(3, calc.size());

    }

    @Test
    public void ClearTest() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        calc.clear();
        assertEquals(0, calc.size());

    }

    @Test
    public void AddingTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("10");
        calc.execute("+");
        assertEquals("15", calc.pop());
    }

    @Test
    public void SubtractionTest() {
        Calculator calc = engine(8);
        calc.push("12");
        calc.push("5");
        calc.execute("-");
        assertEquals("7", calc.pop());
    }

    @Test
    public void MultiplyTest() {
        Calculator calc = engine(8);
        calc.push("10");
        calc.push("5");
        calc.execute("*");
        assertEquals("50", calc.pop());
    }

    @Test
    public void DivideTest() {
        Calculator calc = engine(8);
        calc.push("20");
        calc.push("5");
        calc.execute("/");
        assertEquals("4", calc.pop());
    }

    @Test
    public void ModulusTest() {
        Calculator calc = engine(8);
        calc.push("12");
        calc.push("5");
        calc.execute("%");
        assertEquals("2", calc.pop());
    }

    @Test
    public void ExecuteClear() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        calc.execute("clear");
        assertEquals(0, calc.size());
    }

    @Test
    public void ExecuteSize() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        calc.execute("size");
        assertEquals("3", calc.pop());
    }

    @Test
    public void InvalidExecuteCall() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.execute("+");
        assertEquals("ERROR", calc.peek(0));
    }

    @Test
    public void TooManyOperators() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("2");
        calc.execute("+");
        calc.execute("*");
        calc.execute("/");
        calc.execute("-");
        assertEquals("ERROR", calc.peek(0));
    }

    @Test
    public void DivideByZero() {
        Calculator calc = engine(8);
        calc.push("1");
        calc.push("0");
        calc.execute("/");
        assertEquals("ERROR", calc.peek(0));
    }
}
