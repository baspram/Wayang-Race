import java.util.*;

public class Hand{
	private ArrayList<Card> H;
    private final int HandSize = 7;
    
	public Hand(){
		H = new ArrayList<Card>();
	}
	
	public void Draw(Card CardDrawn){
		H.add(CardDrawn);
		//System.out.println("Card Drawn: " + CardDrawn);
	}
	
	public int getSize(){
		return H.size();
	}
	
	public void DrawStart(Deck _deck){
		for(int i=0;i<HandSize;i++){
			Draw(_deck.Drawn());
		}
	}
		
	public void PlayCard(int i){
		Card CardPlayed = new Card();
		CardPlayed = H.get(i);
		CardPlayed.PlayCard();
		Discard(i);
	}
	
	public void DisplayHand(){
		for(int i=0;i<H.size();i++){
			System.out.println(i + ". " + H.get(i));
		}
	}
	
	public void Discard(int idxCard){
			Game.getDP().GetCard(H.get(idxCard));
			H.remove(idxCard);
	}
	
	public void DiscardAll(){
		int n = H.size();
		for(int i=0;i<n;i++){
			Game.getDP().GetCard(H.get(0));
			H.remove(0);
		}
	}
}
