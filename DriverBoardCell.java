import java.util.Scanner;
public class DriverBoardCell {
	public static void main(String args[])
	{
		Player p1 = new Player();
		Player p2 = new Player();
		Player p3 = new Player();
		Player p4 = new Player();
		Board game = new Board(4);
		String command="";
		Scanner keyboard = new Scanner(System.in);
		while(!command.equals("close") && !command.equals("exit"))
		{
			game.drawBoard();
			System.out.println("Daftar perintah :");
			System.out.println(">> move <nomor pemain> <increment>");
			System.out.println(">> set <nomor tile yang diset trap>");
			System.out.println(">> unset <nomor tile yang diunset trap>");
			System.out.println(">> exit");
			command = keyboard.next();
			command = command.toLowerCase();
			if(command.equals("move"))
			{
				int noPemain = keyboard.nextInt();
				int increment = keyboard.nextInt();
				Player p = new Player();
				switch(noPemain)
				{
				case 1 : p=p1; break;
				case 2 : p=p2; break;
				case 3 : p=p3; break;
				case 4 : p=p4; break;
				}
				int kode = game.move(p,noPemain, increment);
				if(p.getPosition() + increment <= 0)
					p.setPosition(1);
				else
					p.Advance(increment);
				p.StartTurn();
				if(kode == 88)
					System.out.println("TRAP");
			}
			else if(command.equals("close") || command.equals("exit"))
			{
				System.out.println("terimakasih!");
			}
			else if(command.equals("set"))
			{
				int noCell = keyboard.nextInt();
				game.setTrap(noCell);
			}
			else if(command.equals("unset"))
			{
				int noCell = keyboard.nextInt();
				game.unsetTrap(noCell);
			}
		}
		keyboard.close();
	}
}
