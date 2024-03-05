
public class Dunder {
//	You will need to develop 5 classes. The super class Employee, and sub classes ARM,
//	AtoRM, Manager, and the tester class Dunder. The Manager class is also a sub class of the ARM
//	class. 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//	You also need to create a Dunder class which does the following:
//	- Creates 4 Employees: 1, Employee 1 AtoRM, 1 ARM and 1 Manager. Stored in an array,
//	respectively.

		Employee[] Office = new Employee[4];
		Office[0] = new Employee();
		//Office[0] = new Employee("Employee", true, 1); // employee
		Office[1] = new AtoRM("AtoRM", true, 3, 25); // AtoRM
		//Office[1] = new AtoRM("AtoRM", false, 3, 25);
		Office[2] = new ARM("ARM", true, 4); // ARM
		//Office[2] = new ARM("ARM", false, 4);
		Office[3] = new Manager("Manager", true, 4); // Manager

		p(Office); // the Office at the beginning
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	- The AtoRM have their years of experience increased by 10
		System.out.println("===========================================================");
		System.out.println("The AtoRM went to an office workshop and gained 10 years of expirence in 1 day!");
		Office[1].setYearsOfExp(Office[1].getYearsOfExp()+10);
		System.out.println(Office[1]);
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	- The AtoRM employee is terminated
		System.out.println("===========================================================");
		System.out.println("The AtoRM messed up and was found stealing co-workers lunches, that is grounds for termination.");
		((Manager) Office[3]).terminate(Office[1]);
		System.out.println(Office[1]);
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	- The Employee is “rebuilt” as an AtoRM. (hint create a static method “promote” in the
//	AtoRM class)
		System.out.println("===========================================================");
		System.out.println("Someone needed to replace the ex-AtoRM, so the intern gets the job.");
		Office[0] = AtoRM.promote(Office[0]);
		System.out.println(Office[0]);
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	- The original AtoRM is rehired as an Employee
		System.out.println("===========================================================");
		System.out.println("The original AtoRM still needs a job, we pity the guy.");
		Office[1] = Employee.rehire(Office[1]);
		System.out.println(Office[1]);
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	- The ARM is rebuilt as an Employee (hint create a static method “demote” ARM class)
		System.out.println("===========================================================");
		System.out.println("looks like the ARM screwed up a business deal, demoted.");
		Office[2] = ARM.demote(Office[2]);
		System.out.println(Office[2]);
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	- The Manager terminates everyone all Employees in a loop (Not one by one)
		System.out.println("===========================================================");
		System.out.println("The Manager fired everyone!");
		for (int i = 0; i < Office.length; i++) { 
			//if (i!=3) // dont need; can't fire the manager, 
			((Manager) Office[3]).terminate(Office[i]);
			System.out.println(Office[i]);
		}
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
		//after all the changes
		System.out.println("===========================================================");
		System.out.println("End result: ");
		p(Office);
		
		System.out.print("Active: ");
		Employee.pActive();
		System.out.print("Total: ");
		Employee.pTotal();
		
//	Make sure to communicate each action along with any severance information. As much
//	code as possible needs to be reused! The super keyword should be used as much as
//	possible. The static variables can only be edited in the class itself and not the Driver.
	}

	public static void p(Employee[] Office) {
		for (int i = 0; i < Office.length; i++) { 
		System.out.println(Office[i]+"\n");
		}
	}
}
