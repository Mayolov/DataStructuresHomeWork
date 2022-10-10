/*
 * Mayolo Valencia
 * 7/2/2022
 * 
 * User is prompted to input a character to look for
 * in the char list. Then calls a method that calls a
 * tail recursive method calls itself until it loops
 * through the whole list and returns the amount of times
 * the character shows up in the list.
 * 
 */

package AssignmentsDataStructures;

import java.util.Scanner;

public class CharacterCounter {
    public static void main(String[] args) {
    	
        // Suppresses warnings
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        // List of chars
        char[] test = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 't', 'h', 'e',
                ' ', 's', 't', 'r', 'i', 'n', 'g' };

        // Prompts user to input a char
        System.out.print("What character are you looking for? (Case-Sensitive) ");
        char letter = input.next().charAt(0);

        // Returns how many times their char came up
        System.out.println("The character " + letter + " shows up "
                + charCount(test, letter) + " time(s).");
    }

    // Calls this first
    public static int charCount(char[] test, char letter) {
        int pointer = 0;

        // Calls the tail recursive method
        return charCount(test, pointer, letter);
    }

    // Does the calculations for the first method
    private static int charCount(char[] array, int start, char ch) {
    	
        // If the pointer isn't out of bounds of the list length
        if (start < array.length) {
            /* If the given index is equal to the users char
             * 
        	 * Unknown if the the all characters both capital
        	 * and lower case should be counted together or serperate
        	 * but to count them together use:
        	 * if(Character.toLowerCase(array[start]) == Character.toLowerCase(ch))
        	 */
            if (array[start] == ch) {
            
                // Add 1 to the count and pointer
                return 1 + charCount(array, start + 1, ch);
            } else {
            	
                // Calls itself again when the index isn't equal to char
                return charCount(array, start + 1, ch);
            }
        }
        
        /* When start exceeds the list length returns 0
         * Base case                                  */
        return 0;
    }
}
