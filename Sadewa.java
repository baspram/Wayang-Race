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
    final String description = "Sadewah: Sadewa dapat meniru karakter wayang lain dan menggunakan kekuatan mereka\n"
            + "Kekuatan dapat dilakukan tiap 4 putaran atau lebih";
    
     public boolean isAction(){
        return rechargeTime >=4 && !actionUsed;
    }
    
    /* Action of werkudara */
    public void useAction(){
        //Lalala put your code's here 
        //Kekuatannya sadewa bisa clone
        if(isAction()){
            int playerType = SelectType();
            if(playerType==1){
                  Arjuna P = new Arjuna();
                  P.setDiceRolled(1);
                  P.useAction();
            }else{
                if(playerType==2){
                    Yudhistira P = new Yudhistira();
                    P.setRechargeTime(5);
                    P.useAction();
                }
                else{
                    if(playerType==3){
                        Werkudara P = new Werkudara();
                        P.setRechargeTime(5);
                        P.useAction();
                    }
                    if(playerType==4){
                        Nakula P = new Nakula();
                        P.setRechargeTime(5);
                        P.useAction();
                    }
                    else{
                        System.out.println("Tidak bisa menggunakan aksi ini.");
                    }
                }
            }
            rechargeTime = 0;
            actionUsed = true;
        }
        else
            System.out.println("Tidak bisa menggunakan aksi ini.");
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
    
    public String getRole(){
        return "Sadewa";
    }
}
