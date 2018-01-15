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
				/*Check if line length is less than the size of the two objects and greater than 1*/
				if(Checker.lineLength() < O.getSize() + getArena().Contents.get(ct).getSize() && Checker.lineLength() > 1)
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
	public int[] directionDeterminator(int TempX, int TempY)
	{
		/*Return the result from the super.DirectionDeterminator using passed values*/
		return super.directionDeterminator(TempX, TempY);
	}
	/**
	 * Function definition for AttemptMove()
	 * <p>
	 * Attempts to move using the LightSensor()
	 */
	public void attemptMove()
	{
		/*Create temporary variables using the XPositon and YPosition*/
		int Coordinate[] = directionDeterminator(this.getXPosition(), this.getYPosition());
		int TempX = Coordinate[0], TempY = Coordinate[1];
		/*If move is not possible*/
		if(LightSensor(TempX, TempY, this) == true)
		{
			/*Set DirectionChangeFlag to true*/
			getDirectionChangeFlag(true);
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
}
