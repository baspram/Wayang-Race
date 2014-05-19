import java.util.*;

/**
 * <h1>Kelas dasar untuk tiap role di dalam game</h1>
 * Kelas player merupakan kelas yang mewakili setiap
 * pemain dalam permainan. Kelas ini dapat diturunkan
 * menjadi lima role, yaitu Arjuna, Nakula, Sadewa, Werkudara
 * dan Yudhistira
 * @author LimaPandawa
 * @version 1.0
 */
public class Player {
    /**
     * Kelas player menggunakan kelas Hand, dimana kelas 
     * ini mewakili tangan yang menyimpan kartu yang dimiliki
     */
    private Hand hand;
    /**
     * Player's Role; 
     * 1 = Arjuna; 
     * 2 = Nakula; 
     * 3 = Sadewa; 
     * 4 = Werkudara; 
     * 5 = Yudhistira 
     */
    protected int id;
    /** 
     * Posisi pemain di board
     */
    private int position;
    /**
     * Lamanya putaran pemain yang terhenti
     */
    private int stop;
    /** 
     * Jumlah lap yang telah dilewati pemain
     */
    private int currentLap;
    /** 
     * Penanda apakah pemain sudah menggunakan salah satu
     * kartu per satu putaran. Akan bernilai true jika pemain
     * telah memainkan kartu
     */
    private boolean PlayedCard;
    /**
     * Penanda apakah pemain sudah mengocok dadu per satu putaran.
     * Akan bernilai true jika pemain telah mengocok dadunya
     */
    private boolean Advanced;
    /**
     * Penanda apakah pemain sudah memakai aksi karakternya 
     * per satu putaran. Akan bernilai true jika pemain telah 
     * memakai aksinya
     */
    protected boolean actionUsed;
    /**
     * Penanda keimunan pemain. Jika bernilai true, maka pemain 
     * tidak dapat terkena serangan dari bentuk serangan apapun
     */
    protected boolean imunity;
    /**
     * Lamanya waktu bagi tiap pemain untuk merecharge kekuatan
     * yang akan dipakai dalam menggunakan aksi tiap Role
     */
    protected int rechargeTime;
    /**
     * Menyimpan nilai angka dadu yang dikocok oleh pemain
     * tiap putarannya
     */
    private int diceRolled;
    /**
     * Konstruktor dari kelas Player. Dipanggil tiap kali
     * pemain diciptakan, dan menginisialisasi tiap atribut dari
     * Player
     */
    public Player(){
        hand = new Hand();
        position = 1;
        stop = 0;
        currentLap = 1;
        Advanced = false;
        PlayedCard = false;
	imunity = false;
	rechargeTime = 0;
        diceRolled = 0;
        actionUsed = false;
        id = 0;
    }
    /**
     * Player's getter position
     */
    public int getPosition(){
        return position;
    }
    /**
     * Player's getter ID
     */
    public int getID(){
        return id;
    }
    /**
     * Player's getter Stop
     */
    public int getStop(){
        return stop;
    }
    /**
     * Player's getter CurrentLap
     */
    public int getCurrentLap(){
        return currentLap;
    }
    /**
     * Player's getter dice rolled
     */
    public int getDiceRolled(){
        return diceRolled;
    }
    /**
     * Player's getter hand
     */
    public Hand getHand(){
		return hand;
    }
    /**
     * Player's getter advanced
     */
    public boolean hasAdvanced(){
            return Advanced;
    }
    /**
     * Player's getter playedCard
     */
    public boolean hasPlayedACard(){
            return PlayedCard;
    }
    /**
     * Player's setter dicerolled
     */
    public void setDiceRolled(int num){
        diceRolled = num;
    }
    /**
     * Player's settter rechargeTime
     */
    public void setRechargeTime(int num){
        rechargeTime = num;
    }
    /** 
     * Print status dari pemain saat ini
     * Yang diprint adalah role, banyaknya putaran serta posisinya
     */
    public String toString(){
		String role;
                if(id == 1){
                    role = "Arjuna";
                }
                else{
                    if(id == 2){
                        role = "Nakula";
                    }
                    else{
                        if(id==3){
                            role = "Sadewa";
                        }
                        else{
                            if(id==4){
                                role = "Werkudara";
                            }
                            else{
                                role = "Yudhistira";
                            }
                        }
                    }
                }
                String S = "Role: " + role + "\nPutaran: " + currentLap + "\nPosisi: " + position;
		return S;
	}
    /**
     * Menginisialisasi atribut player 
     * setiap giliran putaran
     */
    public void StartTurn(){
		Advanced = false;
		PlayedCard = false;
		imunity = false;
                diceRolled = 0;
                actionUsed = false;
    }
    /**
     * Mengembalikan indeks dari pemain yang ingin 
     * diserang
     */
    public int SelectTarget(){
			Scanner targetin = new Scanner(System.in);
		Random rnd = new Random();
		int targetpl;
		System.out.println("Choose Target: ");
		try{
			targetpl = targetin.nextInt();
			if(targetpl<1||targetpl>Game.getPlayers().size()){
				throw new Exception();
			}
		}
		catch(Exception e){
			targetpl = 1+rnd.nextInt(Game.getPlayers().size()-1);
		}
		return targetpl;
    }
    /**
     * Mengembalikan indeks dari kartu yang ingin 
     * diambil
     */
    public int SelectCardTarget(){
		Scanner targetin = new Scanner(System.in);
    		System.out.println("Choose Card: ");
		return targetin.nextInt();
    }
    /**
     * Mengembalikan true apaila pemain dapat
     * melakukan aksi mengocok dadu
     */
    public boolean CanAdvance(){
		return !Advanced&& stop==0;
	}
    /**
     * Merubah posisi dari player sebanyak 
     * kocokan dadu yang diberikan. Apabila posisi player telah melalui
     * banyaknya Lap yang ditentukan di game maka player akan menang
     */
    public void Advance(int diceNum){
		position += diceNum;
		while(position>=Board.getNBlock()){
			position %= Board.getNBlock();
			currentLap++;
			StartLap();		
		}
        diceRolled = diceNum;
		Advanced = true;
                if(currentLap > Game.getLapNumber()){
                    System.out.println("Balapan ini dimenangkan oleh: " + this);
                    Game.Finish();
                }
    }
    /**
     * Mengubah posisi dari pemain karena telah
     * terkena serangan
     */
    public void Attacked(int damage){
            if(position==0||position==42){
				return;
			}
            if(!imunity){
                    position -= damage;
                    if(position<1){
                            position = 1;
                    }
            }
     }
    /**
     * Mempercepat pemain dengan menambah posisi
     * pemain di dalam peta sebanyak power
     */
    public void Boost(int power){
            position +=power;
            while(position>=Board.getNBlock()){
                    position %= Board.getNBlock();
                    currentLap++;
                    StartLap();	
            }
    }    
    /**
     * Player memainkan kartu yang dimilikinya ditangan
     */
    public void PlayCard(){
		if(PlayedCard==false){
			Scanner CardChoice = new Scanner(System.in);
			System.out.print("Pilih nomor kartu yang ingin dimainkan(99 untuk batal): ");
			int IdxCard = CardChoice.nextInt();
			if(IdxCard!=99 && IdxCard<hand.getSize()){
				hand.PlayCard(IdxCard);
				PlayedCard = true;
			}
		}
		else{
			System.out.println("Anda telah menggunakan kartu di putaran ini");
		}
    }
    /**
     * Putaran pemain dihentikan sebanyak suspend kali
     */
    public void Stopped(int suspend){
        if(!imunity){
            stop = suspend;
        }
    }
    /**
     * Menambahkan currentLap player sebanyak satu
     */
    public void increaseLap(){
        currentLap++;
    }
    /**
     * Setiap player menyelesaikan giliran permainannya, maka
     * fungsi ini akan dipanggil
     */
    public void EndTurn(){
		if (stop>0){
			stop--;
		}
                rechargeTime++;
	}
    /**
    * Setiap pemain berhasil menyelesaikan satu lap maka
    * fungsi ini akan dipanggil
    */
    public void StartLap(){
		hand.DiscardAll();
		hand.DrawStart();
	}
   /**
    * Fungsi yang akan di-override oleh 
    * role-role
    */
    public void useAction(){
        ;
    }
    /***
     * Setter dari position
     */
    public void setPosition(int pos){
        position = pos;
    }
    /**
     * Getter dari description
     */
    public String getDescription(){
        return "";
    }
    /**
     * mengembalikan true apabila
     * rechargeTime dari player besar 0
     */
    public boolean isAction(){
        return rechargeTime>0;
    }
    /**
     * getter dari Imun
     */
    public boolean getImun(){
        return imunity;
    }
}
