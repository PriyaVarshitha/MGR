public class Passenger
{
  private static float basePrice = 350;
  private String Name;
  private int Age;
  private String Gender;
  private float ticketCost;


  public Passenger(String n,int a,String g)
  {
     Name=n;
     Age=a;
     Gender=g;
  }

  public String getname() 
  {
    return Name;
  }
  public int getage() 
  {
    return Age;
  }
  public String getgender() 
  {
    return Gender;
  }
  public float gettc()
{
   if ((Age > 65 && Gender == "m") || (Age > 58 && Gender == "f"))
			ticketCost = basePrice * 0.75f;
		else if (Age < 5)
			ticketCost = 0;
		else if (Age >=5 && Age <= 12)
			ticketCost = basePrice * 0.50f;
		else
			ticketCost = basePrice;
    return ticketCost;
}
}