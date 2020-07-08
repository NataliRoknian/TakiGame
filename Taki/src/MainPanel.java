import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel 
{
	private Img background;
	
	public MainPanel()
	{
		setLayout(null);
		setBounds(0, 0, 600, 600);
		background = new Img("\\Images\\main.jpg", 0, 0, 1280, 720);
		
		
	}
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		background.drawImg(g);
		
	}

}
