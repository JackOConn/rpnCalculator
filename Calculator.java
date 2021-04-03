import java.util.*;

public class Calculator {
    private String[] flags = { "o", "u" };
    private Stack<String> stack = new Stack<String>();
    private int max;

    public Calculator(int wordSize) {
        if (wordSize < 4 || wordSize > 64) {
            throw new IndexOutOfBoundsException();
        }
        max = (int) Math.pow(2.0, Double.valueOf(wordSize)) - 1;
    }

    public void push(String s) {
        int input = Integer.parseInt(s);

        if (input < 0) {
            input = (input % max + max) % max;
            flags[0] = "O";
        } else if (input >= max) {
            input = input % max;
            flags[1] = "U";
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
        int result = 0;
        int firstValue = 0;
        int secondValue = 0;
        boolean isCleared = false;
        List<String> ops = Arrays.asList("+", "-", "*", "/", "%", ">>", "<<", "|", "^", "&", "==", "!=", "<", ">", "<=", ">=");

        if (ops.contains(operation)) {
            secondValue = Integer.parseInt(stack.pop());
            firstValue = Integer.parseInt(stack.pop());
        }

        switch (operation) {
        case "+":
            result = firstValue + secondValue;
            break;

        case "-":
            result = firstValue - secondValue;
            break;

        case "*":
            result = firstValue * secondValue;
            break;

        case "/":
            result = firstValue / secondValue;
            break;

        case "%":
            result = firstValue % secondValue;
            break;

        case ">>":
            result = firstValue >>> secondValue;
            break;

        case "<<":
            result = firstValue << secondValue;
            break;

        case "|":
            result = firstValue | secondValue;
            break;

        case "^":
            result = firstValue ^ secondValue;
            break;

        case "~":
            result = ~(Integer.parseInt(stack.pop()));
            break;

        case "&":
            result = firstValue & secondValue;
            break;

        case "==":
            if (firstValue == secondValue) {
                result = 1;
            } else {
                result = 0;
            }
            break;

        case "!=":
            if (firstValue != secondValue) {
                result = 1;
            } else {
                result = 0;
            }
            break;

        case "<":
            if (firstValue < secondValue) {
                result = 1;
            } else {
                result = 0;
            }
            break;

        case ">":
            if (firstValue > secondValue) {
                result = 1;
            } else {
                result = 0;
            }
            break;

        case "<=":
            if (firstValue <= secondValue) {
                result = 1;
            } else {
                result = 0;
            }
            break;

        case ">=":
            if (firstValue >= secondValue) {
                result = 1;
            } else {
                result = 0;
            }
            break;

        case "clear":
        case "c":
            isCleared = true;
            stack.clear();
            break;

        case "size":
        case "s":
            result = stack.size();
            break;
        }

        if (result < 0) {
            flags[1] = "U";
            result = (result % max + max) % max;
        } else if (result >= max) {
            flags[0] = "O";
            result = result % max;
        }

        if (!isCleared) {
            stack.push(String.valueOf(result));
        }

    }

    public String getOverflow() {
        return flags[0];
    }

    public String getUnderflow() {
        return flags[1];
    }

}