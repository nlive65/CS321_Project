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
 * @author Everyone
 */
public class Poker321 {

    public static void main(String[] args) 
    {
        // Declaring the rules and deck object
        GameRules rules = new GameRules();
        CardDeck deck = new CardDeck();
        
        // Placeholders for info from GUI
        String playerName = "Hold";
        int chipAmount = 200;
        int rounds = 5;
        
        // Initiate the players
        Player user = new Player(playerName, chipAmount);
        Player barry = new Player("Barry", chipAmount);
        Player jerry = new Player("Jerry", chipAmount);
        Player larry = new Player("Larry", chipAmount);
        
        // Dealer variable will choose who the dealer is
        Player dealer = null;
        
        // Deal based upon the turn. The user will always start, and we will go in a clockwise motion
        // deciding who the next dealer will be
        for(int turn = 1; turn <= rounds && user.getBalance() > 0; turn++)
        {
            if(turn == 1 || turn == 5 || turn == 9)
            {
                rules.Deal(deck, user, barry, jerry, larry);
                dealer = user;
            }
            else if(turn == 2 || turn == 6 || turn == 10)
            {
                rules.Deal(deck, barry, jerry, larry, user);
                dealer = barry;
            }
            else if(turn == 3 || turn == 7)
            {
                rules.Deal(deck, jerry, larry, user, barry);
                dealer = jerry;
            }
            else
            {
                rules.Deal(deck, larry, user, barry, jerry);
                dealer = larry;
            }
            
            // Declare a card variable for a card drawn from the deck
            Card pulledCard = null;
            
            for(int index = 0; index < 2; index++)
            {
                pulledCard = deck.PullTopCard();
                // !!! This Card will be a card that is displayed in the middle of the table !!!
                
                user.GetCardHand().AddToFullHand(pulledCard);
                barry.GetCardHand().AddToFullHand(pulledCard);
                jerry.GetCardHand().AddToFullHand(pulledCard);
                larry.GetCardHand().AddToFullHand(pulledCard);
                
            }
            
            for(int betTurn = 1; betTurn <= 3; betTurn++)
            {
                pulledCard = deck.PullTopCard();
                // This card will be siplayed in the middle of the table
                
                user.GetCardHand().AddToFullHand(pulledCard);
                barry.GetCardHand().AddToFullHand(pulledCard);
                jerry.GetCardHand().AddToFullHand(pulledCard);
                larry.GetCardHand().AddToFullHand(pulledCard);
                
                int userBet = 0;
                int barryBet = 0;
                int jerryBet = 0;
                int larryBet = 0;
                
                if(dealer.getName() == user.getName())
                {
                    
                    // PLay game with the user as dealer
                    // user makes bet
                    rules.AddToPot(userBet);
                    
                    // barry Bets
                    rules.AddToPot(barryBet);
                    
                    // jerry bets
                    rules.AddToPot(jerryBet);
                    
                    // larry bets
                    rules.AddToPot(larryBet);
                }
                else if(dealer.getName() == barry.getName())
                {
                    // PLay game with barry as dealer
                    // barry bets
                    rules.AddToPot(barryBet);
                    
                    // jerry bets
                    rules.AddToPot(jerryBet);
                    
                    // larry bets
                    rules.AddToPot(larryBet);
                    
                    // user bets
                    rules.AddToPot(userBet);
                }
                else if(dealer.getName() == jerry.getName())
                {
                    // Play with jerry as the dealer
                    // jerry bets
                    rules.AddToPot(jerryBet);
                    
                    // larry bets
                    rules.AddToPot(larryBet);
                    
                    // user bets
                    rules.AddToPot(userBet);
                    
                    // barry bets
                    rules.AddToPot(barryBet);
                }
                else
                {
                    // Play with larry as the dealer
                    // larry bets
                    rules.AddToPot(larryBet);
                    
                    // user bets
                    rules.AddToPot(userBet);
                    
                    // barry bets
                    rules.AddToPot(barryBet);
                    
                    // jerry bets
                    rules.AddToPot(jerryBet);
                }
            }
            // Showdown
            rules.DeclareWinner(user, barry, jerry, larry);
            rules.ResetPot();
            deck.ResetDeck();
        }
        
        
    }
}
