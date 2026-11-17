/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
public abstract class Employee {
    
private String firstName;
    private String lastName;
    private Department department;
    private Manager manager;   // stays null if the person is not a manager

    public Employee(String firstName, String lastName, Department department, Manager manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.manager = manager;
    }
    
     public String getFirstName() {
        return firstName;
    }

     public String getLastName() {
        return lastName; }
     
    
      public String getFullName() {
        return firstName + " " + lastName;
    }
      
      public Department getDepartment() {
        return department;
    }
      
      public Manager getManager() {
        return manager;
    }
      
      public String getManagerTitle() {
        if (manager == null) {
            return "Employee (not a manager)";
        }
        return manager.getTitle();
    }
      
      public String getSortKey() {
        return (lastName + " " + firstName).toLowerCase();
    }
      
          // Each employee type says what kind of contract it has
    public abstract String getEmploymentType();
    
    // Builds the right employee type from the Position column in the file
    public static Employee createEmployee(String firstName, String lastName, String position,
                                          Department department, Manager manager) {
        {
        String text = position.trim().toLowerCase();
        if (text.equals("intern")) {
            return new InternEmployee(firstName, lastName, department, manager);
        } else if (text.equals("contract")) {
            return new ContractEmployee(firstName, lastName, department, manager);
        }
        return new FullTimeEmployee(firstName, lastName, department, manager);
    }
    }
    public String toString() {
        return getFullName() + " | " + getManagerTitle() + " | "
                + department.getName() + " | " + getEmploymentType();
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String firstName, String lastName, Department department, Manager manager) {
        super(firstName, lastName, department, manager);
    }

    public String getEmploymentType() {
        return "Full-time";
    }
}

class ContractEmployee extends Employee {
    public ContractEmployee(String firstName, String lastName, Department department, Manager manager) {
        super(firstName, lastName, department, manager);
    }

    public String getEmploymentType() {
        return "Contract";
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String firstName, String lastName, Department department, Manager manager) {
        super(firstName, lastName, department, manager);
    }

    public String getEmploymentType() {
        return "Intern";
    }
}
    


