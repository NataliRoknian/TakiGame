import java.util.Collections;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

//cards logic manager class
public class CardsLogicManager 
{
	protected Stack<Card> allCards;
	protected LinkedList<Card> specialCards;
	protected Card card;
	private final static CardsLogicManager INSTANCE = new CardsLogicManager();

	//push to stack
	private CardsLogicManager()
	{
		init();
	}

	//take card from pile
	public Card takeCard()
	{
		try {
			return allCards.pop();
		}
		catch(EmptyStackException e)
		{
			return null;
		}
	}

	
	public static CardsLogicManager getInstance() 
	{
		return INSTANCE;
	}


	public void init()
	{
		allCards=new Stack<Card>();
		specialCards = new LinkedList<Card>();

		fillStack();
		fillStack();

		Collections.shuffle(allCards);
	}
	
	
	//filters cards that cannot be opening cards method
	public Card takeFirstCard()
	{
		Card fc = null;
		LinkedList<Card> c = new LinkedList<Card>();
		boolean flag = false;
		while(!flag)
		{
			fc = allCards.pop();
			if(fc.get_value().equals("2"))
				c.add(fc);
			else
				if(fc.get_value().equals("10"))
					c.add(fc);
				else
					if(fc.get_value().equals("11"))
						c.add(fc);
					else
						if(fc.get_value().equals("12"))
							c.add(fc);
						else

							if(fc.get_value().equals("13"))
								c.add(fc);
							else

								if(fc.get_value().equals("14"))
									c.add(fc);
								else

									if(fc.get_value().equals("15"))
										c.add(fc);
									else
										flag = true;
		}

		if (c.size() != 0)
		{
			while(c.size() != 0)
			{
				allCards.add(c.remove(0));
			}
		}

		Collections.shuffle(allCards);

		return fc;

	}
	
	public void fillStack()
	{
		for (int i = 1; i <= 12; i++) 
		{
			card=new Card("blue", Integer.toString(i));
			allCards.push(card);
		}

		for (int i = 1; i <= 12; i++) 
		{
			card=new Card("green", Integer.toString(i));
			allCards.push(card);
		}

		for (int i = 1; i <= 12; i++) 
		{
			card=new Card("red", Integer.toString(i));
			allCards.push(card);
		}

		for (int i = 1; i <= 12; i++) 
		{
			card=new Card("yellow", Integer.toString(i));
			allCards.push(card);
		}

		//special

		card=new Card("all", Integer.toString(13));
		specialCards.push(card);

		card=new Card("allTaki", Integer.toString(13));
		specialCards.push(card);

		card=new Card("blueC", Integer.toString(14));
		specialCards.push(card);

		card=new Card("greenC", Integer.toString(14));
		specialCards.push(card);

		card=new Card("redC", Integer.toString(14));
		specialCards.push(card);

		card=new Card("yellowC", Integer.toString(14));
		specialCards.push(card);

		card=new Card("blueCT", Integer.toString(15));
		specialCards.push(card);

		card=new Card("greenCT", Integer.toString(15));
		specialCards.push(card);

		card=new Card("redCT", Integer.toString(15));
		specialCards.push(card);

		card=new Card("yellowCT", Integer.toString(15));
		specialCards.push(card);
	}

	//special cards method
	public Card chooseSpecialCard(String color, String value)
	{

		for (int i = 0; i < specialCards.size(); i++)
		{
			if (specialCards.get(i).get_value().equals(value))
			{
				if(specialCards.get(i).get_color().contains(color))
					return specialCards.get(i);
			}
		}

		System.out.println("chooseSpecialCard " + color + " " + value);

		return null;
	}
	
	//refill card stack (pile)
	public void refill(Stack<Card> allCards)
	{
		while(!allCards.isEmpty())
		{
			allCards.push(allCards.pop());
		}
	}
}
