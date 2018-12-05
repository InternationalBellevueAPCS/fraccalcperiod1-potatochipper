import java.util.Scanner;
public class FracCalc {
    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	String answerProduced; // for checkpoint 1
    	
    	boolean running = true;
    	while (running) {
    		System.out.println("Enter a problem or \"quit\" to exit. ");
    		String input = console.nextLine();
    		if (input.toLowerCase().contains("quit")) {
    			running = false;
    		} else {
    			answerProduced = produceAnswer(input);
    			System.out.println("Your input: " + answerProduced);
    		}
    	}
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
    	int spacesEncountered = 0;
    	String firstOperand = "";
    	String operator = "";
    	String secondOperand = "";
    	for (int i = 0; i < input.length(); i++) {
    		if (input.charAt(i) == ' ') { 
    			spacesEncountered++;
    			if (spacesEncountered == 1) {
    				firstOperand = input.substring(0, i);
    				operator = input.substring(i, i + 2); // IMPORTANT: the operator is couched btwn spaces, like " + "
    			}
    			if(spacesEncountered == 2) { // so before this comes diff. behaviors at each space number: 1st operand, operation sign, 2nd operand
    				secondOperand = input.substring(i+1);
    			}
    		}
    	}
    	
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        
        return secondOperand;
    }

    // TODO: Fill in the space below with helper methods
    
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
