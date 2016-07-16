package proj;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RectSquaresObserver extends OutputFrame implements Observer{
	
	public void update(Observable o, Object arg) {
		outputstring = "";
		//ArrayList<Shape> Shapelist = (ArrayList<Shape>) arg;
		ArrayListWithRectIterator Shapelist = new ArrayListWithRectIterator();
		
		Shapelist.setList((ArrayList<Shape>) arg);
		MyIterator rit = Shapelist.createIterator();
		for (rit.first(); !(rit.endList()); rit.next()){	
			if(rit.current() instanceof Compound){
				if(compoundHasRect((Compound) rit.current())){
					outputstring += rit.current().toString();
				}
			} else {
				outputstring += rit.current().toString();
			}
		}
		outputArea.setText(outputstring);
	}
	
	private boolean compoundHasRect(Compound c){
		for(Shape s: c.getComponents()){
			if(s instanceof Rectangle || s instanceof Square){
				return true;
			}
			if(s instanceof Compound){
				if(compoundHasRect((Compound) s)){
					return true;
				}
			}
		}
		return false;
	}
}
