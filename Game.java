import java.util.*;
import java.io.*;

/**Kelas utama dalam game ini yang berisi atribut-atribut static yang berada pada dunia game ini,
 * yaitu Array of Player, ActionDeck, TrapDeck, GameBoard, dan DiscardPile. Kelas ini juga menghandle
 * semua fungsi utama dalam mekanisme game, initialization dan turn */
public class Game{
	private static ArrayList<Player> Players;
	private static Deck ActionDeck;
	private static Deck TrapDeck;
	private static Board GameBoard;
	private static DiscardPile DumpPile;
	private static int CurrentPlayer;
	private final int DiceSide = 12;
	private static boolean GameFinish;
	private static int LapNumber;
       
    /**Konstruktor game yang langsung menginisialisasi semua komponen yang diperlukan dalam game
     * @param Nplayer jumlah player yang akan memainkan game, berkisar dari 2-4
     */
	Game(int Nplayer){
		ActionDeck = new Deck();
		TrapDeck = new Deck();
		GameBoard = new Board(Nplayer);
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
	}
		
		/**method yang akan meminta pengguna memasukkan jumlah lap/putaran yang diperlukan untuk menyelesaikan
		 * permainan*/
        public void setNumberLap(){
                Scanner targetin = new Scanner(System.in);
                System.out.println("Number of lap: ");
                LapNumber = targetin.nextInt();
        }
        
        /**getter untuk jumlah lap yang dibutuhkan untuk finish
         * @return jumlah lap yang required untuk finish*/
        public static int getLapNumber(){
                return LapNumber;
        }

		/**fungsi untuk meminta pengguna memilih role yang akan dia gunakan
		 * @return String berupa nama role yang akan dikirim ke PlayerFactory untuk
		 * menghasilkan instansiasi player yang akan digunakan */
        public String SelectTarget(){
                Scanner targetin = new Scanner(System.in);
                String s;
                int i = 0;
                do{
                    if(i>0){
                        System.out.println("Ulangi pilihan role");
                    }
                    System.out.println("Choose Role: ");
                    System.out.println("(Arjuna/Nakula/Sadewa/Werkudara/Yudhistira)");
                    s = targetin.next();
                    i++;
                    if(s.equalsIgnoreCase("Arjuna") || s.equalsIgnoreCase("nakula") ||
                       s.equalsIgnoreCase("sadewa") || s.equalsIgnoreCase("werkudara") ||
                        s.equalsIgnoreCase("yudhistira")){
                        break;
                    }
                }while(i>0);
                return s;
        }   

	/**getter untuk discardpile yang digunakan di dunia game*/
	public static DiscardPile getDP(){
		return DumpPile;
	}
	/**getter untuk Array of player yang bermain game*/
	public static ArrayList<Player> getPlayers(){
		return Players;
	}
	/**getter untuk board game yang digunakan*/
	public static Board getBoard(){
		return GameBoard;
	}
	/**getter untuk index player yang sedang aktif menjalankan gilirannya*/
	public static int getCurrentPlayerIdx(){
		return CurrentPlayer;
	}
	/**getter untuk Player yang sedang aktif menjalankan gilirannya*/
	public static Player getCurrentPlayer(){
		return Players.get(CurrentPlayer);
	}
	/**getter untuk ActionDeck yang digunakan dalam permainan*/
	public static Deck getActionDeck(){
		return ActionDeck;
	}
	/**method untuk memberitahu program bahwa game telah selesai*/
	public static void Finish(){
		GameFinish = true;
	}
	
	/**method untuk menginisialisasi elemen-elemen yang digunakan, seperti meload deck dari file eksternal
	 * hingga menjadi deck yang bisa dimainkan di dalam game*/
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
	
	/**Method utama yang menjalankan semua aksi yang dapat digunakan pemain dalam permainan,
	 * seperti maju dengan kocokan dadu, mainkan kartu tangan, gunakan aksi, hingga pengguna mengakhiri giliran
	 * dan game mengganti pemain yang aktif menjalankan giliran*/
	public void Turn(){
		int opt=0;
		int targettilestatus;
		int movement;
		boolean switchplayer=true;
		BufferedReader Buff = new BufferedReader(new InputStreamReader(System.in));
		Scanner Option = new Scanner(System.in);
                Random Dice = new Random();
		Players.get(CurrentPlayer).StartTurn();
		while(opt!=5){
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
                        System.out.print("3) Aksi Khusus");
                        if(Players.get(CurrentPlayer).isAction()){
                            System.out.println("(Aksi bisa dilakukan!)");
                        }
                        else{
                            System.out.println("(Tidak bisa melakukan aksi)");
                        }
			System.out.println("4) Cek Status");
			System.out.println("5) Akhiri Giliran");
			System.out.println("");
			System.out.println("Kartu di tangan: ");
			if(switchplayer){
				System.out.println("Giliran pemain " + CurrentPlayer + ", tekan enter untuk memulai giliran");
				try{String line = Buff.readLine();}
				catch(Exception e){}
				switchplayer=false;					
			}
			else
			{
				Players.get(CurrentPlayer).getHand().DisplayHand();
				System.out.println("\nPilihan Aksi: ");
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
					case 2: Players.get(CurrentPlayer).PlayCard(); break;
					case 3: Players.get(CurrentPlayer).useAction(); break;
					case 4: printStatus(); break;
					case 5: Players.get(CurrentPlayer).EndTurn();
							switchplayer=true;
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
	}
	
	/**method untuk dijalankan saat seorang pemain berhenti di petak yang merupakan jebakan, method ini akan
	 * memainkan kartu teratas dari deck Trap untuk dikenakan kepada pemain yang menginjaknya
	 * @param target indeks pemain yang terkena jebakan untuk diberi efek dari kartu teratas deck Trap
	 * */
	public static void TrapTriggered(int target){
		System.out.println("Pemain terkena jebakan");
		TrapDeck.PlayTopDeck(target);
		GameBoard.unsetTrap(Players.get(CurrentPlayer).getPosition());
	}
	
	/**method utama untuk memulai menjalankan turn pertama dan mengulang hingga GameFinish dibuat true oleh method Finish
	 * dan pada saat permainan selesai menuliskan pemenangnya
	 * */
	public void Start(){
		GameFinish=false;
		while(!GameFinish){
			Turn();
		}
		System.out.println("\n\nDimenangkan oleh pemain: " + CurrentPlayer);
		System.out.println("\n\n\nPermainan selesai");
	}
        
        /**method untuk menampilkan status seluruh pemain, posisi, role dan aksi yang bisa digunakannya
         * */
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
