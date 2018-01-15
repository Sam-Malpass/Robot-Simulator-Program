package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
public class WhiskerRobot extends BasicRobot
{
	/**
	 * RWhisker is a Line object acting as the right whisker
	 */
	Line RWhisker;
	/**
	 * LWhisker is a Line object acting as the left whisker
	 */
	Line LWhisker;
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Calls the constructor for BasicRobot
	 * <p>
	 */
	WhiskerRobot()
	{
		/*Calls the BasicRobot constructor without arguments*/
		super();
		/*Creates the RWhisker*/
		RWhisker = RWhiskerHandler();
		/*Creates the LWhisker*/
		LWhisker = LWhiskerHandler();
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Calls the constructor for BasicRobot using the parameters
	 * as the arguments.
	 * <p>
	 */
	WhiskerRobot(int X, int Y, Arena A)
	{
		/*Calls the BasicRobot constructor with arguments*/
		super(X, Y, A);
		/*Creates the RWhisker*/
		RWhisker = RWhiskerHandler();
		/*Creates the LWhisker*/
		LWhisker = LWhiskerHandler();
	}
	/**
	 * Function definition for AttemptMove()
	 * <p>
	 * Attempts to move using a mixture of its own whiskers and the bump sensor
	 * available in the BasicRobot
	 */
	public void attemptMove()
	{
		/*Create temporary variables using the XPositon and YPosition*/
		int Coordinate[] = super.directionDeterminator(this.getXPosition(), this.getYPosition());
		int TempX = Coordinate[0], TempY = Coordinate[1];
		/*Re-create the whiskers now that the direction may have changed*/
		RWhisker = RWhiskerHandler();
		LWhisker = LWhiskerHandler();
		/*If move is not possible*/
		if(bumpSensor(TempX, TempY, this) == true || (RWhiskerCheck() == true && LWhiskerCheck() == true))
		{
			/*Set DirectionChangeFlag to true*/
			setDirection(getDirection().OppDirection());
			/*Toggle the DirectionChangeFlag off*/
			getDirectionChangeFlag(false);
		}
		/*If the RWhisker detects something*/
		else if(RWhiskerCheck() == true)
		{
			/*Change direction to the next direction*/
			setDirection(getDirection().NextDirection());
			/*Re-create the whiskers*/
			RWhisker = RWhiskerHandler();
			LWhisker = LWhiskerHandler();
			/*Toggle the DirectionChangeFlag off*/
			getDirectionChangeFlag(false);
		}
		/*If the LWhisker detects something*/
		else if(LWhiskerCheck() == true)
		{
			/*Change the direction to the previous direction*/
			setDirection(getDirection().PrevDirection());
			/*Re-create the whiskers*/
			RWhisker = RWhiskerHandler();
			LWhisker = LWhiskerHandler();
			/*Toggle the DirectionChangeFlag off*/
			getDirectionChangeFlag(false);
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
	 * Function definition for RWhiskerCheck()
	 * <p>
	 * If the whisker is touching something that is solid, it should return true
	 * otherwise it will return false.
	 * <p>
	 * @return the result of all the checks
	 */
	public boolean RWhiskerCheck()
	{
		/*For all objects in the Arena*/
		for(int ct = 0; ct < getArena().Contents.size(); ct++)
		{
			/*If the object being tested is the current WhiskerRobot or is a LightSource*/
			if((getArena().Contents.get(ct).getID() == this.getID()) || getArena().Contents.get(ct) instanceof LightSource && !(getArena().Contents.get(ct) instanceof ObstacleBlock))
			{
				/*Just move to the next object*/
				continue;
			}
			/*If the whisker is touching the Arena boundaries*/
			else if(RWhisker.GetCoords()[2] < 0 || RWhisker.GetCoords()[3] < 0|| RWhisker.GetCoords()[2] > getArena().GetWidth()|| RWhisker.GetCoords()[3] > getArena().GetLength())
			{
				/*Return true*/
				return true;
			}
			/*If the whisker is touching a solid object*/
			else if(RWhisker.distanceFrom(getArena().Contents.get(ct).getXPosition(), getArena().Contents.get(ct).getYPosition()) <= getArena().Contents.get(ct).getSize())
			{
				/*Return true*/
				return true;
			}
		}
		/*Return false*/
		return false;
	}
	/**
	 * Function definition for LWhiskerCheck()
	 * <p>
	 * If the whisker is touching something that is solid, it should return true
	 * otherwise it will return false.
	 * <p>
	 * @return the result of all the checks
	 */
	public boolean LWhiskerCheck()
	{
		/*For all objects in the Arena*/
		for(int ct = 0; ct < getArena().Contents.size(); ct++)
		{
			/*If the object being tested is the current WhiskerRobot or is a LightSource*/
			if((getArena().Contents.get(ct).getID() == this.getID()) || (getArena().Contents.get(ct) instanceof LightSource && !(getArena().Contents.get(ct) instanceof ObstacleBlock)))
			{
				/*Just move to the next object*/
				continue;
			}
			/*If the whisker is touching the Arena boundaries*/
			else if(LWhisker.GetCoords()[2] < 0 || LWhisker.GetCoords()[3] < 0 || LWhisker.GetCoords()[2] > getArena().GetWidth() || LWhisker.GetCoords()[3] > getArena().GetLength())
			{
				/*Return true*/
				return true;
			}
			/*If the whisker is touching a solid object*/
			else if(LWhisker.distanceFrom(getArena().Contents.get(ct).getXPosition(), getArena().Contents.get(ct).getYPosition()) <= getArena().Contents.get(ct).getSize())
			{
				/*Return true*/
				return true;
			}
		}
		/*Return false*/
		return false;
	}
	/**
	 * Function definition for RWhiskerHandler()
	 * <p>
	 * Creates a temporary Line object based on the direction the WhiskerRobot
	 * is facing. Returns the Line object when done
	 * <p>
	 * @return the temporary Line
	 */
	public Line RWhiskerHandler()
	{
		/*Create object Temp of type Line*/
		Line Temp = new Line();
		/*Depending on WhiskerRobot's direction, Re-create the Line*/
		switch(getDirection())
		{
		case UP:
			Temp = new Line(getXPosition(), getYPosition()-getSize(), getXPosition()+getSize(), getYPosition() - 40);
			return Temp;
		case DOWN:
			Temp = new Line(getXPosition(), getYPosition()+getSize(), getXPosition()-getSize(), getYPosition() + 40);
			return Temp;
		case LEFT:
			Temp = new Line(getXPosition()-getSize(), getYPosition(), getXPosition() - 40, getYPosition() - getSize());
			return Temp;
		case RIGHT:
			Temp = new Line(getXPosition()+getSize(), getYPosition(), getXPosition() + 40, getYPosition() + getSize());
			return Temp;
		}
		/*Return the Line*/
		return Temp;
	}
	/**
	 * Function definition for LWhiskerHandler()
	 * <p>
	 * Creates a temporary Line object based on the direction the WhiskerRobot
	 * is facing. Returns the Line object when done
	 * <p>
	 * @return the temporary Line
	 */
	public Line LWhiskerHandler()
	{
		/*Create object Temp of type Line*/
		Line Temp = new Line();
		/*Depending on WhiskerRobot's direction, Re-create the Line*/
		switch(getDirection())
		{
		case UP:
			Temp = new Line(getXPosition(), getYPosition()-getSize(), getXPosition()-getSize(), getYPosition() - 40);
			return Temp;
		case DOWN:
			Temp = new Line(getXPosition(), getYPosition()+getSize(), getXPosition()+getSize(), getYPosition() + 40);
			return Temp;
		case LEFT:
			Temp = new Line(getXPosition()-getSize(), getYPosition(), getXPosition() - 40, getYPosition() + getSize());
			return Temp;
		case RIGHT:
			Temp = new Line(getXPosition()+getSize(), getYPosition(), getXPosition() + 40, getYPosition() - getSize());
			return Temp;
		}
		/*Return the Line*/
		return Temp;
	}
}
