/*
 * Mayolo Valencia
 * 7/7/2022
 * 
 * READ THIS FIRST!!!
 * When running on my local computer: java FileName FileName.java
 * did not work.
 * 
 * What did work was: Java FileName.java FileName.java
 * Keep this in mind please if the first command does not work
 * 
 * This program is ran through the command line and displays the
 * occurrence of every word in a file.Its placed in a TreeMap
 * so it sorts itself alphabetically.In the readOccurrences method
 * it goes through the file and appends it to the map while also 
 * adding to the value if the key is scene more than once.
 */

package AssignmentsDataStructures;

import java.util.*;
import java.io.*;

public class CountOccurrenceOfWords {
    public static void main(String[] args) throws FileNotFoundException {

        // Checks if filename is passed
        if (args.length >= 1) {
        	// Get the filename from the command line
            String filename = args[0]; 
            
            // Opens the file with a scanner
            Scanner scanner = new Scanner(new File(filename));
            
            // Creates a TreeMap
            Map<String, Integer> map = new TreeMap<>();
            
            // Passes the scanner file and the map into the method
            readOccurrences(scanner, map);
            
            System.out.println("\nDisplay words and their count in  ascending order of the words");

            // Displays all the sorted keys and values 
            map.forEach((k, v) -> System.out.println(v + "\t" + k));
        }else {
        	
        	// Console suggestion
        	System.out.print("Use (Java FileName FileName.java) or "
        			+ "(Java FileName.java FileName.java) in the command line");
        }
}
    
    
    public static void readOccurrences(Scanner scanner, Map<String, Integer> map) {
    	
    	// While the file isn't empty
        while (scanner.hasNextLine()) {
        	
        	// The line that will go through the file
            String line = scanner.nextLine();
            
            // Array of words split on these chars in the line
            String[] words = line.split("[0-9\\s+\\p{P}<>=+]");
            
            
            for (int i = 0; i < words.length; i++) {
            	
            	//makes every word lowercase and assigns it
                String key = words[i].toLowerCase();

                if (key.length() > 0) {
                	/* If the map doesn't contain the string as key
                	 * add it with a value of 1  				*/
                    if (!map.containsKey(key)) {
                    	
                        map.put(key, 1);
                    } else {
                    	/* If the map contains the key
                    	 * get the key value add 1 then 
                    	 * put it in the map 		 */
                        int value = map.get(key);
                        value++;
                        map.put(key, value);
                    }
                }
            }
        }
    }
}