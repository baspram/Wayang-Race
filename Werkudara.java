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
     /* Werkudara's description */
    final String description = "Kuku pancanaka";
    /* Action of werkudara */
    public void useAction(){
        //Lalala put your code's here 
        // Tiap tiga giliran baru bisa jalan
        if(rechargeTime>=3 && !actionUsed){
            //playerTarget dengan position tersebut diundur sebanyak 4 langkah kecuali sampai start
            int playerTarget = SelectTarget();
            Game.getPlayers().get(playerTarget).Attacked(4);
            rechargeTime = 0;
            actionUsed = true;
        }
    }
    public String getDescription(){
        return description;
    }
}
