package PayrollManagementSystem;

import java.util.Scanner;


abstract class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String post;
    private double basicPay;

    public Employee(int employeeId, String name, String department, String post,double mSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.post = post;
        this.basicPay = mSalary;
    }

	

	// Encapsulation
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPost() {
        return post;
    }

    public double getBasicPay() {
        return basicPay;
    }

   
    protected abstract double calculateBasicPay();

    
}
 // Manager class inheriting from Employee
class Manager extends Employee {
    public Manager(int employeeId, String name, String department,double mSalary) {
        super(employeeId, name, department, "Manager",mSalary);
      
    }
    @Override
	protected double calculateBasicPay() {
		return 0;
	}
}

// Clerk class inheriting from Employee
class Clerk extends Employee {
    public Clerk(int employeeId, String name, String department,double cSalary) {
        super(employeeId, name, department, "Clerk",cSalary);
    }
    @Override
	protected double calculateBasicPay() {
		return 0;
	}
}

// salary calculations
class Payroll {
    public double calculateSalary(Employee employee, int numberOfDays, int leavesTaken) {
        double basicPay = employee.getBasicPay();
        double da = 0.1 * basicPay; // 10% of basic pay as DA
        double hra = 0.05 * basicPay; // 5% of basic pay as HRA
        double medicalAllowance = 5000.0;
        double arrears = 0.0;

        // Deductions
        double hostelBusCharges = 2000.0;
        double securityCharges = 1000.0;
        double welfareFund = 0.02 * basicPay; // 2% of basic pay as welfare fund

        // Calculate total salary
        double totalSalary = (basicPay + da + hra + medicalAllowance + arrears)
                - (hostelBusCharges + securityCharges + welfareFund);

        // Deduct salary for leaves taken
        double leaveDeduction = (basicPay / numberOfDays) * leavesTaken;
        totalSalary -= leaveDeduction;

        return totalSalary;
    }
}

public class PayrollManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        Manager manager = new Manager(1, "Rahim", "Finance",50000);
        Clerk clerk = new Clerk(2, "Karim", "HR",10000);
        
        System.out.println("Enter the details for Manager: ");
        System.out.println("===========================");
        System.out.println("Enter the number of days worked: ");
        int numberOfDays = scanner.nextInt();

        System.out.println("Enter the number of leaves taken: ");
        int leavesTaken = scanner.nextInt();
        System.out.println("Enter the details for Clerk: ");
        System.out.println("===========================");
        System.out.println("Enter the number of days worked: ");
        int numberOfDay = scanner.nextInt();

        System.out.println("Enter the number of leaves taken: ");
        int leaveTaken = scanner.nextInt();
        Payroll payroll = new Payroll();
        double managerSalary = payroll.calculateSalary(manager, numberOfDays, leavesTaken);
        double clerkSalary = payroll.calculateSalary(clerk, numberOfDay, leaveTaken);

        // Display Payroll Details
        System.out.println("===== Payroll Details =====");
        displayEmployeeDetails(manager);
        displaySalaryDetails(managerSalary);

        displayEmployeeDetails(clerk);
        displaySalaryDetails(clerkSalary);
    }

    private static void displayEmployeeDetails(Employee employee) {
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("Post: " + employee.getPost());
    }

    private static void displaySalaryDetails(double salary) {
        System.out.println("Total Salary:" +salary);
        System.out.println("===========================");
    }
}
