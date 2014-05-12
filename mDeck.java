import java.io.*;

class mDeck{
	public static void main(String args[]){
		Deck D = new Deck();
		try{
			D.LoadDeck(new FileInputStream("ActionDeck.txt"));
		}
		catch(Exception e){}
		D.Shuffle();
		D.DisplayDeckContent();
	}
}
