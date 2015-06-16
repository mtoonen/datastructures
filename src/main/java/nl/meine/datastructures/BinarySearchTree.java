/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.meine.datastructures;

/**
 *
 * @author Meine Toonen
 */
public class BinarySearchTree {
    
    Node root;
    public BinarySearchTree(){
        
    }
    
    public void add(int key){
        if( root == null){
            root = new Node(key);
        }else{
            Node current = root;
            Node newNode = new Node(key);
            while (true){
                if( key <= current.key ){
                    if(current.left == null){
                        current.left = newNode;
                        return;
                    }else{
                        current = current.left;
                    }
                }else{
                    if(current.right == null){
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
    
    class Node {
        Node left;
        Node right;
        int key;

        public Node(int key) {
            this.key = key;
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
        BinarySearchTree bt= new BinarySearchTree();
        bt.add(5);
        bt.add(3);
        bt.add(1);
        bt.add(4);
        bt.add(2);
        bt.add(8);
        bt.add(6);
        bt.add(11);
        bt.add(9);
        bt.add(10);
        System.out.println(bt.toString());
    }
}
