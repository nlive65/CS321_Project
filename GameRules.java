/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameloop;
//import java.util.ArrayList;
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
public class GameRules extends CardLogic 
{
       int pot = 0;
       
       
       public int GetPot()
       {
           return pot;
       }
       
       public void AddToPot(int bet1, int bet2, int bet3, int bet4)
       {
           pot = pot + bet1 + bet2 + bet3 + bet4;
       }
    
       public void Deal(int dealer, CardDeck deck, CardHand user, CardHand cpu1, CardHand cpu2, CardHand cpu3)
       {
           
       }
       
        public void CheckHighestHand(int hand1, int hand2, int hand3, int hand4)
    {
        boolean hand1Highest = hand1 >= hand2 && hand1 >= hand3 && hand1 >= hand4;
        boolean hand2Highest = hand2 >= hand1 && hand2 >= hand3 && hand2 >= hand4;
        boolean hand3Highest = hand3 >= hand1 && hand3 >= hand2 && hand3 >= hand4;
        boolean hand4Highest = hand4 >= hand1 && hand4 >= hand2 && hand4 >= hand3;
        
        int largestHands = countTrue(hand1Highest, hand2Highest, hand3Highest, hand4Highest);
        
        if(largestHands == 1)
        {
            if(hand1Highest)
            {
                
            }
            
            else if(hand2Highest)
            {
                
            }
            
            else if(hand3Highest)
            {
                
            }
            
            else
            {
                
            }
                
        }
        else if(largestHands == 2)
        {
            CardHand firstHand = null;
            CardHand secondHand = null;
            
            if(hand1Highest)
            {
                // Set The First Hand
            }
            
            if(hand2Highest)
            {
                if(firstHand != null)
                {
                    // Set First Hand
                }
                else
                {
                    // Set Second Hand
                }
            }
            if(hand3Highest)
            {
                if(firstHand != null)
                {
                    // Set First Hand
                }
                else
                {
                    // Set Second Hand
                }
            }
            if(hand4Highest)
            {
                // Set Second Hand
            }
            drawWithTwo(firstHand, secondHand);
        }
        
        else if(largestHands == 3)
        {
            CardHand firstHand = null;
            CardHand secondHand = null;
            CardHand thirdHand = null;
            
            if(hand1Highest)
            {
                // Set First Hand
            }
            
            if(hand2Highest)
            {
                if(firstHand != null)
                {
                    // Set First Hand
                }
                else
                {
                    // Set Second Hand
                }
            }
            if(hand3Highest)
            {
                if(secondHand != null)
                {
                    // Set Second Hand
                }
                else
                {
                    // Set Third Hand
                }
            }
            if(hand4Highest)
            {
                // Set Third Hand
            }
            drawWithThree(firstHand, secondHand, thirdHand);
        }
        else
        {
            CardHand firstHand = null;
            CardHand secondHand = null;
            CardHand thirdHand = null;
            CardHand fourthHand = null;
            
            // Set the Hands and then pass them to the function
            
            drawWithFour(firstHand, secondHand, thirdHand, fourthHand);
        }
    }
        // This function will only be used if there is a draw between two players
    private void drawWithTwo(CardHand hand1, CardHand hand2)
    {
        int hand1HighCard = hand1.GetTwoCardHand().get(1).GetValue();
        int hand2HighCard = hand2.GetTwoCardHand().get(1).GetValue();
        
        if(hand1HighCard > hand2HighCard)
        {
            // HAnd 1 wins
        }
        else if(hand2HighCard > hand1HighCard)
        {
            // Hand 2 wins
        }
        else
        {
            // It is a draw
        }
    }
    
    private void drawWithThree(CardHand hand1, CardHand hand2, CardHand hand3)
    {
        int hand1HighCard = hand1.GetTwoCardHand().get(1).GetValue();
        int hand2HighCard = hand2.GetTwoCardHand().get(1).GetValue();
        int hand3HighCard = hand3.GetTwoCardHand().get(1).GetValue();
        
        if(hand1HighCard > hand2HighCard && hand1HighCard > hand3HighCard)
        {
            // HAnd 1 wins
        }
        else if(hand2HighCard > hand1HighCard && hand2HighCard > hand3HighCard)
        {
            // Hand 2 wins
        }
        else if(hand3HighCard > hand1HighCard && hand3HighCard > hand2HighCard)
        {
            // Hand 3 Wins
        }
        else if(hand1HighCard == hand2HighCard && hand3HighCard != hand1HighCard)
        {
            // Hands 1 and 2 draw
        }
        else if(hand2HighCard == hand3HighCard && hand1HighCard != hand2HighCard)
        {
            // Hands 2 and 3 draw
        }
        else if(hand3HighCard == hand1HighCard && hand2HighCard != hand1HighCard)
        {
            // Hands 3 and 1 draw
        }
        else
        {
            // All draw
        }
    }
    
    private void drawWithFour(CardHand hand1, CardHand hand2, CardHand hand3, CardHand hand4)
    {
        int hand1HighCard = hand1.GetTwoCardHand().get(1).GetValue();
    }
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
    public void DeclareWinner(CardHand user, CardHand cpu1, CardHand Cpu2, CardHand cpu3)
    {
        
    }
}
