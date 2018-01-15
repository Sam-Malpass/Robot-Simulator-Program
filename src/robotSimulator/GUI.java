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
	 * canvasSize is acts as the width and length of the canvas
	 */
	private int canvasSize = 500;
	/**
	 * arena is an object of type arena
	 */
	private Arena arena;
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
	 * active is a boolean value that says whether an arena object is open or not
	 */
	private boolean active = false;
	/**
	 * timer acts as a measure for the Animation
	 */
	private AnimationTimer timer;
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
	private void alertWindow(String titleStr, String contentStr) 
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
	private int getValue(String TStr) 
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
			/*Prevents arena being too large*/
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
	 * Fills a rectangle the size of the arena on the Canvas before drawing lines
	 * around said rectangle.
	 */
	public void drawArena()
	{
		/*Set colour to DARKGRAY*/
		gc.setFill(Color.DARKGRAY);
		/*Fill a rectangle of given size*/
		gc.fillRect(0, 0, arena.getWidth(), arena.getLength());
		/*Set colour to BLACK*/
		gc.setFill(Color.BLACK);
		/*Set line width to 1*/
		gc.setLineWidth(1);
		/*Draw the top wall*/
		gc.strokeLine(0,0,arena.getWidth(),0);
		/*Draw the right wall*/
		gc.strokeLine(arena.getWidth(),0, arena.getWidth(), arena.getLength());
		/*Draw the bottom wall*/
		gc.strokeLine(arena.getWidth(), arena.getLength(), 0, arena.getLength());
		/*Draw the left wall*/
		gc.strokeLine(0, arena.getLength(), 0, 0);
	}
	/**
	 * Function definition for DrawWhiskerRobot()
	 * <p>
	 * Draw all the WhiskerRobots in the arena
	 */
	public void drawWhiskerRobot()
	{
		for (int ct = 0; ct < arena.contents.size(); ct++)
		{
			int S = arena.contents.get(ct).getSize(), X = arena.contents.get(ct).getXPosition(), Y = arena.contents.get(ct).getYPosition();
			if(arena.contents.get(ct) instanceof WhiskerRobot)
			{
				/*Create a temporary WhiskerRobot by casting the current object*/
				WhiskerRobot B = (WhiskerRobot) arena.contents.get(ct);
				/*Set colour to RED*/
				gc.setFill(Color.RED);
				/*Draw a circle to represent the body*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
				/*Set line width to 5 for the wheels*/
				gc.setLineWidth(5);
				/*If the robot's direction is UP*/
				if(B.getDirection() == DirectionHandler.UP)
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
				else if(B.getDirection() == DirectionHandler.DOWN)
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
				else if(B.getDirection() == DirectionHandler.LEFT)
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
				else if(B.getDirection() == DirectionHandler.RIGHT)
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
	 * Draw all the LightRobots in the arena
	 */
	public void drawLightRobot()
	{
		/*For all objects in the arena*/
		for (int ct = 0; ct < arena.contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = arena.contents.get(ct).getSize(), X = arena.contents.get(ct).getXPosition(), Y = arena.contents.get(ct).getYPosition();
			if(arena.contents.get(ct) instanceof LightRobot)
			{
				gc.setStroke(Color.BLACK);
				/*Create a LightRobot by casting the current object*/
				LightRobot B = (LightRobot) arena.contents.get(ct);
				/*Set colour to PURPLE*/
				gc.setFill(Color.PURPLE);
				/*Draw a circle to represent the robot's body*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
				/*Set line width to 5 for the wheels*/
				gc.setLineWidth(5);
				/*If the robot's direction is UP*/
				if(B.getDirection() == DirectionHandler.UP)
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
				else if(B.getDirection() == DirectionHandler.DOWN)
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
				else if(B.getDirection() == DirectionHandler.LEFT)
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
				else if(B.getDirection() == DirectionHandler.RIGHT)
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
	 * Draw all the BasicRobots in the arena
	 */
	public void drawBasicRobot()
	{
		/*For all objects in the arena*/
		for (int ct = 0; ct < arena.contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = arena.contents.get(ct).getSize(), X = arena.contents.get(ct).getXPosition(), Y = arena.contents.get(ct).getYPosition();
			/*If the object is a BasicRobot*/
			if(arena.contents.get(ct) instanceof BasicRobot && !(arena.contents.get(ct) instanceof WhiskerRobot) && !(arena.contents.get(ct) instanceof LightRobot))
			{
				/*Create a BasicRobot by casting the current object*/
				BasicRobot B = (BasicRobot) arena.contents.get(ct);
				/*Set colour to GREEN*/
				gc.setFill(Color.GREEN);
				/*Draw a circle to represent the robot's body*/
				gc.fillArc(X-S, Y-S, S*2, S*2, 0, 360, ArcType.ROUND);
				/*Set line width to 5 for the wheels*/
				gc.setLineWidth(5);
				/*If the robot's direction is UP or DOWN*/
				if(B.getDirection() == DirectionHandler.UP || B.getDirection() == DirectionHandler.DOWN)
				{
					/*Draw the wheels*/
					gc.strokeLine(X+17,Y+10,X+17,Y-10);
					gc.strokeLine(X-17,Y+10,X-17,Y-10);
				}
				/*If the robot's direction is LEFT or RIGHT*/
				else if(B.getDirection() == DirectionHandler.LEFT || B.getDirection() == DirectionHandler.RIGHT)
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
	 * Draw all LightSources in the arena
	 */
	public void drawLightSource()
	{
		/*For all objects in the arena*/
		for (int ct = 0; ct < arena.contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = arena.contents.get(ct).getSize(), X = arena.contents.get(ct).getXPosition(), Y = arena.contents.get(ct).getYPosition();
			/*If the object is a LightSource*/
			if(arena.contents.get(ct) instanceof LightSource)
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
	 * Draws all ObstacleBlocks in the arena
	 */
	public void drawObstacleBlock()
	{
		for (int ct = 0; ct < arena.contents.size(); ct++)
		{
			/*Create some temporary variables NOTE: This was done to make code easier to write/read*/
			int S = arena.contents.get(ct).getSize(), X = arena.contents.get(ct).getXPosition(), Y = arena.contents.get(ct).getYPosition();
			/*If the object is an ObstacleBlock*/
			if(arena.contents.get(ct) instanceof ObstacleBlock)
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
	public void drawObjects()
	{
		drawWhiskerRobot();
		drawLightRobot();
		drawBasicRobot();
		drawObstacleBlock();
		drawLightSource();
	}
	/**
	 * Function definition for DrawStatus()
	 * <p>
	 * Clear the rtPane, create a new label containing the result of ListContents() and
	 * then add the label to the rtPane. 
	 */
	public void drawStatus() 
	{
		/*Clear the rtPane*/
		rtPane.getChildren().clear();
		/*Create a new Label using the result of ListContents()*/
		Label l = new Label(arena.listContents());
		/*Add the Label to rtPane*/
		rtPane.getChildren().add(l);
	}
	/**
	 * Function definition for DrawAll()
	 * <p>
	 * Clears the Canvas before calling DrawArena(), DrawObjects() and DrawStatus().
	 */
	public void drawAll()
	{
		/*Clear Canvas*/
		gc.clearRect(0, 0, canvasSize, canvasSize);
		/*Draw the arena*/
		drawArena();
		/*Draw the objects*/
		drawObjects();
		/*Draw the positions of the objects*/
		drawStatus();
	}
	/**
	 * Function definition for SetMenu()
	 * <p>
	 * Create a MenuBar, with several Menus that each contain varying amounts of MenuItems.
	 * <p>
	 * @return menuBar with all the buttons on it
	 */
	public MenuBar setMenu() 
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
				/*Save the arena*/
				Save.Save(arena);
			}
		});
		/*Defining the action for the option*/
		mLoad.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Create a FileIO object*/
				FileIO Load = new FileIO();
				/*Load an arena*/
				arena = Load.Load();
				/*Toggle the active variable*/
				active = true;
				/*Draw everything*/
				drawAll();
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
		Menu mOptions = new Menu("arena Options");
		MenuItem mNew = new MenuItem("New arena");
		/*Defining the action for the option*/
		mNew.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Variables are created and assigned values based on information input by the user*/
		    	int W = getValue("arena Width (Min 41, Max 500)");
		    	int L = getValue("arena Length (Min 41, Max 500");
		    	int C = (W * L) / (4*20*20);
		    	/*If any of the values are less than or equal to 0*/
		    	if(W <= 40 || L <= 40 || C <= 0)
		    	{
		    		/*Send out an error alert*/
		    		alertWindow("Error", "arena Failed to Create");
		    	}
		    	/*Otherwise*/
		    	else
		    	{
		    		/*Create a new arena using the values*/
		    		arena = new Arena(W, L, C);
		    		/*Toggle the active variable*/
		    		active = true;
		    		/*Draw everything*/
		    		drawAll();
		    		/*Send out a success alert*/
		    		alertWindow("Success", "arena Created Successfully");
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
				alertWindow("Information",
						"This is the Robot Simulator, to begin, create an arena using the arena options 'new' or load an arena from 'file'. Then add some robots and click start. The robots should then begin moving.");
			}
		});
		/*Defining the action for the option*/
		mAbout.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent actionEvent) 
			{
				/*Send out alert window with the specified information*/
				alertWindow("About",
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
	public void createButtons(HBox ltPane)
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
				timer.start();
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
				timer.stop();
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
				timer.stop();
				/*If there is an arena open*/
		    	if(active == true)
		    	{
		    		/*If the robot is added successfully*/
		    		if(arena.addBasicRobot() == true)
		    		{
		    			/*Send a success alert*/
		    			alertWindow("Success", "Robot Added Successfully");
		    			/*Draw everything*/
		    			drawAll();
		    		}
		    		/*Otherwise*/
		    		else
		    		{
		    			/*Send an error alert*/
		    			alertWindow("Error", "Robot failed to add, perhaps arena is full or there is nowhere to put a robot at this time");
		    		}
		    	}
		    	/*Otherwise*/
		    	else
		    	{
		    		/*Send an error alert*/
		    		alertWindow("Error", "No arena Open");
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
				timer.stop();
				/*If there is an arena open*/
			    if(active == true)
			    {
			    	/*If the robot is created successfully*/
			    	if(arena.addWhiskerRobot() == true)
			    	{
			    		/*Send out a success alert*/
			    		alertWindow("Success", "Robot Added Successfully");
			    		/*Draw everything*/
			    		drawAll();
			    	}
			    	/*Otherwise*/
			    	else
			    	{
			    		/*Send out an error alert*/
			    		alertWindow("Error", "Robot failed to add, perhaps arena is full or there is nowhere to put a robot at this time");
			    	}
			   	}
			    /*Otherwise*/
			   	else
			   	{
			   		/*Send out an error alert*/
			   		alertWindow("Error", "No arena Open");
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
				timer.stop();
				/*If there is an arena open*/
			    if(active == true)
			    {
			    	/*If the robot is created successfully*/
			    	if(arena.addLightRobot() == true)
			    	{
			    		/*Send out a success alert*/
			    		alertWindow("Success", "Robot Added Successfully");
			    		/*Draw everything*/
			    		drawAll();
			    	}
			    	/*Otherwise*/
			    	else
			    	{
			    		/*Send out an error alert*/
			    		alertWindow("Error", "Robot failed to add, perhaps arena is full or there is nowhere to put a robot at this time");
			    	}
			   	}
			    /*Otherwise*/
			   	else
			   	{
			   		/*Send out an error alert*/
			   		alertWindow("Error", "No arena Open");
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
				timer.stop();
				/*If there is an arena open*/
				if(active == true)
		    	{
					/*If the LightSource is added successfully*/
		    		if(arena.addLightSource() == true)
		    		{
		    			/*Send out a success alert*/
		    			alertWindow("Success", "Light Source Added Successfully");
		    			/*Draw everything*/
		    			drawAll();
		    		}
		    		/*Otherwise*/
		    		else
		    		{
		    			/*Send out an error alert*/
		    			alertWindow("Error", "arena at Capacity");
		    		}
		    	}
				/*Otherwise*/
		    	else
		    	{
		    		/*Send out an error alert*/
		    		alertWindow("Error", "No arena Open");
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
				timer.stop();
				/*If there is an arena open*/
				if(active == true)
		    	{
					/*If the ObstacleBlock is created successfully*/
		    		if(arena.addObstacleBlock() == true)
		    		{
		    			/*Send out a success alert*/
		    			alertWindow("Success", "Obstacle Added Successfully");
		    			/*Draw everything*/
		    			drawAll();
		    		}
		    		/*Otherwise*/
		    		else
		    		{
		    			/*Send out an error alert*/
		    			alertWindow("Error", "arena at Capacity");
		    		}
		    	}
				/*Otherwise*/
		    	else
		    	{
		    		/*Send out an error alert*/
		    		alertWindow("Error", "No arena Open");
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
				/*Clear every object out of the arena*/
				arena.clearAll();
				/*Draw everything*/
				drawAll();
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
		/*Create new timer*/
		timer = new AnimationTimer()
		{
		/*Defining the action for the timer*/
		public void handle(long currentNanoTime) 
		{
			/*Call Simulate()*/
			arena.simulate();
			arena.simulate();
			/*Draw everything*/
			drawAll();
		}
		};
		/*Set title of the application to "Robot Simulator"*/
		mainStage.setTitle("Robot Simulator");
		/*Create a new BorderPane*/
		BorderPane bPane = new BorderPane();
		/*Add the menu to the top of bPane*/
		bPane.setTop(setMenu());
		/*Create a new Group*/
		Group base = new Group();
		/*Create a new Canvas*/
		Canvas backdrop = new Canvas(canvasSize, canvasSize);
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
		createButtons(ltPane);
		/*Add the HBox to the BorderPane*/
		bPane.setBottom(ltPane);
		/*Create the mainScene*/
		Scene mainScene = new Scene(bPane, canvasSize * 1.5, canvasSize * 1.2);
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
