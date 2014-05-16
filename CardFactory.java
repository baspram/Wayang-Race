public class CardFactory{
	public CardFactory(){}
	
	public Card getCard(String name, String desc, int power, int ActionID){
		switch(ActionID){
				case 1 : { return new BoostCard(name, desc, power);}
				case 2 : { return new AttackCard(name, desc, power);}
				case 3 : { return new StopCard(name, desc, power);}
				case 4 : { return new TrapSetCard(name, desc);}
				case 5 : { return new DisarmCard(name, desc, power);}
				case 21 : { return new AttackCard(name,desc,power);}
				case 22 : { return new DisarmCard(name,desc,power);}
				case 23 : { return new StopCard(name,desc,power);}
				default: { return new Card();}
			}
		}
	}
