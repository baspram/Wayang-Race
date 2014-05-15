import java.util.Scanner;
public class DriverBoardCell {
	public static void main(String args[])
	{
		Board game = new Board();
		String command="";
		Scanner keyboard = new Scanner(System.in);
		while(!command.equals("close") && !command.equals("exit"))
		{
			game.drawBoard();
			System.out.println("Daftar perintah :");
			System.out.println(">> move <nomor pemain> <increment>");
			System.out.println(">> exit");
			command = keyboard.next();
			command = command.toLowerCase();
			if(command.equals("move"))
			{
				int noPemain = keyboard.nextInt();
				int increment = keyboard.nextInt();
				int kode = game.move(noPemain, increment);
				if(kode == 88)
					System.out.println("TRAP");
			}
			else if(command.equals("close") || command.equals("exit"))
			{
				System.out.println("terimakasih!");
			}
		}
		keyboard.close();
	}
}
