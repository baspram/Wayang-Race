/**
 * <h1>Kelas factory untuk menghasilkan player sesuai 
 * dengan rolenya</h1>
 * Kelas ini akan menerima permintaan untuk menciptakan
 * role-role sesuai dengan namanya
 * @author LimaPandawa
 */
public class PlayerFactory {
    /**
     * Konstruktor dari kelas Player Factory
     */
    public PlayerFactory(){}
    /**
     * Method yang digunakan untuk menghasilkan role sesuai dengan 
     * roleName yang diberi. Factory akan menghasilkan salah satu 
     * diantara role berikut: Arjuna, Nakula, Sadewa, Werkudara, Yudhistira
     * @param roleName
     * @return Player
     */
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
