import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Stored here so every method can use them
    private static ArrayList<Employee> employees;
    private static ArrayList<Employee> sortedList = null;
    private static ArrayList<Employee> newRecords = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the filename to read:");
        String filename = scanner.nextLine().trim();

        FileLoader loader = new FileLoader();
        try {
            employees = loader.loadFile(filename);
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
            return;
        }
        System.out.println("File read successfully");
        System.out.println(employees.size() + " records loaded, "
                + loader.getSkippedLines() + " skipped.");

        boolean running = true;
        while (running) {
            System.out.println("\nDo You wish to SORT or SEARCH:");
            MenuOption.printMenu();

            MenuOption choice = readMenuChoice(scanner);
            if (choice == null) {
                System.out.println("Invalid choice. Please enter a number from the menu.");
                continue;
            }

            switch (choice) {
                
                case SORT:
                    System.out.println("SORT selected");
                    showSortedNames();
                    break;
                    
                case SEARCH:
                    System.out.println("SEARCH selected");
                    searchEmployee(scanner);
                    break;
                    
                case ADD_RECORD:
                    System.out.println("ADD RECORDS selected");
                    addEmployee(scanner);
                    break;
                    
                case CREATE_TREE:
                    System.out.println("Create Binary Tree selected");
                    createTree();
                    break;
                    
                case EXIT:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    // Reads what the user types and turns it into a menu choice.
    // Returns null if the input is not a valid menu number.
    private static MenuOption readMenuChoice(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            int number = Integer.parseInt(input);
            return MenuOption.fromNumber(number);
        } catch (NumberFormatException e) {
            return null;   // the user typed text, not a number
        }
    }

    // Sorts the list with merge sort and prints the first 20 names
    private static void showSortedNames() {
        sortedList = MergeSorter.sort(employees);
        System.out.println("First 20 names:");
        for (int i = 0; i < 20 && i < sortedList.size(); i++) {
            System.out.println((i + 1) + ". " + sortedList.get(i).getFullName());
        }
    }

        // Keeps asking until the user types something (validation)
    private static String readNonEmpty(Scanner scanner, String question) {
        String text = "";
        while (text.isEmpty()) {
            System.out.println(question);
            text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("This cannot be empty. Please try again.");
            }
        }
        return text;
    }

    // Asks for a name, searches the sorted list, and shows the result
    private static void searchEmployee(Scanner scanner) {
        // Binary search only works on a sorted list, so sort first if needed
        if (sortedList == null) {
            sortedList = MergeSorter.sort(employees);
        }

        String firstName = readNonEmpty(scanner, "Enter the first name:");
        String lastName = readNonEmpty(scanner, "Enter the last name:");

        // Same format as Employee.getSortKey(): last name, space, first name, lowercase
        String targetKey = (lastName + " " + firstName).toLowerCase();

        Employee found = BinarySearcher.search(sortedList, targetKey, 0, sortedList.size() - 1);

        if (found == null) {
            System.out.println(firstName + " " + lastName + " was not found.");
        } else {
            System.out.println("Found: " + found.getFullName());
            System.out.println("Manager Type: " + found.getManagerTitle());
            System.out.println("Department: " + found.getDepartment().getName());
        }
 
   }
    // Keeps asking until the user types a whole number between min and max
    private static int readNumberInRange(Scanner scanner, int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // the user typed text, so fall through to the error message
            }
            System.out.println("Invalid choice. Enter a number from " + min + " to " + max + ":");
        }
    }

    // Asks for a name, manager type and department, then adds the new employee
    private static void addEmployee(Scanner scanner) {
        String firstName = readNonEmpty(scanner, "Please input the first name:");
        String lastName = readNonEmpty(scanner, "Please input the last name:");

        // Manager type: the user can only pick a number that exists
        System.out.println("Please select from the following Management Staff:");
        ManagerType.printOptions();
        int managerNumber = readNumberInRange(scanner, 1, ManagerType.values().length);
        ManagerType managerType = ManagerType.fromNumber(managerNumber);

        // Department: the user can only pick a number from the list of valid departments
        System.out.println("Please select the Department:");
        ArrayList<String> departmentNames = Department.getAllNames();
        for (int i = 0; i < departmentNames.size(); i++) {
            System.out.println((i + 1) + ". " + departmentNames.get(i));
        }
        int departmentNumber = readNumberInRange(scanner, 1, departmentNames.size());
        Department department = Department.createDepartment(departmentNames.get(departmentNumber - 1));

        // Build the employee and store it in memory
        Manager manager = Manager.createManager(managerType);
        Employee newEmployee = Employee.createEmployee(firstName, lastName, "full-time", department, manager);
        employees.add(newEmployee);
        newRecords.add(newEmployee);

        // Re-sort so that binary search still works on the updated list
        sortedList = MergeSorter.sort(employees);

        System.out.println("\"" + newEmployee.getFullName() + "\" has been added as \""
                + manager.getTitle() + "\" to \"" + department.getName() + "\" successfully!");

        System.out.println("\nAll newly added records:");
        for (Employee record : newRecords) {
            System.out.println(record);
        }
        
        
    }
    
        // Builds the hierarchy tree from all employees and shows it
    private static void createTree() {
        if (employees.size() < 20) {
            System.out.println("At least 20 records are needed to build the tree.");
            return;
        }

        sortedList = MergeSorter.sort(employees);

        HierarchyTree tree = new HierarchyTree();
        tree.build(sortedList);

        System.out.println("Employee hierarchy (level-order):");
        tree.printLevelOrder();
        System.out.println("Tree height: " + tree.getHeight());
        System.out.println("Total nodes: " + tree.countNodes());
    }




}