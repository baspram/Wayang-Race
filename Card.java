import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Card{
	protected boolean isdiscarded = false; 
	protected final int cardwidth = 100;
	protected final int cardheight = 120;
	protected JButton cardimg;
	protected JLabel cardlabel;
	protected String CardName;
	protected String Description;
	
	public Card(){
		CardName = "";
		Description = "";
		}
	
	boolean isDiscarded()
	{
		return isdiscarded;
	}
	
	void discard()
	{
		isdiscarded = true;
	}
	
	public Card(String Name, String desc)
	{
		CardName = Name;
		Description = desc;
		
		cardimg = new JButton();
		cardlabel = new JLabel();
		//cardimg.setText(CardName);
		ImageIcon kartu = new ImageIcon("images/cardtemp.png");
		cardimg.setIcon(kartu);
		cardlabel.setText(CardName);
		cardimg.setToolTipText(desc);
		cardimg.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	if(!isdiscarded)
		    	{
		    		PlayCard();
		    		isdiscarded = true;
		    		ImageIcon kartu = new ImageIcon("images/usedcard.png");
		    		cardimg.setIcon(kartu);
		    	}
		    }
		});
	}
		
	public void PlayCard(){}
	public void PlayCard(int target){}
	public int SelectTarget(){
		//Scanner targetin = new Scanner(System.in);
		//System.out.println("Choose Target: ");
		//return targetin.nextInt();
		return 1;
	}
	public void Discard(){ Game.getDP().GetCard(this); }
	public String getCardName(){ return CardName; }
	public String getDescription(){ return Description; }
	
	public String toString(){
		String S = getCardName() + "\n" + getDescription();
		return S;
	}
	public void draw(int i)
	{
		Point xy = Hand.cardpos[i][0];
		Point xylabel = Hand.cardpos[i][1];
		cardimg.setBounds((int)xy.getX(),(int)xy.getY(),cardwidth, cardheight);
		cardlabel.setBounds((int)xylabel.getX(),(int)xylabel.getY(),cardwidth, 12);
		Game.boardPanel.add(cardlabel);
		Game.boardPanel.add(cardimg);
		//Game.boardWindow.pack();
		//Game.boardPanel.setVisible(true);
	}
}
