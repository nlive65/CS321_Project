package gameManager;

import card.CardHand;

/**
 *
 * @author Luke
 * @param takes user name, initial balance, and on what turn they play
 */

// A basic idea of what the Player class will need, will likely be appended when collaborating with CPU classes
// Will require much collaboration with GameLoop superclass to assign cards, winning hands, etc.

public class Player {
    
    // Variables needed
    private  String name;
    private int balance;
    private CardHand hand = new CardHand();
    private boolean isActive;
    private int currentBet;
    private int turn;
    protected boolean hasFolded;
    protected boolean hasChecked;
    
    
    // Constructor
    public Player(String name, int initialBalance, int playerTurn) 
    {
        this.name = name;
        this.turn = playerTurn;
        this.balance = initialBalance;
        hand = new CardHand();
        this.isActive = true;
        this.currentBet = 0;
    }
    
    
    // Getters and Setters
    /** Retrieves player name */
    public String getName() 
    {
        return name;
    }
    /** Sets player name to input string */
    public void setName(String inputName)
    {
        name = inputName;
    }
    /** Retrieves player turn */
    public int getTurn()
    {
        return turn;
    }
    /** Sets player turn to input integer */
    public void setTurn(int turn)
    {
        this.turn = turn;
    }
    /** Retrieves the player hand */
    public CardHand GetCardHand()
    {
        return hand;
    }
    /** Retrieves player balance */
    public int getBalance() 
    {
        return balance;
    }
    /** Sets player balance to input integer */
    public void setBalance(int balance) 
    {
        this.balance = balance;
    }
    /** Retrieves if the player is active */
    public boolean isActive() 
    {
        return isActive;
    }
    /** Sets the player to inactive/active*/
    public void setActive(boolean active) 
    {
        this.isActive = active;
    }
    /** Retrieves the current bet amount */
    public int getCurrentBet() 
    {
        return currentBet;
    }
    /** Sets the current bet to input integer */
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
   /** Retrieves if the player has checked */
      public boolean hasChecked()
    {
        return hasChecked;
    }
   /** Retrieves if the player has folded */
    public boolean hasFolded()
    {
        return hasFolded;
    }
    /** Sets the player state to folded */
    public void fold() 
    {
        this.hasFolded = true; 
        System.out.println(name + " has folded");
    }
    /** Allows the player to check */
    public void check() 
    {
        if (this.currentBet == 0)
        {
            this.hasChecked = true; 
            System.out.println(name + " has checked");
        }
        else
        {
            System.out.println("Bet placed, cannot check");
        }
    }
    /** Increase player balance by input amount */
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
