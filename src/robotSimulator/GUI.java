package robotSimulator;

import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
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
			/*Prevents Arena being too large*/
			if(ans > 500)
			{
				ans = 500;
			}
			else if(ans < 41)
			{
				ans = 41;
			}
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
	/**
	 * Function definition for DrawWhiskerRobot()
	 * <p>
	 * Draw all the WhiskerRobots in the Arena
	 */
	public void DrawWhiskerRobot()
	{
		for (int ct = 0; ct < Arena.Contents.size(); ct++)
		{
			int S = Arena.Contents.get(ct).GetSize(), X = Arena.Contents.get(ct).GetXPosition(), Y = Arena.Contents.get(ct).GetYPosition();
			if(Arena.Contents.get(ct) instanceof WhiskerRobot)
			{
				/*Create a temporary WhiskerRobot by casting the current object*/
				WhiskerRobot B = (WhiskerRobot) Arena.Contents.get(ct);
				/*Set colour to RED*/
				gc.setFill(Color.RED);
				/*Draw a circle to represent the body*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
				String lol = "" + Arena.Contents.get(ct).GetID();
				gc.setFill(Color.BLACK);
				gc.fillText(lol, X, Y);
				/*Set line width to 5 for the wheels*/
				gc.setLineWidth(5);
				/*If the robot's direction is UP*/
				if(B.GetDirection() == DirectionHandler.UP)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+17,Y+10,X+17,Y-10);
					gc.strokeLine(X-17,Y+10,X-17,Y-10);
					/*Set line width to 2 for the whiskers*/
					gc.setLineWidth(2);
					/*Draw the whiskers*/
					gc.strokeLine(X, Y-20, X+20, Y-40);
					gc.strokeLine(X, Y-20, X-20, Y-40);
				}
				/*If the robot's direction is DOWN*/
				else if(B.GetDirection() == DirectionHandler.DOWN)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+17,Y+10,X+17,Y-10);
					gc.strokeLine(X-17,Y+10,X-17,Y-10);
					/*Set line width to 2 for the whiskers*/
					gc.setLineWidth(2);
					/*Draw the whiskers*/
					gc.strokeLine(X, Y+20, X+20, Y+40);
					gc.strokeLine(X, Y+20, X-20, Y+40);
				}
				/*If the robot's direction is LEFT*/
				else if(B.GetDirection() == DirectionHandler.LEFT)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+10,Y+17,X-10,Y+17);
					gc.strokeLine(X+10,Y-17,X-10,Y-17);
					/*Set line width to 2 for the whiskers*/
					gc.setLineWidth(2);
					/*Draw the whiskers*/
					gc.strokeLine(X-20, Y, X-40, Y+20);
					gc.strokeLine(X-20, Y, X-40, Y-20);
				}
				/*If the robot's direction is RIGHT*/
				else if(B.GetDirection() == DirectionHandler.RIGHT)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+10,Y+17,X-10,Y+17);
					gc.strokeLine(X+10,Y-17,X-10,Y-17);
					/*Set line width to 2 for the whiskers*/
					gc.setLineWidth(2);
					/*Draw the whiskers*/
					gc.strokeLine(X+20, Y, X+40, Y+20);
					gc.strokeLine(X+20, Y, X+40, Y-20);
				}
			}
		}
	}
	/**
	 * Function definition for DrawLightRobot()
	 * <p>
	 * Draw all the LightRobots in the Arena
	 */
	public void DrawLightRobot()
	{
		/*For all objects in the Arena*/
		for (int ct = 0; ct < Arena.Contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = Arena.Contents.get(ct).GetSize(), X = Arena.Contents.get(ct).GetXPosition(), Y = Arena.Contents.get(ct).GetYPosition();
			if(Arena.Contents.get(ct) instanceof LightRobot)
			{
				gc.setStroke(Color.BLACK);
				/*Create a LightRobot by casting the current object*/
				LightRobot B = (LightRobot) Arena.Contents.get(ct);
				/*Set colour to PURPLE*/
				gc.setFill(Color.PURPLE);
				/*Draw a circle to represent the robot's body*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
				/*Set line width to 5 for the wheels*/
				gc.setLineWidth(5);
				/*If the robot's direction is UP*/
				if(B.GetDirection() == DirectionHandler.UP)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+17,Y+10,X+17,Y-10);
					gc.strokeLine(X-17,Y+10,X-17,Y-10);
					/*Set line width to 2 for the light sensor*/
					gc.setLineWidth(2);
					/*Set colour to BLUE*/
					gc.setStroke(Color.BLUE);
					/*Draw the light sensor*/
					gc.strokeLine(X+5, Y-17, X-5, Y-17);
				}
				/*If the robot's direction is DOWN*/
				else if(B.GetDirection() == DirectionHandler.DOWN)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+17,Y+10,X+17,Y-10);
					gc.strokeLine(X-17,Y+10,X-17,Y-10);
					/*Set line width to 2 for the light sensor*/
					gc.setLineWidth(2);
					/*Set colour to BLUE*/
					gc.setStroke(Color.BLUE);
					/*Draw the light sensor*/
					gc.strokeLine(X+5, Y+17, X-5, Y+17);
				}
				/*If the robot's direction is LEFT*/
				else if(B.GetDirection() == DirectionHandler.LEFT)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+10,Y+17,X-10,Y+17);
					gc.strokeLine(X+10,Y-17,X-10,Y-17);
					/*Set line width to 2 for the light sensor*/
					gc.setLineWidth(2);
					/*Set colour to BLUE*/
					gc.setStroke(Color.BLUE);
					/*Draw the light sensor*/
					gc.strokeLine(X-17, Y+5, X-17, Y-5);
				}
				/*If the robot's direction is RIGHT*/
				else if(B.GetDirection() == DirectionHandler.RIGHT)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+10,Y+17,X-10,Y+17);
					gc.strokeLine(X+10,Y-17,X-10,Y-17);
					/*Set line width to 2 for the light sensor*/
					gc.setLineWidth(2);
					/*Set colour to BLUE*/
					gc.setStroke(Color.BLUE);
					/*Draw the light sensor*/
					gc.strokeLine(X+17, Y+5, X+17, Y-5);
				}
			}
		}
		gc.setStroke(Color.BLACK);
	}
	/**
	 * Function definition for DrawBasicRobot()
	 * <p>
	 * Draw all the BasicRobots in the Arena
	 */
	public void DrawBasicRobot()
	{
		/*For all objects in the Arena*/
		for (int ct = 0; ct < Arena.Contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = Arena.Contents.get(ct).GetSize(), X = Arena.Contents.get(ct).GetXPosition(), Y = Arena.Contents.get(ct).GetYPosition();
			/*If the object is a BasicRobot*/
			if(Arena.Contents.get(ct) instanceof BasicRobot && !(Arena.Contents.get(ct) instanceof WhiskerRobot) && !(Arena.Contents.get(ct) instanceof LightRobot))
			{
				/*Create a BasicRobot by casting the current object*/
				BasicRobot B = (BasicRobot) Arena.Contents.get(ct);
				/*Set colour to GREEN*/
				gc.setFill(Color.GREEN);
				/*Draw a circle to represent the robot's body*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
				/*Set line width to 5 for the wheels*/
				gc.setLineWidth(5);
				/*If the robot's direction is UP or DOWN*/
				if(B.GetDirection() == DirectionHandler.UP || B.GetDirection() == DirectionHandler.DOWN)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+17,Y+10,X+17,Y-10);
					gc.strokeLine(X-17,Y+10,X-17,Y-10);
				}
				/*If the robot's direction is LEFT or RIGHT*/
				else if(B.GetDirection() == DirectionHandler.LEFT || B.GetDirection() == DirectionHandler.RIGHT)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+10,Y+17,X-10,Y+17);
					gc.strokeLine(X+10,Y-17,X-10,Y-17);
				}
			}
		}
	}
	/**
	 * Function definition for DrawLightSource()
	 * <p>
	 * Draw all LightSources in the Arena
	 */
	public void DrawLightSource()
	{
		/*For all objects in the Arena*/
		for (int ct = 0; ct < Arena.Contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = Arena.Contents.get(ct).GetSize(), X = Arena.Contents.get(ct).GetXPosition(), Y = Arena.Contents.get(ct).GetYPosition();
			/*If the object is a LightSource*/
			if(Arena.Contents.get(ct) instanceof LightSource)
			{
				/*Set colour to YELLOW*/
				gc.setFill(Color.rgb(255, 255, 0, 0.5));
				/*Draw a circle to represent the light*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
			}
		}
	}
	/**
	 * Function definition for DrawObstacleBlock()
	 * <p>
	 * Draws all ObstacleBlocks in the Arena
	 */
	public void DrawObstacleBlock()
	{
		for (int ct = 0; ct < Arena.Contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = Arena.Contents.get(ct).GetSize(), X = Arena.Contents.get(ct).GetXPosition(), Y = Arena.Contents.get(ct).GetYPosition();
			/*If the object is an ObstacleBlock*/
			if(Arena.Contents.get(ct) instanceof ObstacleBlock)
			{
				/*Set colour to BLACK*/
				gc.setFill(Color.BLACK);
				/*Draw a circle to represent the block*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
			}
		}
	}
	/**
	 * Function definition for DrawObjects()
	 * <p>
	 * Calls the drawing functions for each of the object types
	 */
	public void DrawObjects()
	{
		DrawWhiskerRobot();
		DrawLightRobot();
		DrawBasicRobot();
		DrawObstacleBlock();
		DrawLightSource();
	}
	/**
	 * Function definition for DrawStatus()
	 * <p>
	 * Clear the rtPane, create a new label containing the result of ListContents() and
	 * then add the label to the rtPane. 
	 */
	public void DrawStatus() 
	{
		/*Clear the rtPane*/
		rtPane.getChildren().clear();
		/*Create a new Label using the result of ListContents()*/
		Label l = new Label(Arena.ListContents());
		/*Add the Label to rtPane*/
		rtPane.getChildren().add(l);
	}
	/**
	 * Function definition for DrawAll()
	 * <p>
	 * Clears the Canvas before calling DrawArena(), DrawObjects() and DrawStatus().
	 */
	public void DrawAll()
	{
		/*Clear Canvas*/
		gc.clearRect(0, 0, CanvasSize, CanvasSize);
		/*Draw the Arena*/
		DrawArena();
		/*Draw the objects*/
		DrawObjects();
		/*Draw the positions of the objects*/
		DrawStatus();
	}
	/**
	 * Function definition for SetMenu()
	 * <p>
	 * Create a MenuBar, with several Menus that each contain varying amounts of MenuItems.
	 * <p>
	 * @return menuBar with all the buttons on it
	 */
	MenuBar SetMenu() 
	{
		/*Object creation*/
		MenuBar menuBar = new MenuBar();
		Menu mFile = new Menu("File");
		MenuItem mSave = new MenuItem("Save");
		MenuItem mLoad = new MenuItem("Load");
		MenuItem mExit = new MenuItem("Exit");
		/*Defining the action for the option*/
		mSave.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent)
			{
				/*Create a FileIO object*/
				FileIO Save = new FileIO();
				/*Save the Arena*/
				Save.Save(Arena);
			}
		});
		/*Defining the action for the option*/
		mLoad.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Create a FileIO object*/
				FileIO Load = new FileIO();
				/*Load an Arena*/
				Arena = Load.Load();
				/*Toggle the Active variable*/
				Active = true;
				/*Draw everything*/
				DrawAll();
			}
		});
		/*Defining the action for the option*/
		mExit.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Exit the application*/
				System.exit(0);
			}
		});
		/*Add all sub-options to the mFile option*/
		mFile.getItems().addAll(mSave, mLoad, mExit);
		/*Object creation*/
		Menu mOptions = new Menu("Arena Options");
		MenuItem mNew = new MenuItem("New Arena");
		/*Defining the action for the option*/
		mNew.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Variables are created and assigned values based on information input by the user*/
		    	int W = GetValue("Arena Width (Min 41, Max 500)");
		    	int L = GetValue("Arena Length (Min 41, Max 500");
		    	int C = (W * L) / (4*20*20);
		    	/*If any of the values are less than or equal to 0*/
		    	if(W <= 40 || L <= 40 || C <= 0)
		    	{
		    		/*Send out an error alert*/
		    		AlertWindow("Error", "Arena Failed to Create");
		    	}
		    	/*Otherwise*/
		    	else
		    	{
		    		/*Create a new Arena using the values*/
		    		Arena = new Arena(W, L, C);
		    		/*Toggle the Active variable*/
		    		Active = true;
		    		/*Draw everything*/
		    		DrawAll();
		    		/*Send out a success alert*/
		    		AlertWindow("Success", "Arena Created Successfully");
		    	}
			}
		});
		/*Add the sub-option to the mOption option*/
		mOptions.getItems().addAll(mNew);
		/*Object creation*/
		Menu mHelp = new Menu("Help");
		MenuItem mInfo = new MenuItem("Information");
		MenuItem mAbout = new MenuItem("About");
		/*Defining the action for the option*/
		mInfo.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Send out alert window with specified information*/
				AlertWindow("Information",
						"This is the Robot Simulator, to begin, create an arena using the arena options 'new' or load an arena from 'file'. Then add some robots and click start. The robots should then begin moving.");
			}
		});
		/*Defining the action for the option*/
		mAbout.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Send out alert window with the specified information*/
				AlertWindow("About",
						"Robot Simulator Program created by Samuel John Malpass, Student Number 24009788 for CS2JA17");
			}
		});
		/*Add sub-buttons to mHelp option*/
		mHelp.getItems().addAll(mInfo, mAbout);
		/*Add main options to the menuBar*/
		menuBar.getMenus().addAll(mFile, mOptions, mHelp);
		/*Return menuBar*/
		return menuBar;
	}
	/**
	 * Function definition for CreateButtons()
	 * <p>
	 * Creates several buttons and defines the behaviour for said buttons before adding
	 * the buttons to the parameter HBox.
	 * <p>
	 * @param ltPane is the HBox the buttons will be added to
	 */
	public void CreateButtons(HBox ltPane)
	{
		/*Object creation*/
		Label l1 = new Label("Animation:");
		Label l2 = new Label("Add:");
		Label l3 = new Label("Reset:");
		Button startButton = new Button("Start");
		/*Defining the action for the option*/
		startButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				/*Start the timer*/
				Timer.start();
			}
		});
		/*Object creation*/
		Button pauseButton = new Button("Pause");
		/*Defining the action for the option*/
		pauseButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{	
				/*Stop the timer*/
				Timer.stop();
			}
		});
		/*Object creation*/
		Button addRobotButton = new Button("Basic Robot");
		/*Defining the action for the option*/
		addRobotButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				/*Stop the timer*/
				Timer.stop();
				/*If there is an Arena open*/
		    	if(Active == true)
		    	{
		    		/*If the robot is added successfully*/
		    		if(Arena.AddBasicRobot() == true)
		    		{
		    			/*Send a success alert*/
		    			AlertWindow("Success", "Robot Added Successfully");
		    			/*Draw everything*/
		    			DrawAll();
		    		}
		    		/*Otherwise*/
		    		else
		    		{
		    			/*Send an error alert*/
		    			AlertWindow("Error", " Arena at Capacity");
		    		}
		    	}
		    	/*Otherwise*/
		    	else
		    	{
		    		/*Send an error alert*/
		    		AlertWindow("Error", "No Arena Open");
		    	}
			}
		});
		/*Object creation*/
		Button addWhiskerButton = new Button("Whisker Robot");
		/*Defining the action for the option*/
		addWhiskerButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event) 
			{
				/*Stop the timer*/
				Timer.stop();
				/*If there is an Arena open*/
			    if(Active == true)
			    {
			    	/*If the robot is created successfully*/
			    	if(Arena.AddWhiskerRobot() == true)
			    	{
			    		/*Send out a success alert*/
			    		AlertWindow("Success", "Robot Added Successfully");
			    		/*Draw everything*/
			    		DrawAll();
			    	}
			    	/*Otherwise*/
			    	else
			    	{
			    		/*Send out an error alert*/
			    		AlertWindow("Error", " Arena at Capacity");
			    	}
			   	}
			    /*Otherwise*/
			   	else
			   	{
			   		/*Send out an error alert*/
			   		AlertWindow("Error", "No Arena Open");
			   	}
			}
		});
		/*Object creation*/
		Button addLSensorButton = new Button("Light Robot");
		/*Defining the action for the option*/
		addLSensorButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				/*Stop the timer*/
				Timer.stop();
				/*If there is an Arena open*/
			    if(Active == true)
			    {
			    	/*If the robot is created successfully*/
			    	if(Arena.AddLightRobot() == true)
			    	{
			    		/*Send out a success alert*/
			    		AlertWindow("Success", "Robot Added Successfully");
			    		/*Draw everything*/
			    		DrawAll();
			    	}
			    	/*Otherwise*/
			    	else
			    	{
			    		/*Send out an error alert*/
			    		AlertWindow("Error", " Arena at Capacity");
			    	}
			   	}
			    /*Otherwise*/
			   	else
			   	{
			   		/*Send out an error alert*/
			   		AlertWindow("Error", "No Arena Open");
			   	}
			}
		});
		/*Object creation*/
		Button addLightButton = new Button("Light");
		/*Defining the action for the option*/
		addLightButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				/*Stop the timer*/
				Timer.stop();
				/*If there is an Arena open*/
				if(Active == true)
		    	{
					/*If the LightSource is added successfully*/
		    		if(Arena.AddLightSource() == true)
		    		{
		    			/*Send out a success alert*/
		    			AlertWindow("Success", "Light Source Added Successfully");
		    			/*Draw everything*/
		    			DrawAll();
		    		}
		    		/*Otherwise*/
		    		else
		    		{
		    			/*Send out an error alert*/
		    			AlertWindow("Error", "Arena at Capacity");
		    		}
		    	}
				/*Otherwise*/
		    	else
		    	{
		    		/*Send out an error alert*/
		    		AlertWindow("Error", "No Arena Open");
		    	}
			}
		});
		/*Object creation*/
		Button addObstacleButton = new Button("Obstacle");
		/*Defining the action for the option*/
		addObstacleButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				/*Stop the timer*/
				Timer.stop();
				/*If there is an Arena open*/
				if(Active == true)
		    	{
					/*If the ObstacleBlock is created successfully*/
		    		if(Arena.AddObstacleBlock() == true)
		    		{
		    			/*Send out a success alert*/
		    			AlertWindow("Success", "Obstacle Added Successfully");
		    			/*Draw everything*/
		    			DrawAll();
		    		}
		    		/*Otherwise*/
		    		else
		    		{
		    			/*Send out an error alert*/
		    			AlertWindow("Error", "Arena at Capacity");
		    		}
		    	}
				/*Otherwise*/
		    	else
		    	{
		    		/*Send out an error alert*/
		    		AlertWindow("Error", "No Arena Open");
		    	}
			}
		});
		/*Object creation*/
		Button addClearButton = new Button("Clear All");
		/*Defining the action for the option*/
		addClearButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event) 
			{
				/*Clear every object out of the Arena*/
				Arena.ClearAll();
				/*Draw everything*/
				DrawAll();
			}
		});
		/*Add all the buttons to the ltPane*/
		ltPane.getChildren().setAll(l1, startButton, pauseButton, l2, addRobotButton, addWhiskerButton, addLSensorButton, addLightButton, addObstacleButton, l3, addClearButton);
		ltPane.setSpacing(2);
	}
	/**
	 * Function definition for start()
	 * <p>
	 * Creates all the objects required to setup the application and then,
	 * when all setup is completed, the window is shown.
	 */
	public void start(Stage mainStage) throws Exception 
	{
		/*Create new Timer*/
		Timer = new AnimationTimer()
		{
		/*Defining the action for the Timer*/
		public void handle(long currentNanoTime) 
		{
			/*Call Simulate()*/
			Arena.Simulate();
			Arena.Simulate();
			/*Draw everything*/
			DrawAll();
		}
		};
		/*Set title of the application to "Robot Simulator"*/
		mainStage.setTitle("Robot Simulator");
		/*Create a new BorderPane*/
		BorderPane bPane = new BorderPane();
		/*Add the menu to the top of bPane*/
		bPane.setTop(SetMenu());
		/*Create a new Group*/
		Group base = new Group();
		/*Create a new Canvas*/
		Canvas backdrop = new Canvas(CanvasSize, CanvasSize);
		/*Set the GraphicsContext to 2D*/
		gc = backdrop.getGraphicsContext2D();
		/*Add the Canvas to the base*/
		base.getChildren().add(backdrop);
		/*Set the base in the centre*/
		bPane.setCenter(base);
		/*Create a new VBox*/
		rtPane = new VBox();
		/*Set the VBox to the right side*/
		bPane.setRight(rtPane);
		/*Create a new HBox*/
		ltPane = new HBox();
		/*Create the buttons*/
		CreateButtons(ltPane);
		/*Add the HBox to the BorderPane*/
		bPane.setBottom(ltPane);
		/*Create the mainScene*/
		Scene mainScene = new Scene(bPane, CanvasSize * 1.5, CanvasSize * 1.2);
		/*Set the stage's scene to the mainScene*/
		mainStage.setScene(mainScene);
		/*Show the window*/
		mainStage.show();
	}
	/**
	 * Main function
	 * <p>
	 * Starts the application.
	 */
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
}
