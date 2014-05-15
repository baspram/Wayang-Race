public class Cell {
	//Atribut
	private String playersIn;
	private String cellNo;
	private boolean isTemporaryTrap = false;
	//Konstantas
	private final String clearCell = "99";
	private final String trapCell = "88";
	private final String noPlayerCell = "0";
	//Methods
	
	//konstruktor
	public Cell()
	{
		cellNo = clearCell;
		playersIn = clearCell;
	}
	
	// get nomor cell ini
	public String getCellNo()
	{
		return cellNo;
	}
	
	// get player-player mana saja yang tokennya ada di cell ini
	public String getPlayersIn()
	{
		return playersIn;
	}
	
	// get nomor cell ini
	public void setCellNo(String No)
	{
		cellNo = No;
	}
	
	// mengeset cell menjadi trap temprorary
	public boolean setTrap()
	{
		//bukan temporary trap dan bukan trap permanen
		if(!playersIn.contains(trapCell))
		{
			setPlayersIn(trapCell); //letakkan mark trap 88
			isTemporaryTrap = true;
			return true;
		}
		else return false;
	}
	
	// unset cell trap temporary menjadi cell biasa
	public boolean unsetTrap()
	{
		if(isTemporaryTrap)
		{
			removePlayer(trapCell); //hapus mark trap 88
			isTemporaryTrap = false;
			return true;
		}
		else return false;
	}
	
	// menambahkan player P ke cell ini
	public void setPlayersIn(String P)
	{
		if(playersIn.equals(clearCell) || playersIn.equals(noPlayerCell))
			playersIn = P;
		else if(!playersIn.contains(P))
			playersIn = playersIn.concat(P);
	}
	
	// menghapus player P dari cell ini
	public boolean removePlayer(String P)
	{
		if(playersIn.contains(P))
		{
			//System.out.println("hapus " + P + " di " + playersIn);
			playersIn = playersIn.replaceAll(P, "");
			//System.out.println("hasilnya : " + playersIn);
			return true;
		}
		else return false;
	}
}
