// Customer Class to store customer details
class Customer {
    int id;
    String name;
    String email;
    
    // Constructor to create a new customer object
    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

// Binary Search Tree (BST) class for CRM
class BSTree {
    // Root of the BST
    private Node root;

    // Node class to represent each node in the BST
    private class Node {
        Customer customer;
        Node left, right;

        // Constructor to create a new node
        public Node(Customer customer) {
            this.customer = customer;
            left = right = null;
        }
    }

    // Method to insert a new customer in the BST
    public void insert(Customer customer) {
        root = insertRec(root, customer);
    }

    // Helper method to insert a customer recursively
    private Node insertRec(Node root, Customer customer) {
        if (root == null) {
            root = new Node(customer);  // Create a new node if empty
            return root;
        }

        // Compare the customer ID and navigate the tree
        if (customer.id < root.customer.id) {
            root.left = insertRec(root.left, customer);  // Insert in left subtree
        } else if (customer.id > root.customer.id) {
            root.right = insertRec(root.right, customer);  // Insert in right subtree
        }

        return root;
    }

    // Method to search for a customer by ID
    public Customer search(int id) {
        return searchRec(root, id);
    }

    // Helper method to search for a customer recursively
    private Customer searchRec(Node root, int id) {
        if (root == null || root.customer.id == id) {
            return (root == null) ? null : root.customer;  // Return customer or null if not found
        }

        // Navigate the tree based on comparison
        if (id < root.customer.id) {
            return searchRec(root.left, id);  // Search in left subtree
        } else {
            return searchRec(root.right, id);  // Search in right subtree
        }
    }

    // Method to delete a customer by ID
    public void delete(int id) {
        root = deleteRec(root, id);
    }

    // Helper method to delete a customer recursively
    private Node deleteRec(Node root, int id) {
        if (root == null) {
            return root;
        }

        // Navigate the tree to find the node to delete
        if (id < root.customer.id) {
            root.left = deleteRec(root.left, id);  // Search in left subtree
        } else if (id > root.customer.id) {
            root.right = deleteRec(root.right, id);  // Search in right subtree
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children, get the inorder successor (smallest in the right subtree)
            root.customer = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.customer.id);
        }

        return root;
    }

    // Helper method to find the minimum value (used for deleting nodes with two children)
    private Customer minValue(Node root) {
        Customer minValue = root.customer;
        while (root.left != null) {
            minValue = root.left.customer;
            root = root.left;
        }
        return minValue;
    }

    // Inorder traversal to display the customers
    public void inorder() {
        inorderRec(root);
    }

    // Helper method for inorder traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);  // Visit left subtree
            System.out.println("ID: " + root.customer.id + ", Name: " + root.customer.name + ", Email: " + root.customer.email);
            inorderRec(root.right);  // Visit right subtree
        }
    }
}

// Main class to simulate the CRM system
public class CRMSystem {
    public static void main(String[] args) {
        BSTree bst = new BSTree();

        // Adding customers with IDs in the order: 20, 30, 40, 50, 60, 70, 80
        bst.insert(new Customer(20, "Alice Smith", "alice@xyz.com"));
        bst.insert(new Customer(30, "Bob Johnson", "bob@xyz.com"));
        bst.insert(new Customer(40, "Charlie Brown", "charlie@xyz.com"));
        bst.insert(new Customer(50, "David Williams", "david@xyz.com"));
        bst.insert(new Customer(60, "Eva Green", "eva@xyz.com"));
        bst.insert(new Customer(70, "Frank White", "frank@xyz.com"));
        bst.insert(new Customer(80, "Grace Lee", "grace@xyz.com"));

        System.out.println("Inorder traversal of the customer tree:");
        bst.inorder();  // Display all customers

        // Searching for a customer by ID
        Customer customer = bst.search(50);
        if (customer != null) {
            System.out.println("\nCustomer found: ID: " + customer.id + ", Name: " + customer.name);
        } else {
            System.out.println("\nCustomer not found.");
        }

        // Deleting a customer by ID
        bst.delete(30);
        System.out.println("\nAfter deletion of customer with ID 30:");
        bst.inorder();  // Display all customers after deletion
    }
}
