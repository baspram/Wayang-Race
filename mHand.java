import java.util.*;
import java.io.*;

class mHand{
	
	public static void main(String args[]){
		Hand H = new Hand();
		Deck D = new Deck();
		Scanner PlayerInput = new Scanner(System.in);
		try{
			D.LoadDeck(new FileInputStream("ActionDeck.txt"));
		}
		catch(Exception e){}
		D.Shuffle();
		for(int i=1;i<=5;i++){
			H.Draw(D);
		}
		H.DisplayHand();
		int IdxCard = PlayerInput.nextInt();
		H.PlayCard(IdxCard);
	}
}

