package robotSimulator;

import java.util.Random;

/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public enum DirectionHandler 
{
	/**
	 * UP, RIGHT, DOWN and LEFT are the four possible directions
	 * a robot can be travelling in.
	 */
	UP, RIGHT, DOWN, LEFT;
	/**
	 * Function definition for RandomDirection()
	 * <p>
	 * Creates an object of type Random and then chooses a random number out of the
	 * values in the enumerator. This random number corresponds to a direction which
	 * is returned by the function.
	 * <p>
	 * @return a random direction
	 */
	public static DirectionHandler randomDirection()
	{
		/*Creates object R of type Random*/
		Random R = new Random();
		/*Return a random direction*/
		return values()[R.nextInt(values().length)];
	}
	/**
	 * Function definition for NextDirection()
	 * <p>
	 * Returns the next direction in the enumerator.
	 * <p>
	 * @return the next direction in the enumerator
	 */
	public DirectionHandler  nextDirection()
	{
		/*Return the next direction in the enumerator*/
		return values()[(this.ordinal() + 1) % values().length];
	}
	/**
	 * Function definition for PrevDirection()
	 * <p>
	 * Returns the previous direction in the enumerator.
	 * <p>
	 * @return the previous direction in the enumerator
	 */
	public DirectionHandler previousDirection()
	{
		/*Return the previous direction in the enumerator*/
		return values()[(this.ordinal() + 3) % values().length];
	}
	/**
	 * Function definition for OppDirection()
	 * <p>
	 * Returns the opposite direction in the enumerator.
	 * <p>
	 * @return the opposite direction in the enumerator
	 */
	public DirectionHandler oppositeDirection()
	{
		/*Return the opposite direction in the enumerator*/
		return values()[(this.ordinal() + 2) % values().length];
	}
}
