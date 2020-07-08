/**
 * מחלקת קלף
 **/
public class Card 
{
	protected String color; //card's color
	protected String value; //card's value
	
	//constructor
	
	Card(String c, String v)
	{
		color=c;
		value=v;
	}

	public String get_color() 
	{
		return color;
	}

	public void set_color(String c) 
	{
		this.color = c;
	}

	public String get_value() 
	{
		return value;
	}

	public void set_value(String v) 
	{
		this.value = v;
	}
}
