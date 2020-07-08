// card panel manager class
public class CardPanelManager 
{
	private final static CardPanelManager INSTANCE = new CardPanelManager();

	//constructor
	private CardPanelManager()
	{
	}

	
	//return cards as panel
	public CardPanel getCardAsPanel()
	{
		Card c = CardsLogicManager.getInstance().takeCard();
		if (c != null)
		{
			return new CardPanel(c, 0, 0, 70, 100);
		}
		return null;
	}

	
	public static CardPanelManager getInstance() 
	{
		return INSTANCE;
	}

	//return first card as panel
	public CardPanel getFirstCardAsPanel()
	{
		Card c = CardsLogicManager.getInstance().takeFirstCard();
		return new CardPanel(c, 0, 0, 70, 100);
	}

	// special cards method 
	public CardPanel chooseSpecialCard(String color, String value)
	{
		Card c = CardsLogicManager.getInstance().chooseSpecialCard(color, value);
		return new CardPanel(c, 0, 0, 70, 100);
	}
}
