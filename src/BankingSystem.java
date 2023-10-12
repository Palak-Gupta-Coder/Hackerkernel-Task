import java.util.ArrayList;
import java.util.List;

class Customer{
    private int customerId;
    private String name;
    private String contactInfo;

    public Customer(int customerId,String name,String contactInfo){
        this.customerId = customerId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class Account{
    private double accountNumber;
    private Customer accountHolder;
    private double balance;

    public Account(double accountNumber,Customer accountHolder){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public double getAccountNumber() {
        return accountNumber;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount){
        if(amount > 0){
            balance = balance + amount;
            System.out.println("deposited $" + amount + "into Account " + accountNumber);
        }else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance = balance - amount;
            System.out.println("withdraw %" + amount + "from Account " + accountNumber);
        }else{
            System.out.println("Invalid withdrawal amount or Insufficient balance.");
        }
    }
}

class Bank{
    private List<Customer> customers;
    private List<Account> accounts;

    public Bank(){
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public void addCustomer(int customerId,String name,String contactInfo){
        customers.add(new Customer(customerId,name,contactInfo));
        System.out.println("Customer added: " + name);
    }

    public  void  createAccount(double accountNumber, int customerId){
        Customer customer = findCustomer(customerId);
        if(customer != null){
            accounts.add(new Account(accountNumber,customer));
            System.out.println("Account created for Customer " + customer.getName());
        }else {
            System.out.println("Customer not fount.");
        }
    }

    public Customer findCustomer(int customerId){
        for(Customer customer : customers){
            if(customer.getCustomerId() == customerId){
                return customer;
            }
        }
        return null;
    }

    public Account findAccount(double accountNumber){
        for(Account account : accounts){
            if(account.getAccountNumber() == accountNumber){
                return account;
            }
        }
        return null;
    }

    public void performTransaction(double accountNumber,double amount,boolean isDeposit){
        Account account = findAccount(accountNumber);
        if(account != null){
            if(isDeposit){
                account.deposit(amount);
            }else {
                account.withdraw(amount);
            }
        }else {
            System.out.println("Account not found.");
        }
    }

    public void displayAccountDetails(double accountNumber){
        Account account = findAccount(accountNumber);
        if(account != null){
            Customer customer = account.getAccountHolder();
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("Contact Info: " + customer.getContactInfo());
            System.out.println("Balance: $" + account.getBalance());
        }else {
            System.out.println("Account not found");
        }
    }

}

public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();

        //for adding Customers --> customerId,Name,ContactInfo
        bank.addCustomer(12,"Palak gupta","palakgupta681@gmail.com");
        bank.addCustomer(13,"kiran","kiran123@gmail.com");

        //for creating account --> accountNumber , CustomerId
        bank.createAccount(124567,12);
        bank.createAccount(124432,13);

        //for performing transactions --> accountNumber , amount, if isdeposit is true, it means customer wants to deposit,if it is false then customer wants to withdraw
        bank.performTransaction(124567,2000,true);
        bank.performTransaction(124432,5000,true);
        bank.performTransaction(124567,200,false);

        //for display account details --> accountNumber
        bank.displayAccountDetails(124567);
        bank.displayAccountDetails(124432);

    }
}
