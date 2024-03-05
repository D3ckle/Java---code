
public class ARM extends Employee { // assistant regional manager

	public ARM(String name, boolean active, int yearsOfExp) {// constructor
		super(name, active, yearsOfExp);
	}

	// All the subclasses should have getSalary methods which are calculated as
	// follows

	public double getSalary() {
		if (super.getActive())
			return super.getSalary() * Math.pow(1.05, super.getYearsOfExp());//	- ARM: employee salary*(1.05)^(yearsOfExperience)
		//	terminate(): Certain employees which are terminated, are eligible for severance pay which is calculated as
//	follows
//	- ARM is 3 months pay
		return super.getSalary() * Math.pow(1.05, super.getYearsOfExp()) * .25; // 3 months pay of a salary (12 months)
																				// so 1/4 of salary
	}

//	- The ARM is rebuilt as an Employee (hint create a static method “demote” ARM class)
	public static Employee demote(Employee e) {
		totalEmp--; // no chang to count variabes, but constructors add anyways, so subtract here
		if (e.active)
		activeEmp--;
		return new Employee(e.name, e.active, e.yearsOfExp);
	}

	public String toString() {//	The severance pay should be reflected in the toString, if they’re terminated and the salary
//	should be reflected if they’re active.
		String temp = super.toString();
		return temp.substring(0,7) + "ARM" + temp.substring(15);
	}
}
