/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.meine.datastructures;

import java.util.ArrayList;
import java.util.List;
import nl.meine.datastructures.util.BTPrinter;

/**
 *
 * @author Meine Toonen
 * @param <Type>
 */
public class BinarySearchTree <Type extends Comparable>{
    private int counter = 0;
    Node<Type> root;
    public BinarySearchTree(){
        
    }
    
    public Type get(int key){
       Node n = getNode(key);
       if(n == null){
           return null;
       }else{
           return (Type)n.object;
       }
    }

    private Node getNode(int key){
         Node<Type> current = root;
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

            List<Node> subtreevalues = values(n, null);
            subtreevalues.remove(n);
            for (Node node : subtreevalues) {
                this.add(node.key, (Type)node.object);
            }
        }
    }

    public List<Type> values(){
        List<Node> nodes = values(root, null);
        List<Type> values = new ArrayList<>(nodes.size());
        for (Node node : nodes) {
            values.add((Type)node.object);
        }
        return values;
    }


    private List<Node> values(Node n, List<Node> values){
        if(values == null){
            values = new ArrayList<>();
        }
        values.add( n);
        if (n.left != null) {
            values(n.left, values);
        }
        if (n.right != null) {
            values(n.right, values);
        }
        

        return values;

    }

    public void add(Type value){
        add(value.hashCode(), value);
    }
    
    public void add(int key, Type value){
        counter ++;
        if( root == null){
            root = new Node(key,value,null);
        }else{
            Node current = root;
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
    
    public static class Node <Type extends Comparable>{
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
        bt.add(5, "Vijf");
        bt.add(3, "Drie");
        bt.add(1,"Een");
        bt.add(4,"Vier");
        bt.add(2,"Twee");
        bt.add(8,"Acht");
        bt.add(6,"Zes");
        bt.add(11,"Elf");
        bt.add(9,"Negen");
        bt.add(10,"Tien");
        BTPrinter.printNode(bt.root);
        List<String> vals = bt.values();
        System.out.println(vals);
        
        System.out.println("11:" + bt.get(11));
        System.out.println("12:" + bt.get(12));
        System.out.println("1" + bt.get(1));
        System.out.println("4" + bt.get(4));

        bt.remove(5);
        BTPrinter.printNode(bt.root);


    }
}
