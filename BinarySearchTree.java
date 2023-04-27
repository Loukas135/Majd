import java.util.ArrayList;

class BinarySearchTree {
    
    class Node {
        int key;
        Node left, right;
        Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int item) {
        Node curr;
        Node prev = null;
        Node newNode = new Node(item);
        if (root == null) {
            root = newNode;
            return;
        }
        curr = root;
        while (curr != null) {
            prev = curr;
            if (item <= curr.key) {
                curr = curr.left;
            } else if (item > curr.key) {
                curr = curr.right;
            }
        }
        if (item <= prev.key) {
            prev.left = newNode;
        } else if (item > prev.key) {
            prev.right = newNode;
        }
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }
    
    boolean isBST(){
        ArrayList<Integer> lst = new ArrayList<>();
        insertingListByInorder(lst, root);
        for(int i = 0; i < lst.size() - 1; i++){
            if(lst.get(i) > lst.get(i + 1))
                return false;
        }
        return true;
    }

    void insertingListByInorder(ArrayList<Integer> lst,  Node root){
        if (root != null) {
            insertingListByInorder(lst, root.left);
            lst.add(root.key);
            insertingListByInorder(lst, root.right);
        }
    }

    boolean search(int item) {
        if (root == null)
            return false;
        Node curr;
        curr = root;
        while (curr != null) {
            if (item == curr.key) {
                return true;
            } else if (item < curr.key) {
                curr = curr.left;
            } else if (item > curr.key) {
                curr = curr.right;
            }
        }
        return false;
    }

    void max() {
        System.out.println(maxValue(root));
    }

    int maxValue(Node root) {
        Node curr;
        if (root.right == null) {
            return root.key;
        }
        curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.key;
    }

    int minValue(Node root) {
        Node curr;
        if (root.left == null) {
            return root.key;
        }
        curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.key;
    }

    void delete(int item) {
        deleteRec(root, item);
    }

    Node deleteRec(Node root, int item) {
        if (root == null)
            return root;
        if (item < root.key) {
            root.left = deleteRec(root.left, item);
        } else if (item > root.key) {
            root.right = deleteRec(root.right, item);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = maxValue(root.left);
            root.left = deleteRec(root.left, root.key);
        }
        return root;
    }

    int count = 10;

    void print(){
        print(root, 0);
    }

    void print(Node root, int space){
        if(root == null) return;
        space += count;
        print(root.right, space);
        System.out.print("\n");
        for(int i = count; i < space; i++){
            System.out.print(" ");
        }
        System.out.print(root.key);
        print(root.left, space);
    }

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(9);
        bst.insert(2);
        bst.insert(4);
        bst.insert(7);
        bst.insert(11);
        bst.insert(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(5);
        bst.print(); 
        
    }

}