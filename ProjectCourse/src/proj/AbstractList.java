package proj;

import java.util.ArrayList;

public abstract class AbstractList {
	
	public abstract void setList(ArrayList<Shape> l);
	
	public abstract int getSize();
	
	public abstract void append(Shape valueToBeInserted);
	
	public abstract Shape get(int i);
	
	private abstract class Iterator implements MyIterator{
		
	}
	
	public abstract MyIterator createIterator();
  
}
