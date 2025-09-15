package diceroller;

import java.util.ArrayList;
import java.util.Scanner;

//Version 1
//9-15-2025
public class DiceDriver {
    public static void main(String[] args) {
        ArrayList<Integer> diceArr = new ArrayList<>();
        


        // Scanner
        Scanner input = new Scanner(System.in);

        // Welcome text
        System.out.println("Welcome to Dice roller");

        // Dice Rolling message
        System.out.println("Enter hom many dice you want to roll 1-5 (inclusive)>");

        // Amount of dice rolls
        
        int diceAmount = input.nextInt();
        while(diceAmount < 0 || diceAmount > 6 ){
            outOfBounderErr();
            diceAmount = input.nextInt();



        }


        System.out.println("Enter how many times you want them to re-rell");
        // ReRolling Amount
        int diceRolls = input.nextInt();

        // Dice Obj that determines the sides of the dice
        Dice dice = new Dice(6);

        for (int i = 0; i < diceRolls; i++) {
            for (int j = 0; j < diceAmount; j++) {
                int number = dice.roll();
                System.out.println(number);
                diceArr.add(number);
            }
        }
        DiceRandomnessChecker diceRand = new DiceRandomnessChecker(diceArr);
        System.out.println(diceRand.printRandom());
        


        

        


    }

    public static void outOfBounderErr(){
        System.out.println("Out Of Bound Error Occured/nPick Another Number>");
    }
    

}
