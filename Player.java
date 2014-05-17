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
    /* Player's Role; 1 = Arjuna; 2 = Nakula; 3 = Sadewa; 4 = Werkudara; 5 = Yudhistira */
    protected int id;
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
        id = 0;
    }
    /* Player's getter */
    public int getPosition(){
        return position;
    }
    public int getID(){
        return id;
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
	/* Print player's condition */
    public String toString(){
		String role;
                if(id == 1){
                    role = "Arjuna";
                }
                else{
                    if(id == 2){
                        role = "Nakula";
                    }
                    else{
                        if(id==3){
                            role = "Sadewa";
                        }
                        else{
                            if(id==4){
                                role = "Werkudara";
                            }
                            else{
                                role = "Yudhistira";
                            }
                        }
                    }
                }
                String S = "Role: " + role + "\nPutaran: " + currentLap + "\nPosisi: " + position;
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
    
    public boolean CanAdvance(){
		return !Advanced&& stop==0;
	}
    /* Player advance on the map as many as the dice rolled */
    public void Advance(int diceNum){
		position += diceNum;
		while(position>=Board.getNBlock()){
			position %= Board.getNBlock();
			currentLap++;
			StartLap();		
		}
        diceRolled = diceNum;
		Advanced = true;
                if(currentLap > Game.getLapNumber()){
                    System.out.println("Balapan ini dimenangkan oleh: " + this);
                    Game.Finish();
                }
    }
    
	/* Change the position of player because damaged */
    public void Attacked(int damage){
            if(position==0||position==42){
				return;
			}
            if(!imunity){
					position -= damage;
					if(position<1){
						position = 1;
					}
				}
			}
	
	public void Boost(int power){
		position +=power;
		while(position>=Board.getNBlock()){
			position %= Board.getNBlock();
			currentLap++;
			StartLap();	
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
    public void StartLap(){
		hand.DiscardAll();
		hand.DrawStart();
	}
   
    public void useAction(){
        ;
    }
    
    public void setPosition(int pos){
        position = pos;
    }

    public String getDescription(){
        return "";
    }
}
