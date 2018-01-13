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
	/**
	 * Function definition for GetXPosition()
	 * <p>
	 * Handles retrieving an object's XPosition.
	 * <p>
	 * @return this.XPosition
	 */
	public int GetXPosition()
	{
		/*Return XPosition*/
		return this.XPosition;
	}
	/**
	 * Function definition for GetYPosition()
	 * <p>
	 * Handles retrieving an object's YPosition.
	 * <p>
	 * @return this.YPosition
	 */
	public int GetYPosition()
	{
		/*Return YPosition*/
		return this.YPosition;
	}
	/**
	 * Function definition for GetID()
	 * <p>
	 * Handles retrieving an object's ID.
	 * <p>
	 * @return this.ID
	 */
	public int GetID()
	{
		/*Return ID*/
		return this.ID;
	}
	/**
	 * Function definition for GetSize()
	 * <p>
	 * Handles retrieving an object's Size.
	 * <p>
	 * @return this.Size
	 */
	public int GetSize()
	{
		/*Return Size*/
		return this.Size;
	}
	/**
	 * Function definition for GetSolid()
	 * <p>
	 * Handles retrieving an object's Solid.
	 * <p>
	 * @return this.Solid
	 */
	public boolean GetSolid()
	{
		/*Return Solid*/
		return this.Solid;
	}
	/**
	 * Function definition for GetArena()
	 * <p>
	 * Handles retrieving an object's Arena.
	 * <p>
	 * @return this.Arena
	 */
	public Arena GetArena()
	{
		/*Return CurrentArena*/
		return this.CurrentArena;
	}
	/**
	 * Function definition for SetXPosition()
	 * <p>
	 * Handles setting an object's XPosition.
	 * <p>
	 * @param X is used to set XPosition
	 */
	public void SetXPosition(int X)
	{
		/*Set XPosition to X*/
		this.XPosition = X;
	}
	/**
	 * Function definition for SetYPosition()
	 * <p>
	 * Handles setting an object's YPosition.
	 * <p>
	 * @param Y is used to set YPosition
	 */
	public void SetYPosition(int Y)
	{
		/*Set YPosition to Y*/
		this.YPosition = Y;
	}
	/**
	 * Function definition for SetID()
	 * <p>
	 * Handles setting an object's ID.
	 * <p>
	 * @param I is used to set ID
	 */
	public void SetID(int I)
	{
		/*Set ID to I*/
		this.ID = I;
	}
	/**
	 * Function definition for SetSize()
	 * <p>
	 * Handles setting an object's Size.
	 * <p>
	 * @param S is used to set Size
	 */
	public void SetSize(int S)
	{
		/*Set Size to S*/
		this.Size = S;
	}
	/**
	 * Function definition for SetSolid()
	 * <p>
	 * Handles setting an object's Solid.
	 * <p>
	 * @param B is used to set Solid
	 */
	public void SetSolid(boolean B)
	{
		/*Set Solid to B*/
		this.Solid = B;
	}
	/**
	 * Function definition for SetArena()
	 * <p>
	 * Handles setting an object's Arena.
	 * <p>
	 * @param A is used to set Arena
	 */
	public void SetArena(Arena A)
	{
		/*Set CurrentArena to A*/
		this.CurrentArena = A;
	}
	/**
	 * Function definition for IDCalculation()
	 * <p>
	 * Handles incrementing the IDCalculator.
	 * <p>
	 * @return IDCalculator and increment
	 */
	public int IDCalculation()
	{
		/*Return IDCalulator++*/
		return IDCalculator++;
	}
	/**
	 * Function definition for ResetIDCalculator()
	 * <p>
	 * Handles resetting the IDCalculator.
	 */
	public static void ResetIDCalculator()
	{
		/*Sets IDCalculator to 0*/
		IDCalculator = 0;
	}
}
