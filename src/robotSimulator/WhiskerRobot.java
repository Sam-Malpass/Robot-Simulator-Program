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
	 * RWhisker is a LineHandler object acting as the right whisker
	 */
	LineHandler RWhisker;
	/**
	 * LWhisker is a LineHandler object acting as the left whisker
	 */
	LineHandler LWhisker;
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
	public void AttemptMove()
	{
		/*Create temporary variables using the XPositon and YPosition*/
		int Coordinate[] = super.DirectionDeterminator(this.GetXPosition(), this.GetYPosition());
		int TempX = Coordinate[0], TempY = Coordinate[1];
		/*Re-create the whiskers now that the direction may have changed*/
		RWhisker = RWhiskerHandler();
		LWhisker = LWhiskerHandler();
		/*If move is not possible*/
		if(BumpSensor(TempX, TempY, this) == true || (RWhiskerCheck() == true && LWhiskerCheck() == true))
		{
			/*Set DirectionChangeFlag to true*/
			SetDirection(GetDirection().OppDirection());
			/*Toggle the DirectionChangeFlag off*/
			SetDirectionChangeFlag(false);
		}
		/*If the RWhisker detects something*/
		else if(RWhiskerCheck() == true)
		{
			/*Change direction to the next direction*/
			SetDirection(GetDirection().NextDirection());
			/*Re-create the whiskers*/
			RWhisker = RWhiskerHandler();
			LWhisker = LWhiskerHandler();
			/*Toggle the DirectionChangeFlag off*/
			SetDirectionChangeFlag(false);
		}
		/*If the LWhisker detects something*/
		else if(LWhiskerCheck() == true)
		{
			/*Change the direction to the previous direction*/
			SetDirection(GetDirection().PrevDirection());
			/*Re-create the whiskers*/
			RWhisker = RWhiskerHandler();
			LWhisker = LWhiskerHandler();
			/*Toggle the DirectionChangeFlag off*/
			SetDirectionChangeFlag(false);
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
