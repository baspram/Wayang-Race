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
    private int currentLap;
    private boolean PlayedCard;
    private boolean Advanced;
    private final int NBLOCK = 42;
    /* Player's constructor */
    public Player(){
		hand = new Hand();
        position = 1;
        stop = 0;
        currentLap = 1;
        Advanced = false;
        PlayedCard = false;
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
    public Hand getHand(){
		return hand;
	}
	public boolean hasAdvanced(){
		return Advanced;
	}
	public boolean hasPlayedACard(){
		return PlayedCard;
	}
    
    public String toString(){
		String S = "Role: " + "\nPutaran: " + currentLap + "\nPosisi: " + position;
		return S;
	}
    
    public void StartTurn(){
		Advanced = false;
		PlayedCard = false;
	}
    
    /* Player advance on the map as many as the dice rolled */
    public void Advance(int diceNum){
		if(Advanced == false && stop==0){
			position += diceNum;
			if(position>=NBLOCK){
				position -=NBLOCK;
				currentLap++;
			}
			Advanced = true;
		}
		else{
			System.out.println("Anda telah maju dari kocokan dadu putaran ini atau sedang terkena stop");
		}
    }
    
    public void setPosition(int posisi)
    {
    	position = posisi;
    }
    
    public void Attacked(int damage){
		position -= damage;
		if(position<0){
			position += NBLOCK;
			currentLap--;
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
    
    public void EndTurn(){
		if (stop>0){
			stop--;
		}
	}
    
    public void StartLap(Deck D){
		hand.DiscardAll();
		hand.DrawStart(D);
	}
		
}
