/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
import java.util.ArrayList;

// Recursive binary search. The list must already be sorted.
public class BinarySearcher {

    // Looks for the target name between position "left" and position "right".
    // Returns the Employee if found, or null if not found.
    public static Employee search(ArrayList<Employee> list, String targetKey, int left, int right) {

        // Stop 1: nothing left to check, so the name is not in the list
        if (left > right) {
            return null;
        }

        // Look at the middle person
        int middle = (left + right) / 2;
        Employee middleEmployee = list.get(middle);
        int result = targetKey.compareTo(middleEmployee.getSortKey());

        if (result == 0) {
            // Stop 2: the middle person is the one we want
            return middleEmployee;
        } else if (result < 0) {
            // The name comes earlier in the alphabet, so search the left half
            return search(list, targetKey, left, middle - 1);
        } else {
            // The name comes later in the alphabet, so search the right half
            return search(list, targetKey, middle + 1, right);
        }
    }
}