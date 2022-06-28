
/**
 * Point of Sale 
 * This program will load a product file into an array list and then act as a
 * point of sale system, updating counts, and producing an inventory report to a file.
 * The input file is | delimited. So to view it in excel you have to changed the region
 * delimited through the control panel to a |
 * 
 * @author Madhu Madhavan
 * @version April 2021
 */
// Standard import for the Scanner class
import java.util.*;
import java.io.*;
public class PointOfSaleTemp {
    private static int transactionNum = 0;
    public static double GST_RATE = 0.05;
    public static void main (String [] args)  throws IOException {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);
        // create an arraylist to store all of the products
        ArrayList<ProductInventory> p = new ArrayList<>();
        final String filename = "c:/temp/UPC datafile.csv";
        // Load all the products
        loadProducts (p, filename);
        int selection;
        selection = displayMenu(in);
        String s;
        while (selection != 9) {
            switch (selection) {
                case 1:
                // Handle point of sale
                pointOfSale(p, in);
                break;
                case 2:
                // produce inventory report
                System.out.printf ("Enter filename for report: ");
                String reportFilename = in.nextLine();
                generateInventoryReport (p, reportFilename);
                System.out.printf ("Report completed and stored in %s%n", reportFilename);
                break;
            }
            selection = displayMenu(in);
        }
    }

    /** pointOfSale - we cause the system to act as a point of sale system
     *  
     * The point of sale system will also display the product description as each product is scanned.
     * For the products that accept a quantity the quantity will be input. From those products
     * that expect a weight the weight will be input. The GST will be charged on the taxable items. The 
     * numInStock (inventory levels) will be adjusted based on each sale (if there is sufficient inventory).
     * Once the clerk is done entered local UPC codes a 
     * transaction summary will be output to the screen. Print a  \f to clear the screen in BlueJ.
     * @param p - input arraylist of products
     * @param in - Scanner object attached to the keyboard
     */
    public static void pointOfSale (ArrayList<ProductInventory> p, Scanner in) {
        String localCode = "";
        Double grandTotal = 0.0; Double gstTotal = 0.0;

        transactionNum++;
        System.out.println("Point of Sale: Transaction #" + transactionNum);
        System.out.print("Local Product Code: (0 to complete order): ");
        localCode = in.next();

        while (!localCode.equals("0")) {
            boolean validLocalCode = false;
            for (ProductInventory prod: p) {
                if (prod.getLocalUPC().equalsIgnoreCase(localCode)) {
                    validLocalCode = true;

                    System.out.println("Title: " + prod.getTitle());
                    System.out.println("Description: " + prod.getDescription());
                    System.out.println("Price $ " + prod.getPrice());

                    if (prod.byWeight()) {
                        System.out.print("Enter the weight: ");
                    }
                    else {
                        System.out.print("Enter the quantity: ");
                    }
                    Double quantity = in.nextDouble();

                    if (prod.sellProduct(quantity)) {
                        Double prodTotal = prod.getPrice() * quantity; 
                        Double gst = 0.0;
                        System.out.printf("%s%.2f%n","Product Total : $", prodTotal);
                        if (prod.getIsTaxable()) {
                            gst = prodTotal * GST_RATE;
                            gstTotal += gst;
                        }
                        System.out.printf("%s%.2f%n","GST : ", gst);

                        grandTotal += prodTotal;
                        prodTotal += gst;

                        System.out.printf("%s%.2f%n","Total : $", prodTotal);
                    }
                    else {
                        System.out.printf ("Insufficient inventory to complete sale!%n");
                    }

                    System.out.printf ("Press Enter to Continue ");
                    in.nextLine();
                    in.nextLine();

                    System.out.print("\f"); 
                }
            }
            if (!validLocalCode) {
                System.out.println("Product not found");
                System.out.printf ("Press Enter to Continue ");
                in.nextLine();
                in.nextLine();

                System.out.print("\f"); 
            }

            System.out.printf ("Local Product Code: (0 to complete order) ");
            localCode = in.next();
        } //clear screen at 0
        System.out.print("\f"); 
        System.out.println("Transaction totals");
        System.out.println("==================");
        System.out.println("Transaction #" + transactionNum);
        String gTotal = String.format("$%6.2f", grandTotal);
        System.out.printf("%13s%12s%n", "Product Total", gTotal);
        System.out.printf("%13s%12s%n", "GST", String.format("$%6.2f", gstTotal));
        System.out.printf("%13s%12s%n", "Total", String.format("$%6.2f", grandTotal + gstTotal));

        System.out.printf ("Press Enter to continue.");
        in.nextLine();
        in.nextLine();
        System.out.print("\f");         

    }

    /** loadProducts
     * 
     * This method populates an arraylist of products from a CSV filename.
     * It uses "|" as the delimiter. After the data for a product is read from the data file a Product
     * object is constructed. Uses a | as the delimited requires some extra attention as | is used for 
     * regular expressions so in order to split using | you have to escape it with \\
     * This Product object is then added to the product arraylist called 'plist'.
     * @param plist ArrayList of Products, initially empty
     * @param filename the filename as a .csv datafile containing the product data.  
     */
    public static void loadProducts (ArrayList<ProductInventory> plist, String filename) throws IOException {
        // Open the datafile
        File f = new File (filename);
        if (!f.exists() ){
            System.out.printf ("Can't find file %s%n", filename);
            System.exit (1);
        }
        Scanner inFile = new Scanner(f);

        // Read and discard header line
        inFile.nextLine();

        // used to input a whole line of data
        String data = "";
        String [] items = null;

        // while more data
        while (inFile.hasNextLine())  {
            // read entire line
            data = inFile.nextLine();            
            // Split line into component parts
            items = data.split("\\|");

            ProductInventory p = new ProductInventory (items[0], items[1], items[2], Double.parseDouble(items[3]), Double.parseDouble(items[4]),
                    (items[5].equals("1") ? true: false), (items[6].equals("1") ? true: false), items[7]);
            plist.add(p);
        }
        inFile.close();
    }

    public static int displayMenu(Scanner k) {
        System.out.print ("\f");
        System.out.printf ("Point of Sale System%n%n");
        System.out.printf ("1. Enter Customer Sale%n");
        System.out.printf ("2. Print Inventory Report%n");
        System.out.printf ("9. Exit%n");
        System.out.printf ("%nEnter your selection: ");
        int selection = k.nextInt();
        k.nextLine(); // discard \n
        System.out.print ("\f");
        return selection;
        
    }

    /** generateInventoryReport - generates an inventory report based on the products
     * The inventory report will include all produces, price, quantity and the retail value of the product.
     * The report is a text file output to the 'reportFilename' input. A total value of all products
     * is also calculated an output.
     * @param p - arraylist of Products
     * @param reportFileName - filename for the report
     */
    public static void generateInventoryReport (ArrayList<ProductInventory>p, String reportFilename) throws IOException {
        File file = new File(reportFilename);
        // Create a file
        PrintWriter output = new PrintWriter(file);

        // Write formatted output to the file
        Date date = new Date();
        output.println("Inventory Report at: " + date.toString());
        output.println(); 
        output.printf("%10s%16s%2s%-40.40s%8s%8s%12s%n","Local UPC","UPC"," ","Title", "Price", "Qty", "Value");
        double count = 0;
        double value = 0;
        for (ProductInventory prod : p) {
           value = prod.getPrice() * prod.getNumInStock();
           count += value;          
           output.printf("%10s%16s%2s%-40.40s%8s%8.1f%12s%n", prod.getLocalUPC(), prod.getUPC(), " ", prod.getTitle(), 
                        String.format("$%5.2f", prod.getPrice()), prod.getNumInStock(),String.format("$%5.2f", value) );
           
        }
        output.println();
        output.printf("%96s%n", String.format("$%5.2f", count));
        
        // Close the file
        output.close();

    }
}
