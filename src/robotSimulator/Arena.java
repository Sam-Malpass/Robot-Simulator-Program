package robotSimulator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public class Arena implements Serializable
{
	/**
	 * serialVersionUID is used for saving created objects
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * width holds the current width of the object.
	 */
	private int width;
	/**
	 * length holds the current length of the object.
	 */
	private int length;
	/**
	 * maxCapacity holds the maximum capacity of the object.
	 */
	private int maxCapacity;
	/**
	 * contents holds the current contents of the object.
	 */
	protected ArrayList<ArenaObject> contents;
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Constructs an Arena object using pre-determined
	 * values.
	 * <p>
	 */
	Arena()
	{
		/*Sets width*/
		setWidth(500);
		/*Sets length*/
		setLength(500);
		/*Sets maxCapacity*/
		maxCapacity = 25;
		/*Sets contents*/
		contents = new ArrayList<ArenaObject>();
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Constructs an Arena object using values passed to the 
	 * constructor.
	 * <p>
	 * @param W is used to set the width
	 * @param L is used to set the length
	 * @param C is used to set the maxCapacity
	 */
	Arena(int W, int L, int C)
	{
		/*Sets width to W*/
		setWidth(W);
		/*Sets length to L*/
		setLength(L);
		/*Sets maxCapacity to C*/
		maxCapacity = C;
		/*Sets contents*/
		contents = new ArrayList<ArenaObject>();
	}
	/**
	 * Function definition for Check()
	 * <p>
	 * Checks whether an object can be placed at a set position.
	 * <p>
	 * @param X is the X coordinate to be tested against
	 * @param Y is the Y coordinate to be tested against
	 * @param O is the object to be tested against
	 * @return true if checks fail or false if all tests succeed
	 */
	public boolean check(int X, int Y, ArenaObject O)
	{
		/*If object is outside the walls of the Arena*/
		if(X < O.getSize() || Y < O.getSize() || X > getWidth()-O.getSize() || Y >getLength()-O.getSize())
		{
			/*Returns true*/
			return true;
		}
		/*Otherwise*/
		else
		{
			/*For all objects in the Arena*/
			for(int ct = 0; ct < contents.size(); ct++)
			{
				/*Create a line between the passed X,Y values and the coordinates of the current object*/
				Line Checker = new Line(X, Y, contents.get(ct).getXPosition(), contents.get(ct).getYPosition());
				/*Check if line length is less than the size of the two objects and greater than 1*/
				if(Checker.lineLength() < O.getSize() + contents.get(ct).getSize() && Checker.lineLength() >= 1)
				{
					/*Return True*/
					return true;
				}
			}
		}
		/*If all tests are passed return false*/
		return false;	
	}
	/**
	 * Function definition for Add()
	 * <p>
	 * Creates temporary variables and objects, tests them within the arena and adds them if the position
	 * generated is suitable
	 * <p>
	 * @return true if robot is created or false if not
	 */
	public boolean add(ArenaObject O)
	{
		/*Temporary variable and object declaration*/
		int TempX = 0, TempY = 0, Possible = 0;
		Random TempR = new Random();
		/*If there is still space in the arena*/
		if(contents.size() < maxCapacity)
		{
			/*Do at least once*/
			do
			{
				if(Possible > 1000)
				{
					return false;
				}
				/*Generate and set random X and Y positions*/
				TempX = TempR.nextInt(this.width - 20);
				TempY = TempR.nextInt(this.length - 20);
				O.setXPosition(TempX);
				O.setYPosition(TempY);
				Possible++;
			/*While the object cannot be placed*/
			}while (check(TempX, TempY, O));
			/*Add object to contents*/
			contents.add(O);
			/*Return true*/
			return true;
		}
		/*Otherwise*/
		else
		{
			/*Return false*/
			return false;
		}
	}
	/**
	 * Function definition for addBasicRobot()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if robot is created or false if not
	 */
	public boolean addBasicRobot()
	{
		/*Creates object R of type BasicRobot*/
		BasicRobot R = new BasicRobot(0, 0, this);
		/*Return the result of Add(R)*/
		return add(R);
	}
	/**
	 * Function definition for AddWhiskerRobot()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if robot is created or false if not
	 */
	public boolean addWhiskerRobot()
	{
		/*Creates object R of type WhiskerRobot*/
		WhiskerRobot R = new WhiskerRobot(0, 0, this);
		/*Return the result of Add(R)*/
		return add(R);
	}
	/**
	 * Function definition for AddLightRobot()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if robot is created or false if not
	 */
	public boolean addLightRobot()
	{
		/*Creates object R of type LightRobot*/
		LightRobot R = new LightRobot(0, 0, this);
		/*Return the result of Add(R)*/
		return add(R);
	}
	/**
	 * Function definition for AddLightSource()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if object is created or false if not
	 */
	public boolean addLightSource()
	{
		/*Creates object R of type LightSource*/
		LightSource R = new LightSource(0, 0, this);
		/*Return the result of Add(R)*/
		return add(R);
	}
	/**
	 * Function definition for AddObstacleBlock()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if object is created or false if not
	 */
	public boolean addObstacleBlock()
	{
		/*Creates object R of type ObstacleBlock*/
		ObstacleBlock R = new ObstacleBlock(0, 0, this);
		/*Return the result of Add(R)*/
		return add(R);
	}
	/**
	 * Function definition for ListContents()
	 * <p>
	 * Handles listing the IDs and positions of all objects within the arena, by going through all object in the arena and adding
	 * the data to a string builder.
	 * <p>
	 * @return s which is the string builder
	 */
	public String listContents()
	{
		/*Declare a String to act as a string builder*/
		String s = "";
		/*For all objects in the arena*/
		for(int ct = 0; ct < contents.size(); ct++)
		{
			/*If the object is a LightSource*/
			if(contents.get(ct) instanceof LightSource)
			{
				/*Add the LightSource's data to the string builder s*/
				s += "Light: " + contents.get(ct).getID() + "  X:" + contents.get(ct).getXPosition() + "  Y:" + contents.get(ct).getYPosition() + "\n";	
			}
			/*If the object is an ObstacleBlock*/
			else if(contents.get(ct) instanceof ObstacleBlock)
			{
				/*Add the ObstacleBlock's data to the string builder s*/
				s += "Obstacle: " + contents.get(ct).getID() + "  X:" + contents.get(ct).getXPosition() + "  Y:" + contents.get(ct).getYPosition() + "\n";
			}
			/*Otherwise the object must a robot*/
			else
			{
				/*Add the Robot's data to the string builder s*/
				s += "Robot: " + contents.get(ct).getID() + "  X:" + contents.get(ct).getXPosition() + "  Y:" + contents.get(ct).getYPosition() + "\n";
			}
		}
		/*Return s*/
		return s;
	}
	/**
	 * Function definition for ClearAll()
	 * <p>
	 * Handles resetting the IDCalculator and clearing the arena of objects.
	 */
	public void clearAll()
	{
		/*Clear the ArrayList contents*/
		contents.clear();
		/*Set the IDCalculator to 0*/
		ArenaObject.resetIDCalculator();
	}
	/**
	 * Function definition for Simulate()
	 * <p>
	 * For all objects within the arena, attempt a movement.
	 */
	public void simulate()
	{
		/*For all objects in the arena*/
		for(int ct = 0; ct < contents.size(); ct++)
		{
			/*Attempt a movement*/
			contents.get(ct).attemptMove();
		}
	}
	/**
	 * Function definition for GetWidth()
	 * <p>
	 * Handles retrieving an object's width.
	 * <p>
	 * @return this.Width
	 */
	public int getWidth() 
	{
		/*Return width*/
		return width;
	}
	/**
	 * Function definition for GetLength()
	 * <p>
	 * Handles retrieving an object's length.
	 * <p>
	 * @return this.Length
	 */
	public int getLength() 
	{
		/*Return length*/
		return length;
	}
	/**
	 * Function definition for SetWidth()
	 * <p>
	 * Handles setting an object's width.
	 * <p>
	 * @return this.Width
	 */
	public void setWidth(int W) 
	{
		/*Sets width to W*/
		width = W;
	}
	/**
	 * Function definition for SetLength()
	 * <p>
	 * Handles setting an object's length.
	 * <p>
	 * @return this.Length
	 */
	public void setLength(int L) 
	{
		/*Sets length to L*/
		length = L;
	}
}
