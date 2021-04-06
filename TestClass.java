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
        assertEquals("4", calc.peek(0));

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
        assertEquals("1", calc.pop());
        assertEquals("O", calc.getOverflow());

    }

    @Test
    public void OverflowTest_MULTIPLY() {
        Calculator calc = engine(8);
        calc.push("16");
        calc.push("17");
        calc.execute("*");
        assertEquals("17", calc.pop());
        assertEquals("O", calc.getOverflow());

    }

    @Test
    public void UnderflowTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("8");
        calc.execute("-");
        assertEquals("252", calc.pop());
        assertEquals("O", calc.getOverflow());

    }

    @Test(expected = NumberFormatException.class)
    public void InvalidInput() {
        Calculator calc = engine(8);
        calc.push("Hello");
        calc.push("World");
        calc.execute("+");

    }

    @Test
    public void ShiftRightTest() {
        Calculator calc = engine(8);
        calc.push("8");
        calc.push("1");
        calc.execute(">>");
        assertEquals("4", calc.pop());
    }

    @Test
    public void ShiftRightTest_2() {
        Calculator calc = engine(8);
        calc.push("250");
        calc.push("5");
        calc.execute(">>");
        assertEquals("7", calc.pop());
    }

    @Test
    public void ShiftLeftTest() {
        Calculator calc = engine(8);
        calc.push("50");
        calc.push("2");
        calc.execute("<<");
        assertEquals("200", calc.pop());
    }

    @Test
    public void ORTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("7");
        calc.execute("|");
        assertEquals("7", calc.pop());
    }

    @Test
    public void XORTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("7");
        calc.execute("^");
        assertEquals("2", calc.pop());
    }

    @Test
    public void CMPTest() {
        Calculator calc = engine(8);
        calc.push("35");
        calc.execute("~");
        assertEquals("219", calc.pop());
    }

    @Test
    public void ANDTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("7");
        calc.execute("&");
        assertEquals("5", calc.pop());
    }

    @Test
    public void Equal_1() {
        Calculator calc = engine(8);
        calc.push("10");
        calc.push("10");
        calc.execute("==");
        assertEquals("1", calc.pop());
    }

    @Test
    public void Equal_0() {
        Calculator calc = engine(8);
        calc.push("10");
        calc.push("6");
        calc.execute("==");
        assertEquals("0", calc.pop());
    }

    @Test
    public void UnEqual_1() {
        Calculator calc = engine(8);
        calc.push("10");
        calc.push("7");
        calc.execute("!=");
        assertEquals("1", calc.pop());
    }

    @Test
    public void UnEqual_0() {
        Calculator calc = engine(8);
        calc.push("10");
        calc.push("10");
        calc.execute("!=");
        assertEquals("0", calc.pop());
    }

    @Test
    public void LessThan_1() {
        Calculator calc = engine(8);
        calc.push("4");
        calc.push("7");
        calc.execute("<");
        assertEquals("1", calc.pop());
    }

    @Test
    public void LessThan_0() {
        Calculator calc = engine(8);
        calc.push("11");
        calc.push("10");
        calc.execute("<");
        assertEquals("0", calc.pop());
    }

    @Test
    public void GreaterThan_1() {
        Calculator calc = engine(8);
        calc.push("10");
        calc.push("7");
        calc.execute(">");
        assertEquals("1", calc.pop());
    }

    @Test
    public void GreaterThan_0() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.push("10");
        calc.execute(">");
        assertEquals("0", calc.pop());
    }

    @Test
    public void LessThanOrEqual_1() {
        Calculator calc = engine(8);
        calc.push("7");
        calc.push("7");
        calc.execute("<=");
        assertEquals("1", calc.pop());
    }

    @Test
    public void LessThanOrEqual_0() {
        Calculator calc = engine(8);
        calc.push("11");
        calc.push("10");
        calc.execute("<=");
        assertEquals("0", calc.pop());
    }

    @Test
    public void GreaterThanOrEqual_1() {
        Calculator calc = engine(8);
        calc.push("7");
        calc.push("7");
        calc.execute(">=");
        assertEquals("1", calc.pop());
    }

    @Test
    public void GreaterThanOrEqual_0() {
        Calculator calc = engine(8);
        calc.push("11");
        calc.push("12");
        calc.execute(">=");
        assertEquals("0", calc.pop());
    }

    @Test
    public void LogicalAND_0() {
        Calculator calc = engine(8);
        calc.push("100");
        calc.push("5");
        calc.execute("&&");
        assertEquals("0", calc.pop());
    }

    @Test
    public void LogicalAND_1() {
        Calculator calc = engine(8);
        calc.push("100");
        calc.push("100");
        calc.execute("&&");
        assertEquals("1", calc.pop());
    }

    @Test
    public void duplicateTest() {
        Calculator calc = engine(8);
        calc.push("5");
        calc.execute("duplicate");
        assertEquals("5", calc.peek(0));
        assertEquals("5", calc.peek(1));
    }

    @Test
    public void dTest() {
        Calculator calc = engine(8);
        calc.push("100");
        calc.execute("d");
        assertEquals("100", calc.peek(0));
        assertEquals("100", calc.peek(1));
    }

    @Test
    public void reverseTest() {
        Calculator calc = engine(8);
        calc.push("100");
        calc.push("50");
        calc.execute("reverse");
        assertEquals("100", calc.peek(0));
        assertEquals("50", calc.peek(1));
    }

    @Test
    public void rTest() {
        Calculator calc = engine(8);
        calc.push("75");
        calc.push("2");
        calc.execute("reverse");
        assertEquals("75", calc.peek(0));
        assertEquals("2", calc.peek(1));
    }

    @Test
    public void LogicalOR_0() {
        Calculator calc = engine(8);
        calc.push("0");
        calc.push("0");
        calc.execute("||");
        assertEquals("0", calc.pop());
    }

    @Test
    public void LogicalOR_1() {
        Calculator calc = engine(8);
        calc.push("100");
        calc.push("0");
        calc.execute("||");
        assertEquals("1", calc.pop());
    }

    @Test
    public void LogicalNOT_1() {
        Calculator calc = engine(8);
        calc.push("0");
        calc.execute("!");
        assertEquals("1", calc.pop());
    }

    @Test
    public void LogicalNOT_0() {
        Calculator calc = engine(8);
        calc.push("100");
        calc.execute("!");
        assertEquals("0", calc.pop());
    }



}
