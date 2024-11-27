/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import com.mycompany.poker321.GAME_EVENTS;
import java.util.HashSet;
import java.util.Set;
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

    private int turn =0;
    public void update(){
        try {
            GUI_STATE checkTransition = this.scenes[this.activeGUI.ordinal()].getTransition();
            java.lang.Thread.currentThread().sleep(10);
            if(this.activeGUI != checkTransition){
                if(this.activeGUI == GUI_STATE.PREPLAY_SETTINGS){
                    this.username = this.scenes[this.activeGUI.ordinal()].getUserName();
                    this.betAmount = this.scenes[this.activeGUI.ordinal()].getBetAmount();
                    this.turnCount = this.scenes[this.activeGUI.ordinal()].getTurnCount();
                }
                this.scenes[this.activeGUI.ordinal()].ResetTransition();
                this.setActiveGUI(checkTransition);
                if(this.activeGUI == GUI_STATE.GAMELOOP){
                    this.scenes[this.activeGUI.ordinal()].setUsername(username);
                    this.scenes[this.activeGUI.ordinal()].setTurnCount(1);
                    this.scenes[this.activeGUI.ordinal()].setTurn(turn);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void render(){
        
    }
}

