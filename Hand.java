import java.util.*;

public class Hand{
	private ArrayList<Card> H;
    private final int HandSize = 7;
    
	public Hand(){
		H = new ArrayList<Card>();
	}
	
	public void Draw(Card CardDrawn){
		if(H.size()<7){
                    H.add(CardDrawn);
                }
                else{
                    System.out.println("Jumlah kartu ditangan telah maksimal");
                }
	}
	
	public int getSize(){
		return H.size();
	}
	
	public void DrawStart(){
		for(int i=0;i<HandSize;i++){
			Draw(Game.getActionDeck().Drawn());
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
