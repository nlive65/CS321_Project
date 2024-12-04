package com.mycompany.poker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luke
 */

// This is a rough sketch of what the Betting Interaction will look like, much tweaking to be done.
// All functions/constructors containing references to the pot will require collaboration with Game

public class BetInteraction {
    
    private final Player player;
    
     // Constructor
    public BetInteraction(Player player, Pot pot) 
    { //This will need collaboration from the GameLoop superclass
      
    }

    // Display available actions to the player (fold, check, call, raise)
    public void displayActions() 
    {
        
    }

    // Handle player's choice (check, call, raise, fold)
    public void handlePlayerChoice(int choice) 
    {
      
   
    }

    // Check if the player can check (if no one has bet yet)
    private boolean canCheck() 
    {
        
    }

    // Check if the player can call (if there's a bet to call)
    private boolean canCall() 
    {
        
        
    }

    // Player checks
    private void check() 
    {
        
    }

    // Player calls
    private void call() 
    {
        
    }

    // Player raises
    private void raise() 
    {
        // Update the pot and player's balance accordingly
    }

    // Player folds
    private void fold() 
    {
        
    }
    
}
