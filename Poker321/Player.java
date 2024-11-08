/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.player;

/**
 *
 * @author giosa
 * 
 * This class represents a player in the poker game, either human or CPU.
 * 
 * The Player class will collaborate with the CardHand class to manage the player's hand
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
    
    public String getName() 
    {
        return name;
    }

    public int getChips() 
    {
        return chips;
    }

    public CardHand getHand() 
    {
        return hand;
    }

    public int getCurrentBet() 
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

    // abstract method for subcall to make moves
    public abstract void makeMove();


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
    public void placeBet(int betAmount) 
    {
        if (betAmount > 0 && betAmount <= chips) 
        {
            this.currentBet = betAmount;
            subChips(betAmount);  // Deduct the bet amount from chips
            System.out.println(name + "has placed a bet: " + betAmount + " chips");
        } 
        else 
        {
            System.out.println("Invalid bet amount!");
        }
    }

    // method to fold
    public void fold() 
    {
        this.hasFolded = true; 
        System.out.println(name + " has folded");
    }

    // method to check
    public void check() 
    {
        if (this.currentBet == 0)
        {
            this.hasChecked = true; //player has checked when no one has bet
            System.out.println(name + " has checked");
        }
        else
        {
            System.out.println("Bet placed, cannot check");
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
