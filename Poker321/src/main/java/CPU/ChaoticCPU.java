package CPU;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giosa
 */

import java.util.Random;

/**
 * this class represents a Chaotic CPU player that makes random betting and folding decisions.
 */


public class ChaoticCPU extends CPU 
{
    // generate random numbers
    private Random random;

    // constructor
    public ChaoticCPU(String name, int chips, int playerTurn) 
    {
        super(name, chips, playerTurn);  // call the parent constructor
        this.random = new Random(); 
    }

    // method to make move randomly
    @Override
    public void makeMove() 
    {
        int playerBet = getCurrentBet();  // get the current bet from the opponent

        // random decision to fold or make a bet
        int decision = random.nextInt(3);  // generate a random number between 0 and 2

        if (decision == 0) 
        {
            // folds randomly
            System.out.println(getName() + " decides to fold!");
            fold();  // calls the fold method
        } else if (decision == 1) 
        {
            int betAmount = random.nextInt(getBalance()) + 1;  // Get a random bet amount between 1 and 50
            // check if CPU has enough chips
            if (betAmount > getBalance())
            {
                betAmount = getBalance(); // adjust to balance
            }
            System.out.println(getName() + " decides to bet " + betAmount + " chips.");
            decreaseBalance(betAmount);  // makes the bet
        } 
        else 
        {
            // Check (if no bet is placed)
            if (playerBet == 0) 
            {
                System.out.println(getName() + " decides to check.");
                check();  // calls the check method if the current bet is 0
            } 
            else 
            {
                System.out.println(getName() + " decides to call.");
                decreaseBalance(playerBet);  // calls the same amount as the opponent's bet
            }
        }
    }
}
