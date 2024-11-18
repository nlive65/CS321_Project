/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package card;
import card.Card;
import java.util.ArrayList;
/**
 *
 * @author Sam Tindol
 * 
 * This class will serve as the hand of cards for the user and CPUs. Each player
 * will have two different array lists of cards, a two card hand of their
 * personal cards and a full up-to seven card hand that will be the user's
 * two private cards plus the up to five in the middle of the playing board.
 */
public class CardHand extends Card
{
    // Value of a user's hand
    private int twoCardHandValue;
    private int fullHandValue;
    private ArrayList<Card> twoCardHand;
    private ArrayList<Card> fullHand;
    
    public int GetFullHandValue()
    {
        return fullHandValue;
    }
    
    public int GetTwoCardHandValue()
    {
        return twoCardHandValue;
    }
    // This function will assign a value to a hand value based on the cards in
    // the hand. Two card and full hand will be tested
    public void SetHandValue(ArrayList<Card> hand)
    {
        
    }
    
    // This will add a card to the two card hand when it is drawn from the deck
    // in the deck class.
    public void AddToTwoCardHand(Card myCard)
    {
        
    }
    
    // This will function the same as the other but it will add to the full hand
    // instead
    public void AddToFullHand(Card myCard)
    {
        
    }
}
