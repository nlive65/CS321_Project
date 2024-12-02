package gameManager;

import card.CardHand;

/**
 *
 * @author Luke
 */

// A basic idea of what the Player class will need, will likely be appended when collaborating with CPU classes
// Will require much collaboration with GameLoop superclass to assign cards, winning hands, etc.

public class Player {
    
    // Variables needed
    private  String name;
    private int balance;
    private CardHand hand;
    private boolean isActive;
    private int currentBet;
    private int turn;
    
    
    // Constructor
    public Player(String name, int initialBalance) 
    {
        this.name = name;
        this.turn = 0;
        this.balance = initialBalance;
        hand = new CardHand();
        this.isActive = true;
        this.currentBet = 0;
    }
    
    
    // Getters and Setters
    public String getName() 
    {
        return name;
    }
    
    public void setName(String inputName)
    {
        name = inputName;
    }

    public int getTurn()
    {
        return turn;
    }
    
    public void setTurn(int turn)
    {
        this.turn = turn;
    }
    public CardHand GetCardHand()
    {
        return hand;
    }
    
    public int getBalance() 
    {
        return balance;
    }

    public void setBalance(int balance) 
    {
        this.balance = balance;
    }

    public boolean isActive() 
    {
        return isActive;
    }

    public void setActive(boolean active) 
    {
        this.isActive = active;
    }

    public int getCurrentBet() 
    {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) 
    {
        this.currentBet = currentBet;
    }

    // Method to decrease the player's balance (when making a bet)
    public void decreaseBalance(int amount) 
    {
        if (amount <= balance)
        {
            balance -= amount;
            currentBet += amount;
        }
        else
        {
            throw new IllegalArgumentException("Insufficient balance to place bet");
        }
    }
    
    public void increaseBalance(int amount)
    {
        balance += amount;
    }

    // Method to reset player's current bet for a new round
    public void resetBet() 
    {
        currentBet = 0;
    }

    // Method to check if the player can place a specific bet amount
    public boolean canBet(int amount) 
    {
        return amount <= balance;
    }
}
