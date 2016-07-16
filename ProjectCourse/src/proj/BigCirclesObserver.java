package proj;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BigCirclesObserver extends OutputFrame implements Observer{
	
	
	public void update(Observable arg0, Object arg) {
		outputstring = "";
		ArrayListWithCircIterator Shapelist =  new ArrayListWithCircIterator();
		Shapelist.setList((ArrayList<Shape>) arg);
		//will use iterator that points to circles only
		MyIterator cit = Shapelist.createIterator();
		for(cit.first(); !(cit.endList()); cit.next()){
			if(cit.current() instanceof Compound){
				if(compoundHasBigCirc((Compound) cit.current())){
					outputstring += (cit.current()).toString();
				}
			}else{
				outputstring += (cit.current()).toString();
			}
		}
		
		outputArea.setText(outputstring);
	}
	
	private boolean compoundHasBigCirc(Compound c){
		for(Shape s: c.getComponents()){
			if(s instanceof Circle || s.calculateArea() > 5000.00){
				return true;
			}
			if(s instanceof Compound){
				if(compoundHasBigCirc((Compound) s)){
					return true;
				}
			}
		}
		return false;
	}
}
