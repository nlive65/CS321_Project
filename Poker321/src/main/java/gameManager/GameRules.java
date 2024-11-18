/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameManager;
//import java.util.ArrayList;

import com.mycompany.cards.CardLogic;
import gameManager.CardHand;
import gameManager.CardDeck;
import gameManager.Card;

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
       
       public void AddToPot(int bet)
       {
           pot += bet;
       }
    
       public void Deal(CardDeck deck, Player p1, Player p2, Player p3, Player p4)
       {
           Card pullCard;
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p1.GetCardHand().AddToTwCardHand(pullCard);
               p1.GetCardHand().AddToFullHand(pullCard);
           }
           
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p2.GetCardHand().AddToTwCardHand(pullCard);
               p2.GetCardHand().AddToFullHand(pullCard);
           }
           
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p3.GetCardHand().AddToTwCardHand(pullCard);
               p3.GetCardHand().AddToFullHand(pullCard);
           }
           
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p4.GetCardHand().AddToTwCardHand(pullCard);
               p4.GetCardHand().AddToFullHand(pullCard);
           }
           
       }
       
    private void drawWithTwo(Player p1, Player p2)
    {
        int p1Value = p1.GetCardHand().GetTwoCardHandHighCard();
        int p2Value = p2.GetCardHand().GetTwoCardHandHighCard();
        if(p1Value > p2Value)
        {
            p1.Winner(pot);
        }
        else if(p2Value > p1Value)
        {
            p2.Winner(pot);
        }
        else
        {
            p1.Winner(pot/2);
            p2.WInner(pot/2);
            
        }
    }
    
    private void drawWithThree(Player p1, Player p2, Player p3)
    {
        int p1Value = p1.GetCardHand().GeTwoCardHandHighCard();
        int p2Value = p2.GetCardHand().GetTwoCardHandHighCard();
        int p3Value = p3.GetCardHand().GetTwoCardHandHighCard();
        
        boolean p1Highest = p1Value >= p2Value && p1Value >= p3Value;
        boolean p2Highest = p2Value >= p1Value && p2Value >= p3Value;
        boolean p3Highest = p3Value >= p1Value && p3Value >= p2Value;
        
        if(p1Highest && !p2Highest && !p3Highest)
        {
            // P1 Winner
        }
        else if(p2Highest && !p1Highest && !p3Highest)
        {
            // P2 Winner
        }
        else if(p3Highest && !p1Highest && !p2Highest)
        {
            // P3 Winner
        }
        else if(p1Highest && p2Highest && !p3Highest)
        {
            // P1 and P2
        }
        else if(p2Highest && p3Highest && !p1Highest)
        {
            // P2 and P3
        }
        else if(p1Highest && p3Highest && !p2Highest)
        {
            
        }
        else
        {
            // P1 and P2 and P3
        }
    }
    
    private void drawWithFour(Player p1, Player p2, Player p3, Player p4)
    {
        int p1Value = p1.GetCardHand().GeTwoCardHandHighCard();
        int p2Value = p2.GetCardHand().GetTwoCardHandHighCard();
        int p3Value = p3.GetCardHand().GetTwoCardHandHighCard();
        int p4Value = p4.GetCardHand().GetTwoCardHandHIghCard();
        
        boolean p1Highest = p1Value >= p2Value && p1Value >= p3Value && p1Value >= p4Value;
        boolean p2Highest = p2Value >= p1Value && p2Value >= p3Value && p2Value >= p4Value;
        boolean p3Highest = p3Value >= p1Value && p3Value >= p2Value && p3Value >= p4Value;
        boolean p4Highest = p4Value >= p1Value && p4Value >= p2Value && p4Value >= p3Value;
        
        if(p1Highest && !p2Highest && !p3Highest && !p4Highest)
        {
            // P1 Wins
        }
        else if(p2Highest && !p1Highest && !p3Highest && !p4Highest)
        {
            // P2 Wins
        }
        else if(p3Highest && !p1Highest && !p2Highest && !p4Highest)
        {
            // P3 Wins
        }
        else if(p4Highest && !p1Highest && !p2Highest && !p3Highest)
        {
            // P4 Wins
        }
        else if(p1Highest && p2Highest && !p3Highest && !p4Highest)
        {
            // P1 and P2
        }
        else if(p2Highest && p3Highest && !p1Highest && !p4Highest)
        {
            // P2 and P3
        }
        else if(p3Highest && p4Highest && !p1Highest && !p2Highest)
        {
            // P3 and P4
        }
        else if(p1Highest && p3Highest && !p2Highest && !p4Highest)
        {
            // P1 and P3
        }
        else if(p1Highest && p4Highest && !p2Highest && !p3Highest)
        {
            // P1 and P4
        }
        else if(p2Highest && p4Highest && !p1Highest && !p3Highest)
        {
            // P2 and P4
        }
        else if(p1Highest && p2Highest && p3Highest && !p4Highest)
        {
            // P1 and P2 and P3
        }
        else if(p1Highest && p3Highest && p4Highest && !p2Highest)
        {
            // P1 and P3 and P4
        }
        else if(p2Highest && p3Highest && p4Highest && !p1Highest)
        {
            // P2 P3 and P4
        }
        else if(p1Highest && p2Highest && p4Highest && !p3Highest)
        {
            // P1 and P2 and P4
        }
        else
        {
            // p1 and P2 and P3 and P4
        }
            
    }
    
    public void DeclareWinner(Player p1, Player p2, Player p3, Player p4)
    {
        int p1HandValue = CheckHand(p1.GetFullHand());
        int p2HandValue = CheckHand(p2.GetFullHand());
        int p3HandValue = CheckHand(p3.GetFullHand());
        int p4HandValue = CheckHand(p4.GetFullHand());
        
        boolean p1HandHighest = p1HandValue >= p2HandValue && p1HandValue >= p3HandValue && p1HandValue >= p4HandValue;
        boolean p2HandHighest = p2HandValue >= p1HandValue && p2HandValue >= p3HandValue && p2HandValue >= p4HandValue;
        boolean p3HandHighest = p3HandValue >= p1HandValue && p3HandValue >= p2HandValue && p3HandValue >= p4HandValue;
        boolean p4HandHighest = p4HandValue >= p1HandValue && p4HandValue >= p2HandValue && p4HandValue >= p3HandValue;
        
        boolean[] trueArray = {p1HandHighest, p2HandHighest, p3HandHighest, p4HandHighest};
        int trueCount = 0;
        for(boolean val : trueArray)
        {
            if(val)
            {
                trueCount++;
            }
        }
        
        if(trueCount == 1)
        {
            if(p1HandHighest)
            {
                // P1 Win
            }
            else if(p2HandHighest)
            {
                // P2 Win
            }
            else if(p3HandHighest)
            {
                // P3 Win
            }
            else
            {
                // P4 WIn
            }
        }
        
        if(trueCount == 2)
        {
            if(p1HandHighest && p2HandHighest)
            {
                drawWithTwo(p1, p2);
            }
            else if(p1HandHighest && p3HandHighest)
            {
                drawWithTwo(p1, p3);
            }
            else if(p1HandHighest && p4HandHighest)
            {
                drawWithTwo(p1, p4);
            }
            else if(p2HandHighest && p3HandHighest)
            {
                drawWithTwo(p2, p3);
            }
            else if(p2HandHighest && p4HandHighest)
            {
                drawWithTwo(p2, p4);
            }
            else
            {
                drawWithTwo(p3, p4);
            }
        }
        else if(trueCount == 3)
        {
            if(p1HandHighest && p2HandHighest && p3HandHighest)
            {
                drawWithThree(p1, p2, p3);
            }
            else if(p2HandHighest && p3HandHighest && p4HandHighest)
            {
                drawWithThree(p2, p3, p4);
            }
            else if(p1HandHighest && p3HandHighest && p4HandHighest)
            {
                drawWithThree(p1, p3, p4);
            }
            else
            {
                drawWithThree(p1, p2, p4);
            }
        }
        else
        {
            drawWithFour(p1, p2, p3, p4);
        }
    }
}
