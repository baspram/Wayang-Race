/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmad
 */
/* Arjuna is one of WayangRace characters */
public class Arjuna extends Player{
    /* Arjuna's description */
    public Arjuna(){
        id = 1;
    }
    final String description = "Panah Pasopati";
    
    /* Action of arjuna */
    public void useAction(){
        //Lalala put your code's here 
        if(getDiceRolled() == 1 && !actionUsed){
            //Choose other player, make it stopped for 1 time
            int choosenPlayer = SelectTarget();
            Game.getPlayers().get(choosenPlayer).Stopped(1);
            actionUsed = true;
        }else{
            if(!hasAdvanced()){
                System.out.println("Kocok dadu terlebih dahulu untuk menjalankan fungsi ini");
            }
            else
                System.out.println("Tidak bisa menggunakan aksi ini.");
        }
    }
    /* show description */
    public String getDescription(){
        return description;
    }
}