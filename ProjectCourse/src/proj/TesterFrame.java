package proj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.event.*;

public class TesterFrame extends JFrame{
	final int WIDTH = 800, HEIGHT = 500; // the width and height of the frame
	/*
	 * showSquaresAndRectangles will display information about all the squares and rectangles
	 * showBigCircles displays information about circles whose area exceeds 5000.00
	 */
	RectSquaresObserver showSquaresAndRectangles; 
	BigCirclesObserver showBigCircles;
	ArrayList<Shape> Shapelist;
	ShapeFactory factory = new ShapeFactory();
	ArrayList<Shape> holdforCompound = new ArrayList<Shape>();
	
	Observed observe = new Observed();
    /* 
     * myShapes is an array of all the shapes (circles, rectangles and squares) that we have
     * In this version we will handle only a maximum of 20 shapes.
     */
	Object currentShape;
	//Object myShapes[];
	/*  
	 * numShapes is the number of shapes the frame is dealing with.
	 * currentPhase depicts the current situation as follows:
	 *   currentPhase = 0, means the user may move any of the shapes
	 *                = 1, a new shape(circles, rectangles and squares) is just created
	 *                = 2, the the color of the new shape is being modified,
	 *                = 3, the width of the new rectangle or the size of the new square or the diameter 
	 *                     of the new circle is being modified,
	 *                = 4, the height of the new rectangle is being modified.
	 *                = 5, a new compound shape is being defined that contains a number of other shapes 
	 *                     (circles, rectangles and squares or other compound shapes). Most of the code 
	 *                     for this phase is not included in this class definition. It is your 
	 *                     responsibility to develop that.
	 */
	int  numShapes, currentPhase;
	
	/* 
	 * The frame includes the following components:
	 * 		myPanel - the panel where all the shapes (circles, rectangles, squares and compound shapes) 
	 *                are displayed.
	 *      shapeButtonPanel -  contains the buttons to specify what type of new shape to create
	 *                (circles, rectangles, squares and compound shapes,
	 *      colorChooserPanel - contains the radiobuttons redButton, greenButton, blueButton
	 *      sizeSpecifier - a slider to specify the size of the shapes (width or height of rectangles, 
	 *                   size of square or the diameter of circles)  
	 *      messageArea - an area for messages to the user 
	 *      yesButton, noButton - buttons for interaction when defining a new shape (circles, rectangles, squares)  
	 *      	
	 */
	EditorPanel myPanel; 
	JPanel shapeButtonPanel, colorChooserPanel;
	JSlider sizeSpecifier;
	JTextField messageArea;
	JRadioButton yesButton, noButton;
	ButtonGroup radioButtonGroup, radioButtonGroupForChoosingColor;
	JRadioButton redButton, greenButton, blueButton;
	
	/*
	 * redisplay has the following responsibilities:
	 * 		a) display in showSquaresAndRectangles details about rectangles and squares
	 *      b) display in showBigCircle details about circles that have an area > 5000
	 */
	
	public Observable getOB()
	{
		return observe;
	}
	
	public void redisplay(){
		
		myPanel.repaint();
		/*
		for (int i = 0; i < numShapes; i++){
			if ((Shapelist.get(i) instanceof Square) || 
			    (Shapelist.get(i) instanceof Rectangle))	
			outputString1 += Shapelist.get(i).toString();
			if ((Shapelist.get(i) instanceof Circle) && 
				(((Circle)Shapelist.get(i)).calculateArea() > 5000)){
				outputString2 += Shapelist.get(i).toString();
			}
		}
		showSquaresAndRectangles.displayResult(outputString1);
		showBigCircles.displayResult(outputString2);*/
		
		//showSquaresAndRectangles.displayresult();
		//showBigCircles.displayresult();
		observe.setChanged();
		observe.notifyObservers(Shapelist);
	}
	
	/*
	 * paintComponent is called when repaint is invoked.
	 * paintComponents simply displays all shapes. Hint : use polymorphism to simplify the code 
	 * 
	 */
	
	private class EditorPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for (int index = 0; index < numShapes; index++){// Show all the shapes in my_shapes. 
				
				(Shapelist.get(index)).showMe(g);
				/*
				if (Shapelist.get(index) instanceof Square){
					((Square) Shapelist.get(index)).showMe(g);
				} else if (Shapelist.get(index) instanceof Rectangle){
					((Rectangle) Shapelist.get(index)).showMe(g);
				} else {
					((Circle) Shapelist.get(index)).showMe(g);
				}*/

			}
		}
	}

	private EditorPanel buildEditorPanel(){
		EditorPanel myPanel;
		myPanel = new EditorPanel();

		myPanel.addMouseListener(new MouseAdapter(){
			/* 
			 * mousePressed is important for selecting a shape for dragging it (currentPhase = 0) and for
			 *              selecting a perimeter of a shape to define a compound shape (currentPhase = 5).
			 * 
			 * mouseReleased is important to denote that the operation of dragging a shape is over.
			 * Hint : use polymorphism to simplify the code
			 */
			public void mouseReleased(MouseEvent e){
				for (int index = 0; index < numShapes; index++){
					// when the mouse button is released reset the flags indicating that shape(s) or 
					// edge(s) has been selected and restore color since the drag operation or the modify 
					// operation is over.
					( Shapelist.get(index)).resetShapeSelected();// resetShapeSelected resets the flag 
                                              //  denoting the shape is selected.
					/*
					if (Shapelist.get(index) instanceof Square){
						((Square) Shapelist.get(index)).resetShapeSelected();// resetShapeSelected resets the flag 
						                                                //  denoting the shape is selected.
					} else if (Shapelist.get(index) instanceof Rectangle){
						((Rectangle) Shapelist.get(index)).resetShapeSelected();
					} else {
						((Circle) Shapelist.get(index)).resetShapeSelected();
					}*/
				}
			}

			public void mousePressed(MouseEvent e){
				int x_value, y_value;
				Coordinates currentMousePosition;
				x_value = e.getX(); // Find the coordinates of the position where the user pressed the mouse button
				y_value = e.getY();
				currentMousePosition = new Coordinates(x_value, y_value);
				if (currentPhase == 0){
					for (int index = 0; index < numShapes; index++){
						/* 
						 * If a shape is selected by pressing mouse button inside the shape, 
						 * save the place where the user pressed mouse button sets the flag 
                         * denoting that the shape is selected.
						 */
						// shapeIsSelected sets the flag 
                            //  denoting the shape is selected.
						boolean selected = ( Shapelist.get(index)).shapeIsSelected(currentMousePosition);
						System.out.println(selected);
						
				/*
						if (Shapelist.get(index) instanceof Square){ // shapeIsSelected sets the flag 
                                                                //  denoting the shape is selected.
							((Square) Shapelist.get(index)).shapeIsSelected(currentMousePosition);
						} else if (Shapelist.get(index) instanceof Rectangle){
							((Rectangle) Shapelist.get(index)).shapeIsSelected(currentMousePosition);
						} else {
							((Circle) Shapelist.get(index)).shapeIsSelected(currentMousePosition);
						}*/

						
					}
				} else if (currentPhase == 5){
					/* 
					 * If we are defining compound shape i.e., currentPhase == 5, we select a
					 * shape by pressing the mouse button very close to the perimeter.
					 * The color of the shape is temporarily changed to yellow. 
					 * Hint : use polymorphism to simplify the code.
					 */
					
					for (int index = 0; index < numShapes; index++){
						if ((Shapelist.get(index)).onThePerimeter(currentMousePosition)){
							( Shapelist.get(index)).changeColorTemporarily();
							holdforCompound.add(Shapelist.get(index));
						}
						/*
						if (Shapelist.get(index) instanceof Square){
							if (((Square) Shapelist.get(index)).onThePerimeter(currentMousePosition)){
								((Square) Shapelist.get(index)).changeColorTemporarily();
							}
						} else if (Shapelist.get(index) instanceof Rectangle){
							if (((Rectangle) Shapelist.get(index)).onThePerimeter(currentMousePosition)){
								((Rectangle) Shapelist.get(index)).changeColorTemporarily();
							}
						} else {
							if (((Circle) Shapelist.get(index)).onThePerimeter(currentMousePosition)){
								((Circle) Shapelist.get(index)).changeColorTemporarily();
							}
						}*/
					}
					
				}
				redisplay();
			}   	                      
		});

		myPanel.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				if (currentPhase == 0){
					/* 
					 * if the mouse is dragged when currentPhase is 0, the selected shapes move 
					 * with the mouse, using method moveShape.
					 * Hint : use polymorphism to simplify the code 
					 */
					for (int index = 0; index < numShapes; index++){ 
						// If the operation is to drag shapes
						if((Shapelist.get(index)).shapeIsSelected()){
							
							(Shapelist.get(index)). moveShape(new Coordinates(e.getX(), e.getY()));
						}
					/*
					ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
					for(int i = 0; i < numShapes; i++){
						if((Shapelist.get(i)).shapeIsSelected()){
							selectedShapes.add(Shapelist.get(i));
						}
					}
					boolean isInCompound = false;
					for(int i = 0; i < numShapes; i++){
						if(Shapelist.get(i) instanceof Compound){
							if(Shapelist.get(i).contains(selectedShapes)){
								
							}
						}
					}*/
						
						/*
						if (Shapelist.get(index) instanceof Square){
							if (((Square)Shapelist.get(index)). shapeIsSelected())
							{
								((Square)Shapelist.get(index)). moveShape(new Coordinates(e.getX(), e.getY()));
							}
						} else if (Shapelist.get(index) instanceof Rectangle){
							if (((Rectangle)Shapelist.get(index)). shapeIsSelected())
							{
								((Rectangle)Shapelist.get(index)). moveShape(new Coordinates(e.getX(), e.getY()));
							} 
						} else if (Shapelist.get(index) instanceof Compound){
							if (((Compound)Shapelist.get(index)). shapeIsSelected())
							{
								((Compound)Shapelist.get(index)). moveShape(new Coordinates(e.getX(), e.getY()));
							} 
						}else if (((Circle)Shapelist.get(index)). shapeIsSelected()){
						       ((Circle)Shapelist.get(index)). moveShape(new Coordinates(e.getX(), e.getY()));
						}*/
						
					}
				}
				redisplay();   
			}

		});

		myPanel.setBackground(Color.WHITE);
		return myPanel;

	}
	
	private JPanel buildShapeChooserPanel(){
		JPanel buttonPanel;
		JButton squareButton, rectangleButton, circleButton, compoundFigureButton;
        /*
         * Create each of the buttons squareButton, rectangleButton, circleButton, compoundFigureButton
         * and define the event handler for ActionEvent.
         */

		buttonPanel = new JPanel();
		
		compoundFigureButton = new JButton("COMPOUND");
		compoundFigureButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (currentPhase == 0){
					currentPhase = 5;
					//Shapelist.add(factory.getShape("COMPOUND"));
					messageArea.setText("select figures to glue then select no");
					redisplay();
				} else{
					messageArea.setText("Operation not allowed");
				}
			}
		}
		);
		buttonPanel.add(compoundFigureButton);

		squareButton = new JButton("SQUARE");
		squareButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (currentPhase == 0){
					currentPhase = 1;
					Shapelist.add(factory.getShape("SQUARE"));//make a factory method
					sizeSpecifier.setValue(50);
					numShapes++;
					messageArea.setText("Change the color?");
					redisplay();
				} else{
					messageArea.setText("Operation not allowed");
				}
			}
		}
		);
		buttonPanel.add(squareButton);

		rectangleButton = new JButton("RECTANGLE");
		rectangleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (currentPhase == 0){
					currentPhase = 1;
					Shapelist.add(factory.getShape("RECTANGLE"));
					sizeSpecifier.setValue(50);
					numShapes++;
					messageArea.setText("Change the color?");
					redisplay();
				} else{
					messageArea.setText("Operation not allowed");
				}
			}
		}
		);

		buttonPanel.add(rectangleButton);

		circleButton = new JButton("CIRCLE");
		circleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (currentPhase == 0){
					currentPhase = 1;
					Shapelist.add(factory.getShape("CIRCLE"));
					
					sizeSpecifier.setValue(50);
					numShapes++;
					messageArea.setText("Change the color?");
					
					redisplay();
				} else{
					messageArea.setText("Operation not allowed");
				}
			}
		}
		);
		buttonPanel.add(circleButton);

		messageArea = new JTextField(20);
		buttonPanel.add(messageArea);
		
		/*
		 * yesButton and noButton is used for a dialog with the user. For the yesButton,
		 * if currentPhase = 1, or 2 the color for the latest shape has to be redefined, 
	     *                 = 3, the width of the new rectangle or the size of the new square or the diameter 
	     *                      of the new circle is being modified,
	     *                 = 4, the height of the new rectangle is being modified.
	     *                 = 5, 
		 */

		yesButton = new JRadioButton("YES");
		yesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if (currentPhase < 3){
					currentPhase = 2;
					messageArea.setText("Select the color");
					radioButtonGroup.clearSelection();
					radioButtonGroupForChoosingColor.clearSelection();
					redisplay();
				} else if (currentPhase == 3){
					if (Shapelist.get(numShapes-1) instanceof Square){
						messageArea.setText("use slider to specify the size of the square");
					} else if (Shapelist.get(numShapes-1) instanceof Rectangle){
						messageArea.setText("use slider to specify the width of the rectangle");
					} else if (Shapelist.get(numShapes-1) instanceof Circle){
						messageArea.setText("use slider to specify the diameter of the circle");
					}


				} else if (currentPhase == 4){
					messageArea.setText("use slider to specify the height of the rectangle");
				}
			}
		}
		);  
		
		/*
		 * For the noButton,
		 * if currentPhase = 1, or 2 the color for the latest shape has to remain the same, 
	     *                 = 3, the width of the new rectangle or the size of the new square or the diameter 
	     *                      of the new circle has to remain the same,
	     *                 = 4, the height of the new rectangle has to remain the same.
	     *                 = 5, defining the compound shape is over. (Right now we only reset the flag 
	     *                      indicating that a shape is no longer selected for inclusion into a compound 
	     *                      shape. You will put in more code to complete this work.
		 */

		noButton = new JRadioButton("NO");
		noButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (currentPhase < 3){
					radioButtonGroup.clearSelection();
					radioButtonGroupForChoosingColor.clearSelection();
					if (Shapelist.get(numShapes-1) instanceof Square){
						messageArea.setText("Change the size of the square?");
						currentPhase = 3;
						redisplay();
					} else if (Shapelist.get(numShapes-1) instanceof Rectangle){
						messageArea.setText("Change the width of the Rectangle?");
						currentPhase = 3;
						redisplay();
					} 
					else if (Shapelist.get(numShapes-1) instanceof Circle){
						messageArea.setText("Change the diameter of the circle?");
						currentPhase = 3;
						redisplay();
					} 
				} else if (currentPhase == 3) {
					if (Shapelist.get(numShapes-1) instanceof Rectangle){
						radioButtonGroup.clearSelection();
						messageArea.setText("Change the height of the Rectangle?");
						currentPhase = 4;
						sizeSpecifier.setValue(50);
					} else{
						radioButtonGroup.clearSelection();
						currentPhase = 0;
						messageArea.setText("");
					}
				}
				else if (currentPhase == 4){
					radioButtonGroup.clearSelection();
					currentPhase = 0;
					messageArea.setText("");
				}
				else if (currentPhase == 5){
					radioButtonGroup.clearSelection();
					currentPhase = 0;
					messageArea.setText("");
					
					for (int index = 0; index < numShapes; index++){ 
						// If the operation is to drag shapes
						
						Shapelist.get(index).changeColorBack();
						
					}   
					Compound compound = (Compound) factory.getShape("COMPOUND");
					
					for(Shape s : holdforCompound){
						compound.add(s);
						
						Shapelist.remove(s);
						numShapes--;
					}
					
					holdforCompound.clear();
					Shapelist.add(compound);
					numShapes++;
					redisplay();
				}
			}

		}
		);

		radioButtonGroup = new ButtonGroup();

		radioButtonGroup.add(yesButton);
		radioButtonGroup.add(noButton);

		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);

		return buttonPanel;
	}

	private void changeColor(Object shape, Color c){
		/* 
		 * change color of shape to c
		 */
		if (currentPhase == 2){ 
			if (shape instanceof Square){
				((Square)shape).changeColor(c);
			} else if (shape instanceof Rectangle){
				((Rectangle)shape).changeColor(c);
			} else if (shape instanceof Circle){
				((Circle)shape).changeColor(c);
			}
			
			messageArea.setText("Change the color?");
			radioButtonGroup.clearSelection();
		} else {
			messageArea.setText("Operation not allowed");
			radioButtonGroup.clearSelection();
		}
	}
	/*
	 * buildColorChooserPanel included 3 radio buttons so that users can select red, blue or green
	 * in addition to the original black color for the newly created shape. The user can select 
	 * one of these buttons to change the color for the newly created shape.
	 * We have used a  straight-forward anonymous handler for events in each radio button.
	 */

	private JPanel buildColorChooserPanel(){
		JPanel radioButtonPanel;
		

		radioButtonPanel = new JPanel();		
		redButton = new JRadioButton("RED");
		redButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeColor(Shapelist.get(numShapes-1), Color.red);
				redisplay();
			}
		}
		);

		greenButton = new JRadioButton("GREEN");
		greenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeColor(Shapelist.get(numShapes-1), Color.green);
				redisplay();
			}
		}
		);

		blueButton = new JRadioButton("BLUE");
		blueButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeColor(Shapelist.get(numShapes-1), Color.blue);
				redisplay();
			}
		}
		);

		radioButtonGroupForChoosingColor = new ButtonGroup();

		radioButtonGroupForChoosingColor.add(blueButton);
		radioButtonGroupForChoosingColor.add(greenButton);
		radioButtonGroupForChoosingColor.add(redButton);

		radioButtonPanel.add(blueButton);
		radioButtonPanel.add(greenButton);
		radioButtonPanel.add(redButton);

		return radioButtonPanel;
	}

/*
 * sizeSpecifer is an object of the  JSlider class. The value specified by the user sets the width 
 * or the height of the rectangle, the size of the circle or the square. Note that the code will be 
 * simplified using polymorphism.
 */
	private void buildSlider(){
		int i;
		sizeSpecifier = new JSlider(SwingConstants.VERTICAL, 0, 200, 50);
		sizeSpecifier.setMajorTickSpacing(10);
		sizeSpecifier.setPaintTicks(true);
		sizeSpecifier.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				radioButtonGroup.clearSelection();
				if (currentPhase == 3){
					if (Shapelist.get(numShapes-1) instanceof Square){
						((Square) Shapelist.get(numShapes-1)).
						changeShape(currentPhase, sizeSpecifier.getValue());
					} else if (Shapelist.get(numShapes-1) instanceof Rectangle){
						((Rectangle) Shapelist.get(numShapes-1)).
						changeShape(currentPhase, sizeSpecifier.getValue());
					} else if (Shapelist.get(numShapes-1) instanceof Circle){
						((Circle) Shapelist.get(numShapes-1)).
						changeShape(currentPhase, sizeSpecifier.getValue());
					}
				} else if (currentPhase == 4){
					((Rectangle) Shapelist.get(numShapes-1)).
					changeShape(currentPhase, sizeSpecifier.getValue());
				}
				redisplay();
			}
		}
		);
	}

	public TesterFrame(){	
		showSquaresAndRectangles = new RectSquaresObserver();//use as observer
		showBigCircles = new BigCirclesObserver();//use as observer
		Shapelist = new ArrayList<Shape>();
		numShapes = 0;
		currentPhase = 0;

		myPanel = buildEditorPanel();
		shapeButtonPanel = buildShapeChooserPanel();
		colorChooserPanel = buildColorChooserPanel();
		buildSlider();
		observe.addObserver(showSquaresAndRectangles);
		observe.addObserver(showBigCircles);
		add(colorChooserPanel, BorderLayout.NORTH);
		add(shapeButtonPanel, BorderLayout.SOUTH);
		add(sizeSpecifier, BorderLayout.EAST);
		add(myPanel, BorderLayout.CENTER);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}

	
	public static void main(String a[]){
		
		TesterFrame aFrame = new TesterFrame();
	}

}
