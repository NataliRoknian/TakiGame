import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.*;


//game panel class
public class GamePanel extends JPanel implements DrawPileInterface, PlayerCardPressedInterface
{
	private int size=8;
	private int width;
	private Img background;
	private Point pointComputer1;
	private Point pointComputer2;
	private Point pointComputer3;
	private Point pointPlayer;
	private LinkedList<CardPanel> computerCards1;
	private LinkedList<CardPanel> computerCards2;
	private LinkedList<CardPanel> computerCards3;
	private LinkedList<CardPanel> playerCards;
	private DrawPileCardPanel drawPile; 
	private CardPanel removeCard;
	private int playerTurn=0;
	private Stack<Card> thrownCards;
	private Img winOrLose;
	private Img yourturn;
	private JLabel winOrLose_jlabel;
	private int plus2Counter;//counter for how many "Plus2" cards played in a row.
	private static boolean isPlus2Active=false;//plus2 card effect is active?
	private static boolean isTakiMode=false;//Taki card effect is active?
	private static boolean isStopOrPlusMode=false;//Taki card effect is active?
	private static boolean isChangeColor=false;//Taki card effect is active?
	private static boolean stillPlaying=false;//player finished his turn?
	private static String takiColor="";//player finished his turn?
	protected CompMove cm;//comp move parameter.
	private Timer timer = new Timer (2000, new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{
			((Timer)e.getSource()).stop();
			if(playerTurn != 0)
			{
				
				whosTurn(playerTurn);
				computerPlayersTurn();
			}
		}
	});

	//constructor
	public GamePanel()
	{
		super();
		setLayout(null);
		setBounds(0, 0, 600, 600);

		cm = new CompMove();

		thrownCards = new Stack<Card>();

		playerCards = new LinkedList<CardPanel>();
		computerCards1 = new LinkedList<CardPanel>();
		computerCards2 = new LinkedList<CardPanel>();
		computerCards3 = new LinkedList<CardPanel>();
		pointPlayer = new Point(470, 470);
		pointComputer1 = new Point(470, 50);
		pointComputer2 = new Point(300, 170);
		pointComputer3 = new Point(880, 170);
		background = new Img("\\Images\\mainBack.jpg", 0, 0, 1400, 1000);
		whosTurn(playerTurn);
		width = 300;

		removeCard = CardPanelManager.getInstance().getFirstCardAsPanel();
		add(removeCard);
		removeCard.setBounds(650, 280, 70, 100);

		//Hand out cards
		init();
	}

	//place of cards on panel
	public void setCardPanelsBounds()
	{
		for(int i=0; i<playerCards.size(); i++)
		{
			playerCards.get(i).setBounds(((int)(pointPlayer.getX() +i*width/playerCards.size())), ((int)(pointPlayer.getY())), 70, 100);
		}

		for(int i=0; i<computerCards1.size(); i++)
		{
			computerCards1.get(i).setBounds(((int)(pointComputer1.getX() +i*width/computerCards1.size())), ((int)(pointComputer1.getY())), 70, 100);
			computerCards1.get(i).flip();
		}

		for(int i=0; i<computerCards2.size(); i++)
		{
			computerCards2.get(i).setBounds(((int)(pointComputer2.getX())), ((int)(pointComputer2.getY() +i*width/computerCards2.size())), 100, 70);
			computerCards2.get(i).flip();
		}

		for(int i=0; i<computerCards3.size(); i++)
		{
			computerCards3.get(i).setBounds(((int)(pointComputer3.getX())), ((int)(pointComputer3.getY() +i*width/computerCards3.size())), 100, 70);
			computerCards3.get(i).flip();
		}
	}

	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		background.drawImg(g);
		if (winOrLose != null)
		{			
			winOrLose.drawImg(g);
		}
		yourturn.drawImg(g);
	}

	//clicked on pile
	public void drawPileClicked() 
	{
		if (isPlus2Active)
		{
			takePlus2Player(plus2Counter);
			isPlus2Active = false;
			plus2Counter = 0;
		}
		else
		{
			playerTakeCard();
			isStopOrPlusMode = false;
		}

		playerTurn++;
		timer.start();
	}

	//new game
	public void restart()
	{
		CardsLogicManager.getInstance().init();
		
		winOrLose = null;
		
		//delete all cards from frame
		this.removeAll();
		computerCards1.clear();
		computerCards2.clear();
		computerCards3.clear();
		playerCards.clear();
		removeCard = CardPanelManager.getInstance().getFirstCardAsPanel();
		add(removeCard);
		removeCard.setBounds(650, 280, 70, 100);
		playerTurn=0;
		whosTurn(playerTurn);
		timer.stop();
	}


	public void init()
	{

		drawPile = new DrawPileCardPanel(CardPanelManager.getInstance().getFirstCardAsPanel(), 530, 280, 70, 100) ;

		drawPile.flip();
		add(drawPile);
		drawPile.addListener(this);

		//add player's card to panel

		for (int i = 0; i < size; i++) 
		{
			playerCards.add(CardPanelManager.getInstance().getCardAsPanel());
			playerCards.get(i).addListenerPlayerCard(this);
			add(playerCards.get(i));
		}

		//add comp's card to panel
		
		for (int i = 0; i < size; i++) 
		{
			computerCards1.add(CardPanelManager.getInstance().getCardAsPanel());
			add(computerCards1.get(i));
			computerCards1.get(i).flip();
		}

		for (int i = 0; i < size; i++) 
		{
			computerCards2.add(CardPanelManager.getInstance().getCardAsPanel());
			add(computerCards2.get(i));
			computerCards2.get(i).set_img(computerCards2.get(i).get_img().rotate(90.00));
			computerCards2.get(i).get_img().setImgSize(100, 70);
			computerCards2.get(i).flip();
		}

		for (int i = 0; i < size; i++) 
		{
			computerCards3.add(CardPanelManager.getInstance().getCardAsPanel());
			add(computerCards3.get(i));
			computerCards3.get(i).set_img(computerCards3.get(i).get_img().rotate(270.00));
			computerCards3.get(i).get_img().setImgSize(100, 70);
			computerCards3.get(i).flip();
		}

		setCardPanelsBounds();
	}

	//clicked on cards - player
	public void PlayerCardPressed(Card c) 
	{
		if(playerTurn != 0)
			return;
		for(int i=0; i<playerCards.size(); i++)
		{
			if(playerCards.get(i).get_card().equals(c))
			{
				if (isSuitableCard(playerCards.get(i).get_card(), removeCard))
				{
					isStopOrPlusMode = false;

					if (isLastOfColor(playerCards.get(i)))
					{
						isTakiMode = false;
					}

					if (playerCards.get(i).get_card().get_value().equals("2"))
					{
						isPlus2Active = true;
						plus2Counter++;
					}

					if (playerCards.get(i).get_card().get_value().equals("10")
							|| playerCards.get(i).get_card().get_value().equals("11"))
					{
						isStopOrPlusMode = true;
					}
					else if (playerCards.get(i).get_card().get_value().equals("12"))
					{//taki green
						if (isLastOfColor(playerCards.get(i)))
						{
							isTakiMode = false;
						}
						else	
							isTakiMode = true;
					}
					else if (playerCards.get(i).get_card().get_value().equals("13") &&
							playerCards.get(i).get_card().get_color().equals("all"))
					{//change color
						changeColor();
					}
					else if (playerCards.get(i).get_card().get_value().equals("13") &&
							playerCards.get(i).get_card().get_color().equals("allTaki"))
					{//change color
						isTakiMode = true;
						changeColor();
					}

					remove(removeCard);
					playerCards.get(i).removeListeners();
					remove(playerCards.get(i));
					if (!removeCard.get_card().get_value().equals("14") &&
							!removeCard.get_card().get_value().equals("15"))
						thrownCards.add(removeCard.get_card());
					removeCard = playerCards.get(i);
					add(removeCard);
					removeCard.setBounds(650, 280, 70, 100);
					playerCards.remove(i);
					setCardPanelsBounds();

					if (!isTakiMode && !isStopOrPlusMode && !isChangeColor)
					{
						if (!isWin(playerCards))
						{
							playerTurn++;
							whosTurn(playerTurn);
							timer.start();
						}
						else
						{
							winnerIs(0);
						}
					}

					break;
				}
			}
		}
	}
	//play comps turns method
	private void computerPlayersTurn()
	{
		if(playerTurn == 1)
		{
			compMove(computerCards3);

			if (!isWin(computerCards3))
			{
				playerTurn++;
				timer.start();
			}
			else
			{
				winnerIs(3);
			}
		}

		else if(playerTurn == 2)
		{
			compMove(computerCards1);

			if (!isWin(computerCards1))
			{
				playerTurn++;
				timer.start();
			}
			else
			{
				winnerIs(1);
			}
		}
		else if(playerTurn == 3)
		{

			compMove(computerCards2);

			if (!isWin(computerCards2))
			{
				playerTurn=0;
				whosTurn(playerTurn);
				timer.start();
			}
			else
			{
				winnerIs(2);
			}
		}
	}


	public synchronized void compMove(LinkedList<CardPanel> comp)
	{

		int compMoveIndex=cm.ChooseCompNextMove(comp, removeCard,isPlus2Active,isTakiMode, takiColor);//get next comp move by the heap and comp hand.



		if(compMoveIndex!=-1 && compMoveIndex!=-2 )//if comp has a card to put, he puts it.
		{
			//plus2
			if(comp.get(compMoveIndex).get_card().get_value().equals("2"))//if the card is +2
			{
				putCardComp(comp, compMoveIndex);//putting the card in the heap.
				plus2Counter++;//counter of +2 increase.
				isPlus2Active=true;
			}

			//1-9 without 2
			else if(Integer.parseInt(comp.get(compMoveIndex).get_card().get_value())>=1 && 
					Integer.parseInt(comp.get(compMoveIndex).get_card().get_value())<=9 && 
					!comp.get(compMoveIndex).get_card().get_value().equals("2"))//all cards from 1 to 9 except of +2 
			{
				putCardComp(comp, compMoveIndex);//putting the card in the heap.
			}

			//plus and stop - by 2 players their means the same.

			else if(Integer.parseInt(comp.get(compMoveIndex).get_card().get_value())==10 || 
					Integer.parseInt(comp.get(compMoveIndex).get_card().get_value())==11)
			{
				putCardComp(comp, compMoveIndex);//putting the plus or stop.
				
				paintImmediately(0, 0, 2000, 2000);
				try 
				{
					Thread.sleep(1500);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				compMove(comp);
			}

			//Taki
			else if(Integer.parseInt(comp.get(compMoveIndex).get_card().get_value())==12)//if the card which choosen is a Taki
			{

				putCardComp(comp, compMoveIndex);

				isTakiMode=true;
				takiColor = removeCard.get_card().get_color();

				while((compMoveIndex=cm.ChooseCompNextMove(comp, removeCard,isPlus2Active,isTakiMode, takiColor))!=-1)
				{
					paintImmediately(0, 0, 2000, 2000);
					try 
					{
						Thread.sleep(2000);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
					putCardComp(comp, compMoveIndex);
				}

				isTakiMode=false;

			}
			
			//super taki
			else if(Integer.parseInt(comp.get(compMoveIndex).get_card().get_value())==13)
			{
				putCardComp(comp, compMoveIndex);
				String colorSelected=findTheMostColorComp(comp);
				//setColorCompByChoice(colorSelected);
				String num = "";
				if (removeCard.get_card().get_color().equals("all"))
					num = "14";
				else
					num = "15";

				remove(removeCard);
				if (!removeCard.get_card().get_value().equals("14") &&
						!removeCard.get_card().get_value().equals("15"))
					thrownCards.add(removeCard.get_card());
				removeCard = CardPanelManager.getInstance().chooseSpecialCard(colorSelected, 
						num);
				add(removeCard);
				removeCard.setBounds(650, 280, 70, 100);

				if (removeCard.get_card().get_value().equals("15"))
				{
					isTakiMode=true;
					takiColor = removeCard.get_card().get_color();

					while((compMoveIndex=cm.ChooseCompNextMove(comp, removeCard,isPlus2Active,isTakiMode, takiColor))!=-1)
					{
						putCardComp(comp, compMoveIndex);
					}

					isTakiMode=false;
				}
			}

		}

		else if(compMoveIndex==-2)//returns -2 when +2 effect is active and the comp has no +2 card in hand.
		{
			takePlus2Comp(comp, plus2Counter);
			isPlus2Active = false;
		}

		else if(compMoveIndex==-1)//if the comp has no card which match to the card in the heap , it takes a card from the deck.
		{
			takeCardComp(comp);
		}
	}


	// the computer takes plus2 as needed.
	
	public void takePlus2Comp(LinkedList<CardPanel> comp, int num)
	{
		for(int i=0;i<2*num;i++)
		{
			takeCardComp(comp);
		}
		plus2Counter=0;//plus2 counter return to 0.
		isPlus2Active=false;//the effect of plus2 is over.
	}


	/*
	 	the player takes plus2 as needed.
	 	num - how many plus2 cards were played.
	 */
	public void takePlus2Player(int num)
	{
		for(int i=0;i<2*num;i++)
		{
			playerTakeCard();
		}
		plus2Counter=0;//plus2 counter return to 0.
		isPlus2Active=false;//the effect of plus2 is over.
	}

	/**
	 * The computer takes a card.
	 */
	public void takeCardComp(LinkedList<CardPanel> comp)
	{
		CardPanel crd=CardPanelManager.getInstance().getCardAsPanel();//get card from deck

		if (crd == null)//14.5
		{
			CardsLogicManager.getInstance().refill(thrownCards);
			thrownCards.clear();
			crd=CardPanelManager.getInstance().getCardAsPanel();
		}
		if(comp.equals(computerCards2))
		{
			crd.setImgSize(100, 70);
			crd.set_img(crd.get_img().rotate(90.00));	
		}
		if(comp.equals(computerCards3))
		{
			crd.setImgSize(100, 70);
			crd.set_img(crd.get_img().rotate(270.00));	
		}

		comp.add(crd);//add to comp hand
		add(crd);//add the card to hand panel

		setCardPanelsBounds();//setting bounds.
	}

	
	 //the player take card auto
	
	public void playerTakeCard()
	{
		CardPanel crd=CardPanelManager.getInstance().getCardAsPanel();
		if (crd == null) 
		{
			CardsLogicManager.getInstance().refill(thrownCards);
			thrownCards.clear();
			crd=CardPanelManager.getInstance().getCardAsPanel();
		}
		playerCards.add(crd);
		crd.addListenerPlayerCard(this);
		add(crd);
		setCardPanelsBounds();
	}

	/*
	  This function finds the most color in the computer "hand"
	 	compHand - the computer "hand" by linked list
	  	@return the color num which is the most in the computer "hand"
	 */
	public String findTheMostColorComp(LinkedList<CardPanel> compHand)
	{
		int blue=0,green=0,red=0,yellow=0;//all counters.

		for(int i=0;i<compHand.size();i++)//look all comp's "hand"
		{
			if(compHand.get(i).get_card().get_color().equals("blue"))//if card is blue
			{
				blue++;//increase by 1
			}
			if(compHand.get(i).get_card().get_color().equals("green"))//if card is green
			{
				green++;//increase by 1
			}
			if(compHand.get(i).get_card().get_color().equals("red"))//if card is red
			{
				red++;//increase by 1
			}
			if(compHand.get(i).get_card().get_color().equals("yellow"))//if card is yellow
			{
				yellow++;//increase by 1
			}
		}

		if(blue>=yellow && blue>=red && blue>=green)//if blue is the most
			return "blue";//blue color num

		if(green>=yellow && green>=blue && green>=red)//if green is the most
			return "green";//green color num

		if(red>=yellow && red>=blue && red>=green)//if red is the most
			return "red";//red color num
		else//else yellow is the most
			return "yellow";//yellow color num

	}

	/*
		sets the color as comp choice.
		colorNum - the color num which selected.
	 */
	public void setColorCompByChoice(String colorNum)
	{
		removeCard.get_card().set_color(colorNum);
	}


	/**
	  the function looks all player's "hand" and "see" if he has more cards with same color 
	  as the current card in the top of the "heap".
	 	playerHand - the player's hand by linked list.
	 	heap - the "heap" top card.
	 	@return boolean parameter - if there is more with same color, or got non color return true, else false.
	 */
	public boolean continueTaki(LinkedList<CardPanel> playerHand,CardPanel heap)
	{
		for(int i=0;i<playerCards.size();i++)
		{
			if(playerCards.get(i).get_card().get_color()==heap.get_card().get_color() /*|| player.get(i).getC().getColor()==0*/)
				return true;
		}
		return false;
	}

	/*
	  the function checks if the player can play the clicked card.
		card - the chosen card.
	  	heap - the current card in the top of the heap.
	  	@return  boolean parameter - if it is a right card return true, else false.
	 */
	public boolean isSuitableCard(Card crd,CardPanel heap)
	{
		//2
		if(heap.get_card().get_value().equals("2") && isPlus2Active)
		{
			if(crd.get_value().equals("2"))
				return true;
			else
				return false;
		}
		else if(heap.get_card().get_value().equals("2"))
		{
			if(heap.get_card().get_color().contains(crd.get_color()) 
					|| heap.get_card().get_value().equals(crd.get_value())
					|| crd.get_color().equals("all") 
					|| crd.get_color().equals("allTaki"))
				return true;
			return false;
		}

		//+ stop
		if (heap.get_card().get_value().equals("10")
				|| heap.get_card().get_value().equals("11") && !isStopOrPlusMode)
		{
			if(heap.get_card().get_color().contains(crd.get_color()) 
					|| crd.get_color().equals("all") 
					|| crd.get_color().equals("allTaki"))
				return true;
			return false;
		}

		if(heap.get_card().get_color().contains(crd.get_color()) 
				|| crd.get_value().equals(heap.get_card().get_value())
				|| crd.get_color().equals("all") 
				|| crd.get_color().equals("allTaki"))
			return true;
		return false;
	}

	public void winnerIs(int num)
	{
		EndGameFrame egf = new EndGameFrame(num);
	}

	/*
	 	put the computer chosen card in the top of the heap and unflip it.
	  	i - the index of the chosen card by computer.
	 */
	protected void putCardComp(LinkedList<CardPanel> comp, int i)
	{
		remove(removeCard);//remove the current card at the top of the "heap".
		if (!removeCard.get_card().get_value().equals("14") &&
				!removeCard.get_card().get_value().equals("15"))
			thrownCards.add(removeCard.get_card());
		removeCard=comp.remove(i);//remove the selected card from the comp "hand" and put it in the top of the "heap".
		removeCard.unflip();//unflip the card in the heap.
		add(removeCard);//add the cardPanel.
		removeCard.repaint();//repaint the panel.
		removeCard.setImgSize(70, 100);
		removeCard.setBounds(650, 280, 70, 100);//set the heap bounds
		setCardPanelsBounds();
	}

	/*	 
	  	the changeColor function opens a frame with 4 rectangles. each one has different color (blue,green,red,yellow)
	 	the player selects the color and the chosen color is set as the new color in the "heap".
	 */
	protected void changeColor()
	{
		final ChooseNewColor ch=new ChooseNewColor();//new chooseNewColor parameter

		add(ch);
		ch.setBounds(90, 320, 150, 150);
		
		isChangeColor = true;//the player is choosing color

		ch.addMouseListener(new MouseAdapter() //add the chooseNewColor a mouse listener
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				super.mousePressed(e);
				String num = "";
				if (removeCard.get_card().get_color().equals("all"))
					num = "14";
				else
					num = "15";
				
				if(e.getX()>=50 && e.getX()<=100 && e.getY()>=60 && e.getY()<=110)//if clicked in the X 50-100 and Y 50-100
				{
					remove(removeCard);
					if (!removeCard.get_card().get_value().equals("14") &&
							!removeCard.get_card().get_value().equals("15"))
						thrownCards.add(removeCard.get_card());
					removeCard = CardPanelManager.getInstance().chooseSpecialCard("yellow", 
							num);
					add(removeCard);
					removeCard.setBounds(650, 280, 70, 100);
				}
				else if(e.getX()>=10 && e.getX()<=60 && e.getY()>=50 && e.getY()<=100)//if clicked in the X 0-50 and Y 50-100
				{
					remove(removeCard);
					if (!removeCard.get_card().get_value().equals("14") &&
							!removeCard.get_card().get_value().equals("15"))
						thrownCards.add(removeCard.get_card());
					removeCard = CardPanelManager.getInstance().chooseSpecialCard("blue", 
							num);
					add(removeCard);
					removeCard.setBounds(650, 280, 70, 100);
				}		
				else if(e.getX()>=40 && e.getX()<=90 && e.getY()>=10 && e.getY()<=60)//if clicked in the X 50-100 and Y 0-50
				{
					remove(removeCard);
					if (!removeCard.get_card().get_value().equals("14") &&
							!removeCard.get_card().get_value().equals("15"))
						thrownCards.add(removeCard.get_card());
					removeCard = CardPanelManager.getInstance().chooseSpecialCard("green", 
							num);
					add(removeCard);
					removeCard.setBounds(650, 280, 70, 100);
				}
				else if(e.getX()>=0 && e.getX()<50 && e.getY()>=0 && e.getY()<=50)//if clicked in the X 0-50 and Y 0-50
				{
					remove(removeCard);
					if (!removeCard.get_card().get_value().equals("14") &&
							!removeCard.get_card().get_value().equals("15"))
						thrownCards.add(removeCard.get_card());
					removeCard = CardPanelManager.getInstance().chooseSpecialCard("red", 
							num);
					add(removeCard);
					removeCard.setBounds(650, 280, 70, 100);
				}
				isChangeColor=false;//player finished choosing color.
				remove(ch);
				
				if (!isTakiMode)
				{
					playerTurn++;
					timer.start();
				}
			}
		});
	}

	public boolean isLastOfColor(CardPanel removed)
	{
		int counter = 0;
		for(int i=0; i<playerCards.size(); i++)
		{
			if (removed.get_card().get_color().contains(playerCards.get(i).get_card().get_color()))
				counter++;
		}

		if (counter == 1)
			return true;
		else
			return false;
	}
	
	//whos win - player or computer?
	public boolean isWin(LinkedList<CardPanel> hand)
	{
		if (hand.size() == 0)
			return true;
		else 
			return false;
	}

	//whos turn - player or computer?
	public void whosTurn(int num)
	{

		if(num==0)
		{
			yourturn = new Img("\\Images\\play.png", 490, 580, 50, 40);
			repaint();
		}
		else
		{
			yourturn = new Img("\\Images\\stop.png",490, 580, 50, 40);
			repaint();
		}
	}
	
}



