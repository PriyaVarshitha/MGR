 interface PriceCalculator
{
  public double CalculatePrice();
}
abstract class Product {
  private int prodId;
  private String name;
  private double price;
  private double gst;
  public Product(int prodId, String name, double price, double gst) {
        this.prodId = prodId;
        this.name = name;
        this.price = price;
        this.gst = gst;
    }
    public int getProdId()
    {
        return prodId;
    }
    public void setProdId(int id)
    {
        prodId=id;
    }
    abstract public double CalculatePrice();
    public String getname()
    {
        return name;
    }
    public void setname(String n)
    {
        name=n;
    }
    public double getprice()
    {
        return price;
    }
    public void setprice(double pri)
    {
        price=pri;
    }
    public double getgst()
    {
        return gst;
    }
    public void setProdId(double gs)
    {
        gst=gs;
    }
  public double calculateShippingCharges(int pincode)
  {
     if (pincode >= 100001 && pincode <= 299999) {
            return price * 0.05; // 5% shipping charges
        } else if (pincode >= 300001 && pincode <= 899999) {
            return price * 0.07; // 7% shipping charges
        } else {
            return 0.0;
        }
  }
  public abstract void displayProfile();
}
class ElectronicProduct extends Product implements PriceCalculator
{
    private double surchargeper;
   private ProductSpec[] productspec;
   ElectronicProduct(int prodId, String name, double price, double gst,double surchargeper)
   {
       super(prodId,name,price,gst);
       this.surchargeper=surchargeper;
   }
   public void setproductspec(ProductSpec[] pr)
   {
       productspec=pr;
       
   }
   public double CalculatePrice()
   {
       double surcharge=getprice()*surchargeper;
       return this.getprice()+surcharge;
   }
   public void displayProfile()
   {
       System.out.println("Electronic Product Details");
        System.out.println("Product ID: " + getProdId());
        System.out.println("Name: " + getname());
        System.out.println("Price: " + getprice());
        System.out.println("GST: " + getgst());
        System.out.println("Shipping Charges: " + calculateShippingCharges(100005));
        System.out.println("Product Specs:");
        for (ProductSpec spec : productspec) {
            System.out.println("Spec Name: " + spec.getspecname());
            System.out.println("Description: " + spec.getdes());
        }
   }
}
class ProductSpec
{
    private String specName;
    private String description;
    public ProductSpec(String sp,String d)
    {
        this.specName=sp;
        this.description=d;
    }
    public String getspecname()
    {
        return specName;
    }
    public String getdes()
    {
        return description;
    }
}
class AgriculturalProduct extends Product implements PriceCalculator
{
    private String ProductDescription;
    private int maxUsagePeriod;
    private double discountper;
     AgriculturalProduct(int prodId, String name, double price, double gst,String pd,int mup,double dp)
   {
       super(prodId,name,price,gst);
       this.ProductDescription=pd;
       this.maxUsagePeriod=mup;
       this.discountper=dp;
   }
   public double CalculatePrice()
   {
        double gstPrice = this.getprice() * (1 + this.getgst());
        double discount = gstPrice * discountper;
        return gstPrice - discount;
   }
   public void displayProfile() {
        System.out.println("Agricultural Product Details");
        System.out.println("Product ID: " + getProdId());
        System.out.println("Name: " + getname());
        System.out.println("Price: " + getprice());
        System.out.println("GST: " + getgst());
        System.out.println("Shipping Charges: " + calculateShippingCharges(0));
        System.out.println("Product Description: " + ProductDescription);
        System.out.println("Max Usage Period: " + maxUsagePeriod);
    }
}
class ImportedProduct extends Product implements PriceCalculator
{
    private double importDuty;
    public ImportedProduct(int prodId, String name, double price, double gst,double Id)
   {
       super(prodId,name,price,gst);
       this.importDuty=Id;
   }
    public double CalculatePrice() {
        double productPrice = this.getprice();
        double importDutyAmount = productPrice * importDuty;
        return productPrice + importDutyAmount;
    }
    public void displayProfile() {
        System.out.println("Imported Product Details");
        System.out.println("Product ID: " + getProdId());
        System.out.println("Name: " + getname());
        System.out.println("Price: " + getprice());
        System.out.println("GST: " + getgst());
        System.out.println("Shipping Charges: " + calculateShippingCharges(0));
        System.out.println("Import Duty: " + importDuty);
    }
}
class Shopping
{
    public void shop(Product p)
    {
        
        System.out.println("product details");
        p.displayProfile();
        System.out.println("Total price :"+(p.CalculatePrice()+p.calculateShippingCharges(100005)));
    }
}
public class ProductDetails
{
    public static void main(String args[])
    {
        Shopping sh=new Shopping();
        ProductSpec[] trav=new ProductSpec[1];
        trav[0]=new ProductSpec("samaung","mobile device");
        
        ElectronicProduct ep=new ElectronicProduct(1,"raj",1000,2,0.03);
        ep.setproductspec(trav);
        sh.shop(ep);
        
        AgriculturalProduct ap=new AgriculturalProduct(2, "Rice", 50.0, 0.05, "Premium quality rice", 365, 0.02);
        sh.shop(ap);
        
        ImportedProduct importedProduct = new ImportedProduct(3, "Watch", 2000.0, 0.12, 0.05);
        sh.shop(importedProduct);
       //Shopping sh=new Shopping() 
    }
}
