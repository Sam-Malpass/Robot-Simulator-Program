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
	 * xPosition holds the current X coordinate of the object.
	 */
	private int xPosition;
	/**
	 * yPosition holds the current Y coordinate of the object.
	 */
	private int yPosition;
	/**
	 * objectID holds the objectID of the object.
	 */
	private int objectID;
	/**
	 * size holds the size of the object.
	 */
	private int size;
	/**
	 * solid to determine if an object is solid.
	 */
	private boolean solid;
	/**
	 * currentArena shows which Arena the object is in.
	 */
	private Arena currentArena;
	/**
	 * objectIDCalculator is used to ensure unique objectID numbers.
	 */
	private static int objectIDCalculator;
	/**
	 * Function definition for GetXPosition()
	 * <p>
	 * Handles retrieving an object's xPosition.
	 * <p>
	 * @return this.XPosition
	 */
	public int getXPosition()
	{
		/*Return xPosition*/
		return this.xPosition;
	}
	/**
	 * Function definition for GetYPosition()
	 * <p>
	 * Handles retrieving an object's yPosition.
	 * <p>
	 * @return this.YPosition
	 */
	public int getYPosition()
	{
		/*Return yPosition*/
		return this.yPosition;
	}
	/**
	 * Function definition for GetID()
	 * <p>
	 * Handles retrieving an object's objectID.
	 * <p>
	 * @return this.ID
	 */
	public int getID()
	{
		/*Return objectID*/
		return this.objectID;
	}
	/**
	 * Function definition for GetSize()
	 * <p>
	 * Handles retrieving an object's size.
	 * <p>
	 * @return this.Size
	 */
	public int getSize()
	{
		/*Return size*/
		return this.size;
	}
	/**
	 * Function definition for GetSolid()
	 * <p>
	 * Handles retrieving an object's solid.
	 * <p>
	 * @return this.Solid
	 */
	public boolean getSolid()
	{
		/*Return solid*/
		return this.solid;
	}
	/**
	 * Function definition for GetArena()
	 * <p>
	 * Handles retrieving an object's Arena.
	 * <p>
	 * @return this.Arena
	 */
	public Arena getArena()
	{
		/*Return currentArena*/
		return this.currentArena;
	}
	/**
	 * Function definition for SetXPosition()
	 * <p>
	 * Handles setting an object's xPosition.
	 * <p>
	 * @param X is used to set xPosition
	 */
	public void setXPosition(int X)
	{
		/*Set xPosition to X*/
		this.xPosition = X;
	}
	/**
	 * Function definition for SetYPosition()
	 * <p>
	 * Handles setting an object's yPosition.
	 * <p>
	 * @param Y is used to set yPosition
	 */
	public void setYPosition(int Y)
	{
		/*Set yPosition to Y*/
		this.yPosition = Y;
	}
	/**
	 * Function definition for SetID()
	 * <p>
	 * Handles setting an object's objectID.
	 * <p>
	 * @param I is used to set objectID
	 */
	public void setID(int I)
	{
		/*Set objectID to I*/
		this.objectID = I;
	}
	/**
	 * Function definition for SetSize()
	 * <p>
	 * Handles setting an object's size.
	 * <p>
	 * @param S is used to set size
	 */
	public void setSize(int S)
	{
		/*Set size to S*/
		this.size = S;
	}
	/**
	 * Function definition for SetSolid()
	 * <p>
	 * Handles setting an object's solid.
	 * <p>
	 * @param B is used to set solid
	 */
	public void setSolid(boolean B)
	{
		/*Set solid to B*/
		this.solid = B;
	}
	/**
	 * Function definition for SetArena()
	 * <p>
	 * Handles setting an object's Arena.
	 * <p>
	 * @param A is used to set Arena
	 */
	public void setArena(Arena A)
	{
		/*Set currentArena to A*/
		this.currentArena = A;
	}
	/**
	 * Function definition for IDCalculation()
	 * <p>
	 * Handles incrementing the objectIDCalculator.
	 * <p>
	 * @return objectIDCalculator and increment
	 */
	public int calculateID()
	{
		/*Return IDCalulator++*/
		return objectIDCalculator++;
	}
	/**
	 * Function definition for ResetIDCalculator()
	 * <p>
	 * Handles resetting the objectIDCalculator.
	 */
	public static void resetIDCalculator()
	{
		/*Sets objectIDCalculator to 0*/
		objectIDCalculator = 0;
	}
	/**
	 * Abstract function declaration for AttemptMove()
	 */
	abstract public void attemptMove();
}
