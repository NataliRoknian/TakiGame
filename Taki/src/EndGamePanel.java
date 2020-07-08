import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;

//end game 

public class EndGamePanel extends JPanel
{
	private Img winner;
	private JButton restart;
	
	public EndGamePanel(int winnerIs)// 0 -> player wins else computer wins
	{
		super();
		setLayout(null);
		setBounds(0, 0, 600, 600);
		if(winnerIs==0)
			winner = new Img("\\Images\\playerWin.png", 0, 0, 1280, 720);
		else
			winner = new Img("\\Images\\compWins.png",0,0,1280,720);
	}
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		winner.drawImg(g);
	}
}
