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
 * The GUIManager class is responsible for managing the graphical user interface (GUI) of the poker game.
 * It handles transitions between different states (such as the home menu, game settings, and the main game loop),
 * user interactions, and updates the display accordingly.
 * <p>
 * The GUIManager interacts with different scene handlers (e.g., home menu, game settings, game loop) to display
 * the relevant content based on the current state of the game. It also tracks the player's username, bet amount,
 * and other game-related settings.
 * </p>
 * <p>
 * This class implements the {@link GraphicsHandler} interface, which requires the implementation of methods 
 * for managing the graphical interface, updating the game state, and handling player actions.
 * </p>
 * 
 * @author Nick
 */

public class GUIManager implements GraphicsHandler {
    /**
     * The currently active GUI state.
     */
    private GUI_STATE activeGUI; 
    
     /**
     * Handler for the home menu scene.
     */
    private HomeMenuScene homeHandler; 
    
    /**
     * Handler for the pre-play settings scene.
     */
    private Game_settings settingsHandler; 
    
    /**
     * Handler for the game loop scene.
     */
    private GameLoopScene gameDisplay;
    
    /**
     * Array of all GUI scenes.
     */
    private Scene[] scenes;
    
    /**
     * The username of the current player.
     */
    private String username; 
    
    /**
     * The number of rounds to play.
     */
    private int turnCount; 
    
    /**
     * The amount of money the player has bet.
     */
    private int betAmount; 
    
    /**
     * The starting amount of money for the player.
     */
    private int maxMoney;
    
    /**
     * The maximum number of rounds to be played.
     */
    private int maxTurns; 
    
    /**
     * Flag to determine if the game should be saved.
     */
    private boolean saveGame;
    
    /**
     * Constructs a new GUIManager and initializes all the GUI scenes.
     */
    public GUIManager(){        
        this.activeGUI = GUI_STATE.HOME_MENU;
        this.homeHandler = new HomeMenuScene();
        this.settingsHandler = new Game_settings();
        this.gameDisplay = new GameLoopScene(); 
        this.scenes = new Scene[] {homeHandler,settingsHandler,gameDisplay};
        //Set all scenes to be invisible initially
        for(int i =0;i<this.scenes.length;i++){
            this.scenes[i].setVisible(false);
        }
        //set active gui to home menu
        this.setActiveGUI(this.activeGUI);
        
        this.saveGame = false;
        this.maxMoney = 0;
        this.maxTurns = 0;
        this.username = "";
    }
    
     /**
     * Returns the starting amount of money for the player.
     * 
     * @return The starting amount of money.
     */
    public int getStartingMoney(){
        return this.maxMoney;
    }
    
    /**
     * Returns the maximum number of rounds in the game.
     * 
     * @return The maximum number of rounds.
     */
    public int getMaxTurns(){
        return this.maxTurns;
    }
    
    /**
     * Returns the username of the current player.
     * 
     * @return The player's username.
     */
    public String getUserName(){
        return username;
    }
    
    
    /**
     * Folds the specified player's hand in the game loop.
     * 
     * @param playerId The ID of the player who wants to fold.
     */

    public void fold(int playerId){
        this.gameDisplay.fold(playerId);
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
    
     /**
     * Returns whether the game should be saved.
     * 
     * @return true if the game should be saved, false otherwise.
     */
    public boolean getSaveGame(){
        return this.saveGame;
    }
    
     /**
     * Acknowledges the game save request and resets the save flag.
     */
    public void saveGameAck(){
        this.gameDisplay.setSaveGame(false);
        this.saveGame = false;
    } 
    
    /**
     * Checks if the player wants to resume the game from the home menu.
     * 
     * @return true if the player chooses to resume the game, false otherwise.
     */
    public boolean getResumeGame(){
        return homeHandler.getResumeGame();
    }
    
    /**
     * Sets the username for the user if the game is resumed
     * @param newName the new username for the gui to use
     */
    public void setUsername(String newName){
        this.username = newName;
    }
    
    /**
     * The player turn 
     */
    private int turn =0;
    
    /**
     * Updates the GUI based on the current game state and transitions between different scenes.
     * This method checks if the current state requires a transition and updates the active GUI accordingly.
     */
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
                    //this.scenes[this.activeGUI.ordinal()].setTurn(turn);
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
    
    /**
     * Renders the current state of the game (method stub).
     */
    public void render(){
       //Rendering handled by scene frame 
    }
    
    /**
     * Sets the turn number for the game.
     * 
     * @param newTurn The new turn number to set. (PlayerId)
     */
    public void setTurn(int newTurn){
        if(newTurn < 4){
            this.turn = newTurn;
            this.gameDisplay.setTurn(newTurn);
        }
    }
    
    /**
     * Sets the total number of turns for the game loop.
     * 
     * @param newTurn The new round number
     */
    public void setTurnCount(int newTurn){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.setTurnCount(newTurn);
        }
    }
    
    /**
     * Sets the amount of money for a specific player.
     * 
     * @param playerId The ID of the player.
     * @param money The amount of money to set for the player.
     */
    public void setMoney(int playerId, int money){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.setPlayerMoney(playerId,money);
        }
    }
    
     /**
     * Deals the cards to the opponent(s) in the game loop.
     */
    public void deal(){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.dealOpponentCards();
        }
    }
    
     /**
     * removes the cards from the game.
     */
    public void unDeal(){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.unDealCards();
        }
    }
    
     /**
     * Reveals the specified player's cards in the game loop.
     * 
     * @param playerID The ID of the player whose cards will be revealed.
     * @param newCard The new card hand to reveal.
     */
    public void reveal(int playerID, CardHand newCard){
        this.gameDisplay.reveal(playerID,newCard);
    }
    
    /**
     * Integer to hold the amount of money the player would like to bet
     */
    private int raiseAmount;
    
     /**
     * Gets the amount raised in the game.
     * 
     * @return The raised amount.
     */
    public int getRaiseAmount(){
        return raiseAmount;
    }
    
    /**
     * Gets the current action taken by the player during the game loop.
     * 
     * @return The player's action.
     */
    public PLAYER_ACTIONS getPlayerAction(){
        return this.gameDisplay.getTakenAction();
    }
    
     /**
     * Waits for the player to make a decision during their turn, such as raising, folding, or calling.
     * 
     * @return The action taken by the player (e.g., RAISE, FOLD).
     */
    public PLAYER_ACTIONS awaitPlayerAction(){
        this.raiseAmount = 0;
        this.gameDisplay.setPlayerAction();
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
        return this.gameDisplay.getTakenAction();
    }
    
    /**
     * Sets the winner of the current round.
     * 
     * @param playerID The ID of the winning player.
     */
    public void setWinner(int playerID){
        if(this.activeGUI == GUI_STATE.GAMELOOP){
            this.gameDisplay.setWinner(playerID);
            while(!this.gameDisplay.getNextStart()){ //Wait for player to comprehend the consequences 
                try{
                    java.lang.Thread.currentThread().sleep(10);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    
     /**
     * Returns the current active GUI state.
     * 
     * @return The current GUI state.
     */
    public GUI_STATE getState(){
        return this.activeGUI;
    }
    
    /**
     * Runs the game loop, checking for transitions and updating the GUI accordingly.
     */
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
    }
}

