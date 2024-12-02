/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import com.mycompany.poker321.GAME_EVENTS;
import java.util.HashSet;
import java.util.Set;
import card.CardHand;
import gameManager.PLAYER_ACTIONS;
/**
 *
 * @author Nick
 */
/**
 * Manages the graphical user interface, handling transitions between different states.
 */
public class GUIManager implements GraphicsHandler {
    private GUI_STATE activeGUI; // The currently active GUI state
    private HomeMenuScene homeHandler; // Handler for the home menu scene
    private Game_settings settingsHandler; // Handler for preplay settings scene
    private GameLoopScene gameDisplay; // Handler for the game loop scene
    private Scene[] scenes;
    private String username;
    private int turnCount;
    private int betAmount;
    private int maxMoney;
    private int maxTurns;
    private boolean saveGame;
    public GUIManager(){        
        this.activeGUI = GUI_STATE.HOME_MENU;
        this.homeHandler = new HomeMenuScene();
        this.settingsHandler = new Game_settings();
        this.gameDisplay = new GameLoopScene(); 
        this.scenes = new Scene[] {homeHandler,settingsHandler,gameDisplay};
        for(int i =0;i<this.scenes.length;i++){
            this.scenes[i].setVisible(false);
        }
        this.setActiveGUI(this.activeGUI);
        this.saveGame = false;
        this.maxMoney = 0;
        this.maxTurns = 0;
        this.username = "";
    }

    public int getStartingMoney(){
        return this.maxMoney;
    }
    public int getMaxTurns(){
        return this.maxTurns;
    }
    public String getUserName(){
        return username;
    }
    
    /**
     * Sets the active GUI state.
     * 
     * @param state The new GUI state to activate.
     */
    public void setActiveGUI(GUI_STATE state) {
        // Implementation
        this.scenes[this.activeGUI.ordinal()].setVisible(false);
        this.activeGUI = state;
        this.scenes[this.activeGUI.ordinal()].setVisible(true);   
    }
    public boolean getSaveGame(){
        return this.saveGame;
    }
    
    public void saveGameAck(){
        this.gameDisplay.setSaveGame(false);
        this.saveGame = false;
    } 
    public boolean getResumeGame(){
        if(this.activeGUI == GUI_STATE.HOME_MENU){
            return homeHandler.getResumeGame();
        }
        return false;
    }
    
    private int turn =0;
    public void update(){
        try {
            GUI_STATE checkTransition = this.scenes[this.activeGUI.ordinal()].getTransition();
            java.lang.Thread.currentThread().sleep(10);
            if(this.activeGUI != checkTransition){
                if(this.activeGUI == GUI_STATE.PREPLAY_SETTINGS){
                    this.username = this.scenes[this.activeGUI.ordinal()].getUserName();
                    this.maxMoney = this.scenes[this.activeGUI.ordinal()].getBetAmount();
                    this.maxTurns = this.scenes[this.activeGUI.ordinal()].getTurnCount();
                }
                this.scenes[this.activeGUI.ordinal()].ResetTransition();
                this.setActiveGUI(checkTransition);
                if(this.activeGUI == GUI_STATE.GAMELOOP){
                    this.scenes[this.activeGUI.ordinal()].setUsername(username);
                    this.scenes[this.activeGUI.ordinal()].setTurnCount(1);
                    this.scenes[this.activeGUI.ordinal()].setTurn(turn);
                }
            }
            else{
                if(this.activeGUI == GUI_STATE.GAMELOOP){
                    runGameloop();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void render(){
        
    }
    
    public void setTurn(int newTurn){
        if(newTurn < 4){
            this.turn = newTurn;
        }
    }
    public void setTurnCount(int newTurn){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.setTurnCount(newTurn);
        }
    }
    public void setMoney(int playerId, int money){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.setPlayerMoney(playerId,money);
        }
    }
    public void deal(){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.dealOpponentCards();
        }
    }
    
    public void unDeal(){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.unDealCards();
        }
    }
    public void reveal(int playerID, CardHand newCard){
        this.gameDisplay.reveal(playerID,newCard);
    }
    private int raiseAmount;
    public int getRaiseAmount(){
        return raiseAmount;
    }
    public PLAYER_ACTIONS getPlayerAction(){
        return this.gameDisplay.getTakenAction();
    }
    public void setWinner(int playerID){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.setWinner(playerID);
        }
    }
    public GUI_STATE getState(){
        return this.activeGUI;
    }
    private void runGameloop(){
        this.saveGame = this.gameDisplay.getSaveGame();
        while(!this.gameDisplay.getNextStart()){ //Wait for player to comprehend the consequences 
            try{
                java.lang.Thread.currentThread().sleep(10);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        if(this.turn == 0 ){
            this.raiseAmount = 0;
            while(this.gameDisplay.getTakenAction() == PLAYER_ACTIONS.IDLE){ //Wait for player to make up their minds fr
                try{
                    java.lang.Thread.currentThread().sleep(10);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            if(this.gameDisplay.getTakenAction() == PLAYER_ACTIONS.RAISE){
                this.raiseAmount = this.gameDisplay.getRaiseAmount();
            }
        }
    }
}

