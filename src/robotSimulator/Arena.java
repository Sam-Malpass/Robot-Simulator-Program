package robotSimulator;

import java.util.ArrayList;

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
