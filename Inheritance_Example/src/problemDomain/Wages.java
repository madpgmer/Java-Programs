package problemDomain;
/** 
 * 
 * @author Madhu
 * @version June 10, 2021
 *
 */

public class Wages extends Employee {
	private double rate;
	private double hours;
	
	private static final double REGULAR_HOURS = 40;
	private static final double OVERTIME_RATE = 1.5;
	
	public Wages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Wages(String id, String name, String address, String phone, long sin, String dob, String dept) {
		super(id, name, address, phone, sin, dob, dept);
		// TODO Auto-generated constructor stub
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getHours() {
		return hours;
	}
	
	public double getPay() {
		if(hours <= 40)
			{
			return rate * hours;
			} else {
				return rate * REGULAR_HOURS + rate * OVERTIME_RATE * (hours - REGULAR_HOURS);
			}
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}
	public Wages(String id, String name, String address, String phone, long sin, String dob, String dept, double rate,
			double hours) {
		super(id, name, address, phone, sin, dob, dept);
		this.rate = rate;
		this.hours = hours;
	}
	@Override
	public String toString() {
		return "Wages [rate=" + rate + ", hours=" + hours + ", getId()=" + getId() + ", getPhone()=" + getPhone()
				+ ", getName()=" + getName() + ", getAddress()=" + getAddress() + ", getSin()=" + getSin()
				+ ", getDob()=" + getDob() + ", getDept()=" + getDept() + "]";
	}
	public double gePay() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
