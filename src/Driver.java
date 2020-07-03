import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Project Title: Infix to Postfix Notation
 * Project Description: Program to convert from infix to postfix and evaluate postfix expressions.
 * Version or Date: March 8, 2019
 * How to Start the Project: Run the Driver.java main method.
 * Palomar ID: 012173849
 *
 * @author Tomas Kaljevic
 */
public class Driver {
    /**
     * Starts the application and converts the infix expressions in infix.txt to postfix,
     * then evaluates the converted postfix expressions.
     * @param args the system arguments being passed in
     * @throws IOException if there is a problem reading or writing data
     */
    public static void main(String[] args) throws IOException {
        Scanner fileScan = new Scanner(new File("infix.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));

        InfixToPostfix postfixConverter = new InfixToPostfix(pw);
        EvalPostfix postfixEvaluator = new EvalPostfix(pw);

        while (fileScan.hasNext()) {
            String buf = fileScan.nextLine();
            String postfix = postfixConverter.convertInfixToPostfix(buf);
            postfixEvaluator.evaluatePostfix(postfix);
        }

        fileScan.close();
        pw.close();
    }
}
