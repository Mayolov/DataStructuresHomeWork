/*
 * Mayolo Valencia
 * 6/26/2022
 * 
 * Prompts user to input a string then calls
 * the reverse display method. That method prints
 * the last indexable character then passes the
 * substring to the tail recursive method and that
 * also prints the last indexable character until it 
 * reaches the base case and prints the final character
 */

package AssignmentsDataStructures;

import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {
    	
    	// Suppresses annoying input warnings
        @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
        
        // user inputs a string to be reversed
        System.out.print("Enter a string: ");
        String value = input.nextLine();

        /* First call for the recursive method
         * passes in the string 			*/
        reverseDisplay(value);
    }

    public static void reverseDisplay(String value) {

        if (value.length() <= 1) { // Base case returns last value remaining
            System.out.print(value);
        } else {

            // Prints the last index char of the string
            System.out.print(value.charAt(value.length() - 1));

            /* Calls the Tail recursive function.
             * Passing in the remaining string for the tail recursive method
             * and new value for the high being the current length-1 	  */
            reverseDisplay(value.substring(0, value.length() - 1));
        }
    }

    public static void reverseDisplay(String value, int high) {
    	
        if (high <= 1) {// Base case returns last value remaining
            System.out.print(value);
        } else {

            // Prints the last index char of the string
            System.out.print(value.charAt(high - 1));

            // Calls the recursive method and passes the remaining substring
            reverseDisplay(value.substring(0, high - 1));
        }
    }
}