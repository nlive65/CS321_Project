/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poker;

/**
 *
 * @author Luke
 */

/**IMPORTANT!!! : This class relies heavily on collaboration with graphics classes that will be
implemented later in development, this is not a reflection of the finished product. The graphics
class will contain the necessary buttons to work with these functions. **/


public class MenuInteraction {
    // Member Variables

    // Constructor
    public MenuInteraction() 
    {
        
    }

    // Display the main menu options
    public void displayMainMenu() 
    {
       
    }

    // Handle menu input and navigate to the chosen action
    private void handleMenuChoice(int choice) 
    {
   
    }

    // Option to start a new game
    private void startNewGame() 
    {
        // Logic to set up players, shuffle deck, and initialize the game. This will
        // be added with collaboration from GameLoop
    }

    // Load a previously saved game state
    private void loadGame() 
    {
        /* Needs collaboration with CPU superclass to load progress of their game state as well.
           When finished, will allow player to load a file that resumes a previous game.
        */
        
        // Define the file path or let the user provide one
    }
    
    // Option to view the game rules
    private void viewGameRules() 
    {
    // Return to the menu after showing rules
    }

    // Option to exit the game
    private void exitGame() 
    {
        
        /*
        Check to see if the player is in-game, if so, save the state of the game
        to a text file to be read from in the loadGame function. If not, close.
        */
        
        
        
        System.exit(0);  // Terminate the application
    }
}
