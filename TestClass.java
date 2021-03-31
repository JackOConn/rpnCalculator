import org.graalvm.compiler.hotspot.stubs.OutOfBoundsExceptionStub;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

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
    public void SimpleEquation() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        calc.execute("+");
        calc.execute("*");
        assertEquals("14", calc.pop());
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

    @Test(expected = EmptyStackException.class)
    public void InvalidExecuteCall() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.execute("+");
    }

    @Test(expected = EmptyStackException.class)
    public void TooManyOperators() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("2");
        calc.execute("+");
        calc.execute("*");
        calc.execute("/");
        calc.execute("-");
    }

    @Test(expected = ArithmeticException.class)
    public void DivideByZero() {
        Calculator calc = engine(8);
        calc.push("1");
        calc.push("0");
        calc.execute("/");
    }

    @Test
    public void OverflowTest_ADD() {
        Calculator calc = engine(8);
        calc.push("255");
        calc.push("1");
        calc.execute("+");
        assertEquals("0", calc.pop());
        assertEquals("O", calc.getOverflow());

    }

    @Test
    public void OverflowTest_MULTIPLY() {
        Calculator calc = engine(8);
        calc.push("16");
        calc.push("17");
        calc.execute("*");
        assertEquals("16", calc.pop());
        assertEquals("O", calc.getOverflow());

    }

    @Test
    public void UnderflowTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("8");
        calc.execute("-");
        assertEquals("253", calc.pop());
        assertEquals("U", calc.getUnderflow());

    }

    @Test(expected = NumberFormatException.class)
    public void InvalidInput() {
        Calculator calc = engine(8);
        calc.push("Hello");
        calc.push("World");
        calc.execute("+");

    }


}
