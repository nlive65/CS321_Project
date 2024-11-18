/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import com.mycompany.poker321.GAME_EVENTS;
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
    GUIManager(){        
        this.activeGUI = GUI_STATE.HOME_MENU;
        this.homeHandler = new HomeMenuScene();
        this.settingsHandler = new Game_settings();
        this.gameDisplay = new GameLoopScene(); 
        this.scenes = new Scene[] {homeHandler,settingsHandler,gameDisplay};
    }
    /**
     * Sets the active GUI state.
     * 
     * @param state The new GUI state to activate.
     */
    public void setActiveGUI(GUI_STATE state) {
        // Implementation
        if(state != this.activeGUI){            
            this.scenes[this.activeGUI.ordinal()].setVisible(false);
            this.activeGUI = state;
            this.scenes[this.activeGUI.ordinal()].setVisible(true);   
        }
    }

    /**
     * Updates the GUI based on the specified event and command.
     * 
     * @param eventType The type of game event that occurred.
     * @param command The command associated with the event.
     */
    public void update(GAME_EVENTS eventType, String command) {
        // Implementation
    }
    public void render(){
        
    }
}

