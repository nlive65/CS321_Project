package CPU;
import card.*;
import java.util.ArrayList;
import gameManager.Player;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author giosa
 * this class represents a Hard CPU player that makes strategic betting and folding decisions.
 */

public class HardCPU extends CPU {
    
    
    public HardCPU(String name, int chips, int turnNumber) {
        super(name, chips, turnNumber);
    }

    // method to evaluate the strength of the hand
    public int evaluateHandStrength(Player player) {
        ArrayList<Card> hand = player.GetCardHand().GetFullHand(); 
        this.GetCardHand().SortFullHand();  

        // check for hand types
        if (isRoyalFlush(hand)) {
            return 100;
        } else if (isStraightFlush(hand)) {
            return 90;  
        } else if (isFourOfAKind(hand)) {
            return 80;  
        } else if (isFullHouse(hand)) {
            return 70; 
        } else if (isFlush(hand)) {
            return 60;  
        } else if (isStraight(hand)) {
            return 50; 
        } else if (isThreeOfAKind(hand)) {
            return 40;  
        } else if (isPair(hand)) {
            return 20;  
        } else {
            return 10; 
        }
    }

    // check for royal flush 
    private boolean isRoyalFlush(ArrayList<Card> hand) {
        return isStraightFlush(hand) && hand.get(0).GetValue() == 10;  // first card should be 10 
    }

    // check for a straight flush 
    private boolean isStraightFlush(ArrayList<Card> hand) {
        return isFlush(hand) && isStraight(hand);  //flush and straight
    }

    // check for four of a kind 
    private boolean isFourOfAKind(ArrayList<Card> hand) {
        return getCardFrequency(hand, 4);
    }

    // check for a full house 
    private boolean isFullHouse(ArrayList<Card> hand) {
        return getCardFrequency(hand, 3) && getCardFrequency(hand, 2);
    }

    // check for a flush 
    private boolean isFlush(ArrayList<Card> hand) {
        char suit = hand.get(0).GetSuit();  // get the suit of the first card
        for (Card card : hand) {
            if (card.GetSuit() != suit) {
                return false;  
            }
        }
        return true;
    }

    // check for a straight 
    private boolean isStraight(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).GetValue() + 1 != hand.get(i + 1).GetValue()) {
                return false;  // if the cards are not in sequence, it's not a straight
            }
        }
        return true;
    }

    // check for three of a kind 
    private boolean isThreeOfAKind(ArrayList<Card> hand) {
        return getCardFrequency(hand, 3);
    }


    // check for a pair (
    private boolean isPair(ArrayList<Card> hand) {
        return getCardFrequency(hand, 2);
    }

    // helper method to get the frequency of a card rank in the hand
    private boolean getCardFrequency(ArrayList<Card> hand, int frequency) {
        int count = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).GetValue() == hand.get(j).GetValue()) {
                    count++;
                }
            }
        }
        return count == frequency - 1; 
    }

    // Method to make a move based on the hand strength
    @Override
    public void makeMove() {
        int handStrength = 0;
        int currentBetAmount = getCurrentBet();  

        // if the hand is strong (strength >= 70), bet 100 chips
        if (handStrength >= 70) {
            int betAmount = 100;
            if (canBet(betAmount)) 
            {
                decreaseBalance(betAmount);  // place the bet 
            } else {
                // call or fold
                if (getBalance() >= currentBetAmount) {
                    System.out.println(getName() + ": I will call with " + currentBetAmount + " chips.");
                    decreaseBalance(currentBetAmount);  // call the current bet
                } else {
                    fold();  // fold if it cannot call
                }
            }
        } else if (handStrength >= 40) {
            // call or fold
            if (getBalance() >= currentBetAmount) {
                decreaseBalance(currentBetAmount);  
            } else {
                System.out.println(getName() + ": Not enough chips to call, I will fold.");
                fold();  // Fold if can't call
            }
        } else {
            // ff the hand is weak, fold
            fold();
        }
    }
}
