/**

@Author: Madhu Madhavan

**/
public class ProductInventory {
	private String title;
	private String description;
	private double price = 0;
	private String localUPC;
	private String UPC;
	private double numInStock = 0;
	private boolean byWeight;
	private boolean isTaxable;
	
	
	public ProductInventory() {
		
}
	public ProductInventory(String localUPC, String UPC, String title, double price,
			double numInStock, boolean byWeight, boolean isTaxable, String description){
		
		this.localUPC = localUPC;
		this.UPC = UPC;
		this.title = title;
		this.price = price;
		this.numInStock = numInStock;
		this.isTaxable = isTaxable;
		this.byWeight = byWeight;
		this.description = description;
		
	}
	public String getLocalUPC () {
		return localUPC;
	}
	public String getUPC() {
		return UPC;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public double getPrice() {
		return price;
	}
	public double getNumInStock() {
		return numInStock;
	}
	public boolean byWeight() {
		return byWeight;
	}
	public boolean getIsTaxable() {
		return isTaxable;
	}
	public boolean sellProduct(double checkStock) {
		if (checkStock < numInStock) {
			numInStock -= checkStock;
			return true;
		}
		else {
			return false;
		}
	}
	public String toString() {
		String output = ( localUPC + ":" + UPC + ":" + title + ":" + price + ":" + numInStock + ":" + "Taxable ? " 
				+ (isTaxable ? "Y": "N") + " By weight ? " + (byWeight ? "Y": "N") + description);
													
		return output;
	}
		
}
