package diceroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Version 1
//9-15-2025
public class DiceDriver {
    public static void main(String[] args) {

        rollTest();

        // ArrayList<Integer> diceArr = new ArrayList<>();
        


        // // Scanner
        // Scanner input = new Scanner(System.in);

        // // Welcome text
        // System.out.println("Welcome to Dice roller");

        // // Dice Rolling message
        // System.out.println("Enter hom many dice you want to roll 1-5 (inclusive)>");

        // // Amount of dice rolls
        
        // int diceAmount = input.nextInt();

        // while(diceAmount < 0 || diceAmount > 6 ){
        //     outOfBounderErr();
        //     diceAmount = input.nextInt();



        // }


        // System.out.println("Enter how many times you want them to re-rell");
        // // ReRolling Amount
        // int diceRolls = input.nextInt();

        // // Dice Obj that determines the sides of the dice
        // Dice dice = new Dice(6);

        // for (int i = 0; i < diceRolls; i++) {
        //     for (int j = 0; j < diceAmount; j++) {
        //         int number = dice.roll();
        //         diceArr.add(number);
        //     }
        // }
        // DiceRandomnessChecker diceRand = new DiceRandomnessChecker(diceArr);
        // diceRand.validateAndPrint();
        


        

        


    }

    public static void rollTest(){
        int counter =0;
        int diceRolls =10;
        int maxRoll = 6;

        while(counter <  maxRoll){
            
            ArrayList<Integer>  arrayOfDiceMultipl = new ArrayList<>();
            Dice diceRollTest = new Dice(6);
            for(int i =0 ; i < diceRolls; i++){
                int number = diceRollTest.roll();
                arrayOfDiceMultipl.add(number);
                
            }
            DiceRandomnessChecker diceRandTest = new DiceRandomnessChecker(arrayOfDiceMultipl);

            
            diceRandTest.validateAndPrint();
           
            arrayOfDiceMultipl.clear();
            diceRolls = diceRolls*10;
            counter++;

        }
        
    }


    public static void outOfBounderErr(){
        System.out.println("Out Of Bound Error Occured/nPick Another Number>");
    }
    

}
