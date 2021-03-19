import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestClass {

    private final Calculator calc = new Calculator("8");

    @Test
    public void PushTest(){
        calc.push("2");
        String result = calc.pop();
        assertEquals("2", result);
    }

}
