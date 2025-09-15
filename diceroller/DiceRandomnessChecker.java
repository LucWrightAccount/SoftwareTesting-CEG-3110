package diceroller;

import java.util.ArrayList;
import java.util.HashMap;



//Version 1
//9-15-2025
public class DiceRandomnessChecker {
    ArrayList<Integer> arr;
    HashMap<Integer, Integer> map = new HashMap<>();

    //Passes An array though Random Checker to be used with methods
    public DiceRandomnessChecker(ArrayList<Integer> arr){
        this.arr = arr;
    }
    

    //Chi-Sqr Test
    public void chiSqrTest(){
        
    }

    //Uniform Distribution Test
    public void uniDisTest(){

    }

    //Creates a hash map of to determin frequency
    public HashMap<Integer,Integer> counter(){
       for(int num: arr){
         map.put(num, map.getOrDefault(num, 0) + 1);
       }
       return map;


    }

    
    public String printRandom(){
      counter();
      System.out.println(arr.size());
       return map.toString();

    }


    
}
