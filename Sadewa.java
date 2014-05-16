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
public class Sadewa extends Player{
    public Sadewa(){
        id = 3;
    }
     /* Werkudara's description */
    final String description = "Sadewah";
    
    /* Action of werkudara */
    public void useAction(){
        //Lalala put your code's here 
        //Kekuatannya sadewa bisa clone
        if(rechargeTime>=4 && !actionUsed){
            int playerType = SelectType();
            if(playerType==1){
                  Arjuna P = new Arjuna();
                  P.useAction();
            }else{
                if(playerType==2){
                    Yudhistira P = new Yudhistira();
                    P.useAction();
                }
                else{
                    if(playerType==3){
                        Werkudara P = new Werkudara();
                        P.useAction();
                    }
                    else{
                        Nakula P = new Nakula();
                        P.useAction();
                    }
                }
            }
            rechargeTime = 0;
            actionUsed = true;
        }
    }
    
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
    
    public String getDescription(){
        return description;
    }
}
