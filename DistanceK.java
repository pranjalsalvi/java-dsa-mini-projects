// Class to represent a node in the binary tree
class Node {
    int data;      // Value of the node
    Node left;     // Left child
    Node right;    // Right child

    // Constructor to initialize node
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class DistanceK {

    // Recursive function to print all nodes at distance k from root
    public static void printNodesAtK(Node root, int k) {

        // Base case: if tree is empty, do nothing
        if (root == null) {
            return;
        }

        // If k becomes 0, we are at required distance → print node
        if (k == 0) {
            System.out.print(root.data + " ");
            return;
        }

        // Recursive call for left subtree with reduced distance
        printNodesAtK(root.left, k - 1);

        // Recursive call for right subtree with reduced distance
        printNodesAtK(root.right, k - 1);
    }

    public static void main(String[] args) {

        /*
                Example Binary Tree:
                        1
                       / \
                      2   3
                     / \
                    4   5

            Nodes at distance k = 2 → Output: 4 5
        */

        // Creating tree manually
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int k = 2;

        // Print result
        System.out.print("Nodes at distance " + k + ": ");
        printNodesAtK(root, k);
    }
}