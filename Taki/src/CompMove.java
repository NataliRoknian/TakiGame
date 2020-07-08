import java.util.LinkedList;


public class CompMove 
{

	/*
	 * The method calculates the next move of the computer
	 
	 compHand - the computer cards in "hand" by linked list.
	 currHeapCard - the current card in the top of the "heap" as card panel.
	 isPlus2Active - boolean parameter which says if "Plus2" card is active or not.
	 isTakiActive - boolean parameter which says if "Taki" mode is active or not
	 The index of the chosen card OR (-1) if no card to put OR (-2) if "Plus2" card is active and no suitable card to put on.
	 */
	
	public int ChooseCompNextMove(LinkedList<CardPanel> compHand,CardPanel currHeapCard,boolean isPlus2Active,boolean isTakiActive,	String takiColor)
	{		
		if (isTakiActive)
		{
			for(int i=0;i<compHand.size();i++)//look all the comp "hand".
			{
				if(takiColor.contains(compHand.get(i).get_card().get_color()))
					return i;//return the index.
			}
			
			return -1;
		}
		
		if(currHeapCard.get_card().get_value().equals("2") && isPlus2Active)//if the top is plus 2 && the "Plus2" effect is on.
		{
			for(int i=0;i<compHand.size();i++)//look all the comp "hand".
			{
				if(compHand.get(i).get_card().get_value().equals("2"))//if "Plus2" is found, return the index.
					return i;//return the index.
			}
		   return -2;//if "Plus2" not found and the effect is active.
		}
		
		
		for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			//if(compHand.get(i).get_card().get_color().equals(currHeapCard.get_card().get_color())
			if(currHeapCard.get_card().get_color().contains(compHand.get(i).get_card().get_color()) 
					&& compHand.get(i).get_card().get_value().equals("10")) //if same color card is found AND it is a Plus Card.
                  
				return i;//return the index.
		}
		
		
		for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			if(currHeapCard.get_card().get_color().contains(compHand.get(i).get_card().get_color())  
					&& compHand.get(i).get_card().get_value().equals("11")) //if same color card is found AND it is a Stop Card.		
				         
				return i;//return the index
		}
		
		for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			if(currHeapCard.get_card().get_color().contains(compHand.get(i).get_card().get_color())  
					&& compHand.get(i).get_card().get_value().equals("12")) //if same color card is found AND it is a TAKI Card.			
				         
				return i;//return the index.
		}
		
		for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			if(compHand.get(i).get_card().get_color().equals(currHeapCard.get_card().get_color())
					&& !compHand.get(i).get_card().get_value().equals("2"))//if same color card is found and its any card from 1 to 9 (no 2)
			
				return i;//return the index.
		}
		
		for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			if(compHand.get(i).get_card().get_value().equals(currHeapCard.get_card().get_value())
					&& !compHand.get(i).get_card().get_value().equals("2"))//if same value card is found and its any card from 1 to 9 (no 2)
			
				return i;//return the index.
		}
		
		
	    for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			if( compHand.get(i).get_card().get_value().equals("2") 
					&& compHand.get(i).get_card().get_color().equals(currHeapCard.get_card().get_color()))//if same color card is found and its Plus2 card.
			
				return i;//return the index.
		}
		
	    
	    for(int i=0;i<compHand.size();i++)//look all comp "hand".
		{
			if(compHand.get(i).get_card().get_color().equals("allTaki") && 
					compHand.get(i).get_card().get_value().equals("13") && !isTakiActive)//if the comp holds Super TAKI card. 
				return i;//return the index.		
		}
	    
	    
	    if(!(isTakiActive))//if TAKI mode is off, comp can use same type/shape card.
	    {
	    	for(int i=0;i<compHand.size();i++)//look all comp "hand".
	    	{
	    		if(currHeapCard.get_card().get_value().equals(compHand.get(i).get_card().get_value()))//if found same shape card.
	    			return i;//return the index.
	    		if(compHand.get(i).get_card().get_color().equals("all") && compHand.get(i).get_card().get_value().equals("13"))//if the comp holds a change color card.
					return i;//return the index.
	    	}
	    }
		
	   
		return -1;//if no card to put, return index -1;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}

