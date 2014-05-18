import java.util.*;

/** <h1>Hand Class</h1>
 * Kelas Hand ini merepresentasikan tangan pemain yang memegang
 * sejumlah kartu yang dapat Ia mainkan
 * 
 * @author Ardi Wicaksono
 * @version 1.0
 * */
public class Hand{
	private ArrayList<Card> H;
    private final int HandSize = 7;
    
    /**Konstruktor default untuk kelas Hand*/
	public Hand(){
		H = new ArrayList<Card>();
	}
	
	/**Method Draw memasukkan kartu pada parameter ke dalam ArrayList H di tangan
	 * @param CardDrawn Kartu yang ditambahkan kedalam tangan, tidak bisa terjadi apabila kartu di tangan lebih dari 7*/
	 	public void Draw(Card CardDrawn){
		if(H.size()<7){
                    H.add(CardDrawn);
                }
                else{
                    System.out.println("Jumlah kartu ditangan telah maksimal");
                }
	}
	
	/**Getter jumlah kartu di tangan
	 * @return jumlah kartu di tangan
	 * */
	public int getSize(){
		return H.size();
	}
	
	/**Method untuk mengambil kartu pada awal lap, mengambil kartu hingga di tangan mencapai 7 kartu
	 * */
	public void DrawStart(){
		for(int i=0;i<HandSize;i++){
			Draw(Game.getActionDeck().Drawn());
		}
	}
	
	/**Method untuk memainkan kartu yang tersedia di tangan
	 * @param i indeks berbasis 0 untuk posisi kartu, kartu ke-i di ArrayList hand
	 * */	
	public void PlayCard(int i){
		Card CardPlayed = new Card();
		CardPlayed = H.get(i);
		CardPlayed.PlayCard();
		Discard(i);
	}
	
	/**Method untuk menampilkan kartu-kartu pada hand pada command line interface*/
	public void DisplayHand(){
		for(int i=0;i<H.size();i++){
			System.out.println(i + ". " + H.get(i));
		}
	}
	
	/**Method untuk men-discard, membuang kartu ke-idxCard dari tangan ke DiscardPile pada Game
	 * @param idxCard Indeks berbasis 0 untuk menunjuk kartu ke-idxCard pada tangn yang akan dibuang
	 * */
	public void Discard(int idxCard){
			Game.getDP().GetCard(H.get(idxCard));
			H.remove(idxCard);
	}
	
	/**Method untuk men-discard seluruh kartu dari tangan ke DiscardPile*/
	public void DiscardAll(){
		int n = H.size();
		for(int i=0;i<n;i++){
			Game.getDP().GetCard(H.get(0));
			H.remove(0);
		}
	}
}
