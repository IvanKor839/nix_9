package ua.com.alevel.secondlevel;

import ua.com.alevel.ProgramRun;
import ua.com.alevel.TaskHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class BinaryTree implements TaskHelper {

    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int x) {
            this.value = x;
        }
    }

    private Node rootNode;

    public BinaryTree() {
        rootNode = null;
    }


    public void add(int value) {
        Node newNode = new Node(value);
        if (rootNode == null) {
            rootNode = newNode;
        } else {
            Node currentNode = rootNode;
            Node parentNode;
            while (true) {
                parentNode = currentNode;
                if (value == currentNode.value) {
                    return;
                } else if (value < currentNode.value) {
                    currentNode = currentNode.left;
                    if (currentNode == null) {
                        parentNode.left = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.right;
                    if (currentNode == null) {
                        parentNode.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public static int maxDeep(Node root) {
        if (root != null)
            return 1 + Math.max(maxDeep(root.left), maxDeep(root.right));
        else
            return 0;
    }

    @Override
    public void run(BufferedReader bufferedReader) throws IOException {
        BinaryTree binaryTree = new BinaryTree();
        String menu = "";
        do {
            try {
                System.out.println("Enter binaryTree root value : ");
                rootNode = new Node(Integer.parseInt(bufferedReader.readLine()));
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Try again.");
            }
        } while (true);
        do {
            System.out.println("1. Insert new node.");
            System.out.println("2. Find max deep.");
            System.out.println("3. Exit to main menu.");
            menu = bufferedReader.readLine();
            switch (menu) {
                case "1": {
                    do {
                        try {
                            int value;
                            System.out.print("Node value:");
                            value = Integer.parseInt(bufferedReader.readLine());
                            add(value);
                            System.out.println();
                            break;
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid input. Try again.");
                        }
                    } while (true);
                    break;
                }
                case "2": {
                    System.out.println("Max deep: " + maxDeep(rootNode));
                    break;
                }
            }
        } while (!menu.equals("3"));
        ProgramRun.preview();
    }
}
