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
    public void AddingTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("10");
        calc.execute("+");
        assertEquals("15", calc.pop());
    }

    @Test
    public void PeekTest() {
        Calculator calc = engine(8);
        calc.push("2");
        calc.push("3");
        calc.push("4");
        assertEquals("3", calc.peek(1));

    }
}
