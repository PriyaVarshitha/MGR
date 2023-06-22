import java.util.Date;

abstract class Loan {
	private int accountNo;
	private String accountTitle;
	private String address;
	private String loanType;
	private String loanRemarks;
	private double loanAmount;
	private int noOfInstallments;
	private int installmentsPaid;
	private double totalToBePaid;
	private double totalPaid;
	private String nominee;
	private int loanTerm;
	private Date startDate;
	private static final double rateOfInt = 0.1; // Assuming a fixed rate of interest of 10%

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanRemarks() {
		return loanRemarks;
	}

	public void setLoanRemarks(String loanRemarks) {
		this.loanRemarks = loanRemarks;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public int getInstallmentsPaid() {
		return installmentsPaid;
	}

	public void setInstallmentsPaid(int installmentsPaid) {
		this.installmentsPaid = installmentsPaid;
	}

	public double getTotalToBePaid() {
		return totalToBePaid;
	}

	protected void setTotalToBePaid(double totalToBePaid) {
		this.totalToBePaid = totalToBePaid;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	protected void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public static double getRateOfInt() {
		return rateOfInt;
	}

	public abstract void calTotalToBePaid();

	public abstract void calEMI();

	public void payEMI(double amt) {
		totalPaid += amt;
	}
}

class GoldLoan extends Loan {
	@Override
	public void calTotalToBePaid() {
		setTotalToBePaid(getLoanAmount() + (getLoanAmount() * getRateOfInt() * getLoanTerm()));
	}

	@Override
	public void calEMI() {
		double principal = getLoanAmount();
		double rateOfInterest = getRateOfInt() / 12; // Monthly interest rate
		int loanTermMonths = getLoanTerm() * 12; // Loan term in months

		double emi = (principal * rateOfInterest * Math.pow(1 + rateOfInterest, loanTermMonths))
				/ (Math.pow(1 + rateOfInterest, loanTermMonths) - 1);

		System.out.println("EMI for Gold Loan: " + emi);
	}
}

class VehicleLoan extends Loan {
	@Override
	public void calTotalToBePaid() {
		setTotalToBePaid(getLoanAmount() + (getLoanAmount() * getRateOfInt() * getLoanTerm()));
	}

	@Override
	public void calEMI() {
		double principal = getLoanAmount();
		double rateOfInterest = getRateOfInt() / 12; // Monthly interest rate
		int loanTermMonths = getLoanTerm() * 12; // Loan term in months

		double emi = (principal * rateOfInterest * Math.pow(1 + rateOfInterest, loanTermMonths))
				/ (Math.pow(1 + rateOfInterest, loanTermMonths) - 1);

		System.out.println("EMI for Vehicle Loan: " + emi);
	}
}

class MortgageLoan extends Loan {
	@Override
	public void calTotalToBePaid() {
		setTotalToBePaid(getLoanAmount() + (getLoanAmount() * getRateOfInt() * getLoanTerm()));
	}

	@Override
	public void calEMI() {
		double principal = getLoanAmount();
		double rateOfInterest = getRateOfInt() / 12; // Monthly interest rate
		int loanTermMonths = getLoanTerm() * 12; // Loan term in months

		double emi = (principal * rateOfInterest * Math.pow(1 + rateOfInterest, loanTermMonths))
				/ (Math.pow(1 + rateOfInterest, loanTermMonths) - 1);

		System.out.println("EMI for Mortgage Loan: " + emi);
	}
}

class EMICalculator {
	public static void generateEMI(Loan ln, double principalAmount, double rateOfInterest, int term) {
		ln.setLoanAmount(principalAmount);
		ln.setLoanTerm(term);
		ln.calEMI();
	}
}

class TotalToPayCalculator {
	public static void calTotalToPay(Loan ln, double principalAmount, int term) {
		ln.setLoanAmount(principalAmount);
		ln.setLoanTerm(term);
		ln.calTotalToBePaid();
		System.out.println("Total amount to be paid: " + ln.getTotalToBePaid());
	}
}

public class Bank {
	public static void main(String[] args) {
		// Demonstration of EMICalculator and TotalToPayCalculator classes
		GoldLoan goldLoan = new GoldLoan();
		VehicleLoan vehicleLoan = new VehicleLoan();
		MortgageLoan mortgageLoan = new MortgageLoan();

		EMICalculator.generateEMI(goldLoan, 10000, Loan.getRateOfInt(), 12);
		TotalToPayCalculator.calTotalToPay(goldLoan, 10000, 12);

		EMICalculator.generateEMI(vehicleLoan, 20000, Loan.getRateOfInt(), 24);
		TotalToPayCalculator.calTotalToPay(vehicleLoan, 20000, 24);

		EMICalculator.generateEMI(mortgageLoan, 50000, Loan.getRateOfInt(), 36);
		TotalToPayCalculator.calTotalToPay(mortgageLoan, 50000, 36);
	}
}
