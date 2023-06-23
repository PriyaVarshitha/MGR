interface Interface1 {
    void method1();
    void method2();
}

interface Interface2 extends Interface1 {
    void method3();
    void method4();
}
class Class1 implements Interface1{
     public void method1() {
        // Implementation for method1
        System.out.println("Method 1 implementation");
    }

    public void method2() {
        // Implementation for method2
        System.out.println("Method 2 implementation");
    }
}

class Class2 implements Interface2{
     public void method1() {
        // Implementation for method1
        System.out.println("Method 1 implementation");
    }

    public void method2() {
        // Implementation for method2
        System.out.println("Method 2 implementation");
    }

    public void method3() {
        // Implementation for method3
        System.out.println("Method 3 implementation");
    }

    public void method4() {
        // Implementation for method4
        System.out.println("Method 4 implementation");
    }
}

public class Demo {
    public static void main(String[] args) {
        Interface1 i1 = new Class1();
        Interface2 i2 = new Class2();
        Class1 c1 = new Class1();
        Class2 c2 = new Class2();
        i1=c1;

        c1.method2();
        i1.method1();
        i1=c1;
        i2=c2;
        i2.method3();
        c2.method4();

        
       
    }
}
