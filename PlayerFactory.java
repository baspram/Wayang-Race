/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahmad
 */
public class PlayerFactory {
    public PlayerFactory(){}
    
    public Player getRole(String roleName){
        if(roleName.equalsIgnoreCase("Arjuna")){
            return new Arjuna();
        }
        else{
            if(roleName.equalsIgnoreCase("Nakula")){
                return new Nakula();
            }
            else{
                if(roleName.equalsIgnoreCase("Sadewa")){
                    return new Sadewa();
                }
                else{
                    if(roleName.equalsIgnoreCase("Werkudara")){
                        return new Werkudara();
                    }
                    else{
                        if(roleName.equalsIgnoreCase("Yudhistira")){
                            return new Yudhistira();
                        }
                    }
                }
            }
        }
        return null;
    }
}
