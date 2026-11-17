/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Reads Applicants_Form.txt and turns each line into an Employee
public class FileLoader {
    private int skippedLines = 0;

    public int getSkippedLines() {
        return skippedLines;
    }
    
    
    public ArrayList<Employee> loadFile(String filename) throws IOException {
        ArrayList<Employee> employees = new ArrayList<>();
        skippedLines = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        reader.readLine();   // skip the header row
String line = reader.readLine();
        while (line != null) {
            Employee employee = readLine(line);
            if (employee == null) {
                skippedLines++;
                System.out.println("Skipped bad line: " + line);
            } else {
                employees.add(employee);
            }
            line = reader.readLine();
        }

        reader.close();
        return employees;
    }

    // Turns one line of the file into an Employee. Returns null if the line is bad.
    private Employee readLine(String line) {
        if (line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",", -1);
        if (parts.length < 9) {
            return null;
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String firstName = parts[0];
        String lastName = parts[1];
        if (firstName.isEmpty() || lastName.isEmpty()) {
            return null;
        }

        Department department = Department.createDepartment(parts[5]);
        if (department == null) {
            return null;   // the department does not exist
        }

        Manager manager = Manager.createManager(ManagerType.fromJobTitle(parts[7]));
        return Employee.createEmployee(firstName, lastName, parts[6], department, manager);
    }
}
 
