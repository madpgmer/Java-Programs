package problem_domain;

public class Gizmo {
	
	private String Id;
	private String name;
	private int quantity = 0;
	private double price = 0;	
	
	public Gizmo() {
		
	}
	
	public Gizmo(String Id, String name, int quantity, double price){
		
		this.Id = Id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;		
	}
	
	@Override
	public String toString() {
		return "Gizmo [Id=" + Id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}		
}
