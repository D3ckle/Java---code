
public class AtoRM extends Employee { // assisstant to the regional manager
	private double hourlyRate;// The AtoRM class will have an additional instance variable of hourlyRate.

	public AtoRM(String name, boolean active, int yearsOfExp, double hourlyRate) {
		super(name, active, yearsOfExp);
		this.hourlyRate = hourlyRate;
	}

	public double getSalary() { // All the subclasses should have getSalary methods which are calculated as
		// follows
		if (super.getActive())
			return 40 * hourlyRate * 52;// - AtoRM: 40*hourlyRate*52
		//	terminate(): Certain employees which are terminated, are eligible for severance pay which is calculated as
//	follows
//	- AtoRM is 3 months pay, assuming 40hrs a week and 4.5 weeks in a month
		return 40 * hourlyRate * 52 * .25; // 3 months pay of a salary (12 months)
											// so 1/4 of salary
	}


//	- The Employee is “rebuilt” as an AtoRM. (hint create a static method “promote” in the
//	AtoRM class)
	public static AtoRM promote(Employee e) {
		if(e.active) {
		activeEmp--;
		}
		totalEmp--; // no chang to count variabes since no employee is technicnally created, but constructors add anyways, so subtract here
		
		return new AtoRM(e.name, e.active, e.yearsOfExp, 24); // 24 rate is roughly equal in salary to what a normal employee has
	}

	public String toString() {//	The severance pay should be reflected in the toString, if they’re terminated and the salary
//	should be reflected if they’re active.
		String temp = super.toString();
		return temp.substring(0, 7) + "AtoRM" + temp.substring(15);
	}

}
