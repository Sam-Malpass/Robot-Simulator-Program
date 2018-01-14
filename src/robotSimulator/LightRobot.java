package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
public class LightRobot extends BasicRobot
{
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Calls the constructor for BasicRobot
	 * <p>
	 */
	LightRobot()
	{
		super();
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Calls the constructor for BasicRobot
	 * <p>
	 */
	LightRobot(int X, int Y, Arena A)
	{
		super(X, Y, A);
	}
	/**
	 * Function definition for LightSensor()
	 * <p>
	 * Looks through all arena objects only this time cannot move even if object is a LightSource.
	 * <p>
	 * @param X is the X coordinate to be tested
	 * @param Y is the Y coordinate to be tested
	 * @param O is the object to be tested
	 */
	public boolean LightSensor(int X, int Y, ArenaObject O)
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
				Line Checker = new Line(X, Y, GetArena().Contents.get(ct).GetXPosition(), GetArena().Contents.get(ct).GetYPosition());
				/*Check if line length is less than the size of the two objects and greater than 1*/
				if(Checker.lineLength() < O.GetSize() + GetArena().Contents.get(ct).GetSize() && Checker.lineLength() > 1)
				{
					return true;
				}
			}
		}
		/*If all tests are passed return false*/
		return false;	
	}
	/**
	 * Function definition for DirectionDeterminator()
	 * <p>
	 * Calls the DirectionDeterminator() in Basic Robot.
	 * <p>
	 * @param TempX is passed to the super.DirectionDeterminator()
	 * @param TempY is passed to the super.DirectionDeterminator();
	 */
	public int[] DirectionDeterminator(int TempX, int TempY)
	{
		/*Return the result from the super.DirectionDeterminator using passed values*/
		return super.DirectionDeterminator(TempX, TempY);
	}
	/**
	 * Function definition for AttemptMove()
	 * <p>
	 * Attempts to move using the LightSensor()
	 */
	public void AttemptMove()
	{
		/*Create temporary variables using the XPositon and YPosition*/
		int Coordinate[] = DirectionDeterminator(this.GetXPosition(), this.GetYPosition());
		int TempX = Coordinate[0], TempY = Coordinate[1];
		/*If move is not possible*/
		if(LightSensor(TempX, TempY, this) == true)
		{
			/*Set DirectionChangeFlag to true*/
			SetDirectionChangeFlag(true);
		}
		/*Otherwise*/
		else
		{
			/*Set XPosition to TempX*/
			this.SetXPosition(TempX);
			/*Set YPosition to TempY*/
			this.SetYPosition(TempY);
		}
	}
}
