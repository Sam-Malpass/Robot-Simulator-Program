package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
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
	 * Constructor with no arguments.
	 * <p>
	 * Constructs a BasicRobot object using pre-determined
	 * values.
	 * <p>
	 */
	BasicRobot()
	{
		/*Sets ID*/
		this.setID(calculateID());
		/*Sets XPosition*/
		this.setXPosition(20);
		/*Sets YPosition*/
		this.setYPosition(20);
		/*Sets Size*/
		this.setSize(20);
		/*Sets Solid*/
		this.setSolid(true);
		/*Sets Direction to a random direction*/
		this.SetDirection(DirectionHandler.UP);
	}
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
		this.setID(calculateID());
		/*Sets XPosition to X*/
		this.setXPosition(X);
		/*Sets YPosition to Y*/
		this.setYPosition(Y);
		/*Sets Size*/
		this.setSize(20);
		/*Sets Solid*/
		this.setSolid(true);
		/*Sets Arena to A*/
		this.setArena(A);
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
		if(X < O.getSize() || Y < O.getSize() || X > getArena().GetWidth()-O.getSize() || Y > getArena().GetLength()-O.getSize())
		{
			/*Returns true*/
			return true;
		}
		/*Otherwise*/
		else
		{
			/*For all objects in the Arena*/
			for(int ct = 0; ct < getArena().Contents.size(); ct++)
			{
				/*Create a line between the passed X,Y values and the coordinates of the current object*/
				Line Checker = new Line(X, Y, getArena().Contents.get(ct).getXPosition(), getArena().Contents.get(ct).getYPosition());
				if(getArena().Contents.get(ct) instanceof LightSource)
				{
				}
				/*Check if line length is less than the size of the two objects and greater than 1*/
				else if(Checker.lineLength() < O.getSize() + getArena().Contents.get(ct).getSize() && Checker.lineLength() > 1)
				{
					return true;
				}
			}
		}
		/*If all tests are passed return false*/
		return false;	
	}
	/**
	 * Function definition for AttemptMove()
	 * <p>
	 * Handles moving a BasicRobot by an X or Y position.
	 */
	public void attemptMove()
	{
		/*Create temporary variables using the XPositon and YPosition*/
		int Coordinate[] = DirectionDeterminator(this.getXPosition(), this.getYPosition());
		int TempX = Coordinate[0], TempY = Coordinate[1];
		/*If move is not possible*/
		if(BumpSensor(TempX, TempY, this) == true)
		{
			/*Set DirectionChangeFlag to true*/
			SetDirectionChangeFlag(true);
		}
		/*Otherwise*/
		else
		{
			/*Set XPosition to TempX*/
			this.setXPosition(TempX);
			/*Set YPosition to TempY*/
			this.setYPosition(TempY);
		}
	}
	/**
	 * Function definition for GetDirectionChangeFlag()
	 * <p>
	 * Handles retrieving an object's DirectionChangeFlag.
	 * <p>
	 * @return this.DirectionChangeFlag
	 */
	public boolean GetDirectionChangeFlag() 
	{
		/*Return DirectionChangeFlag*/
		return this.DirectionChangeFlag;
	}
	/**
	 * Function definition for GetDirection()
	 * <p>
	 * Handles retrieving an object's Direction.
	 * <p>
	 * @return this.Direction
	 */
	public DirectionHandler GetDirection()
	{
		/*Return Direction*/
		return this.Direction;
	}
	/**
	 * Function definition for SetDirectionChangeFlag()
	 * <p>
	 * Handles Setting an object's DirectionChangeFlag.
	 * <p>
	 * @return this.DirectionChangeFlag
	 */
	public void SetDirectionChangeFlag(boolean DCF)
	{
		/*Set DirectionChangeFlag to DCF*/
		this.DirectionChangeFlag = DCF;
	}
	/**
	 * Function definition for SetDirection()
	 * <p>
	 * Handles Setting an object's Direction.
	 * <p>
	 * @return this.Direction
	 */
	public void SetDirection(DirectionHandler D) 
	{
		/*Set Direction to D*/
		this.Direction = D;
	}
}
