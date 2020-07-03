import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Contains the methods pertaining to conversions
 * between infix and postfix expressions.
 *
 * @author Tomas Kaljevic
 */
public class InfixToPostfix {
    private PrintWriter pw;

    /**
     * Initializes the InfixToPostfix class with a given {@link PrintWriter}
     * @param pw the {@link PrintWriter} being used to write
     *           the postfix expression to our file.
     */
    public InfixToPostfix(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * Validates and converts an infix expression to postfix
     * notation, prints the output to the user, then writes
     * the output to a specified output file.
     * @param infix the specified infix expression
     * @return the postfix expression of the specified infix expression
     */
    public String convertInfixToPostfix(String infix) {
        String validInfix = checkValidInfix(infix);
        if (validInfix != null) {
            System.out.println("Invalid Infix Expression: " + infix);
            System.out.println("Reason: " + validInfix);
            System.out.println();

            pw.println("Invalid Infix Expression: " + infix);
            pw.println("Reason: " + validInfix);
            pw.println();
            return null;
        }

        System.out.println("Infix Expression: " + infix);
        pw.println("Infix Expression: " + infix);

        StringBuilder postfix = new StringBuilder();
        ObjectStack stack = new ObjectStack();

        for (char c : infix.toCharArray()) {
            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty()) {
                    char popped = (char) stack.pop();
                    if (popped == '(') {
                        break;
                    }

                    postfix.append(popped);
                }
            } else {
                while (!stack.isEmpty() && priority((char) stack.top()) >= priority(c)) {
                    postfix.append(stack.pop());
                }

                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    /**
     * Checks the priority of an operator in an infix expression.
     * @param op the provided operator
     * @return the priority of the provided operator
     */
    private int priority(char op) {
        switch (op) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Checks whether an infix expression is valid to convert to postfix.
     * @param infix the specified infix expression
     * @return null if the infix expression is valid, or the reason the expression is invalid
     */
    private String checkValidInfix(String infix) {
        List<Character> operator = Arrays.asList('^', '*', '/', '+', '-');

        infix = infix.replace(" ", "");

        for (int i = 0; i < infix.length() - 1; i++) {
            // Check for adjacent operators
            if (operator.contains(infix.charAt(i)) && operator.contains(infix.charAt(i + 1))) {
                return "Adjacent Operators";
            }

            // Check for adjacent operands
            if (Character.isDigit(infix.charAt(i)) && Character.isDigit(infix.charAt(i + 1))) {
                return "Adjacent operands";
            }
        }

        ObjectStack stack = new ObjectStack();
        for (char c : infix.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && (char) stack.top() == '(') {
                    stack.pop();
                } else {
                    return "Extra Parenthesis";
                }
            }
        }

        if (!stack.isEmpty() && (char) stack.top() == '(') {
            return "Missing Parenthesis";
        }

        return null;
    }
}
