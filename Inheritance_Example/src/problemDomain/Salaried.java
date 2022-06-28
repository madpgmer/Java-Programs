package problemDomain;
/** 
 * 
 * @author Madhu
 * @version June 5, 2021
 *
 */

public class Salaried extends Employee {
	
	private double salary;

	public Salaried() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salaried(String id, String name, String address, String phone, long sin, String dob, String dept) {
		super(id, name, address, phone, sin, dob, dept);
		// TODO Auto-generated constructor stub
	}

	public Salaried(String id, String name, String address, String phone, long sin, String dob, String dept,
			double salary) {
		super(id, name, address, phone, sin, dob, dept);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public double getPay() {
		return salary;
	}

	@Override
	public String toString() {
		return "Salaried [salary=" + salary + ", getId()=" + getId() + ", getPhone()=" + getPhone() + ", getName()="
				+ getName() + ", getAddress()=" + getAddress() + ", getSin()=" + getSin() + ", getDob()=" + getDob()
				+ ", getDept()=" + getDept() + "]";
	}

	
	

}
