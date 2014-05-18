/** <h1>CardFactory Class</h1>
 * Kelas yang merepresentasikan Factory/pabrik saat instansiasi kartu.
 * Digunakan untuk mempermudah apabila ada jenis kartu yang baru dan memenuhi
 * desain pattern Creational/Factory
 * 
 * @author Ardi W
 * @versi 1.0
 * */
public class CardFactory{
	/**Konstruktor standar untuk kelas ini*/
	public CardFactory(){}
	
	/**Fungsi utama pada kelas CardFactory
	 * yang membangkitkan instansiasi dari jenis kartu yang diinginkan
	 * berdasarkan ActionIDnya
	 * @param name Nama yang akan dimiliki kartu yang akan diinstansiasi
	 * @param desc Deskripsi yang akan dimiliki kartu yang akan diinstansiasi
	 * @param power nilai kekuatan yang akan dimiliki kartu yang akan diinstansiasi
	 * @param ActionID ID yang menentukan kategori dari kartu yang akan diinstansiasi
	 * @return Card yang telah diinstansiasi dan berkelas sesuai dengan ActionIDnya*/
	public Card getCard(String name, String desc, int power, int ActionID){
		switch(ActionID){
				case 1 : { return new BoostCard(name, desc, power);}
				case 2 : { return new AttackCard(name, desc, power);}
				case 3 : { return new StopCard(name, desc, power);}
				case 4 : { return new TrapSetCard(name, desc);}
				case 5 : { return new DisarmCard(name, desc, power);}
				case 21 : { return new AttackCard(name,desc,power);}
				case 22 : { return new DisarmCard(name,desc,power);}
				case 23 : { return new StopCard(name,desc,power);}
				default: { return new Card();}
			}
		}
	}
