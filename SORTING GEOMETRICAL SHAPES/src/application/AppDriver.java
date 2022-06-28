/**
 * 
 * @author Madhu
 *
 * 
 */
package application;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import Utilities.BubbleSort;
import Utilities.GnomeSort;
import Utilities.InsertionSort;
import Utilities.MergeSort;
import Utilities.QuickSort;
import Utilities.SelectionSort;
import manager.shapesManager;
import shapeObjects.BaseAreaComparator;
import shapeObjects.HeightComparator;
import shapeObjects.Shape;
import shapeObjects.VolumeComparator;

public class AppDriver {
	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @author Madhu, Eze, Emmari, Alan
	 */
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (args != null && args.length >= 3) {
			
			//initialize all variables;
			shapesManager shManager = new shapesManager(); //for reading from file
			Shape[] allShapesFromFile = null; //for storing all the shapes
			//below for all the comparator checks
			boolean baComp = false;
			boolean vComp = false;
			boolean hComp = false;
			//below for all the sorting algorithms
			boolean bubSort = false;
			boolean insSort = false;
			boolean mergSort = false;
			boolean quiSort = false;
			boolean selSort = false;
			boolean mySort = false;
			
			BaseAreaComparator bac = null;
			VolumeComparator vc = null;
			HeightComparator hc = null;
			
			if (args.length > 3) {
				System.out.println("PLEASE NOTE: There are more than 3 arguments passed for the Sorting program, ONLY THE FIRST THREE arguments will be parsed and considered, the rest will be ignored");
			}
		
			boolean argsCorrect = true;
			for (int i=0; i<3; i++) {
				String parseArgument = args[i];
				
				char firstChar = parseArgument.charAt(0);
				char secondChar = parseArgument.charAt(1);
				String restOfArg = parseArgument.substring(2);
				
				if (firstChar != '-') {
					argsCorrect = false;
					break;
				}
				
				if (secondChar == 'f' || secondChar == 'F') {
					//that means restOfArgs is the file path
					shManager.setFile_path(restOfArg);
					shManager.populateShapes();
					allShapesFromFile = shManager.getAllShapes();
			
				} else if (secondChar == 't' || secondChar == 'T') { //that means restOf Args is the type to search by
					
					if (restOfArg.equalsIgnoreCase("v")) {
						vComp = true;
						vc = new VolumeComparator();
					} else if (restOfArg.equalsIgnoreCase("h")) {
						hComp = true;
						hc = new HeightComparator();
					} else if (restOfArg.equalsIgnoreCase("a")) {
						baComp = true;
						bac = new BaseAreaComparator();
					} else {
						argsCorrect = false;
					}
				
				} else if (secondChar == 's' || secondChar == 'S') { //that means restOf Args is the Sorting algorithm

					if (restOfArg.equalsIgnoreCase("b")) {
						bubSort = true;
					} else if (restOfArg.equalsIgnoreCase("s")) {
						selSort = true;
					} else if (restOfArg.equalsIgnoreCase("i")) {
						insSort = true;
					} else if (restOfArg.equalsIgnoreCase("m")) {
						mergSort = true;
					} else if (restOfArg.equalsIgnoreCase("q")) {
						quiSort = true;
					} else if (restOfArg.equalsIgnoreCase("z")) {
						mySort = true;
					} else {
						argsCorrect = false;
					}
					
				} else {
					argsCorrect = false;
					break;
				}
			}
			
			if (argsCorrect) { //if all arguments are correct, then start timer and run the sorting based on arguments passed
				long start, stop;
				String run = "All arguments correct : arraysize-" + allShapesFromFile.length + " : Sort-";
		    	start = System.currentTimeMillis();
		    	
		    	if (bubSort) {
		    		BubbleSort bs = new BubbleSort();
		    		run = run + "Bubble Sort : Comp:";
		    		if (vComp) {
				    	bs.bubbleSort(allShapesFromFile, vc);	
				    	run = run + "volume Comparator";
		    		} else 
		    		if (hComp) {
					   	bs.bubbleSort(allShapesFromFile, hc);
					   	run = run + "height Comparator";
			    	} else
			    	if (baComp) {
					   	bs.bubbleSort(allShapesFromFile, bac);
					   	run = run + "base area Comparator";
			    	}
		    	} else 
	    		if (selSort) {
		    		SelectionSort ss = new SelectionSort();
		    		run = run + "Selection Sort : Comp:";
		    		if (vComp) {
				    	ss.selectionSort(allShapesFromFile, vc);		    			
				    	run = run + "volume Comparator";
		    		} else 
		    		if (hComp) {
					   	ss.selectionSort(allShapesFromFile, hc);
					   	run = run + "height Comparator";
			    	} else
			    	if (baComp) {
					   	ss.selectionSort(allShapesFromFile, bac);
					   	run = run + "base area Comparator";
			    	}
		    	} else
	    		if (insSort) {
		    		InsertionSort is = new InsertionSort();
		    		run = run + "Insertion Sort : Comp:";
		    		if (vComp) {
				    	is.insertionSort(allShapesFromFile, vc);		    			
				    	run = run + "volume Comparator";
		    		} else 
		    		if (hComp) {
					   	is.insertionSort(allShapesFromFile, hc);
					   	run = run + "height Comparator";
			    	} else
			    	if (baComp) {
					   	is.insertionSort(allShapesFromFile, bac);
					   	run = run + "base area Comparator";
			    	}
		    	} else 
	    		if (mergSort) {
		    		MergeSort ms = new MergeSort();
		    		run = run + "Merge Sort : Comp:";
		    		if (vComp) {
				    	ms.mergeSort(allShapesFromFile, vc, 0, allShapesFromFile.length - 1);		    			
				    	run = run + "volume Comparator";
		    		} else 
		    		if (hComp) {
					   	ms.mergeSort(allShapesFromFile, hc, 0, allShapesFromFile.length - 1);		    			
					   	run = run + "height Comparator";
		    		} else
			    	if (baComp) {
					   	ms.mergeSort(allShapesFromFile, bac, 0, allShapesFromFile.length - 1);		    			
					   	run = run + "base area Comparator";
			    	}
		    	} else
	    		if (quiSort) {
		    		QuickSort qs = new QuickSort();
		    		run = run + "Quick Sort : Comp:";
		    		if (vComp) {
				    	qs.quickSort(allShapesFromFile, vc, 0, allShapesFromFile.length - 1);		    			
				    	run = run + "volume Comparator";
		    		} else 
		    		if (hComp) {
					   	qs.quickSort(allShapesFromFile, hc, 0, allShapesFromFile.length - 1);		    			
					   	run = run + "height Comparator";
			    	} else
			    	if (baComp) {
					   	qs.quickSort(allShapesFromFile, bac, 0, allShapesFromFile.length - 1);		    			
					   	run = run + "base area Comparator";
			    	}
		    	} else 
	    		if (mySort) {
		    		GnomeSort mys = new GnomeSort();
		    		run = run + "My (Gnome) Sort : Comp:";
		    		if (vComp) {
				    	mys.gnomeSort(allShapesFromFile, vc, allShapesFromFile.length);		    			
				    	run = run + "volume Comparator";
		    		} else 
		    		if (hComp) {
					   	mys.gnomeSort(allShapesFromFile, hc, allShapesFromFile.length);
					   	run = run + "height Comparator";
			    	} else
			    	if (baComp) {
					   	mys.gnomeSort(allShapesFromFile, bac, allShapesFromFile.length);		    			
					   	run = run + "base area Comparator";
			    	}
		    	}			
		    	shManager.printArray(allShapesFromFile);
		    	
		    	stop = System.currentTimeMillis();
		    	System.out.println(run);
		    	System.out.println("Time for Sorting with : " + (stop - start) + " milliseconds");
		    	
			} else {
				System.out.println("There is a proplem with the arguments passed for the Sorting program, please run the program with the correct arguments (-f[TextFile.txt] –t[Type] –s[Sort] or -F[TextFile.txt] –T[Type] –S[Sort])");
			}
		} else {
			if (args == null) {
				System.out.println("There are no arguments passed for the Sorting program, please run the program with the correct arguments (-f[TextFile.txt] –t[Type] –s[Sort] or -F[TextFile.txt] –T[Type] –S[Sort])");
			} else if (args.length < 3) {
				System.out.println("There are too few arguments passed for the Sorting program, please run the program with the correct arguments (-f[TextFile.txt] –t[Type] –s[Sort] or -F[TextFile.txt] –T[Type] –S[Sort])");
			} else {
				System.out.println("There is a proplem with the arguments passed for the Sorting program, please run the program with the correct arguments (-f[TextFile.txt] –t[Type] –s[Sort] or -F[TextFile.txt] –T[Type] –S[Sort])");
			}
		}
	}
}
