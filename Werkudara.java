/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmad
 */
public class Werkudara extends Player{
    public Werkudara(){
        id = 4;
    }
     /* Werkudara's description */
    final String description = "Kuku pancanaka: Werkudara dapat menyerang lawan sehingga memundurkan posisinya sejauh 4 langkah\n"
            + "Kekuatan dapat dilakukan tiap 3 putaran atau lebih";
    /* Action of werkudara */
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
    public String getDescription(){
        return description;
    }
    
    public String getRole(){
        return "Werkudara";
    }
}
