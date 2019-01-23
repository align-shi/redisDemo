package com.xiaoshi.shop;

import java.util.ArrayList;
import java.util.List;

public class Tree {


    public static List<Node> generateNode() {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(5));
        nodes.add(new Node(3));
        nodes.add(new Node(6));
        nodes.add(new Node(2));
        nodes.add(new Node(4));
        nodes.add(new Node(3.5));
        nodes.add(new Node(4.5));
        nodes.add(new Node(7));

        nodes.add(new Node(6.5));
        nodes.add(new Node(10));
        nodes.add(new Node(6.75));
        nodes.add(new Node(6.25));
        return nodes;
    }

    public static Node generateTree() {
        List<Node> nodes = generateNode();
        Node root = nodes.get(0);
        root.left = nodes.get(1);
        root.right = nodes.get(2);
        nodes.get(1).left = nodes.get(3);
        nodes.get(1).right = nodes.get(4);
        nodes.get(2).right = nodes.get(7);
        nodes.get(4).left = nodes.get(5);
        nodes.get(4).right = nodes.get(6);
        nodes.get(7).left = nodes.get(8);
        nodes.get(7).right = nodes.get(9);
        nodes.get(8).left = nodes.get(11);
        nodes.get(8).right = nodes.get(10);
        return root;
    }

    public static void print(Node root) {
        if (root == null) {
            return;
        } else {
            print(root.left);
            System.out.print(root.value + "  ");
            print(root.right);
        }
    }

    public static void deleteNode(Node root, int value) {
        Node n = findNode(root, value);

        Node temp = n.left.right;

        while (temp.left != null) {
            temp = temp.left;
        }

        temp.left = n.left.left;
        temp.right = n.left.right;
        n.left = temp;
        n.left.right.left = null;
        System.out.println();
    }

    public static Node findNode(Node node, int value) {
        if (node == null) {
            return null;
        } else if ((node.left != null && node.left.value == value) || (node.right != null && node.right.value == value)) {
            return node;
        } else {
            Node n = findNode(node.left, value);
            if (n == null) {
                n = findNode(node.right, value);
            }
            return n;
        }
    }

    public static void dofdf(List list){
        list.remove(0);

    }


    public static void main(String[] args) {
        Node node = generateTree();
        print(node);
        /*Node root = generateTree();
        print(root);
        System.out.println();
        deleteNode(root,7);
        //Node n = findNode(root, 4);
        //System.out.println(n.value);
        //deleteNode(root, 4);
        print(root);*/
    }
}
