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
 * Abstract class representing a scene in the application.
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
    
    public abstract GUI_STATE getTransition();
    
    public abstract void ResetTransition();
}
