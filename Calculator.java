import java.util.*;

public class Calculator {
    private String[] flags = { "o" };
    private Stack<String> stack = new Stack<String>();
    private int max;
    private int min;
    private int bits;
    private boolean isSigned = false;

    public Calculator(int bits) {
        if (bits < 4 || bits > 64) {
            throw new IndexOutOfBoundsException();
        }
        this.bits = bits;
        max = (int) Math.pow(2.0, Double.valueOf(bits)) - 1;
    }

    public void push(String s) {
        int input = Integer.parseInt(s);
        if(isSigned) {
            if (input < 0) {
                input = (input % max + max) % max;
                flags[0] = "O";
            } 
            else if (input >= max) {
                input = input % max;
                flags[0] = "O";
            }
        } else {
            if (input < min) {
                input = (input % max + max) % max;
                flags[0] = "O";
            } 
            else if (input >= max) {
                input = input % max;
                flags[0] = "O";
            }
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
        } 
        else if (op == "d" || op == "duplicate" || op == "!" || op == "~") {
            firstValue = Integer.parseInt(pop());
        }

        if (op == "+" || op == "-" || op == "*" || op == "/" || op == "%") {
            PushArithmeticResult(firstValue, secondValue, op);
        }
        else if (op == ">>" || op == "<<" || op == "|" || op == "^" || op == "&" || op == "~") {
            PushBitwiseResult(firstValue, secondValue, op);
        }
        else if (op == "==" || op == "!=" || op == "<" || op == ">" || op == "<=" || op == ">=") {
            PushRelationalResult(firstValue, secondValue, op);
        }
        else if (op == "&&" || op == "||" || op == "!") {
            PushLogicalResult(firstValue, secondValue, op);
        }
        else if (op == "clear" || op == "c" || op == "size" || op == "s" || op == "duplicate" || op == "d"
                || op == "reverse" || op == "r") {
            PerformStackCommands(firstValue, secondValue, op);
        }
    }

    public void PushArithmeticResult(int firstValue, int secondValue, String op) {
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

    public void PushBitwiseResult(int firstValue, int secondValue, String op) {
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

    public void PushRelationalResult(int firstValue, int secondValue, String op) {
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

    public void PushLogicalResult(int firstValue, int secondValue, String op) {
        switch (op) {
        case "&&": push((firstValue == secondValue) ? "1" : "0");
            break;
        case "||": push(((firstValue + secondValue) == 0) ? "0" : "1");
            break;
        case "!": push((firstValue == 0) ? "1" : "0");
            break;
        }
    }

    public void PerformStackCommands(int firstValue, int secondValue, String op) {
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

    public void changeMode(String mode) {
        if (mode == "signed") {
            isSigned = true;
            max = (int) (Math.pow(2.0, Double.valueOf(bits)) / 2) * -1;
            min = (int) (Math.pow(2.0, Double.valueOf(bits)) / 2) - 1;
        } else {
            isSigned = false;
            max = (int) Math.pow(2.0, Double.valueOf(bits)) - 1;
            min = 0;
        }
    }

}