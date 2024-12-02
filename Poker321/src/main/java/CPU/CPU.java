package CPU;
import gameManager.Player;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giosa
 
 * this class represents a generic CPU player
 */

public abstract class CPU 
{
    // Current bet amount
    protected int currentBet;

    // constructor
    public CPU()
    {
                
    }

    //method for making a move in the game to be implemented by subclasses    
    public abstract void MakeMove();

    //method to place a bet
    public void PlaceBet(int amount) 
    {     
        
    }

    // method to fold
    public void Fold() 
    {
        
    }

    // method to check
    public void Check() 
    {
        
    }

    // method to get the current bet
    public int GetCurrentBet() 
    {
        return currentBet;
    }

    // method to reset the current bet when the rounds are finished
    public void ResetBet() 
    {
        
    }
}


