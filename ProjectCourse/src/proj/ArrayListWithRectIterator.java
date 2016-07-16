package proj;

import java.util.ArrayList;

public class ArrayListWithRectIterator extends AbstractList{

	ArrayList<Shape> myList = new ArrayList<Shape>();
	public void setList(ArrayList<Shape> l){
		myList = l;
	}
	
	public int getSize(){
		return myList.size();
	}
	
	public void append(Shape valueToBeInserted){ // Inserts new int value into into a sorted list of Integers
		myList.add(valueToBeInserted);
	}
	
	public Shape get(int i){
		return myList.get(i);
	}

	private class RectIterator implements MyIterator{

		int current;
		
		public RectIterator(){
			current = 0;
		}
		public void first() {
			current = 0;
			while(!(endList())){
				if(isShape()){
					break;
				}
				else current++;
			}
		}

		public void next() {
			
			current++;
			
			//while (current element is not a square or rectangle) and 
			//current is not at 
			while(!(endList())){
				if(isShape()){
					break;
				}
				else current++;
			}
		}

		public Shape current() {
			return myList.get(current);
		}

		public boolean isShape() {
			if(get(current) instanceof Square || get(current) instanceof Rectangle ||
					get(current) instanceof Compound){
				return true;
			}
			return false;
		}
		@Override
		public boolean endList() {
			if(myList.size() == 0){
				return true;
			}else if(current >= myList.size()){
				return true;
			}
			return false;
		}
		
	}
	@Override
	public MyIterator createIterator() {
		// TODO Auto-generated method stub
		return new RectIterator();
	}

}
