import java.util.*;

class AttackCard extends Card{
	private int damage;
	
	public AttackCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		Scanner targetin = new Scanner(System.in);
		System.out.println("Choose Target: ");
		int targetpl = targetin.nextInt();
		System.out.println("Played: " + this);
		System.out.println("Player: " + targetpl + " mundur " + damage + " langkah");
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
	}
}

class StopCard extends Card{
	private int damage;
	
	public StopCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		Scanner targetin = new Scanner(System.in);
		System.out.println("Choose Target: ");
		int targetpl = targetin.nextInt();
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
	}
}

class TrapSetCard extends Card{
	public TrapSetCard(String Name, String desc){
		super(Name,desc);
	}
	
	public void PlayCard(){
		System.out.println("Played: " + this);
		System.out.println("Satu petak di belakang anda menjadi petak perangkap");
	}
}

class DisarmCard extends Card{
	public DisarmCard(String Name, String desc){
		super(Name,desc);
	}
	
	public void PlayCard(){
		Scanner targetin = new Scanner(System.in);
		System.out.println("Choose Target: ");
		int targetpl = targetin.nextInt();
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
	}
}
