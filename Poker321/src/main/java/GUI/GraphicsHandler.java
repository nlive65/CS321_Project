/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GUI;

/**
 * Interface for rendering graphics in the application.
 * <p>This interface defines the method required for any class that is responsible for rendering graphics. 
 * Any class implementing this interface must provide its own implementation of the {@link #render()} method, 
 * which will handle the drawing or rendering of the graphical state.</p>
 * 
 * @author Nick
 */
public interface GraphicsHandler {
    /**
     * Renders the current graphical state.
     */
    void render();
}

