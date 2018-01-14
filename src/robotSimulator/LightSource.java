package robotSimulator;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
@SuppressWarnings("serial")
public class LightSource extends ArenaObject
{
	/**
	 * Constructor with no arguments.
	 * <p>
	 * Constructs an LightSource object using pre-determined
	 * values.
	 * <p>
	 */
	LightSource()
	{
		/*Sets ID*/
		this.SetID(IDCalculation());
		/*Sets XPosition*/
		this.SetXPosition(20);
		/*Sets YPosition*/
		this.SetYPosition(20);
		/*Sets Size*/
		this.SetSize(40);
		/*Sets Solid*/
		this.SetSolid(false);
	}
	/**
	 * Constructor with arguments.
	 * <p>
	 * Constructs an LightSource object using values passed to the 
	 * constructor.
	 * <p>
	 * @param X is used to set the XPosition
	 * @param Y is used to set the YPosition
	 * @param A is used to set the Arena
	 */
	LightSource(int X, int Y, Arena A)
	{
		/*Sets ID*/
		this.SetID(IDCalculation());
		/*Sets XPosition to X*/
		this.SetXPosition(X);
		/*Sets YPosition to Y*/
		this.SetYPosition(Y);
		/*Sets Size*/
		this.SetSize(40);
		/*Sets Solid*/
		this.SetSolid(false);
		/*Sets Arena to A*/
		this.SetArena(A);
	}
}
