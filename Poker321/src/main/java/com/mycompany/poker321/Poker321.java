/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poker321;

import card.Card;
import card.CardHand;
import card.CardDeck;
import card.CardLogic;
import gameManager.GameRules;
import gameManager.Player;



/**
 *
 * @author Nick
 */
public class Poker321 {

    public static void main(String[] args) {
        
        GameRules myRules = new GameRules();
        
        Player player1 = new Player("Sam", 200);
        Player player2 = new Player("Nick", 200);
        Player player3 = new Player("Luke", 200);
        Player player4 = new Player("Gio", 200);
        
        System.out.println(player1.getBalance());
        System.out.println(player2.getBalance());
        System.out.println(player3.getBalance());
        System.out.println(player4.getBalance());
        
        
        myRules.AddToPot(100);
        
        CardDeck deck = new CardDeck();
        Card myCard;
        
        for(int index = 0; index < 8; index++)
        {
            myCard = deck.PullTopCard();
            if(index < 2)
            {
                player1.GetCardHand().AddToTwoCardHand(myCard);
                player1.GetCardHand().AddToFullHand(myCard);
            }
            else if(index < 4)
            {
                player2.GetCardHand().AddToTwoCardHand(myCard);
                player2.GetCardHand().AddToFullHand(myCard);
            }
            else if(index < 6)
            {
                player3.GetCardHand().AddToTwoCardHand(myCard);
                player3.GetCardHand().AddToFullHand(myCard);
            }
            else
            {
                player4.GetCardHand().AddToTwoCardHand(myCard);
                player4.GetCardHand().AddToFullHand(myCard);
            }
        }
        
        for(int index = 0; index < 5; index++)
        {
            myCard = deck.PullTopCard();
            
            player1.GetCardHand().AddToFullHand(myCard);
            player2.GetCardHand().AddToFullHand(myCard);
            player3.GetCardHand().AddToFullHand(myCard);
            player4.GetCardHand().AddToFullHand(myCard);
        }
        
        System.out.println(myRules.CheckHand(player1.GetCardHand()));
        System.out.println(myRules.CheckHand(player2.GetCardHand()));
        System.out.println(myRules.CheckHand(player3.GetCardHand()));
        System.out.println(myRules.CheckHand(player4.GetCardHand()));
        
        myRules.DeclareWinner(player1, player2, player3, player4);
        
        System.out.println(player1.getBalance());
        System.out.println(player2.getBalance());
        System.out.println(player3.getBalance());
        System.out.println(player4.getBalance());
    }
}
