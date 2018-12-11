import java.util.Scanner;
public class FracCalc {
    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	// String answerProduced; for checkpoint 1, will remove later
    	
    	System.out.println("Please enter spaces between operands and the operator.");
    	
    	boolean running = true;
    	while (running) {
    		System.out.println("Enter a problem or \"quit\" to exit. ");
    		String input = console.nextLine();
    		if (input.toLowerCase().contains("quit")) {
    			running = false;
    		} /*else {
    			answerProduced = produceAnswer(input);
    			System.out.println("Your input: " + answerProduced);
    		} Removed since it's an artifact from Checkpoint 1 */
    		else {
    			System.out.println(produceAnswer(input)); // This is where the program prints the answer, even though it's produced in produceAnswer
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
    	int spacesEncountered = 0; // It identifies each part of the equation by how many spaces come before them
    	String firstOperand = "";
    	String operator = "";
    	String secondOperand = "";
    	
    	for (int i = 0; i < input.length(); i++) {
    		if (input.charAt(i) == ' ') { 
    			spacesEncountered++; // Obviously if it sees a space it'll mark that it's seen one
    			if (spacesEncountered == 1) { // like this, it won't work at all if there are no spaces.
    				firstOperand = input.substring(0, i); // From beginning to space 1 (before operator): operand 1
    				operator = Character.toString(input.charAt(i+1)); // And then we know that there's a space, and then the operator.
    			}
    			if(spacesEncountered == 2) { // After the operator, there's another space.
    				secondOperand = input.substring(i+1); // Then comes the second operand.
    			}
    		}
    	}
    	
    	// After the processing that happens in the String traversal for loop, we've separated the input into parts
    	// Now it's time to break these parts down further into their components
    	// boolean positive1 = isPositive(firstOperand); // These four variables are all for the 1st operand 
    	int whole1 = findWhole(firstOperand); 
    	int numerator1 = findNume(firstOperand);
    	int denominator1 = findDen(firstOperand);
    	
    	// boolean positive2 = isPositive(secondOperand); // These four variables are all for the 2nd operand
    	int whole2 = findWhole(secondOperand); 
    	int numerator2 = findNume(secondOperand);
    	int denominator2 = findDen(secondOperand);
    	
    	String returnForTest = "whole:" + whole2 + " numerator:" + numerator2 + " denominator:" + denominator2;
    	// System.out.println(returnForTest);
    	// Those were for Checkpoint 2. Remove later.
    	
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	
    	// Now, it starts calculating the unsimplified result based on which operator was used.
        // First, it converts mixed numbers to improper fractions for both operands.
    	if (whole1 != 0) {
    		numerator1 += whole1 * denominator1;
    		whole1 = 0; // Have to get rid of the whole number after converting to improper fraction
    	}
    	if (whole2 != 0) { // same process as for the first operand
    		numerator2 += whole2 * denominator2;
    		whole2 = 0; 
    	}
    	
    	int numeResult = 0; // Gotta initialize these to something. Hope it doesn't cause problems.
    	int denomResult = 1;
    	
    	if (operator.equals("+") || operator.equals("-")) {
        	if (denominator1 != denominator2) { // Converts the two fractions into having a common denominator if they don't have one already
        		denomResult = leastCommonMultiple(denominator1, denominator2);
        		numerator1 *= (denomResult / denominator1);
        		numerator2 *= (denomResult / denominator2);
        	} else {
        		denomResult = denominator1; // If the denominators are already the same, then they already have a commonDenom.
        	} 

        	if (operator.equals("+")) {
        		numeResult = numerator1 + numerator2; 
        	} else if (operator.equals("-")) {
        		numeResult = numerator1 - numerator2;
        	}
    	} 
    	else if (operator.equals("*")) {
    		numeResult = numerator1 * numerator2;
    		denomResult = denominator1 * denominator2;
    	} else if (operator.equals("/")) {
    		numeResult = numerator1 * denominator2; // This makes sense because you "flip" the second fraction when dividing
    		denomResult = denominator1 * numerator2;
    	} else {
    		System.out.println("No operator entered.");
    	}
        
    	// for final version, do the simplification (reduce and have whole number) before returning
    	
    	String fracResult = numeResult + "/" + denomResult; 
    	
    	// return returnForTest; // for Checkpoint 2
        return fracResult; // for Checkpoint 3
    }

    // TODO: Fill in the space below with helper methods
    
   public static boolean isPositive(String num) { // I had to write this method to account for negative numbers
    	boolean positive = true;
    	if (num.charAt(0) == '-') { // If it starts with a negative sign
    		positive = false; // then it's not positive (it's negative).
    	}
    	return positive;
    }
    
    public static int findWhole(String num) { // Finds the whole number in either the first or second operand
    	int whole = 0; // Will be changed unless the operand starts at the numerator, in which case it is true anyway.
    	String temp = ""; // will be used
    	boolean wholeFound = false;
    	
    	for (int i = 0; i < num.length(); i++) { // We can start at 0 because now the 1st and 2nd operands are in their own strings
    		if (num.charAt(i) == '_' || num.charAt(i) == ' ') { // These symbols always mark the end of the whole number if there is one
    			// so I used it as a point to find the whole number with
    			// Then it assigns the whole number value
    			temp = num.substring(0, i); 
    			whole = Integer.parseInt(temp);
    			wholeFound = true;
    		} 
    		else if (num.charAt(i) == '/') { // Indicates that the program has found a numerator first, which means there's no whole number
    			wholeFound = true; // So, if it starts right at the numerator, the whole would be 0
    		}
    	} 
    	if (wholeFound == false) { // If the method, after traversing the whole operand string, can't find a "_", 
	    	whole = Integer.parseInt(num); // then the whole thing is the whole number.
		 } // This is useful if there's no symbol marking the end of the whole number.
    	return whole;
    }
    
    public static int findNume(String num) { // Finds the numerator in either the first or second operand
    	int numerator = 0; 
    	int start = 0;
    	for (int i = 0; i < num.length(); i++) {
    		if (num.charAt(i) == '_') { // traverses the string until it finds the start of the numerator (always after the _)
    			start = i+1; // the starting index is 1 after the underscore's index
    		}
    		if (num.charAt(i) == '/') { // this is where it determines where the numerator ends
    			String temp = num.substring(start , i);
    			numerator = Integer.parseInt(temp);
    		}
    	}
    	if (num.charAt(0) == '-' && num.contains("_") == true) { // Checking if the operand is negative or not since the numerator wouldn't be positive in a negative number
    		// so we need to make it negative (the program thinks it's positive because there's no "-" right in front of the numerator)
    		// -1_2/3 is not the same thing as -1 + 2/3
    		// It's actually -1 + -2/3
    		// However, we need to be careful about not negating the negative if it's already negative
    		// For example, -1/2 would already register as negative because the "-" is right in front of the numerator
    		// So, in short, if there's a whole number between the negative sign and the numerator, we have to indicate that the numerator is negative
    		// The "_" only occurs if there's a whole number, that's why we're checking with that.
    		numerator *= -1;
    	}
    	return numerator;
    }
    
    public static int findDen(String num) { // Finds the denominator in either the first or second operand
    	int denominator = 1; // 1 by default because you can't divide by 0 and whole numbers are really fractions w/ denom. 1
    	for (int i = 0; i < num.length(); i++) {
    		if (num.charAt(i) == '/') {
    			String temp = num.substring(i+1);
    			denominator = Integer.parseInt(temp);
    		}
    	}
    	return denominator;
    }
    
    
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
