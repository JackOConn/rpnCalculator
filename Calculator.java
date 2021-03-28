import java.util.*;

public class Calculator {
    private String[] flags = { "o", "u" };
    private Stack<String> stack;
    private int max;

    public Calculator(int wordSize) {
        if (wordSize < 4) {
            wordSize = 4;
        } else if (wordSize > 64) {
            wordSize = 64;
        }

        this.stack = new Stack<String>();
        this.max = (int) Math.pow(2.0, Double.valueOf(wordSize));
    }

    public void push(String s) {
        int input = Integer.parseInt(s);

        if (input < 0 || input >= max) {
            flags[0] = "O";
        }

        stack.push(s);

    }

    public String pop() {
        return stack.pop();
    }

    public String peek(int index) {
        return stack.get(index);
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public void execute(String operation) {
        int result;
        int firstValue = 0;
        int secondValue = 0;

        if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")
                || operation.equals("%")) {
            secondValue = Integer.parseInt(stack.pop());
            firstValue = Integer.parseInt(stack.pop());
        }

        switch (operation) {
        case "+":
            result = firstValue + secondValue;
            if (result >= max) {
                flags[0] = "O";
                result = result % (max);
            }
            stack.push(String.valueOf(result));
            break;

        case "-":
            result = firstValue - secondValue;
            if (result < 0) {
                flags[1] = "U";
                result = (result % max + max) % max;
            }
            stack.push(String.valueOf(result));
            break;

        case "*":
            result = firstValue * secondValue;
            if (result >= max) {
                flags[0] = "O";
                result = result % (max);
            }
            stack.push(String.valueOf(result));
            break;

        case "/":
            stack.push(String.valueOf(firstValue / secondValue));
            break;

        case "%":
            stack.push(String.valueOf(firstValue % secondValue));
            break;

        case "clear":
        case "c":
            stack.clear();
            break;

        case "size":
        case "s":
            stack.push(String.valueOf(stack.size()));
            break;
        }

    }

    public String getOverflow() {
        return flags[0];
    }

    public String getUnderflow() {
        return flags[1];
    }

}