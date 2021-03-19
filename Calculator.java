import java.util.*;


public class Calculator {
    private Stack<String> stack;
    private String wordSize;

    public Calculator() {
        this.stack = new Stack<String>();
        this.wordSize = "8"; 
    }

    public Calculator(String wordSize) {
        this.stack = new Stack<String>();
        this.wordSize = wordSize;
    }

    public void push(String s) {
        stack.push(s);
    }

    public String pop() {
        return stack.pop();
    }
}