/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
public abstract class Department {
    
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    // Each department type says which category it belongs to
    public abstract String getCategory();

    // Lists of valid department names. Anything not in these lists does not exist.
    public static String[] operationsNames =
        {"Front Desk", "Housekeeping", "Operations", "Customer Service", "Technical Support"};
    public static String[] supportNames =
        {"HR", "Finance", "Accounting", "IT Development"};
    public static String[] commercialNames =
        {"Sales", "Marketing"};

    // Checks the name against the lists and builds the right department type.
    // Returns null if the department does not exist (validation).
    public static Department createDepartment(String name) {
        String wanted = name.trim();

        for (String valid : operationsNames) {
            if (valid.equalsIgnoreCase(wanted)) {
                return new OperationsDepartment(valid);
            }
            
        }

      for (String valid : supportNames) {
            if (valid.equalsIgnoreCase(wanted)) {
                return new SupportDepartment(valid);
            }
        }
        for (String valid : commercialNames) {
            if (valid.equalsIgnoreCase(wanted)) {
                return new CommercialDepartment(valid);
            }
        }
        return null;
    }
}
       class OperationsDepartment extends Department {
       public OperationsDepartment(String name) {
           super(name);
    }

    public String getCategory() {
        return "Operations";
    }
}
class SupportDepartment extends Department {
    public SupportDepartment(String name) {
        super(name);
    }

    public String getCategory() {
        return "Support";
    }
}

class CommercialDepartment extends Department {
    public CommercialDepartment(String name) {
        super(name);
    }

    public String getCategory() {
        return "Commercial";
    }
}