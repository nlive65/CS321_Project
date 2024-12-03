/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poker321;

// Including needed classes
import card.Card;
import card.CardHand;
import card.CardDeck;
import card.CardLogic;
import gameManager.GameRules;
import gameManager.Player;
import GUI.GUIManager;
import GUI.GUI_STATE;
import gameManager.PLAYER_ACTIONS;

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
        while(true){
            
        
        // Placeholders for info from GUI
        while(gui.getState() != GUI_STATE.GAMELOOP){
            gui.update();
            if(gui.getResumeGame()){
                //Game manager load the game
            }
        }
        String playerName = gui.getUserName();
        int chipAmount = gui.getStartingMoney()*10;
        int rounds = gui.getMaxTurns();
        // Initiate the players
        Player user = new Player(playerName, chipAmount, 0);
        Player jeff = new Player("Jeff", chipAmount, 1);
        Player eliza = new Player("Eliza", chipAmount, 2);
        Player erin = new Player("Erin", chipAmount, 3);
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
                gui.setTurn(0);
                gui.deal();//deal to opponents
                gui.reveal(0,user.GetCardHand());
                //METHOD TO GET hand off player
                dealer = user;
            }
            else if(turn == 2 || turn == 6 || turn == 10)
            {
                rules.Deal(deck, jeff, eliza, erin, user);
                gui.setTurn(1);
                gui.deal();
                gui.reveal(0,user.GetCardHand());
                dealer = jeff;
                
            }
            else if(turn == 3 || turn == 7)
            {
                rules.Deal(deck, eliza, erin, user, jeff);
                gui.setTurn(2);
                gui.deal();
                gui.reveal(0,user.GetCardHand());
                dealer = eliza;
                
            }
            else
            {
                rules.Deal(deck, erin, user, jeff, eliza);
                gui.setTurn(3);
                gui.deal();
                gui.reveal(0,user.GetCardHand());
                dealer = erin;
                
            }
            //ORIGINAL BETTING NEEDS TO GO HERE
            
            // Declare a card variable for a card drawn from the deck
            Card pulledCard = null;
            CardHand flop = new CardHand();
            for(int index = 0; index < 3; index++)
            {
                pulledCard = deck.PullTopCard();
                flop.AddToFullHand(pulledCard);
                // !!! This Card will be a card that is displayed in the middle of the table !!!
                user.GetCardHand().AddToFullHand(pulledCard);
                jeff.GetCardHand().AddToFullHand(pulledCard);
                eliza.GetCardHand().AddToFullHand(pulledCard);
                erin.GetCardHand().AddToFullHand(pulledCard);
                //System.out.println(flop.GetFullHand().get(index));
            }
            gui.reveal(4, flop);
            for(int betTurn = 1; betTurn <= 3; betTurn++)
            {   
                if(betTurn !=1){
                    pulledCard = deck.PullTopCard();
                // This card will be siplayed in the middle of the table
                    user.GetCardHand().AddToFullHand(pulledCard);
                    jeff.GetCardHand().AddToFullHand(pulledCard);
                    eliza.GetCardHand().AddToFullHand(pulledCard);
                    erin.GetCardHand().AddToFullHand(pulledCard);
                    CardHand newHand = new CardHand();
                    newHand.AddToFullHand(pulledCard);
                    gui.reveal(betTurn+3,newHand);
                }
                int userBet = 0;
                int jeffBet = 0;
                int elizaBet = 0;
                int erinBet = 0;
                
                if(dealer.getName() == user.getName())
                {
                    
                    // PLay game with the user as dealer
                    // user makes bet
                    gui.setTurn(0);
                    PLAYER_ACTIONS action = gui.awaitPlayerAction();
                    //check for raise or bet amount
                    rules.AddToPot(userBet);
                    
                    // jeff Bets
                    
                    gui.setTurn(1);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(jeffBet);
                    
                    // eliza bets
                    
                    gui.setTurn(2);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(elizaBet);
                    
                    // erin bets
                    
                    gui.setTurn(3);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(erinBet);
                }
                else if(dealer.getName() == jeff.getName())
                {
                    // PLay game with jeff as dealer
                    // jeff bets
                    
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(jeffBet);
                    
                    // eliza bets
                    gui.setTurn(2);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(elizaBet);
                    
                    
                    // erin bets
                    gui.setTurn(3);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(erinBet);
                    
                    
                    // user bets
                    gui.setTurn(0);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    PLAYER_ACTIONS action = gui.awaitPlayerAction();
                    
                    //check for raise or bet amount
                    rules.AddToPot(userBet);
                }
                else if(dealer.getName() == eliza.getName())
                {
                    // Play with eliza as the dealer
                    // eliza bets
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(elizaBet);
                    
                    // erin bets
                    gui.setTurn(3);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(erinBet);
                    
                    // user bets
                    gui.setTurn(0);
                    PLAYER_ACTIONS action = gui.awaitPlayerAction();
                    //check for raise or bet amount
                    rules.AddToPot(userBet);
                    
                    
                    // jeff bets
                    gui.setTurn(1);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(jeffBet);
                }
                else
                {
                    // Play with erin as the dealer
                    // erin bets
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(erinBet);
                    
                    // user bets
                    gui.setTurn(0);
                    PLAYER_ACTIONS action = gui.awaitPlayerAction();
                    //check for raise or bet amount
                    rules.AddToPot(userBet);
                    
                    // jeff bets
                    gui.setTurn(1);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(jeffBet);
                    
                    // eliza bets
                    gui.setTurn(2);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    rules.AddToPot(elizaBet);
                }
            }
            // Showdown
            //CHECK WHO DIDN'T FOLD
            CardHand hand = new CardHand();
            if(jeff.isActive()){
                gui.reveal(1,jeff.GetCardHand());
            }
            if(eliza.isActive()){
                gui.reveal(2,eliza.GetCardHand());
            }
            if(erin.isActive()){
                gui.reveal(3, erin.GetCardHand());
            }
            int winner  = rules.DeclareWinner(user, jeff, eliza, erin);
            System.out.println(winner);
            gui.setWinner(winner);//Get the winner on the GUI
            //rules.ResetPot();
            deck.ResetDeck();
            gui.update();
        }
        gui.setActiveGUI(GUI_STATE.HOME_MENU);
        gui.update();
        }
        
    }
}
