package robotSimulator;

import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	/**
	 * Function definition for AlertWindow()
	 * <p>
	 * Creates a new Alert of type INFORMATION before setting the title
	 * of said alert to titleStr and the content to contentStr. The Alert is
	 * then shown.
	 * <p>
	 * @param titleStr is the string used for the title of the window
	 * @param contentStr is the string used for the content of the window
	 */
	private void AlertWindow(String titleStr, String contentStr) 
	{
		/*Creates object alert of type Alert*/
		Alert alert = new Alert(AlertType.INFORMATION);
		/*Sets alert title to titleStr*/
		alert.setTitle(titleStr);
		/*Sets alert header text to null*/
		alert.setHeaderText(null);
		/*Sets alert content to contentStr*/
		alert.setContentText(contentStr);
		/*Shows alert and waits*/
		alert.showAndWait();
	}
	/**
	 * Function definition for GetValue()
	 * <p>
	 * Creates a dialog box call TStr with an prepared value but allows the user
	 * to input a new value.
	 * <p>
	 * @param TStr is the string used to name the dialog window
	 * @return the integer input into the box
	 */
	private int GetValue(String TStr) 
	{
		/*Creates a temporary variable*/
		int ans = 0;
		/*Set the input dialog box to a prepared value*/
		TextInputDialog dialog = new TextInputDialog("500");
		/*Set the title to TStr*/
		dialog.setTitle(TStr);
		/*Set the header text*/
		dialog.setHeaderText("Enter value for " + TStr);
		/*Wait for the input to be confirmed by user*/
		Optional<String> result = dialog.showAndWait();
		/*If there is a result*/
		if (result.isPresent()) 
		{
			/*Parse the result to ans*/
			ans = Integer.parseInt(result.get());
		}
		/*Return ans*/
		return ans;
	}
	/**
	 * Function definition for DrawArena()
	 * <p>
	 * Fills a rectangle the size of the Arena on the Canvas before drawing lines
	 * around said rectangle.
	 */
	public void DrawArena()
	{
		/*Set colour to DARKGRAY*/
		gc.setFill(Color.DARKGRAY);
		/*Fill a rectangle of given size*/
		gc.fillRect(0, 0, Arena.GetWidth(), Arena.GetLength());
		/*Set colour to BLACK*/
		gc.setFill(Color.BLACK);
		/*Set line width to 1*/
		gc.setLineWidth(1);
		/*Draw the top wall*/
		gc.strokeLine(0,0,Arena.GetWidth(),0);
		/*Draw the right wall*/
		gc.strokeLine(Arena.GetWidth(),0, Arena.GetWidth(), Arena.GetLength());
		/*Draw the bottom wall*/
		gc.strokeLine(Arena.GetWidth(), Arena.GetLength(), 0, Arena.GetLength());
		/*Draw the left wall*/
		gc.strokeLine(0, Arena.GetLength(), 0, 0);
	}
}
