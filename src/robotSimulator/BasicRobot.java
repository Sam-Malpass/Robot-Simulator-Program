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
	/**
	 * Function definition for BumpSensor()
	 * <p>
	 * Checks whether a movement is possible and returns true or false accordingly
	 * <p>
	 * @param X is used to check the position 
	 * @param Y is used to check the position
	 */
	public boolean BumpSensor(int X, int Y, ArenaObject O)
	{
		/*If object is outside the walls of the Arena*/
		if(X < O.GetSize() || Y < O.GetSize() || X > GetArena().GetWidth()-O.GetSize() || Y > GetArena().GetLength()-O.GetSize())
		{
			/*Returns true*/
			return true;
		}
		/*Otherwise*/
		else
		{
			/*For all objects in the Arena*/
			for(int ct = 0; ct < GetArena().Contents.size(); ct++)
			{
				/*Create a line between the passed X,Y values and the coordinates of the current object*/
				LineHandler Checker = new LineHandler(X, Y, GetArena().Contents.get(ct).GetXPosition(), GetArena().Contents.get(ct).GetYPosition());
				if(GetArena().Contents.get(ct) instanceof LightSource)
				{
				}
				/*Check if line length is less than the size of the two objects and greater than 1*/
				else if(Checker.lineLength() < O.GetSize() + GetArena().Contents.get(ct).GetSize() && Checker.lineLength() > 1)
				{
					return true;
				}
			}
		}
		/*If all tests are passed return false*/
		return false;	
	}
}
