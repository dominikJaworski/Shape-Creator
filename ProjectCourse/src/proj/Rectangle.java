package proj;

import java.awt.*;
/*
 * Written by Subir Bandyopadhyay Sept 1, 2012
 * Class Rectangle represents a rectangle. 
 * 
 * Properties of class Rectangle
 * referencePoint - where is the rectangle located
 * size  - the height and the width of the rectangle
 * lastMousePosition - the last position where the mouse was pressed, 
 * 						useful when moving the rectangle
 * colorShape - color of the rectangle
 * shapeSelected - a boolean variable which is true if the rectangle is 
 * 					selected for moving 
 * 
 * Properties of class Rectangle (See below for more details)
 * onAnEdge - returns true if mouse is pressed very close to one of the edges
 * changeColor - modify the color of the rectangle
 * changeShape - modify the width or the height of the rectangle
 * moveShape - move the rectangle by dragging the mouse 
 * shapeIsSelected - mark a rectangle as "selected" by pressing the mouse button inside the rectangle
 * resetShapeSelected - reset the shapeSelected flag
 * calculateArea - determine the area of the rectangle
 * showMe - display the rectangle inside the frame
 * toString - return a string describing the rectangle.
 * savePositionWhereUserPressedMouse - useful when dragging the shape
 * 
 */


public class Rectangle extends Shape{

	private int height, width;  // height and width of rectangle

	
	// Constructor creates a black rectangle with size 50 X 50 with upper left point at (200, 200)
	public Rectangle(){

		referencePoint = new Coordinates(200, 200);
		lastMousePosition = referencePoint;
		colorShape = new Color(0, 0, 0);  // colour is black
		height = 50;
		width = 50;
	}
	/*
	 * Method onThePerimeter checks if the user pressed the mouse button on the perimeter. 
	 * If so, it returns true; otherwise it return false.
	 */

	boolean onThePerimeter(Coordinates mousePosition){
		int xWhereMousePressed, yWhereMousePressed;

		xWhereMousePressed = mousePosition.getX();
		yWhereMousePressed = mousePosition.getY();

		/*
		 * If the position where the user pressed the mouse button is within 5 pixels of 
		 * any side of the rectangle, the method will return true;
		 * Otherwise, it will return false.
		 */
		if (((xWhereMousePressed >= referencePoint.getX() - 5) &&
				(xWhereMousePressed <= (referencePoint.getX() + width + 5)) &&
				(yWhereMousePressed >= referencePoint.getY() - 5) &&
				(yWhereMousePressed <= (referencePoint.getY() + 5)))) return true;// top edge is edge # 0

				if (((xWhereMousePressed >= referencePoint.getX() - 5) &&
						(xWhereMousePressed <= (referencePoint.getX() + 5)) &&
						(yWhereMousePressed >= referencePoint.getY() - 5) &&
						(yWhereMousePressed <= (referencePoint.getY() + height + 5)))) return true;// left edge is edge # 1

				if (((xWhereMousePressed >= referencePoint.getX() - 5) &&
						(xWhereMousePressed <= (referencePoint.getX() + width + 5)) &&
						(yWhereMousePressed >= referencePoint.getY() + height - 5) &&
						(yWhereMousePressed <= (referencePoint.getY()+ height + 5)))) return true;//bottom edge-edge # 2

				if (((xWhereMousePressed >= referencePoint.getX() + width - 5) &&
						(xWhereMousePressed <= (referencePoint.getX() + width + 5)) &&
						(yWhereMousePressed >= referencePoint.getY() - 5) &&
						(yWhereMousePressed <= (referencePoint.getY() + height + 5)))) return true;// right edge-edge # 3

				return false;
	}
	/* 
	 * changeColor(c) modifies the color of the rectangle to the new color c
	 */
/*
	public void changeColor(Color c){
		colorShape = c ;
	}
	
	public void changeColorTemporarily(){	 
		originalColorShape = colorShape; 
		colorShape = Color.yellow ;
		perimeterSelected = true;
	}
	
	public void changeColorBack(){	 
		colorShape = originalColorShape; 
		perimeterSelected = false;
	}
	*/
	/*
	 * changeShape changes the width (or the height) of the rectangle, if
	 * currentPhase is 3 (or 4).
	 * currentPhase = 0, means the user is moving the shape
	 *              = 1, a new shape is being created
	 *              = 2, the the color of the new shape is being modified,
	 *              = 3, the width of a rectangle is being modified,
	 *              = 4, the height of a rectangle is being modified.
	 */

	public void changeShape(int currentPhase, int size){
		if (currentPhase == 3){
			width = size; 
		} else {
			height = size; 
		}
	}
	
	public void changeSquareShape(int currentPhase, int size){
		if (currentPhase == 3){
			width = size; 
			height = size;
		}  
	}
	
	/*
	 * Method moveShape(currentPositionMouse) allows the user to drag the rectangle by dragging the mouse
	 * button. The shape is first selected by pressing the mouse button with the cursor inside the shape.
	 * Then, if the user drags the mouse, the shape should move with the mouse.
	 * Moving a rectangle simply means modifying the reference point (upper left corner point) so that
	 * rectangle moves with the mouse position.
	 * The idea is that if the x and the y coordinate of the position of the mouse is moved by specified
	 * amounts,the reference point must change by exactly the same amount.
	 */

	public void moveShape(Coordinates currentPositionMouse){	      
		if (shapeSelected) {
		// If a shape is selected for a move operation, change the reference point 
			// as the mouse is being dragged.
			referencePoint.setX(referencePoint.getX() + 
					currentPositionMouse.getX() -
					lastMousePosition.getX());
			referencePoint.setY(referencePoint.getY() +
					currentPositionMouse.getY() -
					lastMousePosition.getY());
			lastMousePosition = currentPositionMouse;
		}
	}
	
	/* 
	 * Method shapeIsSelected() returns true if the user previously selected the shape by 
	 * pressing the mouse button with the cursor inside the shape.
	 */

	public boolean shapeIsSelected(){
		return shapeSelected;
	}
	
	/*
	 * Method shapeIsSelected(positionOfMouse) checks if the position of the mouse where the user
	 * pressed the left mouse button is within the shape (at least 5 pixels away from the perimeter.
	 * If so, the flag shapeSelected is set and the method returns true.
	 * Otherwise, the flag shapeSelected is reset and the method returns false.
	 */

	public boolean shapeIsSelected(Coordinates positionOfMouse){ 
		// Check if the user pressed the mouse button inside the shape
		int x, y;
		x = positionOfMouse.getX();
		y = positionOfMouse.getY();
		if ((x >= referencePoint.getX() + 5) &&
				(x <= referencePoint.getX() + width - 5) &&
				(y >= referencePoint.getY() + 5) &&
				(y <= referencePoint.getY() + height - 5)) {
			shapeSelected = true;
			lastMousePosition = positionOfMouse;
			return true;
		}else {
			shapeSelected = false;
			return false;
		}
	} 
	
	/* 
	 * resetShapeSelected() simply resets the value of shapeSelected to false.
	 */

	public Coordinates returnReferencePoint(){
		return referencePoint;
	}
	
	public void resetShapeSelected(){
		shapeSelected = false;
	}
	
	/*
	 * calculateArea() returns the area of the rectangle
	 */

	public double calculateArea(){ 
		return (width * height);
	}
	
	/*
	 * showMe(g) displays the rectangle using the Graphic object g.
	 * It sets the color to be used, and draws the rectangle using the specified 
	 * reference point, the width and the height.
	 */

	public void showMe(Graphics g){
		g.setColor(colorShape);
		g.drawRect(referencePoint.getX(), // Draw a rectangle with the specified 
				// corner point
				referencePoint.getY(),  // width and height
				width,
				height);
	}
	
	/* 
	 * toString() returns the description of the rectangle - the color, 
	 * the reference point and the size.
	 */

	public String toString(){
		return ("Rectangle with reference point " + referencePoint + " having width "
				+ width + " and height " + height + "\n");
	}
	/*
	 * savePositionWhereUserPressedMouse(int x, int y)is used to 
	 * save the position where the user Pressed the mouse button.
	 * This is useful for moving the rectangle.
	 */
	/*
	public void savePositionWhereUserPressedMouse(int x, int y){
		lastMousePosition = new Coordinates(x, y);
	}*/

	public double getWidth() {
		return width;
	}
}

