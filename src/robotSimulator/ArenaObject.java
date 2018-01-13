package robotSimulator;

import java.io.Serializable;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public abstract class ArenaObject implements Serializable
{
	/**
	 * serialVersionUID is used for saving created objects
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * XPosition holds the current X coordinate of the object.
	 */
	private int XPosition;
	/**
	 * YPosition holds the current Y coordinate of the object.
	 */
	private int YPosition;
	/**
	 * ID holds the ID of the object.
	 */
	private int ID;
	/**
	 * Size holds the size of the object.
	 */
	private int Size;
	/**
	 * Solid to determine if an object is solid.
	 */
	private boolean Solid;
	/**
	 * CurrentArena shows which Arena the object is in.
	 */
	private Arena CurrentArena;
	/**
	 * IDCalculator is used to ensure unique ID numbers.
	 */
	private static int IDCalculator;
}
