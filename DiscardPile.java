import java.util.*;

public class DiscardPile{
	private ArrayDeque<Card> P;
	
	public DiscardPile(){
		P = new ArrayDeque<Card>();
	}
	
	public int Ndeck(){
		return P.size();
	}
	
	public void GetCard(Card in){
		P.push(in);
	}

	public ArrayDeque<Card> Export(){
		ArrayDeque<Card> temp = new ArrayDeque<Card>();
		Card hapus = new Card();
		while(!P.isEmpty())
		{
			hapus = P.peek();
			P.pop();
			temp.push(hapus);
		}
		return temp;
	}
	
	public Card getTop()
	{
		Card a = P.peek();
		P.pop();
		return a;
	}
}
