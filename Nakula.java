/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmad
 */
public class Nakula extends Player{
    public Nakula(){
        id = 2;
    } 
    /* Nakula's description */
    final String description = "Pranawajati";
    
    /* Action of werkudara */
    public void useAction(){
        //Lalala put your code's here 
        if(rechargeTime>=4 && !actionUsed){
            //Pilih kartu lawan untuk disimpan ke tangan
            int playerTarget  = SelectTarget();
            int cardTarget    = SelectCardTarget();
            Game.getPlayers().get(playerTarget).getHand().Discard(cardTarget);
            Game.getCurrentPlayer().getHand().Draw(Game.getDP().getTop());
            rechargeTime = 0;
            actionUsed = true;
        }
    }
    public String getDescription(){
        return description;
    }
}
