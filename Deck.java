import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

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
	
	public void LoadDeck(File F){
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(F);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("card");
			CardFactory CF = new CardFactory();
			Card cardin;
			int id,power;
			String name,desc;
			int i=1;
			for (int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					id = Integer.parseInt(eElement.getElementsByTagName("ActionID").item(0).getTextContent());
					name = eElement.getElementsByTagName("name").item(0).getTextContent();
					desc = eElement.getElementsByTagName("desc").item(0).getTextContent();
					power = Integer.parseInt(eElement.getElementsByTagName("power").item(0).getTextContent());
					cardin = CF.getCard(name, desc, power, id);
					D.push(cardin);
				}
			}
		}
		catch(Exception e){};
	}
	
	public Card Drawn(){
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
