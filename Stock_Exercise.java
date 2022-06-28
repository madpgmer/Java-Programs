/**
@Author: Madhu Madhavan
**/
public class Stock_Exercise {
	
	public static void main (String[] args) {
		Stock stock = new Stock("ORCL", "Oracle Corporation");
		stock.getSymbol();
		stock.getName();
		stock.setPreviousClosingPrice (34.5);
		stock.setCurrentPrice (34.35);
		stock.getChangePercent();
		System.out.println(stock.toString());
	}
		
}
class Stock {

	private String symbol;
	private String name;
	private double previousClosingPrice;
	private double currentPrice;
	private double percentChange;
	
	public Stock() {
		
	}
	
	public Stock (String symbol, String name) {
		
	}
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPreviousClosingPrice() {
		return previousClosingPrice;
	}

	public void setPreviousClosingPrice(double previousClosingPrice) {
		this.previousClosingPrice = previousClosingPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public double getChangePercent() {
		return percentChange = ((currentPrice - previousClosingPrice) / previousClosingPrice )* 100;
		
	}
	
	public String toString() {
		String output = (this.symbol + " " + this.name + " : The price change percentage is " + percentChange);
		return output;
	}
	
}