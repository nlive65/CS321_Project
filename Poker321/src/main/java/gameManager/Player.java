/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gameManager;
import card.CardHand;
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

    // constructor
    public Player(String name, int chips) 
    {
       
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

    // method to make a move in the game
    public abstract void MakeMove();

    // method to place a bet
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


    // method to reset the current bet when the rounds are finished
    public void resetBet() 
    {
        
    }
}