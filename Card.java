import java.util.*;

/**
* <h1>Card Class</h1>
* Kelas ini merepresentasikan kartu yang digunakan
* dalam permainan
* 
* @author Ardi W
* @version 1.0
*/ 
public class Card{
	protected String CardName;
	protected String Description;
	
	/**Konstruktor default untuk kelas Card ini */
	public Card(){
		CardName = "";
		Description = "";
		}
	
	/**Konstruktor kelas Card dengan parameter
	 * @param Name memberikan nama dari kartu yang diinstansiasi
	 * @param desc memberikan deskripsi, penjelasan dari kartu yang diinstansiasi*/
	public Card(String Name, String desc){
		CardName = Name;
		Description = desc;
		}
	
	/**Method untuk memainkan kartu tersebut ke dalam permainan dan memberi dampak tertentu,
	 * akan dioverwrite oleh kelas yang menginherit kelas Card ini
	 * */
	public void PlayCard(){}
	
	/**Method untuk memainkan kartu tersebut ke dalam permainan dan memberi dampak tertentu,
	 * akan dioverwrite oleh kelas yang menginherit kelas Card ini, method ini menggunakan 
	 * parameter khususnya untuk digunakan saat TrapTriggered
	 * @param target indeks (1-nplayer) untuk menentukan pemain yang dituju kartu
	 * */
	public void PlayCard(int target){}
	
	/**Method untuk memilih pemain yang akan dikenakan efek 
	 * kartu dengan input dari pengguna via command line
	 * @return indeks dari pemain yang akan dikenakan efek kartu
	 * */
	public int SelectTarget(){
		Scanner targetin = new Scanner(System.in);
		System.out.println("Choose Target: ");
		return targetin.nextInt();
	}
	
	/**Method untuk membuang kartu ke discard pile*/
	public void Discard(){ Game.getDP().GetCard(this); }
	
	/**Getter untuk atribut CardName
	 * @return CardName nama dari kartu yang bersangkutan*/
	public String getCardName(){ return CardName; }
	
	/**Getter untuk atribut Description
	 * @return Description nama dari kartu yang bersangkutan*/
	public String getDescription(){ return Description; }
	
	/**konverter toString() untuk Kelas Card untuk ditampilkan saat kartu dimainkan 
	 * atau ditampilkan saat melihat hand
	 * @return String berisi CardName\nDescription*/
	public String toString(){
		String S = getCardName() + "\n" + getDescription();
		return S;
	}	
}
