/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
    
import java.util.ArrayList;

// Recursive merge sort for Employee objects.
// Sorts by last name, then first name (see Employee.getSortKey).
public class MergeSorter {

    // Returns a new sorted list. The original list is not changed.
    public static ArrayList<Employee> sort(ArrayList<Employee> list) {
        // Base case: a list with 0 or 1 items is already sorted
        if (list.size() <= 1) {
            return new ArrayList<>(list);
        }

        // Recursive case: split in half, sort each half, then merge
        int middle = list.size() / 2;
        ArrayList<Employee> left = sort(new ArrayList<>(list.subList(0, middle)));
        ArrayList<Employee> right = sort(new ArrayList<>(list.subList(middle, list.size())));
        return merge(left, right);
    }

    // Joins two sorted lists into one sorted list
    private static ArrayList<Employee> merge(ArrayList<Employee> left, ArrayList<Employee> right) {
        ArrayList<Employee> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        // Take the smaller front item each time
        while (leftIndex < left.size() && rightIndex < right.size()) {
            String leftKey = left.get(leftIndex).getSortKey();
            String rightKey = right.get(rightIndex).getSortKey();

            if (leftKey.compareTo(rightKey) <= 0) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // One list ran out, so copy whatever is left in the other
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }
        return merged;
    }
}
    
