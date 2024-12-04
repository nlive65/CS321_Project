/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card;

/**
 *
 * @author Sam Tindol
 * 
 * This is will be our most basic class of the game rules. This will just allow
 * us to create cards to add to our decks and hands for the game. The Card class just encapsulates
 * the needed identifiers for a playing card for use in the game. We have a character that represents the suit
 * and an integer that represents the value of the card.
 */
public class Card 
{
    // Basic values for each card
    private int value;
    private char suit;
    
    public Card()
    {
                
    }
    public Card(int myValue, char mySuit)
    {
        value = myValue;
        suit = mySuit;
    }
    
    /*
    This cass simply needs to just contain these two values to do checks]
    for hands later in the program. This will allow us to let the poker game
    work.
    */
    public int GetValue()
    {
        return value;
    }
    
    public char GetSuit()
    {
        return suit;
    }
    
    public void SetValue(int myValue)
    {
        value = myValue;
    }
    
    public void SetSuit(char mySuit)
    {
        suit = mySuit;
    }
}
