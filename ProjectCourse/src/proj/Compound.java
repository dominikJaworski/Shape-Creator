package proj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Compound extends Shape{
	ArrayList<Shape> components = new ArrayList<Shape>();
	/*
	protected Coordinates referencePoint; //reference point for shape
	
	protected Coordinates lastMousePosition; // If a shape has 
	// been  selected, the position where the 
	// user has pressed the mouse to select the shape

	protected Color colorShape; // color of a shape 
	
	protected Color originalColorShape;// Used to save the original color when the color changes temporarily

	protected boolean shapeSelected = false;
	protected boolean perimeterSelected = false;*/
	
	public Compound(){
		
	}
	
	public void add(Shape s){
		components.add(s);
	}
	
	public ArrayList<Shape> getComponents(){
		return components;
	}
	//on perimeter of one of its shapes
	boolean onThePerimeter(Coordinates mousePosition) {
		for(Shape s : components){
			if(s.onThePerimeter(mousePosition)){
				//selectAllShapes();
				return true;
			}
		}
		return false;
	}

	@Override
	public void changeShape(int currentPhase, int size) {
		// TODO Auto-generated method stub
		
	}
	
	
	void moveShape(Coordinates currentPositionMouse){
		//are you sure you are using the right current position?
		//hint: look inside each shapes' moveshape function 
		//every shape should remember their current point and adjust it base on the variable you are passing in.
		for(Shape s : components){
			s.moveShape(currentPositionMouse);
		}
		//lastMousePosition = currentPositionMouse;
	}
	
	@Override
	public void changeColorTemporarily(){	 
		originalColorShape = colorShape; 
		colorShape = Color.yellow ;
		perimeterSelected = true;
		for( Shape s : components){
			s.changeColorTemporarily();
		}
	}
	@Override
	public void changeColorBack(){
		colorShape = originalColorShape;
		for( Shape s: components){
			s.changeColorBack();
		}
	}
	
	//make all shapes selected
	void selectAllShapes(Coordinates currentMouse){
		for(Shape s: components){
			if(s instanceof Compound){
				((Compound) s).selectAllShapes(currentMouse);
			}else{
				s.setShapeSelected(currentMouse);
				s.setLastMousePosition(currentMouse);
			}
		}
		//setShapeSelected(currentMouse);
	}
	
	void resetShapesSelected(){
		shapeSelected = false;
		for(Shape s: components){
			s.resetShapeSelected();
		}
	}
	
	//how would i make sure that all shapes in compound are selected
	//based on one shape selection?
	public boolean shapeIsSelected(Coordinates positionOfMouse) {
		
		for(Shape s : components){
			if(s.shapeIsSelected(positionOfMouse)){
				shapeSelected = true;
				selectAllShapes(positionOfMouse);
				return true;
			}
		}
		//shapeIsSelected(positionOfMouse);
		return false;
	}

	double calculateArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void showMe(Graphics g) {
		for(Shape s: components){
			s.showMe(g);
		}
	}

	@Override
	public String toString() {
		String out = "Compound Shape with:\n";
		for(Shape s: components){
			out += "\t" + s.toString() + "\n";
		}
		return out;
	}
}
