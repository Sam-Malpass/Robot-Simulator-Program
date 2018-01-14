package robotSimulator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public class FileIO 
{
	/**
	 * Constructor with no arguments
	 */
	FileIO()
	{	
	}
	/**
	 * Function definition for Save()
	 * <p>
	 * Opens the JFrame file save dialog allowing users to name file
	 * and amending .ser if the user has not done so already before finally
	 * writing the Arena object to the file.
	 *<p>
	 * @param A is an Arena object that will contain various numbers of objects
	 */
	public void Save(Arena A) 
	{
		/*Object Declarations*/
		JFrame Frame = new JFrame();
		JFileChooser SaveAs = new JFileChooser();
		/*Sets the Title of the Dialog Window to "Save As"*/
		SaveAs.setDialogTitle("Save As");
		/*Variable Declaration and Assignment*/
		int Option = SaveAs.showSaveDialog(Frame);
		/*If the Option is Approved*/
		if (Option == JFileChooser.APPROVE_OPTION) 
		{
			/*Set File to be Equal to Selected File*/
			File File = SaveAs.getSelectedFile();
			/*Variable Declaration and Assignment*/
			String FilePath = File.getPath();
			/*Adds the ".ser" File Extension for the user*/
			if(!FilePath.toLowerCase().endsWith(".ser"))	
			{
			    FilePath = FilePath + ".ser";
			}
			/*Attempts to Write to File*/
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FilePath))) 
			{
				oos.writeObject(A);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}	
	}
}
