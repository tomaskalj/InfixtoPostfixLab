import java.io.PrintWriter;

/**
 * Contains the methods pertaining to evaluating
 * postfix expressions.
 *
 * @author Tomas Kaljevic
 */
public class EvalPostfix {
    private PrintWriter pw;

    /**
     * Initializes the EvalPostfix class with a given {@link PrintWriter}
     * @param pw the {@link PrintWriter} being used to write
     *           the postfix evaluation to our file.
     */
    public EvalPostfix(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * Evaluates a valid postfix expression, prints the evaluation
     * to the user, then writes the output to a specified output file.
     * @param postfix the specified postfix expression
     */
    public void evaluatePostfix(String postfix) {
        if (postfix == null) {
            return;
        }

        System.out.println("Postfix Expression: " + postfix);
        pw.println("Postfix Expression: " + postfix);

        ObjectStack stack = new ObjectStack();

        for (char c : postfix.toCharArray()) {
            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else {
                int op2 = (int) stack.pop();
                int op1 = (int) stack.pop();

                stack.push(performOperation(c, op1, op2));
            }
        }

        int evaluation = (int) stack.top();

        System.out.println("Postfix Evaluation: " + evaluation);
        System.out.println();

        pw.println("Postfix Evaluation: " + evaluation);
        pw.println();
    }

    /**
     * Performs the operations between two operands and an operator in a postfix expression.
     * @param operator the operator being used on the two operands
     * @param op1 the first operand
     * @param op2 the second operand
     * @return the calculation from the operation between the two operands
     */
    private int performOperation(char operator, int op1, int op2) {
        switch (operator) {
            case '+':
                return op1 + op2;
            case '*':
                return op1 * op2;
            case '-':
                return op1 - op2;
            case '/':
                return op1 / op2;
            case '^':
                return (int) Math.pow(op1, op2);
            default:
                return 0;
        }
    }
}
