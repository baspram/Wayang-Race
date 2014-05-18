import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

class AttackCard extends Card{
	protected int damage;
	private final int xpos = 992;
	private final int ypos = 64;
	private final int width = 130;
	private final int height = 50;
	private JLabel TargetSerang;
	private JButton[] players;
	
	public AttackCard(String Name, String desc, int dmg){
		super(Name,desc);
		players = new JButton[Game.getPlayers().size()-1];
		damage = dmg;
		TargetSerang = new JLabel();
		TargetSerang.setText("Target Serang");
		TargetSerang.setFont(new Font("Arial", Font.PLAIN, 17));
		TargetSerang.setToolTipText("klik dahulu target player baru klik kartu serang");
		TargetSerang.setBounds(xpos,ypos/3,width*2,height);
		
		for(int i=0;i<Game.getPlayers().size()-1;i++)
		{
			players[i] = new JButton();
			final int a = i;
			players[i].setText(Game.getPlayers().get(i+1).getName()+" (P"+(i+1)+")");
			//players[i].setText("P"+(i+1));
			if(i==0) players[i].setBounds(xpos, ypos, width, height);
			else if (i==1) players[i].setBounds(xpos + width + 10, ypos, width, height);
			else if (i==2) players[i].setBounds(xpos, ypos + height + 10, width, height);
			else if (i==3) players[i].setBounds(xpos + width + 10, ypos + height + 10, width, height);
			players[i].addActionListener(new ActionListener() { 
			    public void actionPerformed(ActionEvent e) {
			    	Game.target = a;
			    	System.out.println("set target : " + a);
			    }
			});
		}
	}
	
	public void draw(int i)
	{
		Point xy = Hand.cardpos[i][0];
		Point xylabel = Hand.cardpos[i][1];
		cardimg.setBounds((int)xy.getX(),(int)xy.getY(),cardwidth, cardheight);
		cardlabel.setBounds((int)xylabel.getX(),(int)xylabel.getY(),cardwidth, 12);
		Game.boardPanel.add(cardlabel);
		Game.boardPanel.add(cardimg);
		Game.boardPanel.add(TargetSerang);
		for(int j=0;j<Game.getPlayers().size()-1;j++)
		{
			Game.boardPanel.add(players[j]);
		}
		Game.boardWindow.revalidate();
		Game.boardWindow.repaint();
		Game.boardWindow.setVisible(true);
	}
	
	public void PlayCard(){
		int targetpl = Game.target+1;
		System.out.println("playcard tanpa parameter");
		System.out.println("Played: " + this);
		System.out.println("Player: " + targetpl + " mundur " + damage + " langkah");
		int targettilestatus;
		targettilestatus = Game.getBoard().move(Game.getPlayers().get(targetpl), targetpl, damage*-1);
		Game.getPlayers().get(targetpl).Attacked(damage);
		if(targettilestatus==88){
			Game.TrapTriggered(targetpl);
		}
	}
	
	public void PlayCard(int target){
		System.out.println("Played: " + this);
		System.out.println("Player: " + target + " mundur " + damage + " langkah");
		int targettilestatus;
		targettilestatus = Game.getBoard().move(Game.getPlayers().get(target), target, damage*-1);
		Game.getPlayers().get(target).Attacked(damage);
		if(targettilestatus==88){
			Game.TrapTriggered(target);
		}
	}
}

class BoostCard extends Card{
	private int damage;
	
	public BoostCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		System.out.println("Played: " + this);
		System.out.println("Karakter anda maju " + damage + " langkah");
		int targettilestatus = Game.getBoard().move(Game.getCurrentPlayer(), Game.getCurrentPlayerIdx(), damage);
		Game.getCurrentPlayer().Boost(damage);
		if(targettilestatus==88){
			Game.TrapTriggered(Game.getCurrentPlayerIdx());
		}
	}
}

class StopCard extends Card{
	protected int damage;
	
	public StopCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
		Game.getPlayers().get(targetpl).Stopped(damage);
	}

	public void PlayCard(int targetpl){
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " berhenti " + damage + " putaran");
		Game.getPlayers().get(targetpl).Stopped(damage);
	}
}

class TrapSetCard extends Card{
	public TrapSetCard(String Name, String desc){
		super(Name,desc);
	}
	
	public void PlayCard(){
		System.out.println("Played: " + this);
		System.out.println("Satu petak di belakang anda menjadi petak perangkap");
		Game.getBoard().setTrap(Game.getPlayers().get(Game.getCurrentPlayerIdx()).getPosition()-1);
	}
}

class DisarmCard extends Card{
	protected int damage;	
	
	public DisarmCard(String Name, String desc, int dmg){
		super(Name,desc);
		damage = dmg;
	}
	
	public void PlayCard(){
		int targetpl = SelectTarget();
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
		Random index = new Random();
		for (int i = 0; i < damage; i++)
		{
			int pil = index.nextInt(Game.getPlayers().get(targetpl).getHand().getSize());
			Game.getPlayers().get(targetpl).getHand().getH().get(pil).discard();;
			Game.getPlayers().get(targetpl).getHand().Discard();
		}
	}
	
	public void PlayCard(int targetpl){
		System.out.println("Played: " + this);
		System.out.println("Player " + targetpl + " membuang 1 kartunya secara random");
		Random index = new Random();
		for (int i = 0; i < damage; i++)
		{
			int pil = index.nextInt(Game.getPlayers().get(targetpl).getHand().getSize());
			Game.getPlayers().get(targetpl).getHand().getH().get(pil).discard();
			Game.getPlayers().get(targetpl).getHand().Discard();
		}
	}
}
