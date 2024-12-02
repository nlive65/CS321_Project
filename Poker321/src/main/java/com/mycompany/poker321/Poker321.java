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
import GUI.GUIManager;
import GUI.GUI_STATE;


/**
 *
 * @author Everyone
 */
public class Poker321 {

    public static void main(String[] args) 
    {
        GUIManager gui = new GUIManager();
        // Declaring the rules and deck object
        GameRules rules = new GameRules();
        CardDeck deck = new CardDeck();
        
        // Placeholders for info from GUI
        while(gui.getState() != GUI_STATE.GAMELOOP){
            gui.update();
            if(gui.getResumeGame()){
                //Game manager load the game
            }
        }
        for(int count =0; count < 10; count ++){//Basically just load in case gameloop doesn't work
            try{
                gui.update();
                java.lang.Thread.currentThread().sleep(10);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        String playerName = gui.getUserName();
        int chipAmount = gui.getStartingMoney();
        int rounds = gui.getMaxTurns();
        // Initiate the players
        Player user = new Player(playerName, chipAmount);
        Player jeff = new Player("Jeff", chipAmount);
        Player eliza = new Player("Eliza", chipAmount);
        Player erin = new Player("Erin", chipAmount);
        gui.setMoney(0, chipAmount);
        gui.setMoney(1, chipAmount);        
        gui.setMoney(2,chipAmount);
        gui.setMoney(3,chipAmount);
        gui.setMoney(4,0);

        // Dealer variable will choose who the dealer is
        Player dealer = null;
        
        // Deal based upon the turn. The user will always start, and we will go in a clockwise motion
        // deciding who the next dealer will be
        for(int turn = 1; turn <= rounds && user.getBalance() > 0; turn++)
        {
            gui.unDeal();
            gui.setTurnCount(turn);
            gui.update();
            if(turn == 1 || turn == 5 || turn == 9)
            {
                rules.Deal(deck, user, jeff, eliza, erin);
                gui.deal();//deal to opponents
                //METHOD TO GET hand off player
                dealer = user;
            }
            else if(turn == 2 || turn == 6 || turn == 10)
            {
                rules.Deal(deck, jeff, eliza, erin, user);
                gui.deal();
                dealer = jeff;
            }
            else if(turn == 3 || turn == 7)
            {
                rules.Deal(deck, eliza, erin, user, jeff);
                gui.deal();
                dealer = eliza;
            }
            else
            {
                rules.Deal(deck, erin, user, jeff, eliza);
                gui.deal();
                dealer = erin;
            }
            
            // Declare a card variable for a card drawn from the deck
            Card pulledCard = null;
            
            for(int index = 0; index < 2; index++)
            {
                pulledCard = deck.PullTopCard();
                // !!! This Card will be a card that is displayed in the middle of the table !!!
                
                user.GetCardHand().AddToFullHand(pulledCard);
                jeff.GetCardHand().AddToFullHand(pulledCard);
                eliza.GetCardHand().AddToFullHand(pulledCard);
                erin.GetCardHand().AddToFullHand(pulledCard);
                //PUSH CARHAND TO GUI REVEAL
                
            }
            
            for(int betTurn = 1; betTurn <= 3; betTurn++)
            {
                pulledCard = deck.PullTopCard();
                // This card will be siplayed in the middle of the table
                gui.setTurn(betTurn--);
                user.GetCardHand().AddToFullHand(pulledCard);
                jeff.GetCardHand().AddToFullHand(pulledCard);
                eliza.GetCardHand().AddToFullHand(pulledCard);
                erin.GetCardHand().AddToFullHand(pulledCard);
                
                int userBet = 0;
                int jeffBet = 0;
                int elizaBet = 0;
                int erinBet = 0;
                
                if(dealer.getName() == user.getName())
                {
                    
                    // PLay game with the user as dealer
                    // user makes bet
                    rules.AddToPot(userBet);
                    
                    // jeff Bets
                    rules.AddToPot(jeffBet);
                    
                    // eliza bets
                    rules.AddToPot(elizaBet);
                    
                    // erin bets
                    rules.AddToPot(erinBet);
                }
                else if(dealer.getName() == jeff.getName())
                {
                    // PLay game with jeff as dealer
                    // jeff bets
                    rules.AddToPot(jeffBet);
                    
                    // eliza bets
                    rules.AddToPot(elizaBet);
                    
                    // erin bets
                    rules.AddToPot(erinBet);
                    
                    // user bets
                    rules.AddToPot(userBet);
                }
                else if(dealer.getName() == eliza.getName())
                {
                    // Play with eliza as the dealer
                    // eliza bets
                    rules.AddToPot(elizaBet);
                    
                    // erin bets
                    rules.AddToPot(erinBet);
                    
                    // user bets
                    rules.AddToPot(userBet);
                    
                    // jeff bets
                    rules.AddToPot(jeffBet);
                }
                else
                {
                    // Play with erin as the dealer
                    // erin bets
                    rules.AddToPot(erinBet);
                    
                    // user bets
                    rules.AddToPot(userBet);
                    
                    // jeff bets
                    rules.AddToPot(jeffBet);
                    
                    // eliza bets
                    rules.AddToPot(elizaBet);
                }
            }
            // Showdown
            rules.DeclareWinner(user, jeff, eliza, erin);
            //gui.setWinner()//Get the winner on the GUI
            //rules.ResetPot();
            deck.ResetDeck();
        }
    }
}
