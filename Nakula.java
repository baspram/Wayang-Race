/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author Ahmad
 */
public class Nakula extends Player{
    public Nakula(){
        id = 2;
    } 
    /* Nakula's description */
    final String description = "Pranawajati: Nakula dapat mengambil salah satu kartu lawan dan menaruhnya di tangan\n"
            + "Kekuatan dapat dilakukan tiap 4 putaran atau lebih";
    
    public boolean isAction(){
        return rechargeTime >=4 && !actionUsed;
    }
    
    /* Action of werkudara */
    public void useAction(){
        //Lalala put your code's here 
        if(rechargeTime>=4 && !actionUsed){
            //Pilih kartu lawan untuk disimpan ke tangan
            Random rnd = new Random();
            int playerTarget  = SelectTarget();
            int cardTarget    = rnd.nextInt(Game.getPlayers().get(playerTarget).getHand().getSize());
            Game.getPlayers().get(playerTarget).getHand().getH().get(cardTarget).discard();
            Game.getPlayers().get(playerTarget).getHand().Discard();
            Game.getCurrentPlayer().getHand().Draw(Game.getDP().getTop());
            rechargeTime = 0;
            actionUsed = true;
            System.out.println("Aksi berhasil dilakukan! Kartu dari pemain ke-" + playerTarget + " ditambahkan ke tangan.");
        }
        else
            System.out.println("Tidak bisa menggunakan aksi ini.");
    }
    public String getDescription(){
        return description;
    }
    
    public String getRole(){
        return "Nakula";
    }
}
