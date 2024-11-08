/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.player;

/**
 *
 * @author giosa
 
 * this class represents a generic CPU player
 */

public abstract class CPU extends Player 
{
    // constructor
    public CPU(String name, int chips) 
    {
        super(name, chips);
        this.currentBet = 0;
    }
    
    //method for making a move in the game to be implemented by subclasses    
    public abstract void makeMove();

    //method to place a bet
    public void placeBet(int amount) 
    {     
        if (amount <= chips && amount > 0)
        {
            super.currentBet = amount;   // Set the bet amount
        }
        else 
        {
            System.out.println("Invalid bet amount");
        }
    }

    // method to fold
    public void fold() 
    {
        super.fold();
    }

    // method to check
    public void check() 
    {
        super.check();
    }

    // method to reset the current bet when the rounds are finished
    public void resetBet() 
    {
        this.currentBet = 0;
        this.hasFolded = false;
        this.hasChecked = false;
    }
}
