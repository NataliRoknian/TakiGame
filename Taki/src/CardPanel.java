import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;

//card's panel class

public class CardPanel extends JPanel 
{
	private Img img;
	private Card card;
	private LinkedList<PlayerCardPressedInterface> listeners;

	//constructor
	public CardPanel(int x, int y, int width, int height, String color,String value)
	{
		setBounds(x,y,width,height);
		listeners = new LinkedList<PlayerCardPressedInterface>();
		card=new Card(color, value);
		String path="images\\"+color+value+".png";
		img=new Img(path, 0, 0, width, height);

		addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				super.mousePressed(e);
				flip();
			}
		});
	}

	
	public CardPanel(Card c, int x, int y, int width, int height)
	{
		setBounds(x,y,width,height);
		listeners = new LinkedList<PlayerCardPressedInterface>();
		card=c;
		String path="\\Images\\"+card.get_color()+card.get_value()+".png";
		img=new Img(path, 0, 0, width, height);

		addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{				
				super.mousePressed(e);
				for(int i=0; i<listeners.size(); i++)
				{
					listeners.get(i).PlayerCardPressed(card);
				}
			}
		});
	}
	
	//player listeners
	public void addListenerPlayerCard(PlayerCardPressedInterface p)
	{
		listeners.add(p);
	}
	
	//remove listener
	public void removeListeners()
	{
		listeners.clear();
	}

	//flip card
	public void flip()
	{
		img.setImg("images\\BackCard.png");
	}

	//unflip card
	public void unflip()
	{
		img.setImg("images\\"+card.get_color()+card.get_value()+".png");
	}

	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		img.drawImg(g);
	}

	public Card get_card() 
	{
		return card;
	}

	public void set_card(Card card) 
	{
		this.card = card;
	}

	public Img get_img() 
	{
		return img;
	}

	public void set_img(Img img) 
	{
		this.img = img;
	}

	//rotate card
	public void rotate(double angle)
	{
		img.rotate(angle);
	}

	
	public void setImgSize(int w, int h)
	{
		img.setImgSize(w, h);
	}
}

