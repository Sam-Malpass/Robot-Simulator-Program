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
}
