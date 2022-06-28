package manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import problem_domain.Gizmo;

public class GizmoInventory {
	private ArrayList<Gizmo> gizmos = null;
	private ArrayList<Gizmo> outOfProdGizmos = null; // a list for gizmos that are out of production
	
	public GizmoInventory(){
		this.gizmos = new ArrayList<Gizmo>();
		this.outOfProdGizmos = new ArrayList<Gizmo>();	
		
	}
	
	public ArrayList<Gizmo> getGizmos() {
		return gizmos;
	}
	
	public void addToInventory(Gizmo newGizmo) {
		this.gizmos.add(newGizmo);
	}
	
	public ArrayList<Gizmo> getOutOfProdGizmos() {
		return outOfProdGizmos;
	}
	
	public void addToOutOfProdGizmos(Gizmo oldGizmo) {
		this.outOfProdGizmos.add(oldGizmo);
	}		
	
	@SuppressWarnings("resource")
	public void readGizmosTxtFile (String filename, Scanner inFile) throws IOException {
		
		// Open the data file
        File f = new File (filename);
        if (!f.exists() ){
            System.out.printf ("Can't find file %s%n", filename);
            System.exit (1);
        }
        inFile = new Scanner(f);
        // used to input a whole line of data
        String data = "";
        String [] items = null;

        	// while more data
        while (inFile.hasNextLine())  {
            // read entire line
            data = inFile.nextLine();            
            // Split line into component parts
            items = data.split(";");

            Gizmo g = new Gizmo(items[0], items[1], Integer.parseInt(items[2]), Double.parseDouble(items[3]));
            this.addToInventory(g);
        }

    }
	
			//Assuming that some gizmos may need to be removed from main inventory
	public void removeOutOfProdGizmos(Gizmo badGizmo) {
			//add the out of production item to the OOPA
		this.outOfProdGizmos.add(badGizmo);
		
		//remove the out of production item from the main inventory list
		int index = this.gizmos.indexOf(badGizmo);
		this.gizmos.remove(index);
	}
	
	public Gizmo searchAndDisplay(String name) {
		Gizmo returnGizmo = null;
		boolean foundIt = false;
		
		for (Gizmo searchGizmo: this.gizmos) {
			if (name.equalsIgnoreCase(searchGizmo.getName())) {
				System.out.println(searchGizmo);
				foundIt = true;
				returnGizmo = searchGizmo;
			}
		}
		if (!foundIt) {
			System.out.println("There are no gizmos with this name: " + name);
		} 
		return returnGizmo;
	}
	
	public void runRetailShop(Scanner in)
	{
		int selection;
        selection = displayMenu(in);
        
        while (selection != 9) {
            switch (selection) {
                case 1: {
	                	// Print all the Gizmos 
	                for (Gizmo gz: this.getGizmos()){
	                	System.out.println("" + gz);
	                }
	                }
                break;
                
                case 2: {
	                	// Add new gizmo to inventory 
	                System.out.printf ("Enter Gizmo Id, name, quantity and price: ID;NAME;QUANTITY;PRICE");
	                String data = "";
	                String [] items = null;
	                	// while more data
	                if (in.hasNextLine())  {
	                    // read entire line
	                    data = in.nextLine();            
	                    // Split line into component parts
	                    items = data.split(";");
	                    Gizmo userGizmo = new Gizmo(items[0], items[1], Integer.parseInt(items[2]), Double.parseDouble(items[3]));
	                    this.getGizmos().add(userGizmo);
	                }
	                else {
	                	System.out.printf ("There is an error with the scanner: check code");
	                }
                }
                break;
                		// repeat case 1 to see the updated list of Gizmos
                case 3: {
	                	// Take Gizmo out of production
	                System.out.printf ("Enter Gizmo name to take out of production: ");
	                String outOfProdGizmo = in.nextLine();
	                Gizmo userEnteredGizmo = searchAndDisplay(outOfProdGizmo);
	                if (userEnteredGizmo != null) {
	                	removeOutOfProdGizmos(userEnteredGizmo);
	                }
                }
                break;
                		// repeat case 1 to see the updated list of Gizmos
                case 4: {
                    // Search for a Gizmo in the inventory (by name)
                    System.out.printf ("Enter Gizmo name to search inventory: ");
                    String searchName = in.nextLine();
                    searchAndDisplay(searchName);
                }
                break;
                	// repeat case 1 to see the updated list of Gizmos
            }
            selection = displayMenu(in);
        }
	}
	
	public static int displayMenu(Scanner k) {
        //System.out.print ("\f");
        System.out.printf ("%s%n", "Gizmo Reatail Shop");
        System.out.printf ("%s%n","Press 1 to PRINT all available Gizmos");
        System.out.printf ("%s%n","Press 2 to ADD new Gizmo to inventory; Then press 1 to see the UPDATED list");
        System.out.printf ("%s%n","Press 3 to DELETE a Gizmo that is out of production; Then press 1 to see the UPDATED list");
        System.out.printf ("%s%n","Press 4 to SEARCH for a Gizmo in the inventory (by name)");
        System.out.printf ("%s%n","Press 9 to EXIT");
        System.out.printf ("%s%n","Enter your selection: ");
        int selection = k.nextInt();
        k.nextLine(); // discard \n
        //System.out.print ("\f");
        return selection;
    }
}
