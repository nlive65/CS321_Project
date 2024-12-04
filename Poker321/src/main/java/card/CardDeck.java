/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package card;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Sam Tindol
 * 
 * his class will serve as our deck of cards for the game. We will be able
 * to create, empty, fill, shuffle, and draw from a deck with this class.
 */
public class CardDeck extends Card
{
    // The deck will just be a 52 element ArrayList of card types
    private ArrayList<Card> deck = new ArrayList<>();
    
    // When initializing a deck, we will go ahead fil/shuffle the deck
    public CardDeck()
    {
        FillDeck();
        ShuffleDeck();
    }
    
    // Reset the ArrayList by emptying it, filling it, and shuffling it
    public void ResetDeck()
    {
        EmptyDeck();
        FillDeck();
        ShuffleDeck();
    }
    // This will populate the deck with 52 cards.
    private void FillDeck()
    {
        char suit = ' ';
        
        // Keep track of the value
        for (int value = 2; value <= 14; value++)
        {
            // Keep track of the suit
            for(int suitIndex = 1; suitIndex <= 4; suitIndex++)
            {
                switch(suitIndex)
                {
                    case 1:
                        suit = 'h';
                        break;
                        
                    case 2:
                        suit = 'd';
                        break;

                    case 3:
                        suit = 'c';
                        break;
                        
                    case 4:
                        suit = 's';
                        break;
                }
                deck.add(new Card(value, suit));
            }
        }
    }
    
    // This will radomize the deck
    private void ShuffleDeck()
    {
        Collections.shuffle(deck);
        //System.out.println(deck.get(0).GetSuit() + deck.get(0).GetValue());
    }
    
    // This will take the top card in the array list and remove it.
    public Card PullTopCard()
    {
        Card returnCard = deck.get(0);
        deck.remove(0);
        return returnCard;
    }
    
    
    // This function will empty the deck in order to start a new game.
    private void EmptyDeck()
    {
        int size = deck.size();
        for(int index = 0; index < size; index++)
        {
            deck.remove(0);
        }
    }
}
