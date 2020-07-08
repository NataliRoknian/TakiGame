import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

//pile class
public class DrawPileCardPanel extends CardPanel
{
	protected LinkedList<DrawPileInterface> listeners;
	
	//add to listeners list 
	
	public DrawPileCardPanel(CardPanel c, int x, int y, int width, int height)
	{
		super(c.get_card(), x, y, width, height);
		listeners = new LinkedList<DrawPileInterface>();
		
		addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				super.mousePressed(e);
				
				for(int i = 0; i < listeners.size(); i++)
					listeners.get(i).drawPileClicked();
			}
		});
	}

	//add listener to listeners list
	public void addListener(DrawPileInterface l)
	{
		listeners.add(l);
	}
}

