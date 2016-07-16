package proj;

import java.util.ArrayList;

public class ArrayListWithCircIterator extends AbstractList{
private ArrayList<Shape> myList;
	
	public ArrayListWithCircIterator(){
		myList = new ArrayList<Shape>();
	}
	
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
	
	
	
	private class BigCircIterator implements MyIterator{

		private int current = 0;
		//myList.size() will act as number of elements
		
		public BigCircIterator(){
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
			
			//while (current element is not a circle bigger than 5000) and 
			//current is less than myList size
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
			if((get(current) instanceof Circle && get(current).calculateArea() >= 5000.00) ||
					get(current) instanceof Compound){
				return true;
			}
			return false;
		}
		
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
		return new BigCircIterator();
	}
}
