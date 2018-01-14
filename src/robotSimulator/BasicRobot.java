package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public class BasicRobot extends ArenaObject
{
	/**
	 * DirectionChangeFlag holds whether the robot needs to change direction.
	 */
	private boolean DirectionChangeFlag;
	/**
	 * Direction holds the robot's current direction.
	 */
	private DirectionHandler Direction;
	/**
	 * Constructor with arguments.
	 * <p>
	 * Constructs a BasicRobot object using values passed to the 
	 * constructor.
	 * <p>
	 * @param X is used to set the XPosition
	 * @param Y is used to set the YPosition
	 * @param A is used to set the Arena
	 */
	BasicRobot(int X, int Y, Arena A)
	{
		/*Sets ID*/
		this.SetID(IDCalculation());
		/*Sets XPosition to X*/
		this.SetXPosition(X);
		/*Sets YPosition to Y*/
		this.SetYPosition(Y);
		/*Sets Size*/
		this.SetSize(20);
		/*Sets Solid*/
		this.SetSolid(true);
		/*Sets Arena to A*/
		this.SetArena(A);
		/*Sets Direction to a random direction*/
		this.SetDirection(DirectionHandler.RandomDirection());
	}
}
