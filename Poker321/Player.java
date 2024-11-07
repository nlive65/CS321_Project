/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poker;

/**
 *
 * @author giosa
 * 
 * this class represents a player in the poker game, either human or CPU.
 * 
 * the Player class will collaborate with the CardHand class to manage the player's hand
 * of cards during the game. 
 */

public abstract class Player 
{
    protected String name; 
    protected int chips; 
    protected CardHand hand; 
    protected int currentBet; 
    protected boolean hasFolded;
    protected boolean hasChecked;

    // constructor
    public Player(String name, int chips) 
    {
        this.name = name;
        this.chips = chips;
        this.hand = new CardHand(); 
        this.currentBet = 0;
        this.hasFolded = false;
        this.hasChecked = false;
    }
    
    // getters
    public String GetName() 
    {
        return name;
    }

    public int GetChips() 
    {
        return chips;
    }

    public CardHand GetHand() 
    {
        return hand;
    }

    public int GetCurrentBet() 
    {
        return currentBet;
    }

    public boolean hasChecked()
    {
        return hasChecked;
    }

    public boolean hasFolded()
    {
        return hasFolded;
    }

    // method to make a move in the game
    public abstract void MakeMove();

    // method to add chips
    public void addChips(int amount)
    {
        if (amount > 0)
        {
            this.chips += amount;
        }
        else
        {
            System.out.println("Invalid chip amount.");
        }
    }

    //method to subtract chips for placing bets
    public void subChips(int amount)
    {
        if (amount <= chips && amount > 0)
        {
            chips -= amount;        // Deduct bet from player's chips
            currentBet += amount;   // Set the current bet
        } 
        else 
        {
            System.out.println("Not enough chips or invalid bet amount.");
        }
    }
    
    // method to place a bet
    public void PlaceBet(int amount) 
    {
         if (betAmount > 0 && betAmount <= chips) 
        {
            this.currentBet = betAmount;
            subChips(betAmount);  // Deduct the bet amount from chips
        } 
        else 
        {
            System.out.println("Invalid bet amount!");
        }
    }

    // method to fold
    public void Fold() 
    {
        this.hasFolded = true; // player folded
        // message indicating user folded
    }

    // method to check
    public void Check() 
    {
        if (this.currentBet == 0)
        {
            this.hasChecked = true; //player checked when no one has bet
        }
        else
        {
            //message indicating player cannot check
        }
    }


    // method to reset the current bet when the rounds are finished
    public void resetBet() 
    {
        this.currentBet = 0;
        this.hasFolded = false;
        this.hasChecked = false;
    }
}
