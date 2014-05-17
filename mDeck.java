import java.io.*;

class mDeck{
	public static void main(String args[]){
		Deck D = new Deck();
		try{
			D.LoadDeck(new File("ActionDeck.xml"));
		}
		catch(Exception e){}
		D.Shuffle();
		int n = D.Ndeck();
		for(int i=1;i<=n+5;i++){
			Card C = D.Drawn();
			System.out.println(C);
			C.Discard();
		}
	}
}
