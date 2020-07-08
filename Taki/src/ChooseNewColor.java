import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;


public class ChooseNewColor extends JPanel
{
	
	public ChooseNewColor()
	{
		setOpaque(false);
	}
	
	private static final long serialVersionUID = 1L;

	//draws the 4 colors to choose from on game screen
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	
		g.setColor(Color.RED);
		g.fillRect(0, 0, 50, 50);
		
		g.setColor(Color.GREEN);
		g.fillRect(40,10,50,50);
		
		g.setColor(Color.BLUE);
		g.fillRect(10,40,50,50);
		
		g.setColor(Color.YELLOW);
		g.fillRect(50,60,50,50);
		
	}
}
