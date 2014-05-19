import java.util.*;
/**
 * <h1>Kelas untuk role Sadewa </h1>
 * Kelas Sadewa merupakan kelas yang mewakili
 * tokoh Sadewa. Tokoh ini memilliki kelebihan yaitu dapat meniru karakter
 * wayang lain dan menggunakan kekuatan mereka.
 * Sadewa memiliki kekuatan yang dinamakan Sadewah
 * @author LimaPandawa
 * @version  1.0
 */
public class Sadewa extends Player{
    /**
     * Konstruktor dari kelas Sadewa, akan mengganti id menjadi bernilai 3
     */
    public Sadewa(){
        id = 3;
    }
     /**
      * Atribut description menjelaskan kekuatan yang dimiliki Sadewa
      */
    final String description = "Sadewah: Sadewa dapat meniru karakter wayang lain dan menggunakan kekuatan mereka\n"
            + "Kekuatan dapat dilakukan tiap 4 putaran atau lebih";
    /**
     * Mengembalikan true apabila Sadewa dapat menggunakan kekuatannya
     * pada suatu putaran.
     * @return boolean
     */
     public boolean isAction(){
        return rechargeTime >=4 && !actionUsed;
    }
    /**
     * Menjalankan aksi dari Sadewa yaitu meniru karakter atau role yang sesuai 
     * dengan pilihan yang dimasukan oleh player
     */
    public void useAction(){
        //Lalala put your code's here 
        //Kekuatannya sadewa bisa clone
        if(rechargeTime>=4 && !actionUsed){
            int playerType = SelectType();
            if(playerType==1){
                  Arjuna P = new Arjuna();
                  P.setDiceRolled(1);
                  P.useAction();
            }else{
                if(playerType==2){
                    Yudhistira P = new Yudhistira();
                    P.setRechargeTime(5);
                    P.useAction();
                }
                else{
                    if(playerType==3){
                        Werkudara P = new Werkudara();
                        P.setRechargeTime(5);
                        P.useAction();
                    }
                    if(playerType==4){
                        Nakula P = new Nakula();
                        P.setRechargeTime(5);
                        P.useAction();
                    }
                    else{
                        System.out.println("Tidak bisa menggunakan aksi ini.");
                    }
                }
            }
            rechargeTime = 0;
            actionUsed = true;
        }
        else
            System.out.println("Tidak bisa menggunakan aksi ini.");
    }
    /**
     * Menentukan role mana yang diinginkan oleh player untuk 
     * ditiru
     * @return int
     */
   public int SelectType(){
		Scanner targetin = new Scanner(System.in);
		System.out.println("Masukan nomor pilihan");
                System.out.println("Pilihan 1: Arjuna");
                System.out.println("Pilihan 2: Yudhistira");
                System.out.println("Pilihan 3: Werkudara");
                System.out.println("Pilihan 4: Nakula");
                System.out.println("Choose Target: ");
		return targetin.nextInt();
    }
    /**
     * getter dari atribut description
     * @return String
     */
    public String getDescription(){
        return description;
    }
    /**
     * getter dari role
     * @return String
     */
    public String getRole(){
        return "Sadewa";
    }
}
