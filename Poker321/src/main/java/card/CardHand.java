/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package card;

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
    private ArrayList<Card> twoCardHand = new ArrayList<>();
    private ArrayList<Card> fullHand = new ArrayList<>();
    
    // Retrun the two card hand ArrayList
    public ArrayList<Card> GetTwoCardHand()
    {
        return twoCardHand;
    }
    
    // Retunr the full hand ArrayList
    public ArrayList<Card> GetFullHand()
    {
        return fullHand;
    }
    
    // We will need the high cards value in draws, so this function streamlines the process
    public int GetTwoCardHandHighCard()
    {
        int returnNum = 0;
        if(twoCardHand.size() == 2)
        {
            returnNum = twoCardHand.get(1).GetValue();
        }
        return returnNum;
    }
    
    // Fast sorting algorithm to sort the two card hand
    public void SortTwoCardHand()
    {
        if(twoCardHand.size() == 2)
        {
            if(twoCardHand.get(0).GetValue() > twoCardHand.get(0).GetValue())
            {
                Card holdCard = twoCardHand.get(1);
                twoCardHand.set(1, twoCardHand.get(0));
                twoCardHand.set(0, holdCard);
            }
        }
    }
    
    // Bubble sort to sort the full card hand
    public void SortFullHand()
{
    int handSize = fullHand.size();
    boolean swapped;
    
    for (int index = 0; index < handSize - 1; index++) 
    {
        swapped = false;
        
        // Reduce the range of the inner loop with each iteration
        for (int innerIndex = 0; innerIndex < handSize - 1 - index; innerIndex++) 
        {
            // Assuming Card has a method getValue() that returns the integer to compare
            if (fullHand.get(innerIndex).GetValue() > fullHand.get(innerIndex + 1).GetValue()) 
            {
                // Swap the cards if the current one is larger than the next one
                Card holdCard = fullHand.get(innerIndex);
                fullHand.set(innerIndex, fullHand.get(innerIndex + 1));
                fullHand.set(innerIndex + 1, holdCard);
                swapped = true;
            }
        }
        
        // If no cards were swapped, the hand is already sorted
            if (!swapped) 
            {
                break;
            }
        }
    }
    
    // This will add a card to the two card hand when it is drawn from the deck
    // in the deck class.
    public void AddToTwoCardHand(Card myCard)
    {
        twoCardHand.add(myCard);
        SortTwoCardHand();
    }
    
    // This will function the same as the other but it will add to the full hand
    // instead
    public void AddToFullHand(Card myCard)
    {
        fullHand.add(myCard);
        SortFullHand();
    }
}
