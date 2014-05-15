/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author Ahmad
 */
/* Base class for every roles in the game */

public class Player {
    /* Current player's cards */
    private Hand hand;
    /* Player's position on the map */
    private int position;
    /* Player's suspended times */
    private int stop;
    /* Player's lap number */
	private int currentLap;
	/* Has player played card? */
    private boolean PlayedCard;
	/* Could player move */
    private boolean Advanced;
        /*Action used */
    protected boolean actionUsed;
	/* Total number of tiles on the block */
    private final int NBLOCK = 42;
	/* Imunity of player */
    protected boolean imunity;
    /* Player's recharge */
    protected int rechargeTime;
    /* Player's dice rolled */
    private int diceRolled;
    /* Player's constructor */
    public Player(){
        hand = new Hand();
        position = 1;
        stop = 0;
        currentLap = 1;
        Advanced = false;
        PlayedCard = false;
	imunity = false;
	rechargeTime = 0;
        diceRolled = 0;
        actionUsed = false;
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
    public int getDiceRolled(){
        return diceRolled;
    }
    public Hand getHand(){
		return hand;
	}
    public boolean hasAdvanced(){
            return Advanced;
    }
    public boolean hasPlayedACard(){
            return PlayedCard;
    }
    public void setPosition(int target){
		position = target;
	}
	/* Print player's condition */
    public String toString(){
		String S = "Role: " + "\nPutaran: " + currentLap + "\nPosisi: " + position;
		return S;
	}
    
	/* Initalize condition, every turn */
    public void StartTurn(){
		Advanced = false;
		PlayedCard = false;
		imunity = false;
                diceRolled = 0;
                actionUsed = false;
	}
    
        /* Select the target for attack */
    public int SelectTarget(){
		Scanner targetin = new Scanner(System.in);
		System.out.println("Choose Target: ");
		return targetin.nextInt();
    }
    
    public int SelectCardTarget(){
		Scanner targetin = new Scanner(System.in);
    		System.out.println("Choose Card: ");
		return targetin.nextInt();
    }
    /* Player advance on the map as many as the dice rolled */
    public void Advance(int diceNum){
		if(!Advanced&& stop==0){
			position += diceNum;
			if(position>=NBLOCK){
				position -=NBLOCK;
				currentLap++;
			}
			Advanced = true;
                        if(currentLap > Game.getReqLap()){
                            Game.finish();
                        }
		}
		else{
			System.out.println("Anda telah maju dari kocokan dadu putaran ini atau sedang terkena stop");
		}
    }
    
	/* Change the position of player because damaged */
    public void Attacked(int damage){
            if(!imunity){
                    position -= damage;
                    if(position<0){
                            position = 1;
                    }
            }
	}
    
    /* Player use one of his card */
    public void PlayCard(){
		if(PlayedCard==false){
			Scanner CardChoice = new Scanner(System.in);
			System.out.print("Pilih nomor kartu yang ingin dimainkan(99 untuk batal): ");
			int IdxCard = CardChoice.nextInt();
			if(IdxCard!=99 && IdxCard<hand.getSize()){
				hand.PlayCard(IdxCard);
				PlayedCard = true;
			}
		}
		else{
			System.out.println("Anda telah menggunakan kartu di putaran ini");
		}
    }
    /* Suspended as many as the number */
    public void Stopped(int suspend){
        stop = suspend;
    }
    /* Increase number of lap  */
    public void increaseLap(){
        currentLap++;
    }
    
	/* Player end his turn */
    public void EndTurn(){
		if (stop>0){
			stop--;
		}
                rechargeTime++;
	}
    
	/* Initalize Lap */
    public void StartLap(Deck D){
		hand.DiscardAll();
		hand.DrawStart(D);
	}
   
    public void useAction(){
        ;
    }
		
}
