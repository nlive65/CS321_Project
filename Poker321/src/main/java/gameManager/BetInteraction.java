
package gameManager;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luke
 * @param Takes player id and pot amount
 */

// This is a rough sketch of what the Betting Interaction will look like, much tweaking to be done.
// All functions/constructors containing references to the pot will require collaboration with Game

public class BetInteraction {
    private GameRules rules = new GameRules();
    private int pot;
    int currentTurn;
    private Player player;
    GameRules game = new GameRules();
    
     // Constructor
    public BetInteraction(Player player, int pot) 
    { //This will need collaboration from the GameLoop superclass
        this.player = player;
        //GameRules.GetPot() = pot;
    }

    // Display available actions to the player (fold, check, call, raise)
    public void displayActions() 
    {
        
        pot = game.GetPot();
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
        //GameRules.NextTurn();

        NextTurn();
    }

    public void NextTurn()
    {
        currentTurn = player.getTurn();
        if (currentTurn > 3)
        {
            player.setTurn(0);
        }
        else 
        {
            player.setTurn(currentTurn++);
        }
    }
    // Player calls
    private void call() 
    {
        if (player.isActive()){
        int amount = player.getCurrentBet();
        player.decreaseBalance(amount);
        rules.AddToPot(player.getCurrentBet());
        game.AddToPot(player.getCurrentBet());
        NextTurn();
        }
        else{
            check();
        }
    }

    // Player raises
    private void raise() 
    {
        if(player.isActive()){
        int raiseAmount = 1; //Change to whatever our minimum raise will be
        int totalRaise = player.getCurrentBet() + raiseAmount;
        
        if (player.canBet(totalRaise))
        {
            player.decreaseBalance(totalRaise);
            player.setCurrentBet(totalRaise);
            //GameRules.AddToPot(player.getCurrentBet());
            game.AddToPot(player.getCurrentBet());
            NextTurn();

        }
      }
        else{
            check();
        }
    }

    // Player folds
    private void fold() 
    {
        player.setActive(false);
        NextTurn();
    }
    
}


