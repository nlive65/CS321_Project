/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package card;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Sam Tindol
 * 
 * This class is an abstract class that simply implements poker logic in Java code.
 * Using the basics of the card class and looking at the attributes of the CardHand
 * class, the class  uses functions that will decipher what hand a user has and will
 * assign the user's hand a value that is associated with its rank in the poker hand hierarchy.
 */
public abstract class CardLogic extends CardHand{
    
    // This function will go through the hands and determine the rnk of the 
    // hand based on the cards. Each hand will have a hand value which will be 
    // checked, and each uer has a fold boolean that will determine if they 
    // are still playing
    
    private int countTrue(boolean hand1, boolean hand2, boolean hand3, boolean hand4)
    {
        int count = 0;
        boolean []booleans = new boolean[4];
        booleans[0] = hand1;
        booleans[1] = hand2;
        booleans[2] = hand3;
        booleans[3] = hand4;
        for(boolean b : booleans)
        {
            if(b == true)
            {
                count++;
            }
        }
        return count;
    }
 
    /*
    Tis large function will figure out what the value of the user's full hand is.
    It will go through the full hand and count the needed pieces, and based off of this
    data, it will figure out the value of the hand.
    */
    public int  CheckHand(CardHand myHand)
    {
        // Initialize all of the variables needed to keep track of.
        int cardVal = 0;
        char cardSuit = ' ';
        int handValue = 0;
        int twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0, sevens = 0, eights = 0, 
                nines = 0, tens = 0, jacks = 0, queens = 0, kings = 0, aces = 0;
        
        int hearts = 0, diamonds = 0, clubs = 0, spades = 0;
        
        
        // For each run of the for loop, we will count how many of each value and suit the hand contains.
        // These values can be used to find pairs, threes, fours, and flushes. Other finctions will handle
        // full house and straights.
        for(int index = 0; index < myHand.GetFullHand().size(); index++)
        {
            cardVal = myHand.GetFullHand().get(index).GetValue();
            cardSuit = myHand.GetFullHand().get(index).GetSuit();
            
            switch(cardVal)
            {
                case 2:
                    twos++;
                    break;
                    
                case 3:
                    threes++;
                    break;
                    
                case 4:
                    fours++;
                    break;
                    
                case 5:
                    fives++;
                    break;
                    
                case 6:
                    sixes++;
                    break;
                    
                case 7:
                    sevens++;
                    break;
                    
                case 8:
                    eights++;
                    break;
                    
                case 9:
                    nines++;
                    break;
                    
                case 10:
                    tens++;
                    break;
                    
                case 11:
                    jacks++;
                    break;
                    
                case 12:
                    queens++;
                    break;
                    
                case 13:
                    kings++;
                    break;
                    
                case 14:
                    aces++;
                    break;
            }
            
            switch(cardSuit)
            {
                case 'h':
                    hearts++;
                    break;
                    
                case 'd':
                    diamonds++;
                    break;
                    
                case 'c':
                    clubs++;
                    break;
                    
                case 's':
                    spades++;
                    break;
            }
        }
        
        // Check for one pair
        if(twos >= 2 || threes >= 2 || fours >= 2 || fives >= 2 || sixes >= 2 || sevens >= 2 ||
                eights >= 2 || nines >= 2 || tens >= 2 || jacks >= 2 || queens >= 2 || kings >= 2 ||
                aces >= 2)
        {
            handValue = 1;
        }
        
        // This helper function will see if there are two pairs of cards
        handValue = CheckForTwoPairs(myHand, handValue);
        
        
        // Check for 3 of a kind
        if (twos >= 3 || threes >= 3 || fours >= 3 || fives >= 3 || sixes >= 3 || sevens >= 3 ||
                eights >= 3 || nines >= 3 || tens >= 3 || jacks >= 3 || queens >= 3 || kings >= 3 || aces >= 3)
        {
            handValue = 3;
        }
        
        // The next hand value is a straight, but that will be checked in a later helper function.
        // We can check for a straight later because it is impossible to have a straight and any of the 
        // following hands at the same time.
        
        // Check for flush
        if(hearts >= 5 || diamonds >= 5 || clubs >= 5 || spades >= 5)
        {
            handValue = 5;
        }
        
        // This helper function will check if the hand is a full house
        handValue = CheckForFullHouse(myHand, handValue);
        
        // Check for four of a kind
        if(twos >= 4 || threes >= 4 || fours >= 4 || fives >= 4 || sixes >= 4 || sevens >= 4
                || eights >= 4 || nines >= 4 || tens >= 4 || jacks >= 4 || queens >= 4
                || kings >= 4 || aces >= 4)
        {
            handValue = 7;
        }
        
        // This helper function will check for different kinds of straights (straight, straigh flush, or royal flush)
        handValue = CheckForStraight(myHand, handValue);
        
        // The handValue integer gets populate based on the hand alue and is returned for use in the GameRules class
        return handValue;
    }
    
    // This private helper function uses the map package to streamline the process of finidng two pairs
    private int CheckForTwoPairs(CardHand myHand, int currentHandVal)
    {
        int handVal = currentHandVal;
        
        Map<Integer, Integer> valueCount = new HashMap<>();
        
        for(int index = 0; myHand.GetFullHand().size() > index; index++)
        {
            int cardValue = myHand.GetFullHand().get(index).GetValue();
            valueCount.put(cardValue, valueCount.getOrDefault(cardValue, 0) + 1);
        }
        
        int pairCount = 0;
        
        for(int count : valueCount.values())
        {
            if(count == 2)
            {
                pairCount++;
            }
            if(pairCount == 2)
            {
                handVal = 2;
            }
        }
        return handVal;
    }
    
    // This private helper fiuntion checks if the hand contains a full house. It uses ther map package
    // to help streamline the process
    private int CheckForFullHouse(CardHand myHand, int currentHandVal)
    {
        int handVal = currentHandVal;
        
        // Initialize the map for checking
        Map<Integer, Integer> valueCount = new HashMap<>();
        
        // Go through the hand and place them in the map
        for(int index = 0; myHand.GetFullHand().size() > index; index++)
        {
            int cardValue = myHand.GetFullHand().get(index).GetValue();
            valueCount.put(cardValue, valueCount.getOrDefault(cardValue, 0) + 1);
        }
        
        // These booleans will make sure that we have three of a find and a pair in the hand
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
            
        // Go through the hand and count the values. If they are the same, the count goes up.
        for(int count : valueCount.values())
        {
            // If there are three of a value, set the corresponding boolean to true
            if(count == 3)
            {
                hasThreeOfAKind = true;
            }
            // If there are two of the same
            else if(count == 2)
            {
                hasPair = true;
            }
        }
        // If both of the bolleans are true, the hand is a full house and the corresponding hand value should
        // be returned
        if(hasThreeOfAKind && hasPair)
        {
            handVal = 6;
        }
        
        return handVal;
    }
    
    // This private helper function is to just check for the different kinds of straights.
    private int CheckForStraight(CardHand myHand, int currentHandVal)
    {
        // Initialize needed variables
        int handVal = currentHandVal;
        boolean straight = false;
        
        // These following variables will be sued to differentiate a straight, straight flush, and royal flush
        boolean sameSuit = false;
        int lastValue = 0, startValue = 0;
        char lastSuit = ' ';
        
        
        int straightCounter = 1;
        
        // Iterate through the hand
        for(int index = 0; index < myHand.GetFullHand().size(); index++)
        {
            // If this is the first value, do not do any comparisons, just initialize the variables
            if(index == 0)
            {
                lastValue = myHand.GetFullHand().get(index).GetValue();
                lastSuit = myHand.GetFullHand().get(index).GetSuit();
                startValue = lastValue;
            }
            else
            {
                // If this value is one greater than the last card's value, we will do some things
                if(myHand.GetFullHand().get(index).GetValue() == lastValue + 1)
                {
                    // INcrease the straight counter
                    straightCounter++;
                    
                    // If this was true and it was the same suit, then set the same suit to true
                    if(myHand.GetFullHand().get(index).GetSuit() == lastSuit)
                    {
                        sameSuit = true;
                    }
                    // Otherwise, the suit wasn't the same and we need to reset the last suit variable to the current suit
                    else
                    {
                        sameSuit = false;
                        lastSuit = myHand.GetFullHand().get(index).GetSuit();
                    }
                }
                
                else if (myHand.GetFullHand().get(index).GetValue() == lastValue)
                {
                    // Leave This Open As A Break from the else statement
                }
                
                // Otherwise, we reset all of our variables back to the start
                else
                {
                    straightCounter = 1;
                    startValue = lastValue;
                }
                
                // Mobe on and male the last value the value we are currently on
                lastValue = myHand.GetFullHand().get(index).GetValue();
                lastSuit = myHand.GetFullHand().get(index).GetSuit();
            }
            // If ther straight counter ever gets greter than or equal to 5, there is a straight and it will be set
            // to true
            if(straightCounter >= 5)
            {
                straight = true;
            }
        }
        
        // We will the hand value based upon the next variables
        if(straight == true)
        {
            // If there was  straight, set the hand value to the corresponding integr
            handVal = 4;
            // If we had the same suit in the straight at any time, then we at least had a straight flush.
            if(sameSuit == true)
            {
                handVal = 8;
                // If the starting value of the straight was a 10, then we know that there was a royal flush
                if(startValue == 10)
                {
                    handVal = 9;
                }
            }
        }
        // Return the value we found.
        return handVal;
    }
    
}
