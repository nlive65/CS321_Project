/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameManager;
import card.CardHand;
import java.util.ArrayList;
/**
 *
 * @author Sam Tindol
 * 
 * This class will be used to keep track of the game. It will take track of 
 * who needs to make bets, what order people need to be dealt in, and other
 * things that will collaborate with the user and CPU classes. It will also
 * compare hands, choose winners, set a pot, and deliver pot money to the winner.
 * 
 * This class will collaborate with the users, CPUs, and game graphics classes
 * as this will be the main class that takes into account the flow of the game
 */

// This class will also extend the from the user class so we can use the user bets
public class GameRules extends CardHand 
{
    // Pot variable for the gameplay. Will increase with bets.
    private int pot = 0;
    
    // Individual hands for the user and cpus to determine winners.
    private CardHand userHand;
    private CardHand cpu1Hand;
    private CardHand cpu2Hand;
    private CardHand cpu3Hand;
    
    // The next four functions are just setters for the individual hands
    public void SetUserCardHand(CardHand user)
    {
        
    }
    
    public void SetCpu1Hand(CardHand cpu1)
    {
        
    }
    
    public void SetCpu2Hand(CardHand cpu2)
    {
        
    }
    
    public void SetCpu3Hand(CardHand cpu3)
    {
        
    }
    
    // Our pot pot will be filled after each player has made their bets using
    // this function
    // Note: This function will likely need collaboration with the user and CPU
    // classes to work as intended.
    public void FillPot()
    {
        
    }
    
    // This function will go through the hands and determine the rnk of the 
    // hand based on the cards. Each hand will have a hand value which will be 
    // checked, and each uer has a fold boolean that will determine if they 
    // are still playing
    public void FindHighestHand(int hand1, boolean hand1Fold, int hand2, boolean hand2Fold,
            int hand3, boolean hand3Fold, int hand4, boolean hand4Fold)
    {
        
    }
    
    // This function will only be used if there is a draw between two players
    public void CheckHighestInDraw()
    {
        
    }
    
    // Returns the pot value to be added to another user's winnings
    public int GetPot()
    {
        return pot;
    }
    /**
     * There will likely be a couple more functions that will deal with placing
     * bets and dishing out the pot money to the winner. This will require 
     * collaboration with the user and CPU classes as their individual winnings
     * and bets will be contained as variables within their classes.
     */
}
