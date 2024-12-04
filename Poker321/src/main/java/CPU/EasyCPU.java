package CPU;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giosa
 * 
 * this class represents a CPU player that plays conservatively by betting low and folding if the user is aggressive.
 */


public class EasyCPU extends CPU 
{
    
    // constructor
    public EasyCPU(String name, int chips, int playerTurn) 
    {
        super(name, chips, playerTurn);  // Calls the parent constructor

    }

    // method to make move based on user
    @Override
    public void makeMove() 
    {
        int playerBet = getCurrentBet();  // current bet from the player
        int cpuBalance = getBalance();    // CPU's balance
     
        // conservative betting strategy
        if (playerBet > cpuBalance * 0.5) {
            // if the player's bet is too high, fold
            System.out.println(getName() + " decides to fold because the bet is too high.");
            fold();  
        } else {
            // otherwise, bet a small amount
            int betAmount = Math.min(1, getBalance());  // bet 1 chips, but not more than the remaining balance
            System.out.println(getName() + " decides to bet " + betAmount + " chips.");
            decreaseBalance(betAmount); 
        }
    }
}
