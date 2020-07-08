import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// game screen
public class GameFrame extends JFrame 
{
	private GamePanel gp;
	private JMenuBar jmb;
	private JMenu menu;
	private JMenuItem exit,restart,instructions;

	//constructor
	public GameFrame(String name)
	{
		super("Taki");
		
		gp = new GamePanel();
		JLabel nameLabel = new JLabel(name);
        nameLabel.setBounds(500, 575, 300, 50);
        nameLabel.setFont(new Font ("SansSerif",Font.BOLD,20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        gp.add(nameLabel);
		add(gp);
		
		
		//add options to game
		jmb=new JMenuBar();
		//menu
		menu=new JMenu("Menu");
		jmb.add(menu);

		//instruction
		instructions = new JMenuItem("Instructions");
		menu.add(instructions);
		//restart
		restart = new JMenuItem("Restart");
		menu.add(restart);
		//exit
		exit = new JMenuItem("Exit");
		menu.add(exit);
				

		jmb.add(menu);	
				
		setJMenuBar(jmb);
		
		
		//clicked on instructions -> opening window of instructions
		instructions.addActionListener(new ActionListener()
		{			        
			public void actionPerformed(ActionEvent e) 
			{
			JFrame f=new JFrame("Instuctions");
	        JLabel l=new JLabel(new ImageIcon(getClass().getResource("Images\\horaot.jpg")));		        
	        f.add(l);
	        f.setBounds(0, 0, 1400, 1000);//instructions window size
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
				
		//clicked on restart
		restart.addActionListener(new ActionListener()
		{		
			public void actionPerformed(ActionEvent e) 
			{
				gp.restart();
				repaint();
				gp.init();
				nameLabel.setBounds(500, 575, 300, 50);
		        nameLabel.setFont(new Font ("SansSerif",Font.BOLD,20));
		        nameLabel.setHorizontalAlignment(JLabel.CENTER);
		        gp.add(nameLabel);
				
			}
		});		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400,1000);
		setVisible(true);
		
	}

}
