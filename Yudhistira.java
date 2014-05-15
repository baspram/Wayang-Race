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
     /* Yudhistiraa's description */
    final String description = "Adityarhedaya";
    
    /* Action of yudhistira */
    public void useAction(){
        //Lalala put your code's here 
        if(rechargeTime>=3 && !actionUsed){
           /* Can't be attacked for this turn */ 
           imunity = true; 
           actionUsed = true;
        }
    }
    public String getDescription(){
        return description;
    }
}
