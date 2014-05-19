/**
 * <h1>Kelas untuk role Arjuna </h1>
 * Kelas Arjuna merupakan kelas yang mewakili
 * tokoh Arjuna. Tokoh ini memilliki kelebihan yaitu dapat memberhentikan
 * pemain sebanyak satu putaran. Nama kekuatan yang dimiliki arjuna adalah
 * Panah Pasopati.
 * @author LimaPandawa
 * @version  1.0
 */
/* Arjuna is one of WayangRace characters */
public class Arjuna extends Player{
    /**
     * Konstruktor dari kelas Arjuna, mengganti nilai 
     * id menjadi 1
     */
    public Arjuna(){
        id = 1;
    }
    /**
     * Atribut description mewakili kekuatan yang dimiliki oleh Arjuna
     */
    final String description = "Panah Pasopati: Arjuna dapat memberhentikan jalan pemain lawan sebanyak satu kali\n"
            + "Kekuatan dapat dilakukan tiap dadu yang dikocok menunjukan angka satu";
    /**
     * Mengembalikan nilai true apabila Arjuna
     * dapat melaksanakan aksinya
     */
    public boolean isAction(){
        return getDiceRolled() == 1 && !actionUsed;
    }
    /**
     * Menjalankan aksi dari Arjuna yaitu menentukan target
     * dari pemain yang ingin diserang, apabila pemain berhasil melakukan aksi
     * maka akan ditampilkan pesan dan target yang diserang akan menghentikan gilirannya selama
     * satu putaran
     */
    public void useAction(){
        //Lalala put your code's here 
        if(getDiceRolled() == 1 && !actionUsed){
            //Choose other player, make it stopped for 1 time
            int choosenPlayer = SelectTarget();
            Game.getPlayers().get(choosenPlayer).Stopped(1);
            actionUsed = true;
            System.out.println("Aksi berhasil dilakukan! Pemain ke-" + choosenPlayer + " berhenti selama satu putaran" );
        }else{
            if(!hasAdvanced()){
                System.out.println("Kocok dadu terlebih dahulu untuk menjalankan fungsi ini");
            }
            else
                System.out.println("Tidak bisa menggunakan aksi ini.");
        }
    }
    /**
     * Getter dari description
     */
    public String getDescription(){
        return description;
    }
    /**
     * Getter dari role
     */
    public String getRole(){
        return "Arjuna";
    }
}