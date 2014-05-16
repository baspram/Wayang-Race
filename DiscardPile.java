import java.util.*;

public class DiscardPile{
	private Stack<Card> P;
	
	public DiscardPile(){
		P = new Stack<Card>();
	}
	
	public int Ndeck(){
		return P.size();
	}
	
	public void GetCard(Card in){
		P.push(in);
	}

	public Stack<Card> Export(){
		Stack<Card> temp = new Stack<Card>();
		Card hapus = new Card();
		temp = P;
		while(!P.isEmpty())
		{
			hapus = P.peek();
			P.pop();
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
