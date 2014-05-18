import java.awt.Point;
import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Hand{
	
	//Static, JSwing
	public static Point cardpos[][]; // lokasi kartu, label
	
	//atribut
	int idxDiscard;
	private ArrayList<Card> H;
    private final int HandSize = 7;
    
    public ArrayList<Card> getH()
    {
    	return H;
    }
    
	public Hand(){
		H = new ArrayList<Card>();
	}
	
	// Init Hand Images
    public static void initHandImg()
    {
    	cardpos = new Point[7][2];
    	/*final int cardwidth = 100;
    	final int cardheight = 120;*/
    	try{
	    	File file = new File("Coord.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("tile");
			
			for (int temp = 0; temp < nList.getLength(); temp++)
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					String title = eElement.getElementsByTagName("title").item(0).getTextContent();
					String x = eElement.getElementsByTagName("x").item(0).getTextContent();
					String y = eElement.getElementsByTagName("y").item(0).getTextContent();
					
					if(title.startsWith("card"))
					{
						int kartuke = Integer.parseInt(title.substring(title.length()-1));
						cardpos[kartuke-1][0] = new Point(Integer.parseInt(x),Integer.parseInt(y));
						cardpos[kartuke-1][1] = new Point(Integer.parseInt(x),Integer.parseInt(y)-12);
						/*JButton card = new JButton();
						JLabel cardlabel = new JLabel();
						card.setText(title);
						ImageIcon kartu = new ImageIcon("images/cardtemp.png");
						card.setIcon(kartu);
						cardlabel.setText(title);
						card.setBounds(Integer.parseInt(x),Integer.parseInt(y),cardwidth, cardheight);
						cardlabel.setBounds(Integer.parseInt(x),Integer.parseInt(y)-12,cardwidth, 12);
						card.setToolTipText("lingga");
						Game.boardPanel.add(cardlabel);
						Game.boardPanel.add(card);*/
					}
				}
			}
    	}
    	catch(Throwable e)
    	{}
    }
	
	public void Draw(Card CardDrawn){
		H.add(CardDrawn);
		//System.out.println("Card Drawn: " + CardDrawn);
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
		CardPlayed.discard();
		Discard();
	}
	
	public void DisplayHand(){
		int i;
		for(i=0;i<H.size();i++){
			System.out.println(i + ". " + H.get(i));
			H.get(i).draw(i);
		}
	}
	public void UndisplayHand(){
		Discard();
		System.out.println("undisplay hand");
		for (int i=0;i<7;i++)
		{
			Point xy = cardpos[i][0];
			Point xy2 = cardpos[i][1];
			Game.boardPanel.remove(Game.boardPanel.getComponentAt((int)xy.getX(), (int)xy.getY()));
			Game.boardPanel.remove(Game.boardPanel.getComponentAt((int)xy2.getX(), (int)xy2.getY()));
			Game.boardPanel.remove(Game.boardPanel.getComponentAt((int)xy.getX(), (int)xy.getY()));
			Game.boardPanel.remove(Game.boardPanel.getComponentAt((int)xy2.getX(), (int)xy2.getY()));
		}
	}
	
	public boolean Discard(){
		boolean kembalian = false;
		for(Card e : H)
		{
			if(e.isDiscarded())
			{
				kembalian = true;
				H.remove(e);
			}
		}
		return kembalian;
	}
	
	public void DiscardAll(){
		int n = H.size();
		for(int i=0;i<n;i++){
			Game.getDP().GetCard(H.get(0));
			H.remove(0);
		}
	}
}
