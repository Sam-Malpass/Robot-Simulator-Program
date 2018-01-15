package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
public class LightSource extends ArenaObject
{
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Constructs an LightSource object using pre-determined
	 * values.
	 * <p>
	 */
	LightSource()
	{
		/*Sets ID*/
		this.setID(calculateID());
		/*Sets XPosition*/
		this.setXPosition(20);
		/*Sets YPosition*/
		this.setYPosition(20);
		/*Sets Size*/
		this.setSize(40);
		/*Sets Solid*/
		this.setSolid(false);
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Constructs an LightSource object using values passed to the 
	 * constructor.
	 * <p>
	 * @param X is used to set the XPosition
	 * @param Y is used to set the YPosition
	 * @param A is used to set the Arena
	 */
	LightSource(int X, int Y, Arena A)
	{
		/*Sets ID*/
		this.setID(calculateID());
		/*Sets XPosition to X*/
		this.setXPosition(X);
		/*Sets YPosition to Y*/
		this.setYPosition(Y);
		/*Sets Size*/
		this.setSize(40);
		/*Sets Solid*/
		this.setSolid(false);
		/*Sets Arena to A*/
		this.setArena(A);
	}
	/**
	 * Function definition for AttemptMove()
	 * <p>
	 * Handles moving the object by an X or a Y Position
	 * <p>
	 * @return instantly as the object doesn't move.
	 */
	public void attemptMove()
	{
		return;
	}
}
