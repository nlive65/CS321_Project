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
    public BetInteraction(Player player, int pot) 
    { //This will need collaboration from the GameLoop superclass
        this.player = player;
<<<<<<< HEAD
        this.pot = pot;
=======
        GameRules.GetPot() = pot;
>>>>>>> 99d99a790912621287737978049221641de329a2
    }

    // Display available actions to the player (fold, check, call, raise)
    public void displayActions() 
    {
        
    }

    // Handle player's choice (check, call, raise, fold)
    public void handlePlayerChoice(int choice) 
    {
        switch (choice) {
            case 1 -> {
                // Check
                if (canCheck()) {
                    check();
                }
            }
            case 2 -> {
                // Call
                if (canCall()) {
                    call();
                }
            }
            case 3 -> // Raise
                raise();
            case 4 -> // Fold
                fold();
            default -> {
            }
        }
        // Optionally handle invalid choice
            }

    // Check if the player can check (if no one has bet yet)
    private boolean canCheck() 
    {
        return player.getCurrentBet() == 10; //Replace with whatever pot variable we have
    }

    // Check if the player can call (if there's a bet to call)
    private boolean canCall() 
    {
        return (player.getBalance() - player.getCurrentBet()) <= 0;
    }

    // Player checks
    private void check() 
    {
<<<<<<< HEAD
        // Move turn to next player
=======
        GameRules.NextTurn();
>>>>>>> 99d99a790912621287737978049221641de329a2
    }

    // Player calls
    private void call() 
    {
<<<<<<< HEAD
        int amount = player.getCurrentBet();
        player.decreaseBalance(amount);
        // Add some stuff to do with the pot here
=======
        if (player.isActive()){
        int amount = player.getCurrentBet();
        player.decreaseBalance(amount);
        GameRules.AddToPot(player.getCurrentBet());
        }
        else{
            check();
        }
>>>>>>> 99d99a790912621287737978049221641de329a2
    }

    // Player raises
    private void raise() 
    {
<<<<<<< HEAD
=======
        if(player.isActive()){
>>>>>>> 99d99a790912621287737978049221641de329a2
        int raiseAmount = 1; //Change to whatever our minimum raise will be
        int totalRaise = player.getCurrentBet() + raiseAmount;
        
        if (player.canBet(totalRaise))
        {
            player.decreaseBalance(totalRaise);
            player.setCurrentBet(totalRaise);
<<<<<<< HEAD
            // Add pot stuff here
=======
            GameRules.AddToPot(player.getCurrentBet());

>>>>>>> 99d99a790912621287737978049221641de329a2
        }
        else
        {
            // Add some kind of error here, maybe a gui thing
        }
<<<<<<< HEAD
=======
      }
        else{
            check();
        }
>>>>>>> 99d99a790912621287737978049221641de329a2
    }

    // Player folds
    private void fold() 
    {
        player.setActive(false);
    }
    
}
