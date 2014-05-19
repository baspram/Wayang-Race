import java.util.*;
/**
 * <h1>Kelas untuk role Nakula </h1>
 * Kelas Nakula merupakan kelas yang mewakili
 * tokoh Nakula. Tokoh ini memilliki kelebihan yaitu dapat mengambil salah satu
 * kartu lawan dan menaruhna di tangan. Kekuatan dari
 * Nakula ini dinamakan Pranawajati.
 * @author LimaPandawa
 * @version  1.0
 */
public class Nakula extends Player{
    /**
     * Konstruktor dari kelas Nakula, mengganti nilai
     * id menjadi 2
     */
    public Nakula(){
        id = 2;
    } 
    /**
     * Atribut description mewakili kekuatan yang dimiliki oleh Nakula
     */
    final String description = "Pranawajati: Nakula dapat mengambil salah satu kartu lawan dan menaruhnya di tangan\n"
            + "Kekuatan dapat dilakukan tiap 4 putaran atau lebih";
    /**
     * Mengembalikan nilai true apabila Nakula dapat melakukan
     * aksinya yaitu rechargeTime >=4 
     */
    public boolean isAction(){
        return rechargeTime >=4 && !actionUsed;
    }
    /**
     * Menjalankan aksi dari Nakula yaitu menentukan
     * player yang akan diambil kartunya untuk disimpan ke tangan player
     */
    public void useAction(){
        //Lalala put your code's here 
        if(rechargeTime>=4 && !actionUsed){
            //Pilih kartu lawan untuk disimpan ke tangan
            Random rnd = new Random();
            int playerTarget  = SelectTarget();
            int cardTarget    = rnd.nextInt(Game.getPlayers().get(playerTarget).getHand().getSize());
            Game.getPlayers().get(playerTarget).getHand().Discard(cardTarget);
            Game.getCurrentPlayer().getHand().Draw(Game.getDP().getTop());
            rechargeTime = 0;
            actionUsed = true;
            System.out.println("Aksi berhasil dilakukan! Kartu dari pemain ke-" + playerTarget + " ditambahkan ke tangan.");
        }
        else
            System.out.println("Tidak bisa menggunakan aksi ini.");
    }
    /**
     * getter dari Description
     * @return String
     */
    public String getDescription(){
        return description;
    }
    /**
     * getter dari Role
     * @return String
     */
    public String getRole(){
        return "Nakula";
    }
}
