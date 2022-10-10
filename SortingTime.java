/*
 * Mayolo Valencia
 * 7/11/2022
 * 
 * Displays the time it takes to sort arrays
 * of varying size, from 50k to 300k indexes. It includes a 
 * Heap class that is made for the heap method, selectionSort method,
 * mergeSort method, quickSort method, and a radixSort method.
 */
package AssignmentsDataStructures;

import java.util.*;

public class SortingTime {
    public static void main(String[] args) {
        Random randomizer = new Random();

    	String outPut = "Array size | Selection  Merge\tQuick\tHeap\tRadix";
        System.out.println(outPut);
        // Prints dashes for the size for the size of the String+10
        for(int i = 0; i<outPut.length() + 10; i++) {
            System.out.print("-");
        }
        // increments the value of K by 50000
        for (int K = 50000; K < 300001; K += 50000) {
        	
        	// creates a int[] list
            int[] listOfK = new int[K];
            
            // Creates an Integer[] list for the Heap sort
            Integer[] heapListOfK = new Integer[K];
            
            for (int i = 0; i < K; i++) {
            	// selects random numbers up to the size of K
                int x = randomizer.nextInt(K);
                
                // Takes in values from x and adds them to the lists
                listOfK[i] = x;
                heapListOfK[i] = x;
            }
            
            // clones all the lists
            int[] selectionList = listOfK.clone();
            int[] mergeList = listOfK.clone();
            int[] quickList = listOfK.clone();
            Integer[] heapList = heapListOfK.clone();
            int[] radixList = listOfK.clone();
            
            // Prints this depending on the value of K
            if (K == 100000) {
                System.out.print("\n    100000 |  ");
            } else if (K == 150000) {
                System.out.print("\n    150000 |  ");
            } else if (K == 200000) {
                System.out.print("\n    200000 |  ");
            } else if (K == 250000) {
                System.out.print("\n    250000 |  ");
            } else if (K == 300000) {
                System.out.print("\n    300000 |  ");
            } else {
                System.out.print("\n    50000  |  ");
            }
            
            // gets the run time of each method and prints it 
            long startTime = System.currentTimeMillis();
            selectionSort(selectionList);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.print(executionTime + "\t");

            startTime = System.currentTimeMillis();
            MergeSort(mergeList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.print(executionTime + "\t");

            startTime = System.currentTimeMillis();
            quickSort(quickList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.print(executionTime + "\t");

            startTime = System.currentTimeMillis();
            heapSort(heapList);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.print(executionTime + "\t");

            startTime = System.currentTimeMillis();
            radixSort(radixList, K);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            System.out.print(executionTime + "\t");

        }

    }

    // Selection sort ITERATIVE
    public static void selectionSort(int[] list) {
        // run n-1 times, where n is array length
        for (int i = 0; i < list.length - 1; i++) {
            // find the minimum element in the unsorted subarray and swap it with index of i
            int min = i;

            for (int j = i + 1; j < list.length; j++) {
                // if list[j] is less, then it is the new min
                if (list[j] < list[min]) {
                    // update the index of minimum element
                    min = j;
                }
            }
            
            int temp = list[min];
            list[min] = list[i];
            list[i] = temp;
        }
    }
    
    // Heap sorts using the Heap class methods
    public static <E extends Comparable<E>> void heapSort(E[] list) {

        Heap<E> heap = new Heap<>();

        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }
        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
    }
    
    // quickSort first call
    public static void quickSort(int[] list) {
        int high = list.length - 1;
        int low = 0;

        quickSort(list, low, high);
    }
    
    // separates for the partition
    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }
    
    // sorts from the partition
    public static int partition(int[] list, int first, int last) {

        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list[low] <= pivot) {
                low++;
            }
            while (low <= high && list[high] > pivot) {
                high--;
            }
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot) {
            high--;
        }
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }
    
    // MergeSort
    public static void MergeSort(int[] list) {
        if (list.length > 1) {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            MergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            MergeSort(secondHalf);

            merge(firstHalf, secondHalf, list);
        }

    }

    // Merges the separated lists in mergeSort
    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }
    
    // Radix sort/Bucket sort
    static void radixSort(int[] list, int maxOrder) {
        for (int order = 1; order < maxOrder; order *= 10) {
            ArrayList<Integer>[] bucket = new ArrayList[10];

            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new java.util.ArrayList<>();
            }

            for (int i = 0; i < list.length; i++) {
                bucket[(list[i] / order) % 10].add(list[i]);
            }

            int k = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != null) {
                    for (int j = 0; j < bucket[i].size(); j++)
                        list[k++] = bucket[i].get(j);
                }
            }
        }
    }
}

// Heap Class
class Heap<E extends Comparable<E>> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

    /** Create a default heap */
    public Heap() {
    }

    /** Create a heap from an array of objects */
    public Heap(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Add a new object into the heap */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(
                    list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else
                break; // the tree is a heap now

            currentIndex = parentIndex;
        }
    }

    /** Remove the root from the heap */
    public E remove() {
        if (list.size() == 0)
            return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size())
                break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else
                break; // The tree is a heap
        }

        return removedObject;
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
        return list.size();
    }
}
