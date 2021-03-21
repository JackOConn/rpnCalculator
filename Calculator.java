import java.util.*;

public class Calculator {
    private Stack<String> stack;
    private int wordSize;
    private int max;

    public Calculator() {
        this.stack = new Stack<String>();
        this.wordSize = 8;
        this.max = 255;
    }

    public Calculator(int wordSize) {
        this.stack = new Stack<String>();
        this.wordSize = wordSize;
        this.max = (int) Math.pow(2.0, Double.valueOf(wordSize)) - 1;
    }

    public void push(String s) {
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
        int firstValue = 0;
        int secondValue = 0;

        if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")
                || operation.equals("%")) {
            try {
                secondValue = Integer.parseInt(stack.pop());
                firstValue = Integer.parseInt(stack.pop());
            } catch (Exception e) {
                stack.clear();
                stack.push("ERROR");
                return;
            }
        }

        switch (operation) {
        case "+":
            stack.push(String.valueOf(firstValue + secondValue));
            break;

        case "-":
            stack.push(String.valueOf(firstValue - secondValue));
            break;

        case "*":
            stack.push(String.valueOf(firstValue * secondValue));
            break;

        case "/":
            if (secondValue == 0) {
                stack.clear();
                stack.push("UNDEFINED");
            } else {
                stack.push(String.valueOf(firstValue / secondValue));
            }
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

}