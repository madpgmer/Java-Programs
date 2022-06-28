/**
 * 
 * @author Madhu
 *
 * 
 */


package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Scanner;

import shapeObjects.Shape;

public class shapesManager {

	private String file_path;
	public Shape[] allShapes;
	
	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	public Shape[] getAllShapes() {
		return allShapes;
	}

	public void setAllShapes(Shape[] allShapes) {
		this.allShapes = allShapes;
	}

	Scanner read = null;
	
	/**
	 * this is the shapesManager constructor
	 */
	public shapesManager() {
	}
	
	/**
	 * reads all the shapes from a input csv
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 * @author Madhu
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populateShapes() throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		File file = new File(this.file_path);

		read = new Scanner(file);
		String data = "";
		String[] items = null;

		while (read.hasNextLine()) {

			data = read.nextLine();
			items = data.split(" ");
			
			int numberOfShapes = 0;
			numberOfShapes = Integer.parseInt(items[0]);
			
			allShapes = new Shape[numberOfShapes];
			int shCounter = 0;
			
			for (int i=1; i<items.length; i=i+3) {
				
				//using reflection, create the objects for the array - Madhu
				// need to add the package name before the class - Madhu
				Class cls = Class.forName("shapeObjects." + items[i]);
				Class paramTypes[] = new Class[2];
				
				paramTypes[0] = Double.TYPE;
				paramTypes[1] = Double.TYPE;
				
				Constructor ct = cls.getConstructor(paramTypes);
				
				Object argList[] = new Object[2];
				argList[0] = new Double(Double.parseDouble(items[i+1]));
				argList[1] = new Double(Double.parseDouble(items[i+2]));
								
				Object o = ct.newInstance(argList);
				
				allShapes[shCounter] = (Shape)o;
				shCounter++;
				
			}
		}
	}
	
	public void printArray(Shape[] allShapes) {
		for( int i = 0; i < allShapes.length; i++ )
		{
			DecimalFormat df = new DecimalFormat("0.00");
			
			System.out.println(String.format("%-" + 20 + "s", allShapes[i].getClass().getSimpleName()) 
			+ String.format("%-" + 10 + "s", " height:") + String.format("%-" + 10 + "s", df.format(allShapes[i].getHeight())) 
			+ String.format("%-" + 15 + "s", " :base area:") + String.format("%-" + 15 + "s", df.format(allShapes[i].calcBaseArea())) 
			+ String.format("%-" + 15 + "s", " :volume:") + String.format("%-" + 15 + "s", df.format(allShapes[i].calcVolume())) + "; "); 
		}
		System.out.println();
	}
}
