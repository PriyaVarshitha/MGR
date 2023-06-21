class TestTicket
{
  public static void main(String args[])
  {
      Ticket tt=new Ticket(1234,3712,"21-06-2023","vsp","ppm");
      //tt.displayTicketDetails();
      Passenger Travel[]=new Passenger[3];
      Travel[0]=new Passenger("ramu",2,"m");
      Travel[1]=new Passenger("sai",8,"f");
      Travel[2]=new Passenger("raju",70,"m");
      tt.setPassenger(Travel);
      tt.displayTicketDetails();
  }
}