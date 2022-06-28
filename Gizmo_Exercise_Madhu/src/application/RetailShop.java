package application;

/**
 * The small retail shop that sells Gizmos requires a small application to manage inventory of different types of gizmos it sells.  
 * The store owner wants to be able to modify the store’s inventory by 
 * 
 * adding new gizmos, 
 * deleting old out of production gizmos, 
 * updating current inventory of gizmos and 
 * searching current gizmo stock by gizmo name.  
 * 
 * @author Madhu Madhavan
 * @version May 2021
 */
import java.util.*;

import manager.GizmoInventory;

import java.io.*;

public class RetailShop {

	public static void main (String [] args)  throws IOException {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);
        final String filename = "data/gizmos.txt";
        
		//create the gizmo inventory
		GizmoInventory gzInventory = new GizmoInventory();
		gzInventory.readGizmosTxtFile(filename, in);
		
        gzInventory.runRetailShop(in);
        
        in.close();
    }

    
}
