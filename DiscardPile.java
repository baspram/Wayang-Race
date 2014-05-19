import java.util.*;

/**Kelas yang merepresentasikan zona pembuangan kartu, untuk menampung
 * kartu yang telah terpakai atau dibuang paksa, perlu dibuat untuk
 * mengatasi saat deck utama telah habis terpakai untuk diisi ulang oleh discardpile ini
 * */
public class DiscardPile{
	private ArrayDeque<Card> P;
	
	/**Konstruktor default kelas DiscardPile()
	 * */
	public DiscardPile(){
		P = new ArrayDeque<Card>();
	}
	
	/**getter untuk mengambil ukuran tumpukan discardpile
	 * @return int yang merepresentasikan ukuran dari discardpile pada saat diinvoke*/
	public int Ndeck(){
		return P.size();
	}
	
	/**method untuk memasukkan kartu ke dalam discardpile
	 * @param in kelas Card yang akan dimasukkan ke dalam discardpile
	 * */
	public void GetCard(Card in){
		P.push(in);
	}

	/**method untuk memindahkan seluruh isi discardpile ke ArrayDeque<Card> lain,
	 * utamanya digunakan untuk memindahkan isi discardpile ke suatu Deck
	 * @return ArrayDeque<Card> ArrayDeque baru yang berisi kartu-kartu yang tadinya di dalam discardpile
	 * */
	public ArrayDeque<Card> Export(){
		ArrayDeque<Card> temp = new ArrayDeque<Card>();
		Card hapus = new Card();
		while(!P.isEmpty())
		{
			hapus = P.peek();
			P.pop();
			temp.push(hapus);
		}
		return temp;
	}
	
	/**method untuk mengambil kartu teratas dari discard pile, utamanya digunakan untuk
	 * aksi spesial dari nakula untuk mengambil kartu seorang pemain yang dibuang, kartu kemudian
	 * tidak ada lagi di discardpile
	 * @return Card yang berada paling atas di discardpile*/
	public Card getTop()
	{
		Card a = P.peek();
		P.pop();
		return a;
	}
}
