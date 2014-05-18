import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cell {
	//Window
	int ntoken;
	ImageIcon cellIcon;
	JLabel cellText;
	JLabel cellTile;
	ArrayList<JLabel> Player;
	String[] P;
	private final int tileSize = 73;
	private final int tokenSize = 40;
	private final String normalTile = "images/normal.jpg";
	private final String trapTile = "images/trap.jpg";
	//Atribut
	private String playersIn;
	private String cellNo;
	private boolean isTemporaryTrap = false;
	//Konstantas
	private final String clearCell = "99";
	private final String trapCell = "88";
	private final String noPlayerCell = "0";
	//Methods
	
	//konstruktor
	public Cell(String[] Pl, int nPlayer)
	{
		//Window
		P = new String[nPlayer];
		Player = new ArrayList<JLabel>(nPlayer);
		for(int i=0; i<nPlayer; i++)
		{
			JLabel e = new JLabel();
			Player.add(e);
			P[i]=new String(Pl[i]);
		}
		ntoken = nPlayer;
		cellText = new JLabel();
		cellTile = new JLabel();
		//normal
		cellNo = clearCell;
		playersIn = clearCell;
	}
	
	// window setTilePosition
	public void setTilePosition(int x, int y)
	{
		//System.out.println("setlocation : " + x + " " + y);
		cellTile.setLocation(x, y);
		cellTile.setBounds(x,y,tileSize,tileSize);
		cellTile.setVisible(true);
		
		cellText.setLocation(x, y);
		cellText.setBounds(x,y,tileSize,tileSize);
		cellText.setVisible(true);
		
		for(int i=0; i<ntoken; i++)
		{
			int addx=0; int addy=0;
			switch(i)
			{
			case 1: addx = 36; break; //token 2
			case 2: addy = 36; break; //token 3
			case 3: addx = 36; addy = 36; break; //token 4
			}
			Player.get(i).setLocation(x+addx, y+addy);
			Player.get(i).setBounds(x+addx,y+addy,tokenSize,tokenSize);
			Player.get(i).setVisible(true);
		}
	}
	
	// window gettile
	public JLabel[] getTile()
	{
		System.out.println("getTile");
		JLabel[] gi = new JLabel[ntoken+2];
		gi[0] = cellTile;
		gi[1] = cellText;
		for(int i=2; i<ntoken+2; i++)
			gi[i] = Player.get(i-2);
		return gi;
	}
	
	// get nomor cell ini
	public String getCellNo()
	{
		return cellNo;
	}
	
	// get player-player mana saja yang tokennya ada di cell ini
	public String getPlayersIn()
	{
		return playersIn;
	}
	
	// get nomor cell ini
	public void setCellNo(String No)
	{
		cellNo = No;
		
		//text dalam tile
		cellText.setSize(tileSize, tileSize);
		cellText.setText(No);
		cellText.setForeground(Color.white);
		cellText.setFont(new Font("Arial", Font.PLAIN, 20));
		cellText.setVerticalTextPosition(JLabel.CENTER);
		cellText.setHorizontalAlignment(JLabel.CENTER);
		cellText.setVisible(true);
	}
	
	// mengeset cell menjadi trap temprorary
	public boolean setTrap()
	{
		//bukan temporary trap dan bukan trap permanen
		if(!playersIn.contains(trapCell))
		{
			setPlayersIn(trapCell); //letakkan mark trap 88
			isTemporaryTrap = true;
			return true;
		}
		else return false;
	}
	
	public boolean isTrap()
	{
		return playersIn.contains(trapCell);
	}
	
	// unset cell trap temporary menjadi cell biasa
	public boolean unsetTrap()
	{
		if(isTemporaryTrap)
		{
			removePlayer(trapCell); //hapus mark trap 88
			isTemporaryTrap = false;
			return true;
		}
		else return false;
	}
	
	// menambahkan player P ke cell ini
	public void setPlayersIn(String P)
	{	
		int a = Integer.parseInt(P);
		if(a >= 1 && a <= ntoken)
		{
			System.out.println("set token : images/" + this.P[a-1] + ".png");
			ImageIcon c = new ImageIcon("images/" + this.P[a-1] + ".png");
			Player.get(a-1).setIcon(c);
		}
		
		//urusan player
		if(playersIn.equals(clearCell) || playersIn.equals(noPlayerCell))
		{
			playersIn = P;
		}
		else if(!playersIn.contains(P))
		{
			playersIn = playersIn.concat(P);
		}
		
		//window buat trap atau bukan
		if(playersIn.contains(trapCell))
			cellIcon = new ImageIcon(ClassLoader.getSystemResource(trapTile));
		else
			cellIcon = new ImageIcon(ClassLoader.getSystemResource(normalTile));
		cellTile.setVerticalTextPosition(JLabel.CENTER);
		cellTile.setIcon(cellIcon);
		cellTile.setSize(tileSize,tileSize);
		cellTile.setVisible(true);
	}
	
	// menghapus player P dari cell ini
	public boolean removePlayer(String P)
	{
		if(playersIn.contains(P))
		{
			//System.out.println("hapus " + P + " di " + playersIn);
			playersIn = playersIn.replaceAll(P, "");
			//System.out.println("hasilnya : " + playersIn);
			
			if(P.equals(trapCell))
			{
				cellIcon = new ImageIcon(ClassLoader.getSystemResource(normalTile));
				cellTile.setIcon(cellIcon);
				cellTile.setVisible(true);
			}
			int a = Integer.parseInt(P)-1;
			if(a>=0 && a<ntoken) Player.get(a).setIcon(null);
			return true;
		}
		else return false;
	}

}
