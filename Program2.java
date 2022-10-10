/*
 * Mayolo Valencia
 * 6/27/2022
 * 
 * User is prompted to input a string, the longer it
 * is the longer it will take to display all permutations.
 * The string passes in to the first method and then it moves
 * into the tail-recursive method and passing in "" as the
 * head and it moves along the string taking up the char of the i
 * index. It then prints all possible permutations one after another. 
 */

package AssignmentsDataStructures;

import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        // Suppresses annoying input warnings
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        // Prompts user to input a string
        System.out.print("Input a string: ");
        String s = input.nextLine();

        /* First call for the recursive method
         * passes in the string 			*/
        displayPermutation(s);
    }

    public static void displayPermutation(String s) {
    	
    	// Passes "" and the string the tail recursive method
        displayPermutation("", s);
    }

    private static void displayPermutation(String s1, String s2) {
    	
        if (s2.length() == 0) {// Base case
            System.out.println(s1);
        } else {
        	
            // i moves through the string as "" and becomes the charAt(i)
            for (int i = 0; i < s2.length(); i++) {
            	
                /* Calls recursive function
                 * returns all permutations of input, moving through the string and
                 * appending													*/
                displayPermutation(s1 + s2.charAt(i),
                        s2.substring(0, i) + s2.substring(i + 1, s2.length()));
            }
        }
    }
}
