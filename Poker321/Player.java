/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poker;

/**
 *
 * @author Luke
 */

// A basic idea of what the Player class will need, will likely be appended when collaborating with CPU classes

public class Player {
    
    // Variables needed
    private final String name;
    private int balance;
    private int hand;
    private boolean isActive;
    private int currentBet;
    
    
    // Constructor
    public Player(String name, int initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.isActive = true;
        this.currentBet = 0;
    }
    
    
    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    // Method to decrease the player's balance (when making a bet)
    public void decreaseBalance(int amount) {
        if (balance >= amount) {
            balance -= amount;
            currentBet += amount;
        } else {
            System.out.println("Not enough balance to make the bet.");
        }
    }

    // Method to reset player's current bet for a new round
    public void resetBet() {
        currentBet = 0;
    }

    // Method to fold (leave the current round)
    public void fold() {
        isActive = false;
        System.out.println(name + " folds.");
    }

    // Method to check if the player can place a specific bet amount
    public boolean canBet(int amount) {
        return balance >= amount;
    }

    // Display player's current hand (for debugging or in-game display)
    public void displayHand() {
        System.out.println(name + "'s hand: " + hand);
    }
}
