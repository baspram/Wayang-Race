import java.util.*;

public class mGame{
	
	public static void main(String args[]){
		int nplayer;
		Scanner scn = new Scanner(System.in);
		System.out.println("||Wayang Race||");
		System.out.println("Masukkan jumlah pemain(2-4):");
		try{
			nplayer = scn.nextInt();
			if(nplayer<2 || nplayer>4){
				throw new Exception();
			}
		}
		catch(Exception e){
			System.out.println("masukan invalid, jumlah pemain menjadi default (4)");
			nplayer = 4;
		}
		Game WayangRace = new Game(nplayer);
		WayangRace.Start();
	}
}
