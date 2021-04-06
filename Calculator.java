import java.util.*;

public class Calculator {
    private String[] flags = { "o" };
    private Stack<String> stack = new Stack<String>();
    private int max;
    private boolean isPushed;

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
            flags[0] = "O";
        }

        stack.push(s);
    }

    public String pop() {
        return stack.pop();
    }

    public String peek(int index) {
        if (index == 0) {
            return stack.get(stack.size() - 1);
        }
        return stack.get(stack.size() - 1 - index);
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public void execute(String op) {
        int result = 0;
        int firstValue = 0;
        int secondValue = 0;
        List<String> opsWithTwoValues = Arrays.asList("+", "-", "*", "/", "%", ">>", "<<", "|", "^", "&", "==", "!=",
                "<", ">", "<=", ">=", "&&", "||", "reverse", "r");

        if (opsWithTwoValues.contains(op)) {
            secondValue = Integer.parseInt(stack.pop());
            firstValue = Integer.parseInt(stack.pop());
        } else if (op == "d" || op == "duplicate" || op == "!" || op == "~")
            firstValue = Integer.parseInt(stack.pop());

        if (op == "+" || op == "-" || op == "*" || op == "/" || op == "%")
            result = BasicArithmeticCalculation(firstValue, secondValue, op);
        else if (op == ">>" || op == "<<" || op == "|" || op == "^" || op == "&" || op == "~")
            result = BitwiseCalculation(firstValue, secondValue, op);
        else if (op == "==" || op == "!=" || op == "<" || op == ">" || op == "<=" || op == ">=")
            result = RelationalCalculation(firstValue, secondValue, op);
        else if (op == "&&" || op == "||" || op == "!")
            result = LogicalCalculation(firstValue, secondValue, op);
        else if (op == "clear" || op == "c" || op == "size" || op == "s" || op == "duplicate" || op == "d"
                || op == "reverse" || op == "r")
            result = StackOperations(firstValue, secondValue, op);

        if (result < 0) {
            flags[0] = "O";
            result = (result % max + max) % max;
        } else if (result >= max) {
            flags[0] = "O";
            result = result % max;
        }

        if (!isPushed)
            stack.push(String.valueOf(result));
    }

    public int BasicArithmeticCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case "+":
            return (firstValue + secondValue);

        case "-":
            return (firstValue - secondValue);

        case "*":
            return (firstValue * secondValue);

        case "/":
            return (firstValue / secondValue);

        case "%":
            return (firstValue % secondValue);

        default:
            return 0;
        }
    }

    public int BitwiseCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case ">>":
            return firstValue >>> secondValue;

        case "<<":
            return firstValue << secondValue;

        case "|":
            return firstValue | secondValue;

        case "^":
            return firstValue ^ secondValue;

        case "&":
            return firstValue & secondValue;

        case "~":
            return ~firstValue;

        default:
            return 0;
        }
    }

    public int RelationalCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case "==":
            return (firstValue == secondValue) ? 1 : 0;

        case "!=":
            return (firstValue != secondValue) ? 1 : 0;

        case "<":
            return (firstValue < secondValue) ? 1 : 0;

        case ">":
            return (firstValue > secondValue) ? 1 : 0;

        case "<=":
            return (firstValue <= secondValue) ? 1 : 0;

        case ">=":
            return (firstValue >= secondValue) ? 1 : 0;

        default:
            return 0;
        }
    }

    public int LogicalCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case "&&":
            return (firstValue == secondValue) ? 1 : 0;

        case "||":
            return ((firstValue + secondValue) == 0) ? 0 : 1;

        case "!":
            return (firstValue == 0) ? 1 : 0;

        default:
            return 0;
        }
    }

    public int StackOperations(int firstValue, int secondValue, String op) {
        switch (op) {
        case "clear":
        case "c":
            isPushed = true;
            stack.clear();
            return 0;

        case "size":
        case "s":
            return stack.size();

        case "duplicate":
        case "d":
            isPushed = true;
            stack.push(String.valueOf(firstValue));
            stack.push(String.valueOf(firstValue));
            return 0;

        case "reverse":
        case "r":
            isPushed = true;
            stack.push(String.valueOf(secondValue));
            stack.push(String.valueOf(firstValue));
            return 0;

        default:
            return 0;
        }
    }

    public String getOverflow() {
        return flags[0];
    }

}