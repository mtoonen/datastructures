/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.meine.datastructures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import nl.meine.datastructures.util.BTPrinter;

/**
 *
 * @author Meine Toonen
 * @param <Type>
 */
public class BinarySearchTree <Type extends Comparable<Type>>{
    private int counter = 0;
    Node root;
    public BinarySearchTree(){
    }
    
    public Type get(int key){
       Node n = getNode(key);
       if(n == null){
           return null;
       }else{
           return n.object;
       }
    }

    private Node getNode(int key){
        Node current = root;
        boolean stop = false;
        while(!stop){
            if(current.key == key){
                stop = true;
            }else {
                if (key < current.key) {
                    if(current.left == null){
                        current = null;
                        stop = true;
                    }else{
                        current = current.left;
                    }
                }else{
                    if(current.right == null){
                        stop = true;
                        current = null;
                    }else{
                        current = current.right;
                    }
                }
            }
        }
        if(current != null){
            return current;
        }else{
            return null;
        }
    }
    
    public void remove(Type value){
        remove(value.hashCode());
    }

    public void remove(int key){
        counter--;
        Node n = getNode(key);
        if(n != null){
            Node parent = n.parent;
            if (parent != null) {
                if (parent.left == n) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }else{
                root = null;
            }

            List<Node> subtreevalues = getSubTree(n, null);
            subtreevalues.remove(n);
            for (Node node : subtreevalues) {
                this.add(node.key, node.object);
            }
        }
    }

    public List<Node> getLeftToRight(){
        List<Node> nodes = getLeftToRight(root);
        return nodes;
    }

    private List<Node> getLeftToRight(Node node){
        List<Node> nodes = new ArrayList<>();
        getLeftToRight(node, nodes);
        return nodes;
    }

    private void getLeftToRight(Node node, List<Node> nodes){
        if(node == null){
            return;
        }
        getLeftToRight(node.left, nodes);
        nodes.add(node);
        getLeftToRight(node.right, nodes);
    }

    private void balance (Node[] array){
        int mid =array.length/2;
        Node midNode = array[mid];
        this.add(midNode.key,midNode.object);
        if(array.length == 1){
            return;
        }

        Node[] small = (Node[])Array.newInstance(Node.class, mid);// (Node[])new Node[mid];
        System.arraycopy(array, 0, small, 0, mid);

        Node[] large = (Node[])Array.newInstance(Node.class, mid-1);// new Node[mid - 1];
        System.arraycopy(array, mid + 1, large, 0, mid - 1);

        balance(small);

        if(large != null && large.length > 0){
            balance(large);
        }
    }

    public void balance(){
        List<Node> list = getLeftToRight(this.root);
        this.root = null;
        Node[] array  = list.toArray((Node[])Array.newInstance(Node.class, 0)); //new Node[0]);

        balance(array);
    }

    private List<Node> getSubTree(Node n, List<Node> values){
        if(values == null){
            values = new ArrayList<>();
        }
        values.add( n);
        if (n.left != null) {
            getSubTree(n.left, values);
        }
        if (n.right != null) {
            getSubTree(n.right, values);
        }

        return values;
    }

    public void add(Type value){
        add(value.hashCode(), value);
    }

    public int size(){
        return counter;
    }
    
    public void add(int key, Type value){
        counter ++;
        if( root == null){
            root = new Node(key,value,null);
        }else{
            Node current = root;
            Type t = root.object;
            while (true){
                if( key <= current.key ){
                    if(current.left == null){
                        Node newNode = new Node(key,value, current);
                        current.left = newNode;
                        return;
                    }else{
                        current = current.left;
                    }
                }else{
                    if(current.right == null){
                        Node newNode = new Node(key,value, current);
                        current.right = newNode;
                        return;
                    }else{
                        current = current.right;
                    }
                }
            }
        }
    }
    
    @Override
    public String toString(){
        String s = root != null ? root.toString() : "";
        return s;
    }
    
    public class Node{
        public Node left;
        public Node right;
        public Node parent;
        public int key;
        public Type object;

        public Node(int key, Type object, Node parent) {
            this.key = key;
            this.object = object;
            this.parent = parent;
        }

        public Node() {
        }

        @Override
        public String toString(){
            String s = "" + key;
            if(left != null || right != null){
                s += " (";
                if(left != null){
                    s += "L:" + left.toString();
                }
                
                if( right != null){
                    if(left != null){
                        s += ", ";
                    }
                    s += "R: " + right.toString();
                    s += ")";
                }
            }
            return s;
        }
        
    }
    
    public static void main (String[] args){
        BinarySearchTree<String> bt= new BinarySearchTree<>();
        bt.add(1,"Een");
        bt.add(2,"Twee");
        bt.add(3, "Drie");
        bt.add(4,"Vier");
        bt.add(5, "Vijf");
        bt.add(6,"Zes");
        bt.add(8,"Acht");
        bt.add(9,"Negen");
        bt.add(10,"Tien");
        bt.add(11,"Elf");
       // BTPrinter.printNode(bt.root);
        //List<String> vals = bt.values();
       // System.out.println(vals);
        
        System.out.println("11:" + bt.get(11));
        System.out.println("12:" + bt.get(12));
        System.out.println("1" + bt.get(1));
       // System.out.println("4" + bt.get(4));

        //bt.remove(5);
        //bt.remove(3);
       // BTPrinter.printNode(bt.root);
        List<BinarySearchTree<String>.Node> nodes = bt.getLeftToRight();
        for (BinarySearchTree.Node node : nodes) {
            System.out.print(node.object + ", ");
        }
         System.out.println();
         System.out.println(bt);
         bt.balance();
         System.out.println(bt);
        BTPrinter.printNode(bt.root);
        
    }
}
