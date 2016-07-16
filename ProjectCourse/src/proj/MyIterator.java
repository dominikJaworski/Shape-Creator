package proj;

import java.util.Iterator;

public interface MyIterator{
	
	public void first();
	
	public void next();
	
	public Shape current();
	
	public boolean isShape();
	
	public boolean endList();
}
