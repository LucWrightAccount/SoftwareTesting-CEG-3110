package diceroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



//Version 1
//9-15-2025 4:22pm
public class DiceRandomnessChecker {
    List<Integer> arr;
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> consectiveRolls = new HashMap<>();


    //Passes An array though Random Checker to be used with methods
    public DiceRandomnessChecker(List<Integer> arr){
        this.arr = arr;
        counter();
        chiSqrTest();

    }
    

    //Chi-Sqr Test
    public double chiSqrTest(){
        int n = arr.size();
        double expected = (double) n/6.0; // expected rolls
        double chiSquare =0.0;

        for(int i =0; i <=6; i ++){
          int observed = map.get(i);
          chiSquare+=Math.pow(observed - expected, 2)/expected;


        }
        return chiSquare;


    }

    //Uniform Distribution Test
    public void uniDisTest(){

    }

    //Creates a hash map of to determin frequency

    //TODO: figure out consecutive rolls
    public void counter(){
      int previous =0;
      int countOcc =0;

       for(int num: arr){
        
        while(previous == num){
          countOcc++;

        }
        consectiveRolls.getOrDefault(previous, )
        countOcc = 0;

         map.put(num, map.getOrDefault(num, 0) + 1);
         int previous = arr.get(num);
       }


    }

    
    public void printRandom(){

    

    }


    
}
