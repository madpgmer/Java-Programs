/**

@Author: Madhu Madhavan

**/

package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import problemDomain.Employee;
import problemDomain.PartTime;
import problemDomain.Salaried;
import problemDomain.Wages;

public class Manager {
	
	private ArrayList<Employee> employees = new ArrayList<>();
	private static final String FILE_PATH = "res/employees.txt";
		
	public Manager() throws IOException {
		loadEmployeesFromFile();
		System.out.println("The average pay for all the employee is " + averagePay());
		System.out.println("The Wages employee with the highest pay is: " + highestPayWagesEmployee() );
		System.out.println("The Salaried employee with the lowest pay is: " + lowestPaySalariedEmployee() );
		System.out.println("Percentage of Salaried employees is: " + percentageOfSalaried() + "%");
		System.out.println("Percentage of Wages employees is: " + percentageOfWages() + "%");
		System.out.println("Percentage of Part Time employees is: " + percentageOfPartTime() + "%");

		}
	
	private double percentageOfPartTime() {
		return 0;
		// TODO Auto-generated method stub
		
	}

	private double percentageOfWages() {
		return 0;
		// TODO Auto-generated method stub
		
	}

	private double percentageOfSalaried() {
		int count = 0;
		for (Employee emp: employees) {
			if (emp instanceof Salaried) {
				count++;
			}
		}
		return count / employees.size() * 100;
	}

	private String lowestPaySalariedEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	private Wages highestPayWagesEmployee() {
		double highestPay = 0;
		Wages highestPayEmp = null;
		
		for (int i = 0; i< employees.size(); i++) {
			Employee e = employees.get(i);
			if (e instanceof Wages) {
				Wages wageEmp = (Wages)e;
				if (wageEmp.getPay() > highestPay) {
					highestPay = wageEmp.gePay();
					highestPayEmp = wageEmp;
				}
			}
		}
		return highestPayEmp;
	}

	private double averagePay() {
		double totalPay = 0;
		for (Employee e: employees) {
			if(e instanceof Salaried) {
				totalPay += ((Salaried) e).getPay();
				
			} else if (e instanceof Wages) {
				totalPay += ((Wages) e).getPay();
				
			}else if (e instanceof PartTime) {
				totalPay += ((PartTime) e).getPay();
				
			}
		}
		return totalPay;
	}
	
	private void loadEmployeesFromFile() throws IOException {
		Scanner in = new Scanner(new File(FILE_PATH));
	

	//read the file and split
	
			
		while (in.hasNext()) {
			String line = in.nextLine();
			String [] fields = line.split(":");
			char firstChar = fields[0].charAt(0);
			
				
			switch(firstChar) {
			
			case 0 :
			case 1 :
			case 2 :
			case 3 :
			case 4:
				
				
						 
			 employees.add(new Salaried(fields[0],fields[1],fields[2],fields[3], Long.parseLong(fields[4]), fields[5], fields[6], Double.parseDouble(fields[7])));
			 
			 case 5:
			 case 6:
			 case 7: 
				 employees.add(new Wages (fields[0],fields[1],fields[2],fields[3], Long.parseLong(fields[4]), fields[5],fields[6], Double.parseDouble(fields[7]), Double.parseDouble(fields[7])));
			}
		}
	}

}
