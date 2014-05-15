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

	public stack<Card> Export(){
		stack<Card> temp = new stack<Card>();
		Card hapus = new Card();
		temp = P;
		while(!P.isEmpty())
		{
			hapus = P.peek();
			P.pop();
		}
		return temp;
	}
}
