package version5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * File management for the Fairytale Library system.
 * @author <center><i>Group 3</i><br /> Szilard, Simos, Mathias & Jonas</center>
 *
 */
public class FileIO 
{
	private String name;
	
	
	/**
	 * 
	 * @param name File name for the File IO to communicate with
	 */
	public FileIO(String name)
	{
		File f = new File(name);
		
		if(f.exists())
			System.out.println("(@FileIO) File already exists.");
		
		this.name = name;
	}
	
	/**
	 * 
	 * @param obj Object to append
	 * @return Returns 0 if everything went as it should, -1 if things went wrong.
	 * @throws Exception
	 */
	public int fappend(Object obj) throws Exception
	{
		ArrayList<Object> appendList = new ArrayList<Object>();
		Object[] fileGetContent = null;
		
		try {
			fileGetContent = freadArray();
		} catch (Exception e) {
			System.err.println("Failed to read current content.");
		}
		
		if(fileGetContent != null)
		{
			for(int i = 0; i < fileGetContent.length; i++)
			{
				appendList.add(fileGetContent[i]);
			}
		}
		
		appendList.add(obj);
		
		ObjectOutputStream fileOut = null;
		
		try {
			FileOutputStream streamOut = new FileOutputStream(name, false);
			
			fileOut = new ObjectOutputStream(streamOut);
			
			for(int k = 0; k < appendList.size(); k++)
			{
				fileOut.writeObject(appendList.get(k));
			}
		} finally {
			if(fileOut != null)
			{
				try {
					
					fileOut.close();
					return 0;
				} catch (IOException e)
				{
					System.err.println("File could not be closed.");
					return -1;
				}
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param obj Object array to append
	 * @return Returns 0 if everything went as it should, -1 if things went wrong.
	 * @throws Exception
	 */
	public int fappend(Object[] obj) throws Exception
	{
		ArrayList<Object> appendList = new ArrayList<Object>();
		Object[] fileGetContent = null;
		
		try {
			fileGetContent = freadArray();
		} catch (Exception e) {
			System.err.println("Failed to read current content.");
		}
		
		if(fileGetContent != null)
		{
			for(int i = 0; i < fileGetContent.length; i++)
			{
				appendList.add(fileGetContent[i]);
			}
		}
		
		for(int z = 0; z < obj.length; z++)
		{
			appendList.add(obj[z]);
		}
		
		ObjectOutputStream fileOut = null;
		
		try {
			FileOutputStream streamOut = new FileOutputStream(name, false);
			
			fileOut = new ObjectOutputStream(streamOut);
			
			for(int k = 0; k < appendList.size(); k++)
			{
				fileOut.writeObject(appendList.get(k));
			}
		} finally {
			if(fileOut != null)
			{
				try {
					fileOut.close();
					return 0;
				} catch (IOException e)
				{
					System.err.println("File could not be closed.");
					return -1;
				}
			}
		}
		return 0;
	}
	/**
	 * 
	 * @param obj The object that is being written to the file.
	 * @return Returns 0 if everything went as it should, -1 if things went wrong.
	 * @throws Exception
	 */
	public int fwrite(Object obj) throws Exception
	{
		ObjectOutputStream fileOut = null;
		
		try {
			FileOutputStream streamOut = new FileOutputStream(name, false);
			
			fileOut = new ObjectOutputStream(streamOut);
			
			fileOut.writeObject(obj);
		}
		finally {
			if(fileOut != null)
			{
				try {
					fileOut.close();
					return 0;
				} catch (IOException e)
				{
					System.err.println("File could not be closed.");
					return -1;
				}
				
			}
		}
		
		return 0;
	}
	
	/**
	 * 
	 * @param obj The object array that is being written to the file.
	 * @return Returns 0 if everything went as it should, -1 if things went wrong.
	 * @throws Exception
	 */
	public int fwrite(Object[] obj) throws Exception
	{
		ObjectOutputStream fileOut = null;
		
		try {
			FileOutputStream streamOut = new FileOutputStream(name, false);
			fileOut = new ObjectOutputStream(streamOut);
			
			for(int i = 0; i < obj.length; i++)
			{
				fileOut.writeObject(obj[i]);
			}
		}
		finally {
			if(fileOut != null)
			{
				try {
					fileOut.close();
					return 0;
					
				} catch (IOException e)
				{
					System.err.println("File could not be closed");
					return -1;
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * 
	 * @return Returns the first object in the file
	 * @throws Exception
	 */
	public Object fread() throws Exception
	{
		Object obj = null;
		ObjectInputStream fileIn = null;
		
		try {
			FileInputStream streamIn = new FileInputStream(name);
			fileIn = new ObjectInputStream(streamIn);
			
			try {
				obj = fileIn.readObject();
			} catch (EOFException eof)
			{
				;
			}
		} finally {
			if(fileIn != null)
			{
				try {
					fileIn.close();
				} catch (IOException e)
				{
					System.err.println("Error closing file");
				}
			}
		}
		
		return obj;
	}
	
	
	/**
	 * 
	 * @return Returns an array with all objects in the file
	 * @throws Exception
	 */
	public Object[] freadArray() throws Exception
	{
		ArrayList<Object> objList = new ArrayList<Object>();
		
		ObjectInputStream fileIn = null;
		
		try {
			FileInputStream streamIn = new FileInputStream(name);
			fileIn = new ObjectInputStream(streamIn);
			
			while(true)
			{
				try {
					objList.add(fileIn.readObject());
				} catch(EOFException eof)
				{
					break;
				}
			}
		} finally {
			if(fileIn != null)
			{
				try {
					fileIn.close();
					System.out.println("!!! closed");
				} catch (IOException e) {
					System.err.println("Error closing file");
				}
			} 

		}
		
		return objList.toArray();
	}
}
