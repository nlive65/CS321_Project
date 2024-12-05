/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 * Abstract class representing a scene in the application.
 * <p>This class serves as the base class for all scenes in the application (e.g., the home menu, game loop, settings). 
 * It provides the structure for scene transitions and methods for interacting with the current scene.</p>
 * <p>Subclasses of this class must implement methods related to transitioning between states, rendering, and updating the scene.</p>
 * 
 * @author Nick
 */
public abstract class Scene extends javax.swing.JFrame {
    /**
     * Renders the scene.
     */
    //public abstract void render();

    /**
     * Updates the scene.
     */
    //public abstract void update();
    
    
    /**
     * Returns the current transition state of the scene.
     * <p>Subclasses must provide an implementation to determine the next state or transition of the scene.</p>
     * 
     * @return The transition state of the scene, represented by a {@link GUI_STATE} value.
     */
    public abstract GUI_STATE getTransition();
    
    /**
     * Resets the transition state of the scene.
     * <p>Subclasses must provide an implementation to reset the scene's transition to a default state.</p>
     */
    public abstract void ResetTransition();
    
    /**
     * Returns the current username associated with the scene.
     * <p>This is a default implementation that returns an empty string. Subclasses may override this method to provide the actual username.</p>
     * 
     * @return The username as a string.
     */
    public String getUserName(){return "";}
    
     /**
     * Returns the current bet amount associated with the scene.
     * <p>This is a default implementation that returns 0. Subclasses may override this method to provide the actual bet amount.</p>
     * 
     * @return The bet amount as an integer.
     */
    public int getBetAmount(){return 0;}
    
    /**
     * Returns the current turn count associated with the scene.
     * <p>This is a default implementation that returns 0. Subclasses may override this method to provide the actual turn count.</p>
     * 
     * @return The turn count as an integer.
     */
    public int getTurnCount(){return 0;}
    
    
    /**
     * Sets the username for the scene.
     * <p>This is a default implementation that does nothing. Subclasses may override this method to set the actual username.</p>
     * 
     * @param name The username to set.
     */
    public void setUsername(String name){}
    
    /**
     * Sets the turn count for the scene.
     * <p>This is a default implementation that does nothing. Subclasses may override this method to set the actual turn count.</p>
     * 
     * @param turn The turn count to set.
     */
    public void setTurnCount(int turn){}
    
     /**
     * Sets the current turn for the scene.
     * <p>This is a default implementation that does nothing. Subclasses may override this method to set the actual turn.</p>
     * 
     * @param turn The turn number to set.
     */
    public void setTurn(int turn){}
}
