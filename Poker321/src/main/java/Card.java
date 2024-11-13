/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameloop;

/**
 *
 * @author Sam Tindol
 * 
 * This is will be our most basic class of the game rules. This will just allow
 * us to create cards to add to our decks and hands for the game.
 */
public class Card 
{
    // Basic values for each card
    private int value;
    private char suit;
    
    public Card()
    {
        
    }
    
    /*
    This cass simply needs to just contain these two values to do checks]
    for hands later in the program. This will allow us to let the poker game
    work.
    */
    public int GetValue()
    {
        return value;
    }
    
    public char GetSuit()
    {
        return suit;
    }
}
