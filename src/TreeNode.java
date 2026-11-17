/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
// One box in the tree. It stores a name, designation and department,
// and can have at most two children (left and right).
public class TreeNode {
    private String name;
    private String designation;
    private String department;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Employee employee) {
        this.name = employee.getFullName();
        this.department = employee.getDepartment().getName();

        if (employee.getManager() == null) {
            this.designation = "Staff (" + employee.getEmploymentType() + ")";
        } else {
            this.designation = employee.getManager().getTitle();
        }
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String toString() {
        return name + " | " + designation + " | " + department;
    }
}