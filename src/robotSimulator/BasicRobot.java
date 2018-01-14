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
	/**
	 * Function definition for DirectionDeterminator()
	 * <p>
	 * Checks whether or not to change the Direction of the robot.
	 * Determines where the coordinate should be based on the Direction of the robot.
	 * <p>
	 * @param TempX  may be changed based on Direction
	 * @param TempY may be changed based on Direction
	 */
	public int[] DirectionDeterminator(int TempX, int TempY)
	{
		/*If DirectionChangeFlag is true*/
		if(this.GetDirectionChangeFlag() == true)
		{
			/*Set Direction to the next direction in the enumerator*/
			this.Direction = Direction.NextDirection();
			/*Set the DirectionChangeFlag to false*/
			this.SetDirectionChangeFlag(false);
		}
		/*Switch on the Direction*/
		switch(this.Direction)
		{
		/*If UP*/
		case UP:
			/*Decrement TempY*/
			TempY--;
			break;
		/*If DOWN*/
		case DOWN:
			/*Increment TempY*/
			TempY++;
			break;
		/*If LEFT*/
		case LEFT:
			/*Decrement TempX*/
			TempX--;
			break;
		/*If RIGHT*/
		case RIGHT:
			/*Increment TempX*/
			TempX++;
			break;
		}
		/*Declare and set Coordinate to TempX and TempY*/
		int Coordinate[] = {TempX, TempY};
		/*Return Coordinate*/
		return Coordinate;
	}
}
