package proj;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

	protected Coordinates referencePoint; //reference point for shape
	
	protected Coordinates lastMousePosition; // If a shape has 
	// been  selected, the position where the 
	// user has pressed the mouse to select the shape

	protected Color colorShape; // color of a shape 
	
	protected Color originalColorShape;// Used to save the original color when the color changes temporarily

	protected boolean shapeSelected = false;
	protected boolean perimeterSelected = false;// Whether the shape has  been selected for 
	// moving by pressing mouse button inside the shape

	abstract boolean onThePerimeter(Coordinates mousePosition);
	
	public void changeColor(Color c){
		colorShape = c ;
	}
	
	public void changeColorTemporarily(){	 
		originalColorShape = colorShape; 
		colorShape = Color.yellow ;
		perimeterSelected = true;
	}
	
	public void setShapeSelected(boolean b){
		shapeSelected = b;
	}
	
	public void setShapeSelected(Coordinates currentMousePosition){
		shapeSelected = true;
		lastMousePosition = currentMousePosition;
	}
	public void setLastMousePosition(Coordinates lastMouse){
		lastMousePosition = lastMouse;
	}
	public void changeColorBack(){	 
		colorShape = originalColorShape; 
		perimeterSelected = false;
	}
	public abstract void changeShape(int currentPhase, int size);
	
	public boolean shapeIsSelected(){
		return shapeSelected;
	}
	public abstract boolean shapeIsSelected(Coordinates positionOfMouse);
	public void resetShapeSelected(){
		shapeSelected = false;
	}
	void moveShape(Coordinates currentPositionMouse){	      
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
	abstract double calculateArea();
	public abstract void showMe(Graphics g);
	public abstract String toString();
	
	public void savePositionWhereUserPressedMouse(int x, int y){
		lastMousePosition = new Coordinates(x, y);
	}
	
}
