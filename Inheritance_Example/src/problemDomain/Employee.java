package problemDomain;

/** 
 * Class description for the Employee object
 * @author Madhu
 * @version June 1, 2021
 *
 */
		

public class Employee {
	
	private String id;
	private String name;
	private String address;
	private String phone;
	private long sin;
	private String dob;
	private String dept;
	
	/**
	 * Creates an employee object with default value
	 */
	
	public Employee() {
		super();
		
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Creates an employee object with provided values
	 * 
	 * @param id The employee id
	 * @param name The employee name
	 * @param address The employee address
	 * @param phone The phone
	 * @param sin
	 * @param dob
	 * @param dept
	 */

	public Employee(String id, String name, String address, String phone, long sin, String dob, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.sin = sin;
		this.dob = dob;
		this.dept = dept;
	}
	
	/**
	 * 
	 * @return the employee phone
	 */

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getSin() {
		return sin;
	}

	public void setSin(long sin) {
		this.sin = sin;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", sin=" + sin
				+ ", dob=" + dob + ", dept=" + dept + "]";
	}
	
	

}
