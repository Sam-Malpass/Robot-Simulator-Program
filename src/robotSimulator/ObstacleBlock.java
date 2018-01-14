package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
public class ObstacleBlock extends ArenaObject
{
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Constructs an ObstacleBlock object using pre-determined
	 * values.
	 * <p>
	 */
	ObstacleBlock()
	{
		/*Sets ID*/
		this.SetID(IDCalculation());
		/*Sets XPosition*/
		this.SetXPosition(20);
		/*Sets YPosition*/
		this.SetYPosition(20);
		/*Sets Size*/
		this.SetSize(20);
		/*Sets Solid*/
		this.SetSolid(true);
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Constructs an ObstacleBlock object using values passed to the 
	 * constructor.
	 * <p>
	 * @param X is used to set the XPosition
	 * @param Y is used to set the YPosition
	 * @param A is used to set the Arena
	 */
	ObstacleBlock(int X, int Y, Arena A)
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
	}
}
