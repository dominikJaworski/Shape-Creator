package proj;

public class ShapeFactory {
	public Shape getShape(String input){
		if(input.compareToIgnoreCase("SQUARE")== 0)
			return new Square();
		else if(input.compareToIgnoreCase("RECTANGLE")== 0)
			return new Rectangle();
		else if(input.compareToIgnoreCase("CIRCLE")== 0)
			return new Circle();
		else if(input.compareToIgnoreCase("COMPOUND") == 0)
			return new Compound();
		return null;
	}
}
