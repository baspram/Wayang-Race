import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Game{
	//atribut
	public static JButton dice;
	public static JButton spaction;
	public static JButton endturn;
	private static JLabel lapindicator;
	private static JLabel actionindicator;
	private static JLabel advanceindicator;
	public static JFrame boardWindow;
	public static JPanel boardPanel;
	private static ArrayList<Player> Players;
	private static Deck ActionDeck;
	private static Deck TrapDeck;
	private static Board GameBoard;
	private static DiscardPile DumpPile;
	private static int CurrentPlayer;
	private final int DiceSide = 12;
	private static boolean GameFinish;
	private static int LapNumber;
	public static int target;
        
	Game(int Nplayer){
		ActionDeck = new Deck();
		TrapDeck = new Deck();
		DumpPile = new DiscardPile();
		Players = new ArrayList<Player>();
                PlayerFactory Factory = new PlayerFactory();
		Players.add(new Player()); //untuk menutup indeks player ke 0
		for(int i=1;i<=Nplayer;i++){
			Player playerin = Factory.getRole(SelectTarget());
			Players.add(playerin);
		}
		CurrentPlayer=1;
                setNumberLap();
		Initialization();
		initWindow();
		GameBoard = new Board(Nplayer,Players);
		Players.get(CurrentPlayer).getHand().DisplayHand();
		boardWindow.revalidate();
		boardWindow.repaint();
		boardWindow.setVisible(true);
	}
	
	//inisialisasi window
	public static void initWindow()
	{
		Game.boardPanel = new JPanel();
		Game.boardPanel.setLayout(null);
		Game.boardWindow = new JFrame();
		Game.boardWindow.setLocationRelativeTo(null);
		Game.boardWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.boardWindow.setSize(1366,768);
		Game.boardWindow.setLocation(200,200);
		Game.boardWindow.getContentPane().add(Game.boardPanel);
		initButtons();
		Hand.initHandImg();
		Game.boardWindow.pack();
		Game.boardWindow.setVisible(true);
	}
	
	public static void initButtons()
	{
		final int posx = 194;
		final int posy = 340;
		final int addy = 60;
		final int w = 124;
		final int h = 45;
		
		//INDIKATOR LAP
		lapindicator = new JLabel();
		lapindicator.setFont(new Font("Arial", Font.PLAIN, 15));
		lapindicator.setText("Putaran ke-1");
		lapindicator.setBounds(posx,posy,w,h);
		boardPanel.add(lapindicator);
		
		//INDIKATOR AKSI
		actionindicator = new JLabel();
		actionindicator.setFont(new Font("Arial", Font.PLAIN, 15));
		if(Players.get(CurrentPlayer).isAction())
			actionindicator.setText("Aksi Siap");
		else
			actionindicator.setText("Aksi Belum Siap");
		actionindicator.setBounds(posx,posy+addy,w,h);
		boardPanel.add(actionindicator);
		
		//INDIKATOR ADVANCE
		advanceindicator = new JLabel();
		advanceindicator.setFont(new Font("Arial", Font.PLAIN, 15));
		if(Players.get(CurrentPlayer).CanAdvance())
			advanceindicator.setText("Boleh Berjalan");
		else
			advanceindicator.setText("Belum boleh berjalan");
		advanceindicator.setBounds(posx,posy+2*addy,w,h);
		boardPanel.add(advanceindicator);
		
		//DICE
		dice = new JButton();
		dice.setFont(new Font("Arial", Font.PLAIN, 20));
		dice.setText("1");
		dice.setBounds(724, 340, 103, 103);
		dice.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	int movement = 0;
		    	int DiceSide = 12;
		    	int targettilestatus;
		    	Random Dice = new Random();
		    	if(Players.get(CurrentPlayer).CanAdvance()){
					movement = 1+Dice.nextInt(DiceSide);
					targettilestatus = GameBoard.move(Players.get(CurrentPlayer), CurrentPlayer, movement);
					Players.get(CurrentPlayer).Advance(movement);
					if(targettilestatus==88){
						TrapTriggered(CurrentPlayer);
					}
					Integer m = new Integer(movement);
					dice.setText(m.toString());
					
				}
				else{
					System.out.println("Anda telah maju dari kocokan dadu putaran ini atau sedang terkena stop");
				}

		    	Players.get(CurrentPlayer).getHand().UndisplayHand();
		    	Players.get(CurrentPlayer).getHand().DisplayHand();
				boardWindow.revalidate();
				boardWindow.repaint();
				boardWindow.setVisible(true);
				lapindicator.setText(lapindicator.getText().substring(0, 11) + Players.get(CurrentPlayer).getCurrentLap());
				if(Players.get(CurrentPlayer).CanAdvance())
					advanceindicator.setText("Boleh Berjalan");
				else
					advanceindicator.setText("Belum boleh berjalan");
		    } 
		});
		boardPanel.add(dice);
		//SPACTION
		spaction = new JButton();
		spaction.setText("Special Action");
		spaction.setBounds(724, 443, 103, 36);
		spaction.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	Players.get(CurrentPlayer).useAction();
		    	Players.get(CurrentPlayer).getHand().DisplayHand();
		    	lapindicator.setText(lapindicator.getText().substring(0, 11) + Players.get(CurrentPlayer).getCurrentLap());
		    	if(Players.get(CurrentPlayer).isAction())
					actionindicator.setText("Aksi Siap");
				else
					actionindicator.setText("Aksi Belum Siap");
		    	if(Players.get(CurrentPlayer).CanAdvance())
					advanceindicator.setText("Boleh Berjalan");
				else
					advanceindicator.setText("Belum boleh berjalan");
		    } 
		});
		boardPanel.add(spaction);
		//ENDTURN
		endturn = new JButton();
		endturn.setText("End Turn");
		endturn.setBounds(724, 479, 103, 36);
		endturn.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("lap total adalah : " + LapNumber);
				System.out.println("lap saya : " + Players.get(CurrentPlayer).getCurrentLap());
		    	Players.get(CurrentPlayer).getHand().UndisplayHand();
		    	Players.get(CurrentPlayer).EndTurn();
				if(CurrentPlayer<Players.size()-1){
					CurrentPlayer++;
					}
				else{
					CurrentPlayer=1;
					} 
				Players.get(CurrentPlayer).StartTurn(); 
				Players.get(CurrentPlayer).getHand().DisplayHand();
				boardWindow.revalidate();
				boardWindow.repaint();
				boardWindow.setVisible(true);
				//boardWindow.setSize(1366,768);
				lapindicator.setText(lapindicator.getText().substring(0, 11) + Players.get(CurrentPlayer).getCurrentLap());
				if(Players.get(CurrentPlayer).isAction())
					actionindicator.setText("Aksi Siap");
				else
					actionindicator.setText("Aksi Belum Siap");
				if(Players.get(CurrentPlayer).CanAdvance())
					advanceindicator.setText("Boleh Berjalan");
				else
					advanceindicator.setText("Belum boleh berjalan");
		    } 
		});
		boardPanel.add(endturn);
	}
	
	
	
    public void setNumberLap(){
            Scanner targetin = new Scanner(System.in);
            System.out.println("Number of lap: ");
            LapNumber = targetin.nextInt();
    }
    
    public static int getLapNumber(){
            return LapNumber;
    }

    public String SelectTarget(){
                Scanner targetin = new Scanner(System.in);
                System.out.println("Choose Role: ");
                System.out.println("(Arjuna/Nakula/Sadewa/Werkudara/Yudhistira)");
                return targetin.next();
    }   

	public static DiscardPile getDP(){
		return DumpPile;
	}
	public static ArrayList<Player> getPlayers(){
		return Players;
	}
	public static Board getBoard(){
		return GameBoard;
	}
	public static int getCurrentPlayerIdx(){
		return CurrentPlayer;
	}
	public static Player getCurrentPlayer(){
		return Players.get(CurrentPlayer);
	}
	public static Deck getActionDeck(){
		return ActionDeck;
	}
	public static void Finish(){
		GameFinish = true;
		boardPanel.removeAll();
		boardPanel.revalidate();
		boardPanel.repaint();
		boardWindow.removeAll();
		boardWindow.revalidate();
		boardWindow.repaint();
		boardWindow.setVisible(true);
	}
	
	public void Initialization(){
		try{
			ActionDeck.LoadDeck(new File("ActionDeck.xml"));
			TrapDeck.LoadDeck(new File("TrapDeck.xml"));
			GameBoard.initRoad();
		}
		catch(Exception e){}
		ActionDeck.Shuffle();
	            TrapDeck.Shuffle();
		for(int i=1;i<Players.size();i++){
			Players.get(i).StartLap();
		}
	}
	
	public void Turn(){
		int opt=0;
		int targettilestatus;
		int movement;
		//BufferedReader Buff = new BufferedReader(new InputStreamReader(System.in));
		Scanner Option = new Scanner(System.in);
	            Random Dice = new Random();
		Players.get(CurrentPlayer).StartTurn();
		while(opt!=5){
			GameBoard.drawBoard();
			System.out.println("Pemain: " + CurrentPlayer);
			System.out.println(Players.get(CurrentPlayer));
			//System.out.print("1) Kocok Dadu");
			if(Players.get(CurrentPlayer).getStop()>0){
				System.out.println("(tidak bisa dilakukan, terkena stop untuk " + Players.get(CurrentPlayer).getStop() + " putaran)");
			}
			else if(Players.get(CurrentPlayer).hasAdvanced()){
				System.out.println("(tidak bisa dilakukan, anda telah melangkah)");
			}
			else{
				System.out.println("");
			}
			//System.out.print("2) Mainkan Kartu");
			/*if(Players.get(CurrentPlayer).hasPlayedACard()){
				System.out.println("(tidak bisa dilakukan, anda telah memainkan sebuah kartu)");
			}
			else{
				System.out.println("");
			}
	        System.out.println("3) Aksi Khusus");
			System.out.println("4) Cek Status");
			System.out.println("5) Akhiri Giliran");
			System.out.println("");
			System.out.println("Kartu di tangan: ");*/
				Players.get(CurrentPlayer).getHand().DisplayHand();
				/*System.out.println("\nPilihan Aksi: ");
				opt = Option.nextInt();
				switch(opt){
					case 1: if(Players.get(CurrentPlayer).CanAdvance()){
								movement = 1+Dice.nextInt(DiceSide);
								targettilestatus = GameBoard.move(Players.get(CurrentPlayer), CurrentPlayer, movement);
								Players.get(CurrentPlayer).Advance(movement);
								if(targettilestatus==88){
									TrapTriggered(CurrentPlayer);
								}
							}
							else{
								System.out.println("Anda telah maju dari kocokan dadu putaran ini atau sedang terkena stop");
							}
							break;
					//case 2: Players.get(CurrentPlayer).PlayCard(); break;
					case 3: Players.get(CurrentPlayer).useAction(); break;
					case 4: printStatus(); break;
					case 5: Players.get(CurrentPlayer).EndTurn();
							if(CurrentPlayer<Players.size()-1){
								CurrentPlayer++;
								}
							else{
								CurrentPlayer=1;
								} 
							Players.get(CurrentPlayer).StartTurn(); 
							break;
					default: System.out.println("input tidak valid"); break;
				}
			System.out.println("");*/ 
		}
	}
	
	public static void TrapTriggered(int target){
		System.out.println("Pemain terkena jebakan");
		TrapDeck.PlayTopDeck(target);
		GameBoard.unsetTrap(Players.get(CurrentPlayer).getPosition());
	}
	
	public void Start(){
		GameFinish=false;
		while(!GameFinish){
			
		}
		System.out.println("\n\nDimenangkan oleh pemain: " + CurrentPlayer);
		System.out.println("\n\n\nPermainan selesai");
	}
    
    public void printStatus(){
          int i =0;
          for(Player e: Players){
              if(i>0){
                System.out.println("Player ke-"+i);
                System.out.println(e.toString());
                System.out.println("Aksi:");
                System.out.println(e.getDescription());
              }
              i++;
          }  
    }
}
