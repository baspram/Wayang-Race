/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmad
 */
public class Yudhistira extends Player{
    public Yudhistira(){
        id = 5;
    }
    /* Yudhistiraa's description */
    final String description = "Adityarhedaya: Yudhistira dapat menjaga dirinya dari serangan sebanyak satu putaran\n"
            + "Kekuatan dapat dilakukan tiap 3 putaran atau lebih";
    
     public boolean isAction(){
        return rechargeTime >=4 && !actionUsed;
    }
    
    /* Action of yudhistira */
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
    public String getDescription(){
        return description;
    }
    
    public String getRole(){
        return "Yudhistira";
    }
}
