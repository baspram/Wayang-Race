/**
 * <h1>Kelas untuk role Werkudara </h1>
 * Kelas Werkudara merupakan kelas yang mewakili
 * tokoh Werkudara. Tokoh ini memiliki kekuatan untuk menyerang lawan
 * dan memundurkan posisinya sebanyak 4 langkah
 * Werkudra memiliki kekuatan yang dinamakan Kuku pancanaka
 * @author LimaPandawa
 * @version  1.0
 */
public class Werkudara extends Player{
    /**
     * Konstruktor dari kelas Werkudara, mengganti nilai id menjadi 4
     */
    public Werkudara(){
        id = 4;
    }
     /**
      * Atribut description menjelaskan kekuatan yang dimiliki Werkudara
      */
    final String description = "Kuku pancanaka: Werkudara dapat menyerang lawan sehingga memundurkan posisinya sejauh 4 langkah\n"
            + "Kekuatan dapat dilakukan tiap 3 putaran atau lebih";
    /**
     * Mengembalikan true apabila Werkudara dapat menggunakan kekuatannya
     * pada suatu putaran.
     * @return boolean
     */
    public boolean isAction(){
        return rechargeTime >=3 && !actionUsed;
    }
    /**
     * Menjalankan aksi dari Werkudara yaitu dapat memundurkan posisi
     * pemain sejauh 4 langkah
     */
    public void useAction(){
        //Lalala put your code's here 
        // Tiap tiga giliran baru bisa jalan
        if(rechargeTime>=3 && !actionUsed){
            //playerTarget dengan position tersebut diundur sebanyak 4 langkah kecuali sampai start
            int playerTarget = SelectTarget();
           	int targettilestatus = Game.getBoard().move(Game.getPlayers().get(playerTarget), playerTarget, -4);
            Game.getPlayers().get(playerTarget).Attacked(4);
            if(targettilestatus==88){
				Game.TrapTriggered(Game.getCurrentPlayerIdx());
			}
            rechargeTime = 0;
            actionUsed = true;
            System.out.println("Aksi berhasil dilakukan! Pemain ke-" + playerTarget + " mundur sebanyak 4 langkah");
        }
        else{
            System.out.println("Tidak bisa menggunakan aksi ini.");
        }
    }
    /**
     * Getter dari descriptiion
     * @return String
     */
    public String getDescription(){
        return description;
    }
    /**
     * getter dari role
     * @return 
     */
    public String getRole(){
        return "Werkudara";
    }
}
