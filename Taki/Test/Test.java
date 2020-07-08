import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;


public class Test 
{
	GamePanel game ;  

	@Before
	public void setUp()
	{
		game = new GamePanel();
		System.out.println("setUp");
	}
	


	@org.junit.Test
	public void testFindTheMostColorComp() 
	{
		System.out.println("testFindTheMostColorComp");
		Card card1 = new Card("blue","5"); 
		Card card2 = new Card("green","8"); 
		Card card3 = new Card("green","1"); 
		Card card4 = new Card("yellow","9"); 
		Card card5 = new Card("yellow","3"); 
		Card card6 = new Card("yellow","6"); 
		Card card7 = new Card("red","1"); 
		Card card8 = new Card("red","4"); 
		Card card9 = new Card("red","2"); 
		Card card10 = new Card("red","6"); 
		CardPanel c1 =new CardPanel(card1, 0, 0, 0, 0);
		CardPanel c2 =new CardPanel(card2, 0, 0, 0, 0);
		CardPanel c3 =new CardPanel(card3, 0, 0, 0, 0);
		CardPanel c4 =new CardPanel(card4, 0, 0, 0, 0);
		CardPanel c5 =new CardPanel(card5, 0, 0, 0, 0);
		CardPanel c6 =new CardPanel(card6, 0, 0, 0, 0);
		CardPanel c7 =new CardPanel(card7, 0, 0, 0, 0);
		CardPanel c8 =new CardPanel(card8, 0, 0, 0, 0);
		CardPanel c9 =new CardPanel(card9, 0, 0, 0, 0);
		CardPanel c10 =new CardPanel(card10, 0, 0, 0, 0);
		LinkedList<CardPanel> compCards = new LinkedList<CardPanel>();	
		String result =game.findTheMostColorComp(compCards);
		compCards.add(c1);
		result =game.findTheMostColorComp(compCards);
		assertEquals("If the number of blue cards is the highest","blue",result);
		compCards.add(c2);
		compCards.add(c3);
		result =game.findTheMostColorComp(compCards);
		assertEquals("If the number of green cards is the highest","green",result);
		compCards.add(c4);
		compCards.add(c5);
		compCards.add(c6);
		result =game.findTheMostColorComp(compCards);
		assertEquals("If the number of yellow cards is the highest","yellow",result);
		compCards.add(c7);
		compCards.add(c8);
		compCards.add(c9);
		compCards.add(c10);
		result =game.findTheMostColorComp(compCards);
		assertEquals("If the number of red cards is the highest","red",result);
		System.out.println("the most color is:" +result);
	}

}
