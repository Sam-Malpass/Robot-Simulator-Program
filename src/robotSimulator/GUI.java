package robotSimulator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public class GUI extends Application
{
	/**
	 * CanvasSize is acts as the width and length of the canvas
	 */
	private int CanvasSize = 500;
	/**
	 * Arena is an object of type Arena
	 */
	private Arena Arena;
	/**
	 * gc is the GraphicsContext of the application
	 */
	private GraphicsContext gc;
	/**
	 * rtPane is a vertical box on the right hand side of the application
	 */
	private VBox rtPane;
	/**
	 * ltPane is a horizontal box going from left to right
	 */
	private HBox ltPane;
	/**
	 * Active is a boolean value that says whether an Arena object is open or not
	 */
	private boolean Active = false;
	/**
	 * Timer acts as a measure for the Animation
	 */
	private AnimationTimer Timer;
}
