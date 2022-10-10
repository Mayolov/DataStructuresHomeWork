/*
 * Mayolo Valencia
 * 7/5/2022
 * 
 * Creates 100 random points and then sorts
 * them from highest to lowest using the x
 * coord first, if they are equal sort by y.
 * Then the second list is the same points, but
 * they are sorted with the y coords and if they
 * are equal they are sorted with the x coord.
 * After displaying user is prompted to tap any key
 * and press enter to do the process again. Else, 
 * if left blank program ends
 */
//package AssignmentsDataStructures;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Random;
//import java.util.Scanner;
//
//public class CoordinateSorter {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        // Object for creating random points
//        Random randomizer = new Random();
//        // Creates a Point obj list with 100 slots
//        Point[] coordHolder = new Point[100];
//
//        do {
//        	
//            // Creates the 100 x and y points and puts them in the list
//            for (int i = 0; i < 100; i++) {
//                double x = randomizer.nextDouble() * 100;
//                double y = randomizer.nextDouble() * 100;
//                Point coord = new Point(x, y);
//                coordHolder[i] = coord;
//            }
//
//            // sorts using the Arrays sort method that uses the
//            // Point class to verify
//            Arrays.sort(coordHolder);
//            System.out.println("Points sorted on x-coordinates");
//            
//            // Prints the x-coords sorted from lowest to highest
//            for (Point coords : coordHolder) {
//                System.out.println(coords);
//            }
//
//            // Sorts using Arrays sort method, with a specifier
//            // from the CompareY class
//            Arrays.sort(coordHolder, new CompareY());
//            System.out.println("\nPoints sorted on y-coordinates");
//            
//            // Prints y-coords lowest to highest
//            for (Point coords : coordHolder) {
//                System.out.println(coords);
//            }
//
//            // User input to continue or end the program
//            System.out.println("Press and Enter any key to continue . . .");
//            String choice = input.nextLine();
//            if (choice == "") {
//                break;
//            }
//
//        } while (true);
//    }
//}
//
//class Point implements Comparable<Point> {
//    private double x;
//    private double y;
//
//    // Constructor
//    public Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    // Setters and Getters
//    public double setX(double x) {
//        return this.x;
//    }
//
//    public double getX() {
//        return this.x;
//    }
//
//    public double setY(double y) {
//        return this.y;
//    }
//
//    public double getY() {
//        return this.y;
//    }
//
//    /* Custom compareTo method works the same
//     * as a normal compareTo method except this
//     * also compares the y-coord on an equals case */
//    @Override
//    public int compareTo(Point o) {
//
//        if (this.x < o.getX()) {
//            return -1;
//
//        } else if (this.x == o.getX()) {
//            if (y < o.getY()) {
//                return -1;
//
//            } else if (y == o.getY()) {
//                return 0;
//
//            } else {
//                return 1;
//
//            }
//        } else {
//            return 1;
//
//        }
//    }
//
//    /* Override toSting method to get values
//     * not the hashcode					  */
//    @Override
//    public String toString() {
//        return "(" + getX() + ", " + getY() + ")";
//    }
//}
//
//class CompareY implements Comparator<Point> {
//
//    /*  Compares 2 obj of the same class and
//     * compares, if they are equal it checks the
//     * x-coord 								  */
//    @Override
//    public int compare(Point o1, Point o2) {
//        if (o1.getY() < o2.getY()) {
//            return -1;
//
//        } else if (o1.getY() == o2.getY()) {
//            if (o1.compareTo(o2) == 0) {
//                return 0;
//
//            } else if (o1.compareTo(o2) == -1) {
//                return -1;
//
//            } else {
//                return 1;
//
//            }
//        } else {
//            return 1;
//
//        }
//    }
//}