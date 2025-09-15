package diceroller;

import java.util.Random;

public class Dice {
    Random random = new Random();
    
    int sides;

    public Dice(int sides){
        this.sides = sides;
    }

    public int roll(){
       return random.nextInt(sides) + 1;

    }

    
}
