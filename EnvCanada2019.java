/**

@Author: Madhu Madhavan

**/
import java.util.*;
import java.io.*;
public class EnvCanada2019 {
    public static void main (String [] args) throws IOException {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);
        System.out.print ("Enter the filename: ");
        String filename = in.nextLine();
        File myFile = new File (filename);
        Scanner inFile = new Scanner(myFile);

        // Read and discard header line
        inFile.nextLine();
        
        // used to input a whole line of data
        String data = "";
        String [] items = null;
        
        //declare variables
        int numMaxDays = 0;
        int numMinDays = 0;
        double totalMaxTemps = 0;
        double totalMinTemps = 0;
        double maxTemp;
        double minTemp;
        int year, month, day;
        
        // while more data
        while (inFile.hasNextLine())  {
            // read entire line
            data = inFile.nextLine();            
            // Split line into component parts
            items = data.split(",");
            // extract year, month and day
            year = Integer.parseInt (items[5]);
            month = Integer.parseInt (items[6]);
            day = Integer.parseInt (items[7]);
            
            // Check to see if a max temperature is present
            if (!items[9].equals("")) {
                // temperature present convert to double
                maxTemp = Double.parseDouble (items[9]);
                totalMaxTemps += maxTemp;
                numMaxDays++;
            }
            
            // Check to see if a min temperature is present
            if (!items[11].equals("")) {
                // temperature present convert to double
                minTemp = Double.parseDouble (items[11]);
                totalMinTemps += minTemp;
                numMinDays++;
            }        
        } // loop to process all temperatures
        inFile.close();
        
        // Calculate average maximum
        double maxAverage = totalMaxTemps/numMaxDays;
        
        // Calculate average minimum
        double minAverage = totalMinTemps/numMinDays;
        System.out.printf ("# of days Max Temp reported = %d%n", numMaxDays);
        System.out.printf ("The average maximum temperature is %.2f%n",maxAverage);
        System.out.printf ("# of days Min Temp reported = %d%n", numMinDays);
        System.out.printf ("The average minimum temperature is %.2f%n",minAverage);

    }
}
