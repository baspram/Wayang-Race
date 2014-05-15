import java.util.*;
import java.io.*;

class AttackCard extends Card{
	protected int damage;
	
	public AttackCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(Game G){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
		System.out.println("Player: " + targetpl + " mundur " + damage + " langkah");
		G.getPlayers().get(targetpl).Attacked(damage);
		G.getBoard().move(G.getPlayers().get(targetpl), targetpl, damage*-1);
	}
}

class BoostCard extends Card{
	private int damage;
	
	public BoostCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(Game G){
		System.out.println("Played: " + this);
		System.out.println("Karakter anda maju " + damage + " langkah");
		G.getCurrentPlayer().Attacked(damage*-1);
		G.getBoard().move(G.getCurrentPlayer(), G.getCurrentPlayerIdx(), damage);
	}
}

class StopCard extends Card{
	protected int damage;
	
	public StopCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(Game G){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
		G.getPlayers().get(targetpl).Stopped(damage);
	}
}

class TrapSetCard extends Card{
	public TrapSetCard(String Name, String desc){
		super(Name,desc);
	}
	
	public void PlayCard(Game G){
		System.out.println("Played: " + this);
		System.out.println("Satu petak di belakang anda menjadi petak perangkap");
		
	}
}

class DisarmCard extends Card{
	protected int damage;	
	
	public DisarmCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(Game G){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
		Random index = new Random();
		for (int i = 0; i < damage; i++)
		{
			int pil = index.nextInt(G.getCurrentPlayer().getHand().getSize());
			G.getPlayers().get(targetpl).getHand().Discard(pil, G.getDP());
		}
	}
}
