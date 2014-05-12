/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Player;

/**
 *
 * @author Ahmad
 */
/* Base class for every roles in the game */

public class Player {
    /* Current player's cards */
    private Hand hand
    /* Player's position on the map */
    private int position;
    /* Player's suspended times */
    private int stop;
    private int currentLap;
    
    /* Player's constructor */
    public Player(){
		hand = new Hand();
        position = 0;
        stop = 0;
        currentLap = 0;
    }
    /* Player's getter */
    public int getPosition(){
        return position;
    }
    public int getStop(){
        return stop;
    }
    public int getCurrentLap(){
        return currentLap;
    }
    /* Player advance on the map as many as the dice rolled */
    public void Advance(int diceNum){
       if(stop>0){
            stop --;
       }
       else{
            position += diceNum;
       }
    }
    
    /* Player use one of his card */
    public void PlayCard(){
        
    }
    /* Suspended as many as the number */
    public void Stopped(int suspend){
        stop = suspend;
    }
    /* Increase number of lap  */
    public void increaseLap(){
        currentLap++;
    }
}
