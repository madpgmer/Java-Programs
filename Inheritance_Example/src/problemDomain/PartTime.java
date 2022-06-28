package problemDomain;

/** 
 * 
 * @author Madhu
 * @version June 5, 2021
 *
 */

public class PartTime extends Employee {

	private double rate;
	private double hours;

	public PartTime() {
		super();

	}

	public PartTime(String id, String name, String address, String phone, long sin, String dob, String dept,
			double rate, double hours) {
		super(id, name, address, phone, sin, dob, dept);
		this.rate = rate;
		this.hours = hours;
	}

	public PartTime(double rate, double hours) {
		super();
		this.rate = rate;
		this.hours = hours;
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
		return rate * hours;
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "PartTime [rate=" + rate + ", hours=" + hours + ", Id=" + getId() + ", Phone=" + getPhone()
				+ ", Name=" + getName() + ",Address=" + getAddress() + ", Sin=" + getSin()
				+ ", Dob=" + getDob() + ", Dept=" + getDept() + "]";
	}

}
