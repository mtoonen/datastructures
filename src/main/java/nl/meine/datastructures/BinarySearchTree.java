/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.meine.datastructures;

/**
 *
 * @author Meine Toonen
 * @param <Type>
 */
public class BinarySearchTree <Type>{
    
    Node root;
    public BinarySearchTree(){
        
    }
    
    public Type get(int key){
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
            return current.object;
        }else{
            return null;
        }
    }
    
    public void add(int key, Type value){
        if( root == null){
            root = new Node(key,value);
        }else{
            Node current = root;
            Node newNode = new Node(key,value);
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
    
    class Node{
        Node left;
        Node right;
        int key;
        Type object;

        public Node(int key, Type object) {
            this.key = key;
            this.object = object;
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
        System.out.println(bt.toString());
        
        System.out.println("11:" + bt.get(11));
        System.out.println("12:" + bt.get(12));
        System.out.println("1" + bt.get(1));
        System.out.println("4" + bt.get(4));
    }
}
