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

import java.util.Scanner;


public class MenuInteraction {
    // Member Variables
    private final Scanner scanner;

    // Constructor
    public MenuInteraction() {
        this.scanner = new Scanner(System.in);  // For user input
    }

    // Display the main menu options
    public void displayMainMenu() {
        System.out.println("Welcome to Texas Hold 'Em Poker!");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. View Game Rules");
        System.out.println("4. Exit");
        System.out.print("Please choose an option: ");
        
        int choice = scanner.nextInt();
        handleMenuChoice(choice);
    }

    // Handle menu input and navigate to the chosen action
    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> startNewGame();
            case 2 -> loadGame();
            case 3 -> viewGameRules();
            case 4 -> exitGame();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                displayMainMenu();
            }
        }
    }

    // Option to start a new game
    private void startNewGame() {
        System.out.println("Starting a new game...");
        // Logic to set up players, shuffle deck, and initialize the game
    }

    // Load a previously saved game state
    private void loadGame() {
        System.out.println("Loading saved game...");
        
        /* Needs collaboration with Game
        
        // Define the file path or let the user provide one
        String fileName = "saved_game.dat";  // Example path for the saved game file
        
       
    }
    
    // Option to view the game rules
    private void viewGameRules() {
        System.out.println("Texas Hold 'Em Poker Rules:");
        System.out.println("1. Each player is dealt two cards face down.");
        System.out.println("2. Five community cards are dealt face up.");
        System.out.println("3. Players must make the best hand using any combination of their two cards and the five community cards.");
        System.out.println("4. Betting rounds occur before and after the flop, turn, and river.");
        System.out.println("5. The player with the best hand at the showdown wins the pot.");
        System.out.println();
        displayMainMenu();  // Return to the menu after showing rules
    }

    // Option to exit the game
    private void exitGame() {
        System.out.println("Exiting the game. Thank you for playing!");
        
        /*
        Check to see if the player is in-game, if so, save the state of the game
        to a text file to be read from in the loadGame function. If not, close.
        */
        
        
        
        System.exit(0);  // Terminate the application
    }
}
