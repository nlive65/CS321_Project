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

public abstract class CPU extends Player
{
    // current bet amount
    protected int currentBet;

    // constructor
    public CPU(String name, int chips, int playerTurn) 
    {
        super(name, chips, playerTurn);
        this.currentBet = 0;
    }
 
    //method for making a move in the game to be implemented by subclasses    
    public abstract void makeMove();

    // method to fold
    @Override
    public void fold() 
    {
        super.fold();
    }

    // method to check
    @Override
    public void check() 
    {
        super.check();
    }

    // method to get the current bet
    @Override
    public int getCurrentBet() 
    {
        return currentBet;
    }

    // method to reset the current bet when the rounds are finished
    @Override
    public void resetBet() 
    {
        this.currentBet = 0;
        this.hasFolded = false;
        this.hasChecked = false;
    }
}


