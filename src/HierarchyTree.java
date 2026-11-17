/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author simo
 */
import java.util.ArrayList;

// A binary tree of employees. Senior staff are placed nearer the top.
public class HierarchyTree {
    private TreeNode root;

    // Rank decides who goes nearer the top: 1 = Head Manager ... 4 = other staff
    private int getRank(Employee employee) {
        if (employee.getManager() == null) {
            return 4;
        }
        String title = employee.getManager().getTitle();
        if (title.equals("Head Manager")) {
            return 1;
        } else if (title.equals("Assistant Manager")) {
            return 2;
        }
        return 3;   // Team Lead
    }

    public void build(ArrayList<Employee> employees) {
        // Step 1: put the employees in order of rank (most senior first)
        ArrayList<Employee> ordered = new ArrayList<>();
        for (int rank = 1; rank <= 4; rank++) {
            for (Employee employee : employees) {
                if (getRank(employee) == rank) {
                    ordered.add(employee);
                }
            }
        }

        // Step 2: make one tree node for each employee
        ArrayList<TreeNode> nodes = new ArrayList<>();
        for (Employee employee : ordered) {
            nodes.add(new TreeNode(employee));
        }

        // Step 3: connect the nodes. The node at position i gets the
        // nodes at positions 2i+1 and 2i+2 as its left and right child.
        for (int i = 0; i < nodes.size(); i++) {
            int leftPosition = 2 * i + 1;
            int rightPosition = 2 * i + 2;
            if (leftPosition < nodes.size()) {
                nodes.get(i).setLeft(nodes.get(leftPosition));
            }
            if (rightPosition < nodes.size()) {
                nodes.get(i).setRight(nodes.get(rightPosition));
            }
        }

        // The first node is the top of the tree
        if (nodes.size() > 0) {
            root = nodes.get(0);
        } else {
            root = null;
        }
    }

    // Prints the tree one level at a time (level-order traversal)
    public void printLevelOrder() {
        int levels = getHeight();
        for (int level = 1; level <= levels; level++) {
            System.out.println("Level " + level + ":");
            printLevel(root, level);
        }
    }

    // Prints only the nodes that are on the wanted level, left to right
    private void printLevel(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.println("   " + node);
        } else {
            printLevel(node.getLeft(), level - 1);
            printLevel(node.getRight(), level - 1);
        }
    }

    // Height = number of levels (a tree with only a root has height 1)
    public int getHeight() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Total number of nodes in the tree
    public int countNodes() {
        return count(root);
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.getLeft()) + count(node.getRight());
    }
}