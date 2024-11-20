/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameManager;

import card.Card;
import gameManager.Player;
//import java.util.ArrayList;

import card.CardLogic;
import card.CardHand;
import card.CardDeck;

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
               p1.GetCardHand().AddToTwoCardHand(pullCard);
               p1.GetCardHand().AddToFullHand(pullCard);
           }
           
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p2.GetCardHand().AddToTwoCardHand(pullCard);
               p2.GetCardHand().AddToFullHand(pullCard);
           }
           
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p3.GetCardHand().AddToTwoCardHand(pullCard);
               p3.GetCardHand().AddToFullHand(pullCard);
           }
           
           for(int index = 0; index < 2; index++)
           {
               pullCard = deck.PullTopCard();
               p4.GetCardHand().AddToTwoCardHand(pullCard);
               p4.GetCardHand().AddToFullHand(pullCard);
           }
           
       }
       
    private void drawWithTwo(Player p1, Player p2)
    {
        int p1Value = p1.GetCardHand().GetTwoCardHandHighCard();
        int p2Value = p2.GetCardHand().GetTwoCardHandHighCard();
        if(p1Value > p2Value)
        {
            p1.increaseBalance(pot);
            System.out.println(p1.getName() + " wins!");
        }
        else if(p2Value > p1Value)
        {
            p2.increaseBalance(pot);
            System.out.println(p2.getName() + " wins!");
        }
        else
        {
            p1.increaseBalance(pot/2);
            p2.increaseBalance(pot/2);
            
            System.out.println(p1.getName() + " and " + p2.getName() + " tied!");
            
        }
    }
    
    private void drawWithThree(Player p1, Player p2, Player p3)
    {
        int p1Value = p1.GetCardHand().GetTwoCardHandHighCard();
        int p2Value = p2.GetCardHand().GetTwoCardHandHighCard();
        int p3Value = p3.GetCardHand().GetTwoCardHandHighCard();
        
        boolean p1Highest = p1Value >= p2Value && p1Value >= p3Value;
        boolean p2Highest = p2Value >= p1Value && p2Value >= p3Value;
        boolean p3Highest = p3Value >= p1Value && p3Value >= p2Value;
        
        if(p1Highest && !p2Highest && !p3Highest)
        {
            p1.increaseBalance(pot);
            System.out.println(p1.getName() + " wins!");
        }
        else if(p2Highest && !p1Highest && !p3Highest)
        {
            p2.increaseBalance(pot);
            System.out.println(p2.getName() + " wins!");
        }
        else if(p3Highest && !p1Highest && !p2Highest)
        {
            p3.increaseBalance(pot);
            System.out.println(p3.getName() + " wins!");
        }
        else if(p1Highest && p2Highest && !p3Highest)
        {
            p1.increaseBalance(pot/2);
            p2.increaseBalance(pot/2);
            
            System.out.println(p1.getName() + " and " + p2.getName() + " tied");
        }
        else if(p2Highest && p3Highest && !p1Highest)
        {
            p2.increaseBalance(pot/2);
            p3.increaseBalance(pot/2);
            
            System.out.println(p2.getName() + " and " + p3.getName() + " tied");
        }
        else if(p1Highest && p3Highest && !p2Highest)
        {
            p1.increaseBalance(pot/2);
            p3.increaseBalance(pot/2);
            
            System.out.println(p1.getName() + " and " + p3.getName() + " tied");
        }
        else
        {
            p1.increaseBalance(pot/3);
            p2.increaseBalance(pot/3);
            p3.increaseBalance(pot/3);
            
            System.out.println(p1.getName() + ", " + p2.getName() + ", and " + p3.getName() + " tied!");
        }
    }
    
    private void drawWithFour(Player p1, Player p2, Player p3, Player p4)
    {
        int p1Value = p1.GetCardHand().GetTwoCardHandHighCard();
        int p2Value = p2.GetCardHand().GetTwoCardHandHighCard();
        int p3Value = p3.GetCardHand().GetTwoCardHandHighCard();
        int p4Value = p4.GetCardHand().GetTwoCardHandHighCard();
        
        boolean p1Highest = p1Value >= p2Value && p1Value >= p3Value && p1Value >= p4Value;
        boolean p2Highest = p2Value >= p1Value && p2Value >= p3Value && p2Value >= p4Value;
        boolean p3Highest = p3Value >= p1Value && p3Value >= p2Value && p3Value >= p4Value;
        boolean p4Highest = p4Value >= p1Value && p4Value >= p2Value && p4Value >= p3Value;
        
        if(p1Highest && !p2Highest && !p3Highest && !p4Highest)
        {
            p1.increaseBalance(pot);
        }
        else if(p2Highest && !p1Highest && !p3Highest && !p4Highest)
        {
            p2.increaseBalance(pot);
        }
        else if(p3Highest && !p1Highest && !p2Highest && !p4Highest)
        {
            p3.increaseBalance(pot);
        }
        else if(p4Highest && !p1Highest && !p2Highest && !p3Highest)
        {
            p4.increaseBalance(pot);
        }
        else if(p1Highest && p2Highest && !p3Highest && !p4Highest)
        {
            p1.increaseBalance(pot/2);
            p2.increaseBalance(pot/2);
            
            System.out.println(p1.getName() + " and " + p2.getName() + " tied");
        }
        else if(p2Highest && p3Highest && !p1Highest && !p4Highest)
        {
            p2.increaseBalance(pot/2);
            p3.increaseBalance(pot/2);
            
            System.out.println(p3.getName() + " and " + p4.getName() + " tied");
        }
        else if(p3Highest && p4Highest && !p1Highest && !p2Highest)
        {
            p3.increaseBalance(pot/2);
            p4.increaseBalance(pot/2);
            
            System.out.println(p3.getName() + " and " + p4.getName() + " tied");
        }
        else if(p1Highest && p3Highest && !p2Highest && !p4Highest)
        {
            p1.increaseBalance(pot/2);
            p3.increaseBalance(pot/2);
            
            System.out.println(p1.getName() + " and " + p3.getName() + " tied");
        }
        else if(p1Highest && p4Highest && !p2Highest && !p3Highest)
        {
            p1.increaseBalance(pot/2);
            p4.increaseBalance(pot/2);
            
            System.out.println(p1.getName() + " and " + p4.getName() + " tied");
        }
        else if(p2Highest && p4Highest && !p1Highest && !p3Highest)
        {
            p2.increaseBalance(pot/2);
            p4.increaseBalance(pot/2);
            
            System.out.println(p2.getName() + " and " + p4.getName() + " tied");
        }
        else if(p1Highest && p2Highest && p3Highest && !p4Highest)
        {
            p1.increaseBalance(pot/3);
            p2.increaseBalance(pot/3);
            p3.increaseBalance(pot/3);
            
            System.out.println(p1.getName() + ", " + p2.getName() + ", and " + p3.getName() + " tied!");
        }
        else if(p1Highest && p3Highest && p4Highest && !p2Highest)
        {
            p1.increaseBalance(pot/3);
            p3.increaseBalance(pot/3);
            p4.increaseBalance(pot/3);
            
            System.out.println(p1.getName() + ", " + p3.getName() + ", and " + p4.getName() + " tied!");
        }
        else if(p2Highest && p3Highest && p4Highest && !p1Highest)
        {
            p2.increaseBalance(pot/3);
            p3.increaseBalance(pot/3);
            p4.increaseBalance(pot/3);
            
            System.out.println(p2.getName() + ", " + p3.getName() + ", and " + p4.getName() + " tied!");
        }
        else if(p1Highest && p2Highest && p4Highest && !p3Highest)
        {
            p1.increaseBalance(pot/3);
            p2.increaseBalance(pot/3);
            p4.increaseBalance(pot/3);
            
            System.out.println(p1.getName() + ", " + p2.getName() + ", and " + p4.getName() + " tied!");
        }
        else
        {
            p1.increaseBalance(pot/4);
            p2.increaseBalance(pot/4);
            p3.increaseBalance(pot/4);
            p4.increaseBalance(pot/4);
            
            System.out.println("Everyone Tied!");
        }
            
    }
    
    public void DeclareWinner(Player p1, Player p2, Player p3, Player p4)
    {
        int p1HandValue = CheckHand(p1.GetCardHand());
        int p2HandValue = CheckHand(p2.GetCardHand());
        int p3HandValue = CheckHand(p3.GetCardHand());
        int p4HandValue = CheckHand(p4.GetCardHand());
        
        if(!p1.isActive())
        {
            p1HandValue = -1;
        }
        if(!p2.isActive())
        {
            p2HandValue = -1;
        }
        if(!p3.isActive())
        {
            p3HandValue = -1;
        }
        if(!p4.isActive())
        {
            p4HandValue = -1;
        }
        
        boolean p1HandHighest = p1HandValue >= p2HandValue && p1HandValue >= p3HandValue && p1HandValue >= p4HandValue;
        boolean p2HandHighest = p2HandValue >= p1HandValue && p2HandValue >= p3HandValue && p2HandValue >= p4HandValue;
        boolean p3HandHighest = p3HandValue >= p1HandValue && p3HandValue >= p2HandValue && p3HandValue >= p4HandValue;
        boolean p4HandHighest = p4HandValue >= p1HandValue && p4HandValue >= p2HandValue && p4HandValue >= p3HandValue;
        
        int trueCount = 0;
        
        if(p1HandHighest)
        {
            trueCount++;
        }
        if(p2HandHighest)
        {
            trueCount++;
        }
        if(p3HandHighest)
        {
            trueCount++;
        }
        if(p4HandHighest)
        {
            trueCount++;
        }
        
        System.out.println(trueCount);
        System.out.print(" number of trues.");
        
        if(trueCount == 1)
        {
            if(p1HandHighest)
            {
                p1.increaseBalance(pot);
                
                System.out.println(p1.getName() + " wins!");
            }
            else if(p2HandHighest)
            {
                p2.increaseBalance(pot);
                
                System.out.println(p2.getName() + " wins!");
            }
            else if(p3HandHighest)
            {
                p3.increaseBalance(pot);
                
                System.out.println(p3.getName() + " wins!");
            }
            else
            {
                p4.increaseBalance(pot);
                
                System.out.println(p4.getName() + " wins!");
            }
        }
        
        else if(trueCount == 2)
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