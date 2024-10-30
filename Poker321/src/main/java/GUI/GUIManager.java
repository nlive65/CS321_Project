/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

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
    private PreplaySettingsScene settingsHandler; // Handler for preplay settings scene
    private GameloopScene gameDisplay; // Handler for the game loop scene
    private LeaderboardScene leaderboardHandler; // Handler for the leaderboard scene

    /**
     * Sets the active GUI state.
     * 
     * @param state The new GUI state to activate.
     */
    public void setActiveGUI(GUI_STATE state) {
        // Implementation
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
}

