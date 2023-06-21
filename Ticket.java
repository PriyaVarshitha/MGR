public class Ticket
{
   int TicketNo;
   int TrainNo;
   String TravelDate;
   String Source;
   String Destination;
   Passenger[] passengers;
   public Ticket(int TNo,int TrNo,String TD,String Src,String Dest)
   {
      this.TicketNo=TNo;
      this.TrainNo=TrNo;
      this.TravelDate=TD;
      this.Source=Src;
      this.Destination=Dest;
   }
   public void setPassenger(Passenger[] p)
  {
      passengers=p;
  }
  public int getTicketno()
  { 
     return TicketNo;
  }
  public int getTrainno()
  { 
     return TrainNo;
  }
  public String getTraveldate()
  { 
     return TravelDate;
  }
  public String getsource()
  { 
     return Source;
  }
  public String getdest()
  { 
     return Destination;
  }
  public void displayTicketDetails()
  {
     System.out.println("TicketNo :" + TicketNo);
      System.out.println("TrainNo :" + TrainNo);
     System.out.println("TravelDate :" + TravelDate);
     System.out.println(" Source :" + Source);
     System.out.println("Destination :" + Destination);
     for(Passenger t:passengers)
     {
         System.out.println("passenger name:"+t.getname()+" "+"age :"+ t.getage()+"gender :"+t.getgender()+ t.gettc());
     }
  }
}