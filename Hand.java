import java.util.*;

public class Hand{
	private ArrayList<Card> H;
	
	public Hand(){
		H = new ArrayList<Card>();
	}
	
	public void Draw(Deck _deck){
		Card CardDrawn = _deck.Draw();
		H.add(CardDrawn);
		System.out.println("Card Drawn: " + CardDrawn);
	}
		
	public void PlayCard(int i){
		Card CardPlayed = new Card();
		CardPlayed = H.get(i);
		CardPlayed.PlayCard();
	}
	
	public void DisplayHand(){
		for(int i=0;i<H.size();i++){
			System.out.println(i + ". " + H.get(i));
		}
	}
}
