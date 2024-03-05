
public class Manager extends ARM {

	public Manager(String name, boolean active, int yearsOfExp) {
		super(name, active, yearsOfExp);
	} // manager
		// All the subclasses should have getSalary methods which are calculated as
		// follows
//	- Manager: 50000*(1.05)^(yearsOfExperience) + 1000*yearsOfExperience

	public double getSalary() {
		if (super.getActive())
			return super.getSalary() * Math.pow(1.05, super.getYearsOfExp()) + 10000 * super.getYearsOfExp();

		return super.getSalary() * Math.pow(1.05, super.getYearsOfExp()) * .25; // 3 months pay of a salary (12 months)
																				// so 1/4 of salary
	}

	public void terminate(Employee e) {// The Manager class should also have the ability to terminate Salaried and
										// Hourly but not other Managers.
		// can fire any employee so long as theyre not a manager
		if (!(e instanceof Manager)) { // should already be an employee
			if (e.getActive())
				activeEmp--; // no longer active employee, count
			e.setActive(false);

		}
	}

	public String toString() {
		String temp = super.toString();
		return temp.substring(0, 7) + "Manager" + temp.substring(10); // takes ARM toString, not employee toString
	}
}
