import java.util.*;

public class Calculator {
    private Stack<String> stack;
    private int wordSize;

    public Calculator() {
        this.stack = new Stack<String>();
        this.wordSize = 8;
    }

    public Calculator(int wordSize) {
        this.stack = new Stack<String>();
        this.wordSize = wordSize;
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

    public void execute(String operation) {
        int secondValue = Integer.parseInt(stack.pop());
        int firstValue = Integer.parseInt(stack.pop());

        switch (operation) {
        case "+":
            stack.push(String.valueOf(firstValue + secondValue));
            break;
        }

    }

}