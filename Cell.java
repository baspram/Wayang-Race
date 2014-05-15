public class Cell {
	//Atribut
	private String playersIn;
	private String cellNo;
	//Konstanta
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
	
	public void setTrap()
	{
		cellNo = trapCell;
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
	public void removePlayer(String P)
	{
		if(playersIn.contains(P))
		{
			//System.out.println("hapus " + P + " di " + playersIn);
			playersIn = playersIn.replaceAll(P, "");
			//System.out.println("hasilnya : " + playersIn);
		}
	}
}
