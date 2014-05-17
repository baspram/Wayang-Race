import java.util.*;
import java.io.*;

class AttackCard extends Card{
	protected int damage;
	
	public AttackCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
                int targettilestatus =0;
                if(!Game.getPlayers().get(targetpl).getImun()){
                    System.out.println("Player: " + targetpl + " mundur " + damage + " langkah");                 
                    targettilestatus = Game.getBoard().move(Game.getPlayers().get(targetpl), targetpl, damage*-1);
                    Game.getPlayers().get(targetpl).Attacked(damage);
                    if(targettilestatus==88){
			Game.TrapTriggered(targetpl);
                    }
                }
                else{
                    System.out.println("Player " + targetpl + " terlindungi dari serangan");
                }
	}
	
	public void PlayCard(int target){
		System.out.println("Played: " + this);
		if(!Game.getPlayers().get(target).getImun()){
                    System.out.println("Player: " + target + " mundur " + damage + " langkah");
                    int targettilestatus;
                    targettilestatus = Game.getBoard().move(Game.getPlayers().get(target), target, damage*-1);
                    Game.getPlayers().get(target).Attacked(damage);
                    if(targettilestatus==88){
                            Game.TrapTriggered(target);
                    }
                }
                else{
                    System.out.println("Player " + target + " terlindungi dari serangan");
                }
	}
}

class BoostCard extends Card{
	private int damage;
	
	public BoostCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		System.out.println("Played: " + this);
		System.out.println("Karakter anda maju " + damage + " langkah");
		int targettilestatus = Game.getBoard().move(Game.getCurrentPlayer(), Game.getCurrentPlayerIdx(), damage);
		Game.getCurrentPlayer().Boost(damage);
		if(targettilestatus==88){
			Game.TrapTriggered(Game.getCurrentPlayerIdx());
		}
	}
}

class StopCard extends Card{
	protected int damage;
	
	public StopCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
                if(!Game.getPlayers().get(targetpl).getImun()){
                    System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
                    Game.getPlayers().get(targetpl).Stopped(damage);
                }
                else{
                    System.out.println("Player " + targetpl + " terlindungi dari serangan");
                }
	}

	public void PlayCard(int targetpl){
		System.out.println("Played: " + this);
           	if(!Game.getPlayers().get(targetpl).getImun()){
                    System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
                    Game.getPlayers().get(targetpl).Stopped(damage);
                }
                else{
                    System.out.println("Player " + targetpl + " terlindungi dari serangan");
                }
	}
}

class TrapSetCard extends Card{
	public TrapSetCard(String Name, String desc){
		super(Name,desc);
	}
	
	public void PlayCard(){
		System.out.println("Played: " + this);
		System.out.println("Satu petak di belakang anda menjadi petak perangkap");
		if(!Game.getPlayers().get(Game.getCurrentPlayerIdx()).getImun()){
                    if(Game.getPlayers().get(Game.getCurrentPlayerIdx()).getPosition()==0){
                        Game.getBoard().setTrap(41);
                    }
                    else{
                        Game.getBoard().setTrap(Game.getPlayers().get(Game.getCurrentPlayerIdx()).getPosition()-1);
                    }
                }
	}
}

class DisarmCard extends Card{
	protected int damage;	
	
	public DisarmCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
                if(!Game.getPlayers().get(targetpl).getImun()){
                    System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
                    Random index = new Random();
                    for (int i = 0; i < damage; i++)
                    {
                            int pil = index.nextInt(Game.getPlayers().get(targetpl).getHand().getSize());
                            Game.getPlayers().get(targetpl).getHand().Discard(pil);
                    }
                }
                else{
                    System.out.println("Player " + targetpl + " terlindungi dari serangan");
                }
	}
	
	public void PlayCard(int targetpl){
		System.out.println("Played: " + this);
                if(!Game.getPlayers().get(targetpl).getImun()){
                    System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
                    Random index = new Random();
                    for (int i = 0; i < damage; i++)
                    {
                            int pil = index.nextInt(Game.getPlayers().get(targetpl).getHand().getSize());
                            Game.getPlayers().get(targetpl).getHand().Discard(pil);
                    }
                }
                else{
                    System.out.println("Player " + targetpl + " terlindungi dari serangan");
                }
	}
}
