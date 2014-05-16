import java.awt.Font;

import javax.swing.*;


public class Token extends JLabel {
	//konstanta
	final int tokensize = 50;
	
	//methods
	public Token()
	{
		super();
	}
	
	public Token(Integer kode)
	{
		super(new ImageIcon("images/token" + kode.toString() + ".png"), JLabel.CENTER);
		setVisible(true);
		setSize(100,100);
	}
	
	// mindah posisi token
	
}
