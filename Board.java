import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Board {
	//Constant
	private final int BOARDX = 12;
	private final int BOARDY = 7;
	private final int NBLOCK = 42; 
	private final String CLEARCELL = "99";
	private final String TRAPCELL = "88";
	private final String boardfilename = "circuit.xml";
	//Attribute
	private Cell cell[][];
	boolean initialized = false;
	int nplayer;
	//Method
	// Constructor
	public Board()
	{
		cell = new Cell[BOARDY][BOARDX];
		for(int i=0; i<BOARDY; i++)
			for(int j=0; j<BOARDX; j++)
				cell[i][j] = new Cell();
		initialized = initRoad();
	}
	public Board(int Nplayer)
	{
		cell = new Cell[BOARDY][BOARDX];
		for(int i=0; i<BOARDY; i++)
			for(int j=0; j<BOARDX; j++)
				cell[i][j] = new Cell();
		initialized = initRoad();
		for(int i=1; i<=Nplayer; i++)
		{
			Integer in = new Integer(i);
			cell[0][0].setPlayersIn(in.toString());
		}
	}
	
	// Scan dari file eksternal, membuat jalan
	public boolean initRoad()
	{
        try {
			File file = new File(boardfilename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("tile");
			int i=0; int j=0;
			for (int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					String CellNum = eElement.getElementsByTagName("CellNum").item(0).getTextContent();
					String CellP = eElement.getElementsByTagName("CellP").item(0).getTextContent();
					cell[i][j].setCellNo(CellNum);
					cell[i][j].setPlayersIn(CellP);
					j++;
					if(j==12)
					{
						i++;
						j=0;
					}
				}
			}
			
        } catch (Exception e) {
        	return false;
        }
        return true;
	}
	
	// menggambarkan board ke layar
	public void drawBoard()
	{
		
		if(initialized)
		{
			for(int i=0 ; i<BOARDY ; i++)
			{
				//cetak bagian atas cell, untuk player 1 dan 2
				for(int j=0 ; j<BOARDX ; j++)
				{
					String sel = cell[i][j].getPlayersIn();
					{/*
						String num = cell[i][j].getCellNo();
						if(num.equals(CLEARCELL)) System.out.print("\t");
						else System.out.print(num +"\t");*/
					}
					if(sel.equals(CLEARCELL))
						System.out.print("     ");
					else
					{
						if(sel.contains(TRAPCELL))
							System.out.print("\\");
						else System.out.print("|");
						
						if(sel.contains("1") && sel.contains("2"))
							System.out.print("1|2");
						else if(sel.contains("1"))
							System.out.print("1|_");
						else if(sel.contains("2"))
							System.out.print("_|2");
						else
							System.out.print("_|_");
					
						if(sel.contains(TRAPCELL))
							System.out.print("/");
						else System.out.print("|");
					}
				}
				System.out.println();
				//cetak bagian bawah cell, untuk player 3 dan 4
				for(int j=0 ; j<BOARDX ; j++)
				{
					//System.out.print("\t");
					String sel = cell[i][j].getPlayersIn();
					if(sel.equals(CLEARCELL))
						System.out.print("     ");
					else
					{
						if(sel.contains(TRAPCELL))
							System.out.print("/");
						else System.out.print("|");
						
						if(sel.contains("3") && sel.contains("4"))
							System.out.print("3|4");
						else if(sel.contains("3"))
							System.out.print("3|_");
						else if(sel.contains("4"))
							System.out.print("_|4");
						else
							System.out.print("_|_");
					
						if(sel.contains(TRAPCELL))
							System.out.print("\\");
						else System.out.print("|");
					}
				}
				System.out.println();
			}
			System.out.println();
			/*System.out.println("Kartu Aksi");
			System.out.println(" ___ ");
			System.out.println("|   |");
			System.out.println("|   |");
			System.out.println("|___|");
			System.out.println();*/
		}
	}
	
	// mengembalikan 2 buah integer dalam array yang merupakan koordinat cell dengan nomor tertentu NoCell
	public int[] getCellCoor(String NoCell)
	{
		int baris = 0; int kolom = 0;
		boolean breakloop = false;
		int i=0; int j=0;
		for (i=0 ; i<BOARDY && !breakloop ; i++)
		{
			for (j=0 ; j<BOARDX && !breakloop ; j++)
			{
				if (cell[i][j].getCellNo().equals(NoCell))
					breakloop = true;
			}
		}
		if(breakloop)
		{
			baris = i-1;
			kolom = j-1;
		}
		int kembalian[] = new int[2];
		kembalian[0] = baris;
		kembalian[1] = kolom;
		return kembalian;
	}
	
	public void setTrap(int NoCell)
	{
		Integer trap = new Integer(NoCell);
		int koordinat[] = getCellCoor(trap.toString());
		cell[koordinat[0]][koordinat[1]].setTrap();
	}
	
	// memindahkan player nomor playernumber sebesar increment
	public int move(Player pl, int playernumber, int increment)
	{
		Integer pn = new Integer(playernumber);
		int posnow = pl.getPosition()-increment;
		if(posnow<0){
			posnow = posnow+NBLOCK;
		}
		/*
		switch(playernumber)
		{
		case 1 : 
			posnow = P1pos;
			P1pos = (P1pos + increment)%NBLOCK;
			break;
		case 2 : 
			posnow = P2pos;
			P2pos = (P2pos + increment)%NBLOCK;
			break;
		case 3 : 
			posnow = P3pos;
			P3pos = (P3pos + increment)%NBLOCK;
			break;
		case 4 : 
			posnow = P4pos;
			P4pos = (P4pos + increment)%NBLOCK;
			break;
		}
		*/
		//LETAKKAN di tujuan
		Integer tujuan = new Integer((posnow + increment)%NBLOCK);
		int koordinat[] = getCellCoor(tujuan.toString());
		cell[koordinat[0]][koordinat[1]].setPlayersIn(pn.toString());
		String istrap = cell[koordinat[0]][koordinat[1]].getPlayersIn();
		
		//HAPUS di asal
		Integer asal = new Integer(posnow);
		koordinat = getCellCoor(asal.toString());
		cell[koordinat[0]][koordinat[1]].removePlayer(pn.toString());
		if(istrap.contains(TRAPCELL))
			return 88;
		else
			return 1;
	}
}
