/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gameManager;
        
import card.Card;
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
    // The deck will just be a 52 piece ArrayList of card types
    private ArrayList<Card> deck = new ArrayList<>();
    
    public CardDeck()
    {
        FillDeck();
        ShuffleDeck();
    }
    
    
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
        for (int value = 2; value <= 14; value++)
        {
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
                        
                    default:
                        suit = 'l';
                }
                deck.add(new Card(value, suit));
            }
        }
    }
    
    // This will radomize the deck
    private void ShuffleDeck()
    {
        Collections.shuffle(deck);
        System.out.println(deck.get(0).GetSuit() + deck.get(0).GetValue());
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
        for(int index = 0; index < deck.size(); index++)
        {
            deck.remove(0);
        }
    }
    
    // TEST FUNCTION
    public void ReadCard(Card myCard)
    {
        char mySuit = myCard.GetSuit();
        int myValue = myCard.GetValue();
        System.out.println(mySuit + " " + myValue);
    }
}
