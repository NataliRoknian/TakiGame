import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame
{
	private MainPanel mp;
	private JMenuBar jmb;
	private JMenu menu;
	private JMenuItem exit,instructions;
	private JButton StartBtn;
	private TextField playerName;

	//constructor
	static MainFrame myMainFrame;
	
	
	
	public static MainFrame getInstance() {
		if (myMainFrame == null)
			myMainFrame = new MainFrame();
		return myMainFrame;
	}


	private MainFrame() 
	{
		mp = new MainPanel();
		StartBtn = new JButton("Start Game");
		StartBtn.setBounds(500, 450, 300, 50);
		StartBtn.setFont(new Font ("SansSerif",Font.BOLD,30));
		StartBtn.setVisible(true);
        mp.add(StartBtn);
        playerName = new TextField();
        playerName.setBounds(500, 350, 300, 50);
        playerName.setFont(new Font ("SansSerif",Font.BOLD,50));
        mp.add(playerName);
        add(mp);
		
		//add options to game
		jmb=new JMenuBar();
		//menu
		menu=new JMenu("Menu");
		jmb.add(menu);

		//instructions
		instructions = new JMenuItem("Instructions");
		menu.add(instructions);
		//exit
		exit = new JMenuItem("Exit");
		menu.add(exit);
				

		jmb.add(menu);	
				
		setJMenuBar(jmb);
		
		StartBtn.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				GameFrame j = new GameFrame(playerName.getText());
				
			}
		});
		
		//clicked on instructions -> opening window of instructions
		instructions.addActionListener(new ActionListener()
		{			        
			public void actionPerformed(ActionEvent e) {
			JFrame f=new JFrame("Instuctions");
	        JLabel l=new JLabel(new ImageIcon(getClass().getResource("Images\\horaot.jpg")));		        
	        f.add(l);
	        f.setBounds(0, 0, 1400, 1000);//גודל חלון של ההוראות
	        f.setVisible(true);
	        }		    
		});
				
				
		//clicked on exit
		exit.addActionListener(new ActionListener()
		{	
			
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
				
			
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400,1000);
		setVisible(true);
		
}
	
}
