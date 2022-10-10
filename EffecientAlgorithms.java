/*
 * Mayolo Valencia
 * 7/9/2022
 * 
 * 100 random points are created and using the divide
 * and conquer method it searches through the left strip
 * and gets the smallest distance between points then does
 * the same on the right.Finally getting d3 which would be
 * the points where stripL and stripR didn't search then returns
 * the closest pair of points from between distance1, 2 & 3.
 * In the end printing the closest distance, both points, and
 * time to execute.
 */
package AssignmentsDataStructures;

import java.util.*;

public class EffecientAlgorithms {
    public static void main(String[] args) {
        // Object for creating random points
        Random randomizer = new Random();
        // Creates a point obj list with 100 slots
        Point[] coordHolder = new Point[100];

        // Creates the 100 x and y points and puts them in the list
        for (int i = 0; i < 100; i++) {
            double x = randomizer.nextDouble() * 100;
            double y = randomizer.nextDouble() * 100;
            Point coord = new Point(x, y);
            coordHolder[i] = coord;
        }

        // Start getting the time
        long startTime = System.currentTimeMillis();
        // gets the pair object that has the shortest distance
        Pair pair = Pair.getClosestPair(coordHolder);
        // gets the end time
        long endTime = System.currentTimeMillis();

        // displays the distance between the points and the 2 points
        System.out.println("The shortest distance is " + pair.getDistance()
                + " between the points \n" + pair.getP1() + " and " + pair.getP2());

        // displays total run time for the divide and conquer algorithm
        System.out.println("Time spent on the divide-and-conquer algorithm is "
                + (endTime - startTime) + " milliseconds");
    }
}

class Point implements Comparable<Point> {
    private double x;
    private double y;

    // Constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Setters and Getters
    public double setX(double x) {
        return this.x;
    }

    public double getX() {
        return this.x;
    }

    public double setY(double y) {

        return this.y;
    }

    public double getY() {
        return this.y;
    }

    /*
     * Custom compareTo method works the same
     * as a normal compareTo method except this
     * also compares the y-coord on an equals case
     */
    @Override
    public int compareTo(Point o) {

        if (this.x < o.getX()) {
            return -1;

        } else if (this.x == o.getX()) {
            if (y < o.getY()) {
                return -1;

            } else if (y == o.getY()) {
                return 0;

            } else {
                return 1;

            }
        } else {
            return 1;

        }
    }

    /*
     * Override toSting method to get values
     * not the hashcode
     */
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}

class CompareY implements Comparator<Point> {

    /*
     * Compares 2 obj of the same class and
     * compares, if they are equal it checks the
     * x-coord
     */
    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getY() < o2.getY()) {
            return -1;

        } else if (o1.getY() == o2.getY()) {
            if (o1.compareTo(o2) == 0) {
                return 0;

            } else if (o1.compareTo(o2) == -1) {
                return -1;

            } else {
                return 1;

            }
        } else {
            return 1;
        }
    }
}

class Pair {
    private Point p1;
    private Point p2;

    // Constructor
    public Pair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    // Setters and getters
    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    /*
     * When this is called it returns the distance method
     * that is recursive.
     */
    public static Pair getClosestPair(Point[] points) {
        // sorts points on X
        Arrays.sort(points);
        // Clones the list
        Point[] pointsOrderedOnY = points.clone();
        // sorts on Y
        Arrays.sort(pointsOrderedOnY, new CompareY());
        return distance(points, 0, points.length - 1, pointsOrderedOnY);

    }

    /*
     * Does a call to distance to get the distance between
     * 2 points
     */
    public double getDistance() {
        return distance(p1, p2);
    }

    // Gets distance between 2 points
    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
                + (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()));
    }

    // divide and conquer method that recursively calls itself until it hits a
    // basecase
    private static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
        if (low >= high) {// If low index is greater than high return null
            return null;
        } else if (high <= 3) {// If the length of the high index is less than or equal to 3 use bruteForce method
            return bruteforce(pointsOrderedOnX);
        } else if (low + 1 == high) {// if low is 1 index away from high return both of those indexes
            return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
        }

        // initializing an empty Pair object
        Pair answer = null;
        /*
         * initializing a minDistance double that will take the minDistance
         * between points
         */
        double minDistance = 0;
        // mid index
        int midIndex = (low + high) / 2;
        // Point obj at mid index
        Point mid = pointsOrderedOnX[midIndex];
        // distance 1
        Pair d1 = distance(pointsOrderedOnX, low, midIndex, pointsOrderedOnY);
        // distance 2
        Pair d2 = distance(pointsOrderedOnX, midIndex + 1, high, pointsOrderedOnY);

        // If both objects are null returns null to show exception;
        if (d1 == null && d2 == null) {
            return null;
        } else if (d1 == null) { // If d1 has no valid input assign it minDist and answer
            minDistance = d2.getDistance();
            answer = d2;
        } else if (d2 == null) {// If d2 has no valid input assign it minDist and answer
            minDistance = d1.getDistance();
            answer = d1;
        } else {// If no exceptions minDist is the lowest between d1&d2
        	
            // min distance between d1 and d2
            minDistance = Math.min(d1.getDistance(), d2.getDistance());
            
            // answer Pair obj is d1 or d2
            if (d1.getDistance() <= d2.getDistance()) {
                answer = d1;
            } else {
                answer = d2;
            }
        }

        /*
         * Creates 2 strips to separate the list and
         * do the divide and conquer approach
         */
        ArrayList<Point> stripL = new ArrayList<Point>();
        ArrayList<Point> stripR = new ArrayList<Point>();

        // The Algorithm for Obtaining stripL and stripR
        for (Point p : pointsOrderedOnY) {
            if (mid.getX() - p.getX() <= minDistance) {
                stripL.add(p);
            } else {
                stripR.add(p);
            }
        }

        // Algorithm for Finding the Closest Pair
        double d3 = minDistance;

        // r is the index of a point in stripR
        int r = 0;
        for (Point p : stripL) {
            // Skip the points in stripR below p.y - d
            while (r < stripR.size() && stripL.get(r).getY() <= p.getY() - d3) {
                r++;
            }

            int r1 = r;
            while (r1 < stripR.size() && Math.abs(stripR.get(r1).getY() - p.getY()) <= d3) {
                // Check if (p, q[r1] is a possible closest pair
                if (distance(p, stripR.get(r1)) < d3) {
                    d3 = distance(p, stripR.get(r1));
                    answer = new Pair(p, stripR.get(r1));
                }
                r1++;
            }
        }
        return answer;
    }

    /*
     * When the bruteForce method is called it goes through all the points
     * and retrieves the closest pair, though much slower in o(n^2) time
     */
    private static Pair bruteforce(Point[] pointsOrderedOnX) {

        // High initial distance to make sure it can be used for many utilizations
        double minDist = 100000;
        // An empty pair object to be assigned later
        Pair closestPair = null;

        // Goes through the length of the list with 2 pointers
        for (int i = 0; i < pointsOrderedOnX.length; i++) {
            for (int j = i + 1; j < pointsOrderedOnX.length; j++) {

                // gets the distance of the 2 pointers
                double dist = distance(pointsOrderedOnX[i], pointsOrderedOnX[j]);

                /*
                 * If the distance of the 2 points is less
                 * than the previous it assigns it to the old var
                 * and saves those 2 points in the Pair obj ultimately
                 * returning the Pair obj with the lowest distance between points
                 */
                if (dist < minDist) {
                    minDist = dist;
                    closestPair = new Pair(pointsOrderedOnX[i], pointsOrderedOnX[j]);
                }
            }
        }
        return closestPair;
    }
}

