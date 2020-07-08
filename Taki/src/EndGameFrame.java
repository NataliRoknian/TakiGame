import javax.swing.JFrame;

// end game

public class EndGameFrame extends JFrame 
{
	private EndGamePanel egp;
	public EndGameFrame(int winnerIs)
	{
		egp = new EndGamePanel(winnerIs);
		add(egp);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400,1000);
		setVisible(true);
	}
}
