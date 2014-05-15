import java.io.*;

class mPlayer{
	public static void main(String args[]){
		Player pl = new Player();
		Deck ActionDeck = new Deck();
		Deck TrapDeck = new Deck();
		try{
			ActionDeck.LoadDeck(new File("ActionDeck.txt"));
			TrapDeck.LoadDeck(new File("TrapDeck.txt"));
		}
		catch(Exception e){}
		ActionDeck.Shuffle();
		pl.StartLap(ActionDeck);
		pl.getHand().DisplayHand();
	}
}
