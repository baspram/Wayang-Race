public class AttackTrap extends AttackCard{
	public AttackTrap(String Name, String desc, int dmg){
		super(Name,desc,dmg);
	}
	
	public int SelectTarget(){
		return 0; //0 diganti index of active player
	}
}

class DisarmTrap extends DisarmCard{
	public DisarmTrap(String Name, String desc, int dmg){
		super(Name,desc,dmg);
	}
	
	public int SelectTarget(){
		return 0; //0 diganti index of active player
	}
}

class StopTrap extends StopCard{
	public StopTrap(String Name, String desc, int dmg){
		super(Name,desc,dmg);
	}
	
	public int SelectTarget(){
		return 0; //0 diganti index of active player
	}
}
