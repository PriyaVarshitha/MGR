class Employee
{
   private int empno;
   private String name;
   private String job;
   private double salary;
   public Employee(int eno,String n,String j,double s)
   {
      this.empno=eno;
      this.name=n;
      this.job=j;
      this.salary=s;
   }
   public Employee(int eno,double s)
   {
      this.empno=eno;
      this.salary=s;
   }
   public Employee(int eno,String n,double s)
   {
      this.empno=eno;
      this.name=n;
      this.salary=s;
   }
   public int getempno()
  {
    return empno;
  }
  public String getname()
  {
    return name;
  }
  public String getjob()
  {
    return job;
  }
  public double getsalary()
  {
    return salary;
  }
     
   public void displayProfile()
   {
        System.out.println(" Employee no:" +empno);
        System.out.println(" employee name :" +name);
        System.out.println(" Employee job:" +job);
        System.out.println(" employee salary :" +salary);
        
   }
   public double calMonthlyPayroll(int nod,int attend,int noh)
   {
    int nol=nod-noh-attend;
    int nopd=nod-nol;
    double payroll=salary/30*nopd;
    return payroll;
   }
   public static void main(String args[])
   {
     Employee ee1=new Employee(101,"john","ASE",30000.0);
     Employee ee2=new Employee(102,60000.0);
     Employee ee3=new Employee(103,"kalam",80000.0);
     ee1.displayProfile();
     System.out.println("salary of the employee : "+ee1.calMonthlyPayroll(30,25,2));
     System.out.println();
     ee2.displayProfile();
     System.out.println();
     ee3.displayProfile();
   }
}