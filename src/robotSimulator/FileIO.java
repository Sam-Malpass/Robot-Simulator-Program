package robotSimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

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
	/**
	 * Function definition for Load()
	 * <p>
	 * Creates a file filter to show only folders and .ser files, utilises
	 * the JFrame load dialog to allow users to select a file to load, and then 
	 * loads the selected file.
	 * <p>
	 * @return an object of type Arena
	 */
	public Arena Load() 
	{
		Arena Arena = null;
		/*File Filter*/
		FileFilter Filter = new FileFilter()
		{
			/*Filters "ser" and Directory*/
			public boolean accept(File F) 
			{
				if(F.getAbsolutePath().endsWith(".ser"))
				{
					return true;
				}
				if(F.isDirectory())
				{
					return true;
				}
				return false;
			}
			public String getDescription()
			{
				return "ser";
			}
		};
		/*Object Declarations*/
		JFrame Frame = new JFrame();
		JFileChooser LoadFrom = new JFileChooser();
		/*Sets the Title of the Dialog Window to "Load From"*/
		LoadFrom.setDialogTitle("Load From");
		LoadFrom.setFileFilter(Filter);
		/*Variable Declaration and Assignment*/
		int Option = LoadFrom.showOpenDialog(Frame);
		/*If the Option is Approved*/
		if(Option == JFileChooser.APPROVE_OPTION)
		{
			/*Set File to be Equal to Selected File*/
			File File = LoadFrom.getSelectedFile();
			/*Attempts to Read the File*/
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(File)))
			{
				Arena = (Arena) ois.readObject();

			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		/*Returns an Arena*/
		return Arena;
	}
}
