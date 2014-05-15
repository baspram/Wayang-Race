import java.util.*;
import java.io.*;

public class Game{
	private static ArrayList<Player> Players;
	private Deck ActionDeck;
	private static Deck TrapDeck;
	private static Board GameBoard;
	private static DiscardPile DumpPile;
	private static int CurrentPlayer;
	private final int DiceSide = 12;
	private static final int ReqLap = 3;
	
	Game(int Nplayer){
		ActionDeck = new Deck();
		TrapDeck = new Deck();
		GameBoard = new Board(Nplayer);
		DumpPile = new DiscardPile();
		Players = new ArrayList<Player>();
		Players.add(new Player()); //untuk menutup indeks player ke 0
		for(int i=1;i<=Nplayer;i++){
			Player playerin = new Player();
			Players.add(playerin);
		}
		CurrentPlayer=1;
		Initialization();
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
	public static int getReqLap(){
		return ReqLap;
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
			Players.get(i).StartLap(ActionDeck);
		}
	}
	
	public void Turn(){
		int opt=0;
		int targettilestatus;
		int movement;
		Scanner Option = new Scanner(System.in);
		Random Dice = new Random();
		Players.get(CurrentPlayer).StartTurn();
		while(opt!=4){
			GameBoard.drawBoard();
			System.out.println("Pemain: " + CurrentPlayer);
			System.out.println(Players.get(CurrentPlayer));
			System.out.print("1) Kocok Dadu");
			if(Players.get(CurrentPlayer).getStop()>0){
				System.out.println("(tidak bisa dilakukan, terkena stop untuk " + Players.get(CurrentPlayer).getStop() + " putaran)");
			}
			else if(Players.get(CurrentPlayer).hasAdvanced()){
				System.out.println("(tidak bisa dilakukan, anda telah melangkah)");
			}
			else{
				System.out.println("");
			}
			System.out.print("2) Mainkan Kartu");
			if(Players.get(CurrentPlayer).hasPlayedACard()){
				System.out.println("(tidak bisa dilakukan, anda telah memainkan sebuah kartu)");
			}
			else{
				System.out.println("");
			}
			System.out.println("3) Pakai Aksi Khusus");
			System.out.println("4) Akhiri Giliran");
			System.out.println("");
			System.out.println("Kartu di tangan: ");
			Players.get(CurrentPlayer).getHand().DisplayHand();
			System.out.println("\nPilihan Aksi: ");
			opt = Option.nextInt();
			switch(opt){
				case 1: movement = 1+Dice.nextInt(DiceSide);
						targettilestatus = GameBoard.move(Players.get(CurrentPlayer), CurrentPlayer, movement);
						Players.get(CurrentPlayer).Advance(movement);
						if(targettilestatus==88){
							TrapTriggered();
						}
						break;
				case 2: Players.get(CurrentPlayer).PlayCard(); break;
				case 3: Players.get(CurrentPlayer).useAction(); break;
				case 4: Players.get(CurrentPlayer).EndTurn();
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
			System.out.println(""); 
		}
	}
	
	public static void TrapTriggered(){
		System.out.println("Pemain terkena jebakan");
		TrapDeck.PlayTopDeck();
	}
	
	public static void finish(){
	}
	
	public void Start(){
		boolean finish = false;
		while(!finish){
			Turn();
		}
	}
	
}
