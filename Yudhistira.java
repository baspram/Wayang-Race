/**
 * <h1>Kelas untuk role Yudhistira </h1>
 * Kelas Yudhistira merupakan kelas yang mewakili
 * tokoh Yudhistira. Tokoh ini memiliki kekuatan menjaga dirinya dari serangan
 * sebanyak satu putaran.
 * Yudhistira memiliki kekuatan yang dinamakan Adityarhedaya
 * @author LimaPandawa
 * @version  1.0
 */
public class Yudhistira extends Player{
    /**
     * Konstruktor dari Yudhistira yang akan mengganti id menjadi bernilai 5
     */
    public Yudhistira(){
        id = 5;
    }
    /**
     * Atribut description mennjelaskan kekuatan yang dimiliki Yudhistira
     */
    final String description = "Adityarhedaya: Yudhistira dapat menjaga dirinya dari serangan sebanyak satu putaran\n"
            + "Kekuatan dapat dilakukan tiap 3 putaran atau lebih";
    /**
     * Mengembalikan true apabila Yudhistira dapat menggunakan aksi pada
     * suatu putaran
     * @return boolean
     */
     public boolean isAction(){
        return rechargeTime >=4 && !actionUsed;
    }
    /**
     * Menjalankan aksi dari Yudhistira yaitu selama satu putaran 
     * tidak dapat diserang oleh siapa-siapa.
     */
    public void useAction(){
        //Lalala put your code's here 
        if(rechargeTime>=3 && !actionUsed){
           /* Can't be attacked for this turn */ 
           imunity = true; 
           actionUsed = true;
           System.out.println("Aksi berhasil dilakukan! Pemain imun dari serangan sebanyak 1 putaran");
        }
        else{
            System.out.println("Tidak bisa menggunakan aksi ini.");
        }
    }
    /**
     * Getter dari description
     * @return String
     */
    public String getDescription(){
        return description;
    }
    /**
     * Getter dari role
     * @return String 
     */
    public String getRole(){
        return "Yudhistira";
    }
}
