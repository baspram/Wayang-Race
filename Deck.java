import java.util.*;
import java.io.*;

public class Deck{
	private Stack<Card> D;
	
	public Deck(){
		D = new Stack<Card>();
		}
		
	public int Ndeck(){
		return D.size();
		}
		
	public void Shuffle(){
		Card tempdeck[] = new Card[D.size()+1];
		Card elemen = new Card();
		Random rnd = new Random();
		int Ndeck = D.size();
		int i;
		while(!D.isEmpty()){
			elemen = D.peek();
			D.pop();
			i = 1+rnd.nextInt(Ndeck);
			while (tempdeck[i] != null){
				i = 1+rnd.nextInt(Ndeck);
			}
			tempdeck[i] = elemen;
		}
		for(i=1;i<=Ndeck;i++){
			D.push(tempdeck[i]);
		}
	}
	
	public void LoadDeck(FileInputStream F){
		Scanner CardLoader = new Scanner(F);
		Scanner LineParser;
		CardFactory CF = new CardFactory();
		Card cardin;
		int id,power;
		String line,name,desc;
		int i=1;
		while(CardLoader.hasNextLine()){
			line = CardLoader.nextLine();
			LineParser = new Scanner(line).useDelimiter(";");
			id = LineParser.nextInt();
			power = LineParser.nextInt();
			name = LineParser.next();
			desc = LineParser.next();
			cardin = CF.getCard(name, desc, power, id);
			D.push(cardin);
			i++;
		}
		CardLoader.close();
	}
	
	public Card Draw(){
		Card DrawnCard = new Card();
		DrawnCard = D.peek();
		D.pop();
		return DrawnCard;
	}
	
	public void DisplayDeckContent(){
		while(!D.isEmpty()){
			System.out.println(D.peek());
			D.pop();
		}
	}
}
