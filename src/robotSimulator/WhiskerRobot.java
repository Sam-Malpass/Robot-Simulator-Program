package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
public class WhiskerRobot extends ArenaObject
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
}
