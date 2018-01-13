package robotSimulator;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public class Arena 
{
	/**
	 * serialVersionUID is used for saving created objects
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Width holds the current width of the object.
	 */
	private int Width;
	/**
	 * Length holds the current length of the object.
	 */
	private int Length;
	/**
	 * MaxCapacity holds the maximum capacity of the object.
	 */
	private int MaxCapacity;
	/**
	 * Contents holds the current contents of the object.
	 */
	protected ArrayList<ArenaObject> Contents;
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Constructs an Arena object using pre-determined
	 * values.
	 * <p>
	 */
	Arena()
	{
		/*Sets Width*/
		SetWidth(500);
		/*Sets Length*/
		SetLength(500);
		/*Sets MaxCapacity*/
		MaxCapacity = 25;
		/*Sets Contents*/
		Contents = new ArrayList<ArenaObject>();
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Constructs an Arena object using values passed to the 
	 * constructor.
	 * <p>
	 * @param W is used to set the Width
	 * @param L is used to set the Length
	 * @param C is used to set the MaxCapacity
	 */
	Arena(int W, int L, int C)
	{
		/*Sets Width to W*/
		SetWidth(W);
		/*Sets Length to L*/
		SetLength(L);
		/*Sets MaxCapacity to C*/
		MaxCapacity = C;
		/*Sets Contents*/
		Contents = new ArrayList<ArenaObject>();
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
	public boolean Check(int X, int Y, ArenaObject O)
	{
		/*If object is outside the walls of the Arena*/
		if(X < O.GetSize() || Y < O.GetSize() || X > GetWidth()-O.GetSize() || Y >GetLength()-O.GetSize())
		{
			/*Returns true*/
			return true;
		}
		/*Otherwise*/
		else
		{
			/*For all objects in the Arena*/
			for(int ct = 0; ct < Contents.size(); ct++)
			{
				/*Create a line between the passed X,Y values and the coordinates of the current object*/
				LineHandler Checker = new LineHandler(X, Y, Contents.get(ct).GetXPosition(), Contents.get(ct).GetYPosition());
				/*Check if line length is less than the size of the two objects and greater than 1*/
				if(Checker.lineLength() < O.GetSize() + Contents.get(ct).GetSize() && Checker.lineLength() > 1)
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
	public boolean Add(ArenaObject O)
	{
		/*Temporary variable and object declaration*/
		int TempX = 0, TempY = 0;
		Random TempR = new Random();
		/*If there is still space in the arena*/
		if(Contents.size() < MaxCapacity)
		{
			/*Do at least once*/
			do
			{
				/*Generate and set random X and Y positions*/
				TempX = TempR.nextInt(this.Width - 20);
				TempY = TempR.nextInt(this.Length - 20);
				O.SetXPosition(TempX);
				O.SetYPosition(TempY);
			/*While the object cannot be placed*/
			}while (Check(TempX, TempY, O));
			/*Add object to Contents*/
			Contents.add(O);
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
	public boolean AddBasicRobot()
	{
		/*Creates object R of type BasicRobot*/
		BasicRobot R = new BasicRobot(0, 0, this);
		/*Return the result of Add(R)*/
		return Add(R);
	}
	/**
	 * Function definition for AddWhiskerRobot()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if robot is created or false if not
	 */
	public boolean AddWhiskerRobot()
	{
		/*Creates object R of type WhiskerRobot*/
		WhiskerRobot R = new WhiskerRobot(0, 0, this);
		/*Return the result of Add(R)*/
		return Add(R);
	}
	/**
	 * Function definition for AddLightRobot()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if robot is created or false if not
	 */
	public boolean AddLightRobot()
	{
		/*Creates object R of type LightRobot*/
		LightRobot R = new LightRobot(0, 0, this);
		/*Return the result of Add(R)*/
		return Add(R);
	}
	/**
	 * Function definition for AddLightSource()
	 * <p>
	 * Create an object and call Add() with it.
	 * <p>
	 * @return true if object is created or false if not
	 */
	public boolean AddLightSource()
	{
		/*Creates object R of type LightSource*/
		LightSource R = new LightSource(0, 0, this);
		/*Return the result of Add(R)*/
		return Add(R);
	}
	/**
	 * Function definition for GetWidth()
	 * <p>
	 * Handles retrieving an object's Width.
	 * <p>
	 * @return this.Width
	 */
	public int GetWidth() 
	{
		/*Return Width*/
		return Width;
	}
	/**
	 * Function definition for GetLength()
	 * <p>
	 * Handles retrieving an object's Length.
	 * <p>
	 * @return this.Length
	 */
	public int GetLength() 
	{
		/*Return Length*/
		return Length;
	}
	/**
	 * Function definition for SetWidth()
	 * <p>
	 * Handles setting an object's Width.
	 * <p>
	 * @return this.Width
	 */
	public void SetWidth(int W) 
	{
		/*Sets Width to W*/
		Width = W;
	}
	/**
	 * Function definition for SetLength()
	 * <p>
	 * Handles setting an object's Length.
	 * <p>
	 * @return this.Length
	 */
	public void SetLength(int L) 
	{
		/*Sets Length to L*/
		Length = L;
	}
}
