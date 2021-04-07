import java.util.*;

public class Calculator {
    private String[] flags = { "o" };
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
            flags[0] = "O";
        }

        stack.push(String.valueOf(input));
    }

    public String pop() {
        return stack.pop();
    }

    public String peek(int index) {
        return stack.get(stack.size() - 1 - index);
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public void execute(String op) {
        int firstValue = 0;
        int secondValue = 0;
        List<String> opsWithTwoValues = Arrays.asList("+", "-", "*", "/", "%", ">>", "<<", "|", "^", "&", "==", "!=",
                "<", ">", "<=", ">=", "&&", "||", "reverse", "r");

        if (opsWithTwoValues.contains(op)) {
            secondValue = Integer.parseInt(pop());
            firstValue = Integer.parseInt(pop());
        } else if (op == "d" || op == "duplicate" || op == "!" || op == "~") {
            firstValue = Integer.parseInt(pop());
        }

        if (op == "+" || op == "-" || op == "*" || op == "/" || op == "%") {
            BasicArithmeticCalculation(firstValue, secondValue, op);
        }
        else if (op == ">>" || op == "<<" || op == "|" || op == "^" || op == "&" || op == "~") {
            BitwiseCalculation(firstValue, secondValue, op);
        }
        else if (op == "==" || op == "!=" || op == "<" || op == ">" || op == "<=" || op == ">=") {
            RelationalCalculation(firstValue, secondValue, op);
        }
        else if (op == "&&" || op == "||" || op == "!") {
            LogicalCalculation(firstValue, secondValue, op);
        }
        else if (op == "clear" || op == "c" || op == "size" || op == "s" || op == "duplicate" || op == "d"
                || op == "reverse" || op == "r") {
            StackOperations(firstValue, secondValue, op);
        }
    }

    public void BasicArithmeticCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case "+": push(String.valueOf(firstValue + secondValue));
            break;
        case "-": push(String.valueOf(firstValue - secondValue));
            break;
        case "*": push(String.valueOf(firstValue * secondValue));
            break;
        case "/": push(String.valueOf(firstValue / secondValue));
            break;
        case "%": push(String.valueOf(firstValue % secondValue));
            break;

        }
    }

    public void BitwiseCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case ">>": push(String.valueOf(firstValue >>> secondValue));
            break;
        case "<<": push(String.valueOf(firstValue << secondValue));
            break;
        case "|": push(String.valueOf(firstValue | secondValue));
            break;
        case "^": push(String.valueOf(firstValue ^ secondValue));
            break;
        case "&": push(String.valueOf(firstValue & secondValue));
            break;
        case "~": push(String.valueOf(~firstValue));
            break;
        }
    }

    public void RelationalCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case "==": push((firstValue == secondValue) ? "1" : "0");
            break;
        case "!=": push((firstValue != secondValue) ? "1" : "0");
            break;
        case "<": push((firstValue < secondValue) ? "1" : "0");
            break;
        case ">": push((firstValue > secondValue) ? "1" : "0");
            break;
        case "<=": push((firstValue <= secondValue) ? "1" : "0");
            break;
        case ">=": push((firstValue >= secondValue) ? "1" : "0");
            break;
        }
    }

    public void LogicalCalculation(int firstValue, int secondValue, String op) {
        switch (op) {
        case "&&": push((firstValue == secondValue) ? "1" : "0");
            break;
        case "||": push(((firstValue + secondValue) == 0) ? "0" : "1");
            break;
        case "!": push((firstValue == 0) ? "1" : "0");
            break;
        }
    }

    public void StackOperations(int firstValue, int secondValue, String op) {
        switch (op) {
        case "clear": case "c": clear();
            break;
        case "size": case "s": push(String.valueOf(size()));
            break;
        case "duplicate": case "d":
            push(String.valueOf(firstValue));
            push(String.valueOf(firstValue));
            break;
        case "reverse": case "r":
            push(String.valueOf(secondValue));
            push(String.valueOf(firstValue));
            break;
        }
    }

    public String flags() {
        return flags[0];
    }

}