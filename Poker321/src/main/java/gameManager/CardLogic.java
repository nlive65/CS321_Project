/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameManager;


import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author eleph
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
    
   
    
    
 
    public int  CheckHand(CardHand myHand)
    {
        int cardVal = 0;
        char cardSuit = ' ';
        int handValue = 0;
        int twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0, sevens = 0, eights = 0, 
                nines = 0, tens = 0, jacks = 0, queens = 0, kings = 0, aces = 0;
        
        int hearts = 0, diamonds = 0, clubs = 0, spades = 0;
        
        
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
        
        if(twos >= 2 || threes >= 2 || fours >= 2 || fives >= 2 || sixes >= 2 || sevens >= 2 ||
                eights >= 2 || nines >= 2 || tens >= 2 || jacks >= 2 || queens >= 2 || kings >= 2 ||
                aces >= 2)
        {
            handValue = 1;
        }
        
        
        handValue = CheckForTwoPairs(myHand, handValue);
        
        
        if (twos >= 3 || threes >= 3 || fours >= 3 || fives >= 3 || sixes >= 3 || sevens >= 3 ||
                eights >= 3 || nines >= 3 || tens >= 3 || jacks >= 3 || queens >= 3 || kings >= 3 || aces >= 3)
        {
            handValue = 3;
        }
        
        // STRAIGHT
        
        if(hearts >= 5 || diamonds >= 5 || clubs >= 5 || spades >= 5)
        {
            handValue = 5;
        }
        
        handValue = CheckForFullHouse(myHand, handValue);
        
        if(twos >= 4 || threes >= 4 || fours >= 4 || fives >= 4 || sixes >= 4 || sevens >= 4
                || eights >= 4 || nines >= 4 || tens >= 4 || jacks >= 4 || queens >= 4
                || kings >= 4 || aces >= 4)
        {
            handValue = 7;
        }
        
        handValue = CheckForStraight(myHand, handValue);
        
        return handValue;
    }
    
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
        //System.out.println(handVal);
        return handVal;
    }
    
    private int CheckForFullHouse(CardHand myHand, int currentHandVal)
    {
        int handVal = currentHandVal;
        //int cardValue;
        
        Map<Integer, Integer> valueCount = new HashMap<>();
        
        for(int index = 0; myHand.GetFullHand().size() > index; index++)
        {
            int cardValue = myHand.GetFullHand().get(index).GetValue();
            valueCount.put(cardValue, valueCount.getOrDefault(cardValue, 0) + 1);
        }
            boolean hasThreeOfAKind = false;
            boolean hasPair = false;
            
            for(int count : valueCount.values())
            {
                if(count == 3)
                {
                    hasThreeOfAKind = true;
                }
                else if(count == 2)
                {
                    hasPair = true;
                }
            }
            if(hasThreeOfAKind && hasPair)
            {
                handVal = 6;
            }
        
        return handVal;
    }
    
    private int CheckForStraight(CardHand myHand, int currentHandVal)
    {
        int handVal = currentHandVal;
        boolean straight = false;
        boolean sameSuit = false;
        int lastValue = 0, startValue = 0;
        char lastSuit = ' ';
        int straightCounter = 1;
        
        for(int index = 0; index < myHand.GetFullHand().size(); index++)
        {
            if(index == 0)
            {
                lastValue = myHand.GetFullHand().get(index).GetValue();
                lastSuit = myHand.GetFullHand().get(index).GetSuit();
                startValue = lastValue;
            }
            else
            {
                if(myHand.GetFullHand().get(index).GetValue() == lastValue + 1)
                {
                    straightCounter++;
                    if(myHand.GetFullHand().get(index).GetSuit() == lastSuit)
                    {
                        sameSuit = true;
                    }
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
                
                else
                {
                    straightCounter = 1;
                    startValue = lastValue;
                    
                }
                
                lastValue = myHand.GetFullHand().get(index).GetValue();
                lastSuit = myHand.GetFullHand().get(index).GetSuit();
            }
            if(straightCounter >= 5)
            {
                straight = true;
            }
        }
        
        if(straight == true)
        {
            handVal = 4;
            if(sameSuit == true)
            {
                handVal = 8;
                if(startValue == 10)
                {
                    handVal = 9;
                }
            }
        }
        
        return handVal;
    }
    
}
