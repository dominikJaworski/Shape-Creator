package proj;

import java.awt.*;
/*
 * Written by Subir Bandyopadhyay Sept 1, 2012
 * Class Rectangle represents a rectangle. 
 * 
 * Properties of class Square
 * referencePoint - where is the square located
 * size  of the square. The width of the square will be automatically the same as the size.
 * lastMousePosition - the last position where the mouse was pressed, 
 * 						useful when moving the square
 * colorShape - color of the square
 * shapeSelected - a boolean variable which is true if the square is 
 * 					selected for moving 
 * 
 * Properties of class Square (See below for more details)
 * changeColor - modify the color of the square
 * changeShape - modify the width or the size of the square
 * moveShape - move the square by dragging the mouse 
 * shapeIsSelected - mark a square as "selected" by pressing the mouse button inside the square
 * resetShapeSelected - reset the shapeSelected flag
 * calculateArea - determine the area of the square
 * showMe - display the square inside the frame
 * toString - return a string describing the square.
 * savePositionWhereUserPressedMouse - useful when dragging the shape
 * 
 */
public class Square extends Shape{

	private int size;
	private Rectangle rect;
	
	// Constructor creates a square of size 50 X 50 with the left corner point at (200, 200)
	public Square(){
		referencePoint = new Coordinates(200, 200);
		rect = new Rectangle();
		size = 50;
	}
	
	/*
	 * Method onThePerimeter checks if the user pressed the mouse button on the perimeter. 
	 * If so, it returns true; otherwise it return false.
	 */

	boolean onThePerimeter(Coordinates mousePosition){
		/*int xWhereMousePressed, yWhereMousePressed;

		xWhereMousePressed = mousePosition.getX();
		yWhereMousePressed = mousePosition.getY();

		/*
		 * If the position where the user pressed the mouse button is within 5 pixels of 
		 * any side of the rectangle, the method will return true;
		 * Otherwise, it will return false.
		 */
		/*
		if (((xWhereMousePressed >= referencePoint.getX() - 5) &&
				(xWhereMousePressed <= (referencePoint.getX() + size + 5)) &&
				(yWhereMousePressed >= referencePoint.getY() - 5) &&
				(yWhereMousePressed <= (referencePoint.getY() + 5)))) return true;// top edge is edge # 0

				if (((xWhereMousePressed >= referencePoint.getX() - 5) &&
						(xWhereMousePressed <= (referencePoint.getX() + 5)) &&
						(yWhereMousePressed >= referencePoint.getY() - 5) &&
						(yWhereMousePressed <= (referencePoint.getY() + size + 5)))) return true;// left edge is edge # 1

				if (((xWhereMousePressed >= referencePoint.getX() - 5) &&
						(xWhereMousePressed <= (referencePoint.getX() + size + 5)) &&
						(yWhereMousePressed >= referencePoint.getY() + size - 5) &&
						(yWhereMousePressed <= (referencePoint.getY()+ size + 5)))) return true;//bottom edge-edge # 2

				if (((xWhereMousePressed >= referencePoint.getX() + size - 5) &&
						(xWhereMousePressed <= (referencePoint.getX() + size + 5)) &&
						(yWhereMousePressed >= referencePoint.getY() - 5) &&
						(yWhereMousePressed <= (referencePoint.getY() + size + 5)))) return true;// right edge-edge # 3

				return false;*/
		return rect.onThePerimeter(mousePosition);
	}
	 
	/* 
	 * changeColor(c) modifies the color of the square to the new color c
	 */

	public void changeColor(Color c){
		//colorShape = c ;
		rect.changeColor(c);
	}
	
	public void changeColorTemporarily(){	 
		//originalColorShape = colorShape; 
		//colorShape = Color.yellow ;
		//perimeterSelected = true;
		rect.changeColorTemporarily();
	}
	
	public void changeColorBack(){	 
		//colorShape = originalColorShape; 
		//perimeterSelected = false;
		rect.changeColorBack();
	}

	/*
	 * changeShape changes the width (or the size) of the square, if
	 * currentPhase is 3 (or 4).
	 * currentPhase = 0, means the user is moving the shape
	 *              = 1, a new shape is being created
	 *              = 2, the the color of the new shape is being modified,
	 *              = 3, the width of a square is being modified.
	 */

	public void changeShape(int currentPhase, int size){
		rect.changeSquareShape(currentPhase, size);
	}

	/*
	 * Method moveShape(currentPositionMouse) allows the user to drag the square by dragging the mouse
	 * button. The shape is first selected by pressing the mouse button with the cursor inside the shape.
	 * Then, if the user drags the mouse, the shape should move with the mouse.
	 * Moving a square simply means modifying the reference point (upper left corner point) so that
	 * square moves with the mouse position.
	 * The idea is that if the x and the y coordinate of the position of the mouse is moved by specified
	 * amounts,the reference point must change by exactly the same amount.
	 */

	public void moveShape(Coordinates currentPositionMouse){	      
		rect.moveShape(currentPositionMouse);
	}

	/* 
	 * Method shapeIsSelected() returns true if the user previously selected the shape by 
	 * pressing the mouse button with the cursor inside the shape.
	 */
@Override
	public void setShapeSelected(Coordinates currentMousePosition){
		rect.setShapeSelected(currentMousePosition);
	}

	public void setLastMousePosition(Coordinates lastMouse){
		rect.setLastMousePosition(lastMouse);
	}
	public boolean shapeIsSelected(){
		return rect.shapeIsSelected();
	}

	/*
	 * Method shapeIsSelected(positionOfMouse) checks if the position of the mouse where the user
	 * pressed the left mouse button is within the shape (at least 5 pixels away from the perimeter.
	 * If so, the flag shapeSelected is set and the method returns true.
	 * Otherwise, the flag shapeSelected is reset and the method returns false.
	 */

	public boolean shapeIsSelected(Coordinates positionOfMouse){ 
		// Check if the user pressed the mouse button inside the shape
		/*
		int x, y;
		x = positionOfMouse.getX();
		y = positionOfMouse.getY();
		if ((x >= referencePoint.getX() + 5) &&
				(x <= referencePoint.getX() + size - 5) &&
				(y >= referencePoint.getY() + 5) &&
				(y <= (referencePoint.getY() + size - 5))) {
			shapeSelected = true;
			lastMousePosition = positionOfMouse;
			return true;
		}else {
			shapeSelected = false;
			return false;
		}*/
		return rect.shapeIsSelected(positionOfMouse);
	}

	/* 
	 * resetShapeSelected() simply resets the value of shapeSelected to false.
	 */

	public void resetShapeSelected(){
		shapeSelected = false;
	}

	/*
	 * calculateArea() returns the area of the rectangle
	 */

	double calculateArea(){ 
		return rect.calculateArea();
	}

	/*
	 * showMe(g) displays the square using the Graphic object g.
	 * It sets the color to be used, and draws the square using the specified 
	 * reference point, the size (representing both the width and the size).
	 */

	public void showMe(Graphics g){
		rect.showMe(g);
		/*
		g.setColor(colorShape);
		g.drawRect(referencePoint.getX(), // Draw a rectangle with the specified 
				// corner point
				referencePoint.getY(),  // width and size
				size,
				size);*/
	}

	/* 
	 * toString() returns the description of the square - the color, 
	 * the reference point and the size.
	 */

	public String toString(){
		return ("Square with reference point " + rect.returnReferencePoint() + 
				" having size " + rect.getWidth() + "\n");
	}

	/*
	 * savePositionWhereUserPressedMouse(int x, int y)is used to 
	 * save the position where the user Pressed the mouse button.
	 * This is useful for moving the rectangle.
	 */
	
	public void savePositionWhereUserPressedMouse(int x, int y){
		rect.savePositionWhereUserPressedMouse(x, y);
	}
}

