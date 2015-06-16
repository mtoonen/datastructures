/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.meine.datastructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author meine
 */
public class LoadTester {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f = new File("/usr/share/dict/dutch");

        List<String> words = new ArrayList<>();
        List<Integer> hashcodes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String word = "";

        while ((word = br.readLine()) != null) {
            words.add(word);
            hashcodes.add(word.hashCode());
        }
        System.out.println("Words:" + words.size());
        for (int i = 0; i < 100; i++) {
            
        testHashmap(words, hashcodes);
        testBSP(words, hashcodes);
            
        }
    }
    
    public static void testBSP(List<String> words, List<Integer> hashcodes){
                System.out.println("***********************");
        System.out.println("Start test BSP:");
        BinarySearchTree<String> bsp = new BinarySearchTree<>();
        System.out.println("Reading:");
        Date start = new Date();
        
        for (String w : words) {
            bsp.add(w.hashCode(), w);
        }
        Date end = new Date();
        System.out.println("Reading done in: " + (end.getTime() - start.getTime()));
        System.out.println("Random reads:");
        int max = words.size() -1;
        long total = 0;
        for (int i = 0; i < 1000; i++) {
            int index = randomWithRange(0, max);
            int key = hashcodes.get(index);
            start = new Date();
            String w = bsp.get(key);
            end = new Date();
            total += (end.getTime() - start.getTime());
        }
        
        double avg = (double)total / (double)max;
        System.out.println("Random reads finished. Avg time" + avg);
    }
    
    public static void testHashmap(List<String> words, List<Integer> hashcodes){
                System.out.println("***********************");
        System.out.println("Start test HashMap:");
        HashMap<Integer,String> bsp = new HashMap<>();
        System.out.println("Reading:");
        Date start = new Date();
        
        for (String w : words) {
            bsp.put(w.hashCode(), w);
        }
        Date end = new Date();
        System.out.println("Reading done in: " + (end.getTime() - start.getTime()));
        System.out.println("Random reads:");
        int max = words.size() -1;
        long total = 0;
        for (int i = 0; i < 1000; i++) {
            int index = randomWithRange(0, max);
            int key = hashcodes.get(index);
            start = new Date();
            String w = bsp.get(key);
            end = new Date();
            total += (end.getTime() - start.getTime());
        }
        
        double avg = (double)total / (double)max;
        System.out.println("Random reads finished. Avg time" + avg);
    }

    static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
