package proj;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.*;

public class OutputFrame extends JFrame{
	final int WIDTH = 200, HEIGHT = 300;
	protected JTextArea outputArea;
	protected String outputstring;
	 
	public OutputFrame(){
		outputArea = new JTextArea(20, 30);
		add(outputArea);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
	
	public void displayResult(String s){
		outputArea.setText(s);
	}
	
	public void displayresult(){
		outputArea.setText(outputstring);
	}
}
