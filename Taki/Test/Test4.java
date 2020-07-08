import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;


public class Test4
{ 
	CompMove cm;

	@Before
	public void setUp()
	{
		cm=new CompMove();
		System.out.println("setUp");
	}
	

	@org.junit.Test
	public void testChooseCompNextMove() 
	{
		System.out.println("testChooseCompNextMove");
		Card card1 = new Card("green","5"); 
		Card card2 = new Card("green","8"); 
		Card card3 = new Card("green","1"); 
		Card card4 = new Card("yellow","9"); 
		Card card5 = new Card("yellow","4"); 
		Card card6 = new Card("yellow","3"); 
		Card card7 = new Card("red","1"); 
		Card card8 = new Card("red","4"); 
		Card card9 = new Card("red","2"); 
		Card card10 = new Card("red","6"); 
		Card currHeapCard = new Card("blue","3");
		CardPanel c1 = new CardPanel(card1, 0, 0, 0, 0);
		CardPanel c2 = new CardPanel(card2, 0, 0, 0, 0);
		CardPanel c3 = new CardPanel(card3, 0, 0, 0, 0);
		CardPanel c4 = new CardPanel(card4, 0, 0, 0, 0);
		CardPanel c5 = new CardPanel(card5, 0, 0, 0, 0);
		CardPanel c6 = new CardPanel(card6, 0, 0, 0, 0);
		CardPanel c7 = new CardPanel(card7, 0, 0, 0, 0);
		CardPanel c8 = new CardPanel(card8, 0, 0, 0, 0);
		CardPanel c9 = new CardPanel(card9, 0, 0, 0, 0);
		CardPanel c10 = new CardPanel(card10, 0, 0, 0, 0);
		CardPanel chp = new CardPanel(currHeapCard,0,0,0,0);
		boolean isPlus2Active=false, isTakiActive=false;	
		String takiColor="red";
		LinkedList<CardPanel> compCards = new LinkedList<CardPanel>();	
		int result =cm.ChooseCompNextMove(compCards,chp,isPlus2Active,isTakiActive,takiColor);
		compCards.add(c1);
		compCards.add(c2);
		compCards.add(c3);
		compCards.add(c4);
		compCards.add(c5);
		compCards.add(c6);
		compCards.add(c7);
		compCards.add(c8);
		compCards.add(c9);
		compCards.add(c10);
		result = cm.ChooseCompNextMove(compCards,chp,isPlus2Active,isTakiActive,takiColor);
		assertEquals("if same value ",5 ,result);
		System.out.println("card 6 is a match");
	}
}
