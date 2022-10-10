/*
 * Mayolo Valencia
 * 6/29/2022
 *  
 * Using arrayLists of different types, the sort method 
 * sorts in ASCII, meaning 'A' is comes before 'a'.
 * The special part of this sort method is that it can take 
 * many kinds of objects using generics and extends them with 
 * the Comparable interface.
 */

package AssignmentsDataStructures;

import java.util.*;

public class TestGenericSort {
	// Removes warnings
	@SuppressWarnings("serial")
	public static void main(String[] args) {
    
    // Create an Integer arrayList
    ArrayList<Integer> intArrayList = new ArrayList<>() {
    	{
    		add(2); // Autoboxed into Integer Obj
    		add(4);
    		add(3);
    	}
    };
    	
    // Create a Double arrayList
    ArrayList<Double> doubleArrayList = new ArrayList<>() {
        { 
        	add(3.4); // Autoboxed into Double Obj
        	add(1.2);
        	add(-12.3);
        }
    };    
   
    // Create a String arrayList
    ArrayList<String> stringArrayList = new ArrayList<>() {
    	{ 
    		add("Bob"); // Autoboxed into String Obj
    		add("Alice");
    		add("Ted");
    		add("Carol");
    	}
    };
   
   // Sort the arrayLists using the generic sort method
   sort(intArrayList);
   sort(doubleArrayList);
   sort(stringArrayList);
   
   // Display the sorted arrayLists
   System.out.print("Sorted Integer objects: ");
   printList(intArrayList);
   
   System.out.print("Sorted Double objects: ");
   printList(doubleArrayList);

   System.out.print("Sorted String objects: ");
   printList(stringArrayList);
   }

    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
    	
    	E currentMin;
        int currentMinIndex;
        
        // Loops through the arrayList
        for(int i = 0; i < list.size(); i++) {
        	
        	// Saves the object
        	currentMin = list.get(i);
        	// Saves the index
        	currentMinIndex = i;
        	
        	// Loops ahead of the i pointer
        	for(int j = i + 1; j < list.size(); j++) {
        		
        		// Compares the object at i to the object at j
        		if (currentMin.compareTo(list.get(j)) > 0) {
        			
        			/* if true the object at j and index get assigned
        			 * to the vars								   */
        			currentMin = list.get(j);
                	currentMinIndex = j;
        		}
        	}
        	if (currentMinIndex != i) {
        		
        		// Swaps the objects if currentMinIndex becomes what j is
        		list.set(currentMinIndex, list.get(i));
        		list.set(i, currentMin);
        	}
        } 
    }
    
    // Print the objects objects in the array lists
    public static <E> void printList(ArrayList<E> list) {
    	
    	// Loops through arrayList and prints
    	for (int i = 0; i < list.size(); i++) {
    		
            System.out.print(list.get(i) + " ");
    	}
        System.out.println();
    }
}
