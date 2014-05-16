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
		System.out.println("Player: " + targetpl + " mundur " + damage + " langkah");
		Game.getBoard().move(Game.getPlayers().get(targetpl), targetpl, damage*-1);
		Game.getPlayers().get(targetpl).Attacked(damage);
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
		Game.getCurrentPlayer().Attacked(damage*-1);
		if(targettilestatus==88){
			Game.TrapTriggered();
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
		System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
		Game.getPlayers().get(targetpl).Stopped(damage);
	}
}

class TrapSetCard extends Card{
	public TrapSetCard(String Name, String desc){
		super(Name,desc);
	}
	
	public void PlayCard(){
		System.out.println("Played: " + this);
		System.out.println("Satu petak di belakang anda menjadi petak perangkap");
		Game.getBoard().setTrap(Game.getPlayers().get(Game.getCurrentPlayerIdx()).getPosition()-1);
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
		System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
		Random index = new Random();
		for (int i = 0; i < damage; i++)
		{
			int pil = index.nextInt(Game.getPlayers().get(targetpl).getHand().getSize());
			Game.getPlayers().get(targetpl).getHand().Discard(pil);
		}
	}
}
