class Worker {
    private String name;
    private double salaryRate;

    public Worker(String name, double salaryRate) {
        this.name = name;
        this.salaryRate = salaryRate;
    }

    public String getName() {
        return name;
    }

    public double getSalaryRate() {
        return salaryRate;
    }

    public double computePay(int hours) {
        return salaryRate * hours;
    }
}

class DailyWorker extends Worker {
    public DailyWorker(String name, double salaryRate) {
        super(name, salaryRate);
    }

    @Override
    public double computePay(int days) {
        return super.getSalaryRate() * days;
    }
}

class SalariedWorker extends Worker {
    private static final int WEEKLY_HOURS = 40;

    public SalariedWorker(String name, double salaryRate) {
        super(name, salaryRate);
    }

    @Override
    public double computePay(int hours) {
        return super.getSalaryRate() * WEEKLY_HOURS;
    }
}

class PayRoll {
    public void calculateMonthlyPay(Worker worker) {
        System.out.println("Calculating monthly pay for worker: " + worker.getName());
        double monthlyPay = worker.computePay(0);
        System.out.println("Monthly Pay: " + monthlyPay);
        System.out.println();
    }
}

public class Workersal {
    public static void main(String[] args) {
        PayRoll payRoll = new PayRoll();

        // Create DailyWorker
        DailyWorker dailyWorker = new DailyWorker("John", 50.0);
        int daysWorked = 25;
        payRoll.calculateMonthlyPay(dailyWorker);

        // Create SalariedWorker
        SalariedWorker salariedWorker = new SalariedWorker("Jane", 30.0);
        int hoursWorked = 45;
        payRoll.calculateMonthlyPay(salariedWorker);
    }
}
