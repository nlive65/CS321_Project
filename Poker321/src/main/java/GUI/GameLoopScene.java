/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import card.CardHand;
import card.Card;
import gameManager.PLAYER_ACTIONS;
import java.util.ArrayList;

/**
 * This class represents the game loop scene in the GUI of the poker game.
 * It handles the player's actions, card dealing, money management, and player status updates.
 * It extends the abstract class {@link Scene} and overrides methods to provide specific functionality
 * for the game loop, such as managing player turns, displaying the result of actions, and updating the 
 * graphical user interface based on the state of the game.
 * <p>
 * The scene is responsible for interacting with various player components, updating their status, 
 * and providing visual feedback during the game.
 * </p>
 * 
 * @author Nick
 */
public class GameLoopScene extends Scene {
    
    /** Internal state signal for transition. */
    private GUI_STATE internalStateTransitionSignal = GUI_STATE.GAMELOOP;
    
    /** The action taken by the player. */
    private PLAYER_ACTIONS takenAction;
    
    /** The amount of money raised by the user in the current round. */
    private int raiseAmount;
    
    /** The previous player's ID (used for managing state transitions). */
    private int prevPlayerId;
    
    /** Flag indicating if the next round can be started. */
    private boolean canStartNextRound;
    
    /** Flag for whether or not the game can be saved*/
    private boolean saveGame;
    
    
    /**
     * Sets the username displayed in the game UI.
     *
     * @param name The username to display.
     */
    @Override
    public void setUsername(String name){
        this.jLabel7.setText(name);
    }
    
    /**
     * Sets the current turn count displayed in the game UI.
     *
     * @param turn The turn number to display.
     */
    @Override
    public void setTurnCount(int turn){
        this.jLabel1.setText("" + turn);
    }
    
     /**
     * Resets the internal state of the game loop to allow for a new game loop cycle.
     */
    @Override
    public void ResetTransition(){
        this.internalStateTransitionSignal = GUI_STATE.GAMELOOP;
    }
    
    
    /**
     * Deals the opponent's cards (represented as face-down images) and makes them visible sequentially.
     * This simulates the process of dealing cards to each opponent.
     */
    public void dealOpponentCards(){
        try{
            jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
            jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
            jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
            jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
            jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
            jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
            jLabel14.setVisible(true);
            java.lang.Thread.currentThread().sleep(250);
            jLabel19.setVisible(true);
            java.lang.Thread.currentThread().sleep(250);
            jLabel16.setVisible(true);
            java.lang.Thread.currentThread().sleep(250);
            jLabel15.setVisible(true);
            java.lang.Thread.currentThread().sleep(250);
            jLabel18.setVisible(true);
            java.lang.Thread.currentThread().sleep(250);
            jLabel17.setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Hides all the player and opponent cards.
     * This is used to reset the visual state when cards are no longer needed.
     */
    public void unDealCards(){
        jLabel14.setVisible(false);
        jLabel19.setVisible(false);
        jLabel16.setVisible(false);
        jLabel15.setVisible(false);
        jLabel18.setVisible(false);
        jLabel17.setVisible(false);
        jLabel3.setVisible(false);
        jLabel20.setVisible(false);
        jLabel3.setVisible(false);
        jLabel22.setVisible(false);
        jLabel23.setVisible(false);
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
    }
    
    /**
     * Sets the player's action to {@link PLAYER_ACTIONS#IDLE}, indicating that no action is being taken.
     */
    public void setPlayerAction(){
        this.takenAction = PLAYER_ACTIONS.IDLE;
    }
    
    /**
     * Returns the action currently taken by the player.
     *
     * @return The current player action.
     */
    public PLAYER_ACTIONS getTakenAction(){
        return this.takenAction;
    }
    
     /**
     * Sets the current turn by updating the player's ID and resetting the game state for the new turn.
     * Also handles updating the visual display to show the active player.
     *
     * @param playerId The ID of the player whose turn it is.
     */
    public void setTurn(int playerId){
        this.raiseAmount = 0;
        this.setPlayerButtons(false);
        this.takenAction = PLAYER_ACTIONS.IDLE;
        this.jButton5.setVisible(false);
        this.jLabel9.setVisible(false);
        switch(this.prevPlayerId){//reset previous player
            case 0:
                jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
                break;
            case 1:
                jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
                break;
            case 2:
                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
                break;
            case 3:
                jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
                break;
        }
        switch(playerId){
            case 0:
                jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/activeUser.png")));
                this.setPlayerButtons(true);
                break;
            case 1:
                jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/activeUser.png")));
                break;
            case 2:
                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/activeUser.png")));
                break;
            case 3:
                jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/activeUser.png")));
                break;
        }
        this.prevPlayerId = playerId;
    }
    
    /**
     * Updates the amount of money for the specified player and displays it on the UI.
     *
     * @param playerId The ID of the player.
     * @param money The amount of money to display.
     */
    public void setPlayerMoney(int playerId, int money){
        switch(playerId){
            case 0:
                this.jLabel13.setText("$" + money);
                
                break;
            case 1:
                this.jLabel10.setText("$" + money);
                break;
            case 2:
                this.jLabel12.setText("$" + money);
                break;
            case 3:
                this.jLabel11.setText("$" + money);
                break;
            case 4:
                this.jLabel21.setText("Pot:$" + money);
                break;
        }
        try{
            java.lang.Thread.currentThread().sleep(10);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Shows or hides the player's action buttons based on the game state.
     *
     * @param setTurn A boolean flag indicating whether to show the buttons for the current player's turn.
     */
    private void setPlayerButtons(boolean setTurn){
        this.jButton2.setVisible(setTurn);
        this.jButton4.setVisible(setTurn);
        this.jButton3.setVisible(setTurn);
        this.jSpinner1.setVisible(setTurn);
    }
    
    /**
     * Returns the current transition state of the game.
     *
     * @return The current {@link GUI_STATE} transition.
     */
    @Override
    public GUI_STATE getTransition(){
        return this.internalStateTransitionSignal;
    }
    
    
    /**
     * Sets the winner of the current round and updates the UI to reflect the win message.
     *
     * @param playerID The ID of the winning player.
     */
    public void setWinner(int playerID){
        this.canStartNextRound = false;
        this.jButton5.setVisible(true);
        this.jLabel9.setVisible(true);
        switch(playerID){    
            case 0:
                this.jLabel9.setText("You Win");
                break;
            case 1:
                this.jLabel9.setText("Jeff Wins");
                break;
            case 2:
                this.jLabel9.setText("Eliza Wins");
                break;
            case 3:
                this.jLabel9.setText("Erin Wins");
                break;
            case 4:
                this.jLabel19.setText("TIE");
                break;
        }
    }
    /**
     * Function at the end of a round to check if the player is ready to begin the next round
     * @return boolean indicating the continue button has been pressed
     */
    public boolean getNextStart(){
        return this.canStartNextRound;
    }
    
    /**
 * Constructs a new GameLoopScene instance.
 * Initializes the components and sets the initial values for labels, buttons, and game state.
 */
    public GameLoopScene() {
        initComponents();
        this.prevPlayerId = 0;
        this.jLabel9.setVisible(false);
        this.jButton5.setVisible(false);
        this.canStartNextRound = true;
        this.jLabel10.setText("$");
        this.jLabel11.setText("$");
        this.jLabel12.setText("$");
        this.jLabel13.setText("$");
        this.jLabel3.setText("");
        this.jLabel20.setText("");
        this.unDealCards();
        
    }
    
    /**
    * Handles the folding action for a specific player. The player's cards are hidden based on the player ID.
    * 
    * @param playerId The ID of the player who is folding (0 = Player, 1 = Jeff, 2 = Eliza, 3 = Erin).
    */
    public void fold(int playerId){
        switch(playerId){
            case 0:
                this.jLabel3.setVisible(false);
                this.jLabel20.setVisible(false);
                break;
            case 1://Jeff
                jLabel14.setVisible(false);
                jLabel15.setVisible(false);
                break;
            case 2://Eliza
                jLabel18.setVisible(false);
                jLabel19.setVisible(false);
                break;
            case 3: //Erin
                jLabel16.setVisible(false);
                jLabel17.setVisible(false);
                break;
        }
    }
    
    /**
    * Reveals the cards for a specific player or the community cards. 
    * The cards are displayed on the UI based on the player ID.
    * 
    * @param playerId The ID of the player whose cards are being revealed.
    *                  (0 = Player, 1 = Jeff, 2 = Eliza, 3 = Erin, 4 = Flop, 5 = Turn, 6 = River).
    * @param newCard The new card hand to be revealed.
    */
    public void reveal(int playerId, CardHand newCard){
        ArrayList<Card> hand = newCard.GetTwoCardHand();
        switch(playerId){
            case 0://Player
                Card card1 = hand.get(0);
                Card card2 = hand.get(1);
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card2.GetSuit()) + card2.GetValue()+ ".png"))); // NOI18N
                jLabel3.setVisible(true);
                jLabel20.setVisible(true);
                break;
            case 1://Jeff
                card1 = hand.get(0);
                card2 = hand.get(1);
                
                jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card2.GetSuit()) + card2.GetValue()+ ".png"))); // NOI18N
                jLabel14.setVisible(true);
                jLabel15.setVisible(true);
                break;
            case 2://Eliza
                card1 = hand.get(0);
                card2 = hand.get(1);
                
                jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card2.GetSuit()) + card2.GetValue()+ ".png"))); // NOI18N
                jLabel18.setVisible(true);
                jLabel19.setVisible(true);
                break;
            case 3: //Erin
                card1 = hand.get(0);
                card2 = hand.get(1);
                
                jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card2.GetSuit()) + card2.GetValue()+ ".png"))); // NOI18N
                jLabel16.setVisible(true);
                jLabel17.setVisible(true);
                break; 
            case 4://flop
                hand = newCard.GetFullHand();
                card1 = hand.get(0);
                card2 = hand.get(1);
                Card card3 = hand.get(2);
                jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card2.GetSuit()) + card2.GetValue()+ ".png"))); // NOI18N
                jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card3.GetSuit()) + card3.GetValue()+ ".png"))); // NOI18N
                jLabel22.setVisible(true);
                jLabel23.setVisible(true);
                jLabel24.setVisible(true);
                break;
            case 5://Turn
                hand = newCard.GetFullHand();
                card1 = hand.get(0);
                jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel25.setVisible(true);
                break;
            case 6://River
                hand = newCard.GetFullHand();
                card1 = hand.get(0);
                jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/"+Character.toUpperCase(card1.GetSuit()) + card1.GetValue()+ ".png"))); // NOI18N
                jLabel26.setVisible(true);
                break;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
        jLabel4.setText("Eliza");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
        jLabel6.setText("Erin");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, -1, -1));

        jButton1.setLabel("Save Game");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 0, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(731, 33, 37, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"))); // NOI18N
        jLabel8.setText("Jeff");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jButton2.setText("Check/Call");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        jButton3.setText("Raise");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, -1, -1));

        jButton4.setText("Fold");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 560, -1, -1));
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 560, -1, -1));

        jLabel9.setText("Winner:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 180, 50));

        jButton5.setText("Continue");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, -1));

        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, -1, -1));

        jLabel12.setText("jLabel12");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, -1));

        jLabel21.setText("Pot:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Back - Top Down 88x124.png"))); // NOI18N
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/background.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(800, 600));
        jLabel2.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel2.setPreferredSize(new java.awt.Dimension(800, 600));
        jLabel2.setRequestFocusEnabled(false);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
    * Handles the action event for jButton1. Sets the saveGame flag to true.
    * This is typically triggered when the user clicks on the save game button.
    * 
    * @param evt The action event triggered by clicking the save game button.
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.saveGame = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * Handles the action event for jButton5. Sets the canStartNextRound flag to true.
    * This is triggered when the user clicks on the button allowing the next round to start.
    * 
    * @param evt The action event triggered by clicking the next round button.
    */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.canStartNextRound = true;
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
    * Handles the action event for jButton2. Sets the takenAction flag to PLAYER_ACTIONS.CHECK.
    * This action is typically triggered when the user selects the "Check" option in the game.
    * 
    * @param evt The action event triggered by clicking the check button.
    */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.takenAction = PLAYER_ACTIONS.CHECK;
    }//GEN-LAST:event_jButton2ActionPerformed
    
    /**
    * Handles the action event for jButton4. Sets the takenAction flag to PLAYER_ACTIONS.FOLD.
    * This action is triggered when the user selects the "Fold" option in the game.
    * 
    * @param evt The action event triggered by clicking the fold button.
    */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.takenAction = PLAYER_ACTIONS.FOLD;
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
    * Handles the action event for jButton3. Sets the takenAction flag to PLAYER_ACTIONS.RAISE
    * and retrieves the raise amount from the spinner.
    * This action is triggered when the user selects the "Raise" option in the game.
    * 
    * @param evt The action event triggered by clicking the raise button.
    */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.takenAction = PLAYER_ACTIONS.RAISE;
        this.raiseAmount = (Integer) jSpinner1.getValue();
        
    }//GEN-LAST:event_jButton3ActionPerformed
    
    /**
     * Gets the amount raised by the player during a raise action.
     *
     * @return The amount the player wants to raise.
     */
    public int getRaiseAmount(){
        return this.raiseAmount;
    }
    
    /**
     * Gets the saveGame flag, which indicates if the game should be saved.
     *
     * @return True if the game should be saved, false otherwise.
     */
    public boolean getSaveGame(){
        return this.saveGame;
    }
    
    /**
     * Sets the saveGame flag to indicate whether the game should be saved or
     * not.
     *
     * @param newSave The new value for the saveGame flag (true to save the
     * game, false otherwise).
     */
    public void setSaveGame(boolean newSave){
        this.saveGame = newSave;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameLoopScene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameLoopScene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameLoopScene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameLoopScene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameLoopScene().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}
