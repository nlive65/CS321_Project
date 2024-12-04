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
import CPU.HardCPU;


import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @author Everyone
 */
public class Poker321 {

    private static void saveGameToJson(Player user, Player jeff, Player eliza, Player erin, GameRules rules, int rounds, int round, String fileName){
        JSONObject gameState = new JSONObject();
        
        gameState.put("rounds",rounds);
        gameState.put("pot",rules.GetPot());
        gameState.put("round",round);
        
        JSONObject userObj = createPlayerObject(user);
        JSONObject jeffObj = createPlayerObject(jeff);
        JSONObject elizaObj = createPlayerObject(eliza);
        JSONObject erinObj = createPlayerObject(erin);
        
        gameState.put("user",userObj);
        gameState.put("jeff",jeffObj);
        gameState.put("eliza",elizaObj);
        gameState.put("erin",erinObj);
        
        
       try(FileWriter file = new FileWriter(fileName)){
           file.write(gameState.toString(4));
       }catch(IOException e){
           e.printStackTrace();
       }
    }
    
    private static JSONObject createPlayerObject(Player player){
        JSONObject playerObj = new JSONObject();
        
        playerObj.put("name", player.getName());
        playerObj.put("balance",player.getBalance());
        playerObj.put("isActive", player.isActive());
        
        /*
        JSONArray handObj = new JSONArray();
        for(int i =0; i<2;i++){
            JSONObject cardObj = new JSONObject();
            cardObj.put("suit",player.GetCardHand().GetTwoCardHand().get(i).GetSuit());
            cardObj.put("value",player.GetCardHand().GetTwoCardHand().get(i).GetValue());
            handObj.put(cardObj);
        }
        playerObj.put("cards", handObj);
        */
        return playerObj;
    }
    
    public static void main(String[] args) 
    {
        GUIManager gui = new GUIManager();
        // Declaring the rules and deck object
        GameRules rules = new GameRules();
        CardDeck deck = new CardDeck();
        
        HardCPU cpuLogic = new HardCPU("", 0, 0);
        while(true){
            
        boolean didResume = false;
        Player user = new Player("",0,0);
        Player jeff = new Player("",0,0);
        Player eliza = new Player("",0,0);
        Player erin = new Player("",0,0);
        String playerName = "";
        int chipAmount = 0;
        int rounds = 0;
        int initTurn = 1;
        // Placeholders for info from GUI
        while(gui.getState() != GUI_STATE.GAMELOOP){
            gui.update();
            if(gui.getResumeGame()){
                
                //Game manager load the game
                didResume = true;
                try{
                  ObjectMapper mapper = new ObjectMapper();
                  File jsonFile = new File("resumeGameSave.json");
                  JsonNode rootNode = mapper.readTree(jsonFile);
                  
                  rounds = rootNode.path("rounds").asInt();
                  initTurn = rootNode.path("round").asInt();
                  
                  JsonNode userNode = rootNode.path("user");
                  playerName = userNode.path("name").asText();
                  int playerMoney = userNode.path("balance").asInt();
                  int jeffMoney = rootNode.path("jeff").path("balance").asInt();
                  int elizaMoney = rootNode.path("eliza").path("balance").asInt();
                  int erinMoney = rootNode.path("erin").path("balance").asInt();
                  user = new Player(playerName,playerMoney,0);
                  jeff = new Player("Jeff",jeffMoney,1);
                  eliza = new Player("Eliza",elizaMoney,2);
                  erin = new Player("Erin",erinMoney,3);
                  gui.setUsername(playerName);
                  
                  gui.setMoney(0,playerMoney);
                  gui.setMoney(1,jeffMoney);
                  gui.setMoney(2,elizaMoney);
                  gui.setMoney(3,erinMoney);
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        if(!didResume){
            playerName = gui.getUserName();
            chipAmount = gui.getStartingMoney()*10;
            rounds = gui.getMaxTurns();
            // Initiate the players
            user = new Player(playerName, chipAmount, 0);
            jeff = new Player("Jeff", chipAmount, 1);
            eliza = new Player("Eliza", chipAmount, 2);
            erin = new Player("Erin", chipAmount, 3);
            gui.setMoney(0, chipAmount);
            gui.setMoney(1, chipAmount);        
            gui.setMoney(2,chipAmount);
            gui.setMoney(3,chipAmount);
            gui.setMoney(4,0);
        }

        // Dealer variable will choose who the dealer is
        Player dealer = null;
        
        // Deal based upon the turn. The user will always start, and we will go in a clockwise motion
        // deciding who the next dealer will be
        for(int turn = initTurn; turn <= rounds && user.getBalance() > 0; turn++)
        {
            if(user.getBalance() == 0)
            {
                user.fold();
            }
            if(jeff.getBalance() == 0)
            {
                jeff.fold();
            }
            if(eliza.getBalance() == 0)
            {
                eliza.fold();
            }
            if(erin.getBalance() == 0)
            {
                erin.fold();
            }
            gui.unDeal();
            gui.setTurnCount(turn);
            gui.update();
            if(gui.getSaveGame()){
                saveGameToJson(user,jeff,eliza,erin,rules,rounds,turn,"resumeGameSave.json");
                gui.saveGameAck();
            }
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
                    
                    if(user.isActive() && user.getBalance() > 0)
                    {
                        PLAYER_ACTIONS action = gui.awaitPlayerAction();
                        //check for raise or bet amount
                        // USER
                        if(action == PLAYER_ACTIONS.FOLD)
                        {
                            user.fold();
                            gui.fold(0);
                        }
                        if(user.isActive())
                        {
                            userBet = gui.getRaiseAmount();
                    
                            while(userBet > user.getBalance())
                            {
                                action = gui.awaitPlayerAction();
                                userBet = gui.getRaiseAmount();
                            }
                            rules.AddToPot(userBet);
                            gui.setMoney(0, user.getBalance() - userBet);
                            gui.setMoney(4, rules.GetPot() + userBet);
                            user.decreaseBalance(userBet);
                        }
                    }
                    // jeff Bets
                    
                    gui.setTurn(1);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // JEFF
                    if(jeff.isActive())
                    {
                        jeffBet = cpuLogic.evaluateHandStrength(jeff);
                        
                        rules.AddToPot(jeffBet);
                        gui.setMoney(1, jeff.getBalance() - jeffBet);
                        gui.setMoney(4, rules.GetPot() + jeffBet);
                        jeff.decreaseBalance(jeffBet);
                    }
                    // eliza bets
                    
                    gui.setTurn(2);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // ELIZA
                    if(eliza.isActive())
                    {
                        elizaBet = cpuLogic.evaluateHandStrength(eliza);
                        rules.AddToPot(elizaBet);
                        gui.setMoney(2, eliza.getBalance() - elizaBet);
                        gui.setMoney(4, rules.GetPot() + elizaBet);
                        eliza.decreaseBalance(elizaBet);
                    }
                    // erin bets
                    
                    gui.setTurn(3);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // ERIN
                    if(erin.isActive())
                    {
                        erinBet = cpuLogic.evaluateHandStrength(erin);
                        rules.AddToPot(erinBet);
                        gui.setMoney(3, erin.getBalance() - erinBet);
                        gui.setMoney(4, rules.GetPot() + erinBet);
                        erin.decreaseBalance(erinBet);
                    }
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
                    // JEFF
                    if(jeff.isActive())
                    {
                        jeffBet = cpuLogic.evaluateHandStrength(jeff);
                        rules.AddToPot(jeffBet);
                        gui.setMoney(1, jeff.getBalance() - jeffBet);
                        gui.setMoney(4, rules.GetPot() + jeffBet);
                        jeff.decreaseBalance(jeffBet);
                    }
                    // eliza bets
                    gui.setTurn(2);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // ELIZA
                    if(eliza.isActive())
                    {
                        elizaBet = cpuLogic.evaluateHandStrength(eliza);
                        rules.AddToPot(elizaBet);
                        gui.setMoney(2, eliza.getBalance() - elizaBet);
                        gui.setMoney(4, rules.GetPot() + elizaBet);
                        eliza.decreaseBalance(elizaBet);
                    }
                    
                    // erin bets
                    gui.setTurn(3);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // ERIN
                    if(erin.isActive())
                    {
                        erinBet = cpuLogic.evaluateHandStrength(erin);
                        rules.AddToPot(erinBet);
                        gui.setMoney(3, erin.getBalance() - erinBet);
                        gui.setMoney(4, rules.GetPot() + erinBet);
                        erin.decreaseBalance(erinBet);
                    }
                    
                    // user bets
                    gui.setTurn(0);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    if(user.isActive())
                    {
                        PLAYER_ACTIONS action = gui.awaitPlayerAction();
                        //check for raise or bet amount
                        // USER
                        if(action == PLAYER_ACTIONS.FOLD)
                        {
                            user.fold();
                        }
                        if(user.isActive())
                        {
                            userBet = gui.getRaiseAmount();
                    
                            while(userBet > user.getBalance())
                            {
                                action = gui.awaitPlayerAction();
                                userBet = gui.getRaiseAmount();
                            }
                            rules.AddToPot(userBet);
                            gui.setMoney(0, user.getBalance() - userBet);
                            gui.setMoney(4, rules.GetPot() + userBet);
                            user.decreaseBalance(userBet);
                        }
                    }
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
                    // ELIZA
                    if(eliza.isActive())
                    {
                        elizaBet = cpuLogic.evaluateHandStrength(eliza);
                        rules.AddToPot(elizaBet);
                        gui.setMoney(2, eliza.getBalance() - elizaBet);
                        gui.setMoney(4, rules.GetPot() + elizaBet);
                        eliza.decreaseBalance(elizaBet);
                    }
                    // erin bets
                    gui.setTurn(3);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // ERIN
                    if(erin.isActive())
                    {
                        erinBet = cpuLogic.evaluateHandStrength(erin);
                        rules.AddToPot(erinBet);
                        gui.setMoney(3, erin.getBalance() - erinBet);
                        gui.setMoney(4, rules.GetPot() + erinBet);
                        erin.decreaseBalance(erinBet);
                    }
                    // user bets
                    gui.setTurn(0);
                   if(user.isActive())
                    {
                        PLAYER_ACTIONS action = gui.awaitPlayerAction();
                        //check for raise or bet amount
                        // USER
                        if(action == PLAYER_ACTIONS.FOLD)
                        {
                            user.fold();
                        }
                        if(user.isActive())
                        {
                            userBet = gui.getRaiseAmount();
                    
                            while(userBet > user.getBalance())
                            {
                                action = gui.awaitPlayerAction();
                                userBet = gui.getRaiseAmount();
                            }
                            rules.AddToPot(userBet);
                            gui.setMoney(0, user.getBalance() - userBet);
                            gui.setMoney(4, rules.GetPot() + userBet);
                            user.decreaseBalance(userBet);
                        }
                    }
                    
                    
                    // jeff bets
                    gui.setTurn(1);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // JEFF
                    if(jeff.isActive())
                    {
                        jeffBet = cpuLogic.evaluateHandStrength(jeff);
                        rules.AddToPot(jeffBet);
                        gui.setMoney(1, jeff.getBalance() - jeffBet);
                        gui.setMoney(4, rules.GetPot() + jeffBet);
                        jeff.decreaseBalance(jeffBet);
                    }
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
                    // ERIN
                    if(erin.isActive())
                    {
                        erinBet = cpuLogic.evaluateHandStrength(erin);
                        rules.AddToPot(erinBet);
                        gui.setMoney(3, erin.getBalance() - erinBet);
                        gui.setMoney(4, rules.GetPot() + erinBet);
                        erin.decreaseBalance(erinBet);
                    }
                    // user bets
                    gui.setTurn(0);
                    if(user.isActive())
                    {
                        PLAYER_ACTIONS action = gui.awaitPlayerAction();
                        //check for raise or bet amount
                        // USER
                        if(action == PLAYER_ACTIONS.FOLD)
                        {
                            user.fold();
                        }
                        if(user.isActive())
                        {
                            userBet = gui.getRaiseAmount();
                    
                            while(userBet > user.getBalance())
                            {
                                action = gui.awaitPlayerAction();
                                userBet = gui.getRaiseAmount();
                            }
                            rules.AddToPot(userBet);
                            gui.setMoney(0, user.getBalance() - userBet);
                            gui.setMoney(4, rules.GetPot() + userBet);
                            user.decreaseBalance(userBet);
                        }
                    }
                    
                    // jeff bets
                    gui.setTurn(1);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // JEFF
                    if(jeff.isActive())
                    {
                        jeffBet = cpuLogic.evaluateHandStrength(jeff);
                        rules.AddToPot(jeffBet);
                        gui.setMoney(1, jeff.getBalance() - jeffBet);
                        gui.setMoney(4, rules.GetPot() + jeffBet);
                        jeff.decreaseBalance(jeffBet);
                    }
                    // eliza bets
                    gui.setTurn(2);
                    try{
                        java.lang.Thread.currentThread().sleep(250);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    // ELIZA
                    if(eliza.isActive())
                    {
                        elizaBet = cpuLogic.evaluateHandStrength(eliza);
                        rules.AddToPot(elizaBet);
                        gui.setMoney(2, eliza.getBalance() - elizaBet);
                        gui.setMoney(4, rules.GetPot() + elizaBet);
                        eliza.decreaseBalance(elizaBet);
                    }
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
            gui.setMoney(0, user.getBalance());
            gui.setMoney(1, jeff.getBalance());
            gui.setMoney(2, eliza.getBalance());
            gui.setMoney(3, erin.getBalance());
            gui.setMoney(4, 0);
            System.out.println(winner);
            gui.setWinner(winner);//Get the winner on the GUI
            rules.ResetPot();
            deck.ResetDeck();
            gui.update();
            user.resetFold();
            
            user.ResetPlayerHand();
            jeff.ResetPlayerHand();
            eliza.ResetPlayerHand();
            erin.ResetPlayerHand();
        }
        gui.setActiveGUI(GUI_STATE.HOME_MENU);
        gui.update();
        }
        
    }
}
