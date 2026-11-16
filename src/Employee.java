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

}
