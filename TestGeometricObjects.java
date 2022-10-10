/*
 * Mayolo Valencia
 * 6/21/2022
 * User inputs 3 values for side1, side2 and side3
 * it evaluates if it's a valid triangle, if it's
 * invalid it returns a message. If the triangle is
 * valid the while loop starts and user is asked to 
 * input the color by name and fill by true/false.
 * Using methods from GeometricObjects and classes
 * that extend it. The code displays the formatted 
 * area and perimeter also the color and fill in 
 * true/false. When all is input correctly.
 * 
 */
package AssignmentsDataStructures;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class TestGeometricObjects {
    @SuppressWarnings("resource")
	public static void main (String []  args) {
    	
    	Scanner input = new Scanner(System.in);
    	NumberFormat circleFormat = new DecimalFormat("#0.00");    
    	NumberFormat formatter = new DecimalFormat("#0.0");     

        double side1 = 1.0, side2 = 1.0, side3 = 1.0;
        String color;
        Boolean choice = false;
        boolean filled;

        while(choice == false) {
	        System.out.print("Please enter the sides of a triangle: ");
	        side1 = input.nextDouble();
	        side2 = input.nextDouble();
	        side3 = input.nextDouble();
	    	input.nextLine();
	        
	        // when the validation loop isn't true informs user their input is wrong
	        if(side1 + side2 <= side3) {
	        	System.out.println("Unable to create a triangle with those sides.");
	        	continue;
	        } else {
	        	// changes to true to continue to the next loop
	        	choice = true;
	        }
        }
        
    	// Validates if the condition is true
        while(side1 + side2 > side3) {
            
        	System.out.print("What is the color of the triangle: ");
        	color = input.nextLine();
        	
        	System.out.print("Is the triangle filled(true/false)?: ");
        	filled = input.nextBoolean();
        	
        	// Checks if the user filled in the vars
        	if(color == null || filled != true && filled != false) {
        		System.out.println("\nPlease give a color and answer if "
        						 + "it is filled with (true/false).\n");
        		continue;
        	}
            
        	/* Constructs a Triangle object with the users input
        	 * along with the color and if filled
        	 */
        	Triangle userTriangle = new Triangle(side1, side2, side3);
        	userTriangle.setColor(color);
        	userTriangle.setFilled(filled);
                
        	System.out.print("\nTriangle:"
        			+ "\nArea: " 
        			+ formatter.format(userTriangle.getArea())
        			+ "\nPerimeter: " 
        			+ formatter.format(userTriangle.getPerimeter())
        			+ "\nColor: " + userTriangle.getColor()
        			+ "\nFilled: " + userTriangle.isFilled()
        			+ "\n");
        	
        	// Creates Circle object with default values
        	Circle preCircle = new Circle(2.0);
        	preCircle.setColor("yellow");
        	preCircle.setFilled(true);
                
        	System.out.print("\nCircle:"
        			+ "\nArea: " 
        			+ circleFormat.format(preCircle.getArea())
        			+ "\nPerimeter: " 
        			+ circleFormat.format(preCircle.getPerimeter())
        			+ "\nColor: " + preCircle.getColor()
        			+ "\nFilled: " + preCircle.isFilled()
        			+ "\n");
        	
        	// Creates Rectangle object with default values
        	Rectangle preRectangle = new Rectangle(2.0, 4.0);
        	preRectangle.setColor("red");
        	preRectangle.setFilled(false);
                
        	System.out.print("\nCircle:"
        			+ "\nArea: " 
        			+ formatter.format(preRectangle.getArea())
        			+ "\nPerimeter: " 
        			+ formatter.format(preRectangle.getPerimeter())
        			+ "\nColor: " + preRectangle.getColor()
        			+ "\nFilled: " + preRectangle.isFilled()
        			+ "\n");
        	break;
        }
   }

} 

abstract class GeometricObject {       
	
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject() {
		dateCreated = new java.util.Date();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject(String color, boolean filled) {
		dateCreated = new java.util.Date();
		this.color = color;
		this.filled = filled;
	 }

	/** Return color */
	public String getColor() {
		return color;
	}

	/** Set a new color */
	public void setColor(String color) {
		this.color = color;
	}

	/** Return filled. Since filled is boolean,
	 *  the get method is named isFilled */
	public boolean isFilled() {
		return filled;
	}

	/** Set a new filled */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	/** Get dateCreated */
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	/** Return a string representation of this object */
	public String toString() {
		return "created on " + dateCreated + "\ncolor: " + color +
				" and filled: " + filled;
	}

	/** Abstract method getArea */
	public abstract double getArea();

	/** Abstract method getPerimeter */
	public abstract double getPerimeter();

}

class Triangle extends GeometricObject {
	
	private double side1;
	private double side2;
	private double side3;

	Triangle(double side1, double side2, double side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	 
	public void setSide1(double side1) {
		this.side1 = side1;
	}

	public void setSide2(double side2) {
		this.side2 = side2;
	}

	public void setSide3(double side3) {
		this.side3 = side3;
	}

	@Override
	public double getPerimeter() {
		return side1 + side2 + side3;
	}
	
	@Override //using Herons formula gets area
	public double getArea() {
		double semiPer = getPerimeter() /2;
		return Math.sqrt(semiPer * ((semiPer - side1)
						* (semiPer - side2) * (semiPer - side3)));
	}
	
	// Prints Triangle date created and area
	public String printTriangle() {
		return "The triangle is created " + getDateCreated()
		+ " and the area is " + getArea();
	}
}

class Circle extends GeometricObject {
	
	private double radius;
	
	public Circle() {
	}
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getArea() {
		return radius * radius * Math.PI;
	}
	
	public double getDiameter() {
		return 2 * radius;
	}
	
	@Override
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}
	
	// Prints circle date created and radius
	public void printCircle() {
		System.out.println("The circle is created " + getDateCreated()
							+ " and the radius is " + radius);
	}
 }

class Rectangle extends GeometricObject {

	 private double width;
	 private double height;

	 public Rectangle() {
	 }

	 public Rectangle(double width, double height) {
		 this.width = width;
		 this.height = height;
	 }

	 // Returns width
	 public double getWidth() {
		 return width;
	 }

	 // Sets a new width
	 public void setWidth(double width) {
		 this.width = width;
	  }

	 //Returns height
	 public double getHeight() {
		 return height;
	 }

	 // Sets a new height
	 public void setHeight(double height) {
		 this.height = height;
	 }

	 @Override // Returns area
	 public double getArea() {
		 return width * height;
	 }

	 @Override // Returns perimeter
	 public double getPerimeter() {
		 return 2 * (width + height);
	 }
	 
	 // Prints rectangle date created and area
	 public String printRectangle() {
		 return "The rectangle is created " + getDateCreated()
		 		+ " and the area is " + getArea();
	 }
}