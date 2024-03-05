
public class Employee {
	// The Employee class will have the instance variables of
	protected String name; // String name
	protected boolean active;// Boolean active (yes = true)
	protected int yearsOfExp;// int yearsOfExp

	// you will also need to create a Static Variable called activeEmp that keeps
	// track of how many ACTIVE
	protected static int activeEmp = 0;

	public static void pActive() {
		System.out.println(activeEmp);
	}

	public static void pTotal() {
		System.out.println(totalEmp);
	}

	// Employees (including all the sub classes) have been hired and totalEmp that
	// keeps track TOTAL employees that have ever existed.
	protected static int totalEmp = 0;

	public Employee() {// It should have a default constructor and The default constructor should set
						// the name to “Dwight Schrute”, active to false and Years of Experience to 0.
		name = "Dwight Schrute";
		active = false;
		yearsOfExp = 0;
		totalEmp++;
	}

	public Employee(String name, boolean active, int yearsOfExp) { // a constructor for all 3 parameters.
		this.name = name;
		this.active = active;
		this.yearsOfExp = yearsOfExp;
		totalEmp++;
		if (active)
			activeEmp++;
	}

	// protected getters and setters; children classes can access but other than
	// that, it works as private
	// On top of the standard getters, setters and toString,
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected boolean getActive() {
		return active;
	}

	protected void setActive(boolean active) {
		this.active = active;
	}

	protected int getYearsOfExp() {
		return yearsOfExp;
	}

	protected void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	// All the subclasses should have getSalary methods which are calculated as
	// follows
//	- Employee $50,000 (average salary, does not need to be a variable)
	public double getSalary() {
		if (active)
			return 50000;
		return 0; // no severance fee for basic employees
	}

//	The severance pay should be reflected in the toString, if they’re terminated and the salary should be reflected if they’re active.
	public String toString() {
		if (active)
			return "Title: Employee" + "\nName: " + name + "\nactive: " + active + "\nyearsOfxp: " + yearsOfExp
					+ "\nSalary: " + getSalary();
		return "Title: Employee" + "\nName: " + name + "\nactive: " + active + "\nyearsOfxp: " + yearsOfExp
				+ "\nSeverance pay: " + getSalary();
	}

	public static Employee rehire(Employee e) { // assume that employee active == false
		totalEmp--;
		// activeEmp--;
		return new Employee(e.name, true, e.yearsOfExp);// can assume that the employee is beign rehired, and thus,
														// active
	}
}
