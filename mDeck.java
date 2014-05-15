import java.io.*;

class mDeck{
	public static void main(String args[]){
		Deck D = new Deck();
		try{
			D.LoadDeck(new File("ActionDeck.xml"));
		}
		catch(Exception e){}
		D.Shuffle();
		D.DisplayDeckContent();
	}
}
