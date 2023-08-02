import java.util.*;
import java.io.*;

public class atm{

    static String f_name_arr[]=new String[100];
    static String l_name_arr[]=new String[100];
    static String username_arr[]=new String[100];
    static String password_arr[]=new String[100];
    static String account_num_arr[]=new String[100];

    static double balance_arr[]= new double[100];
    static String transaction[][]= new String[100][1000]; 

    static int number_of_users=0;

    public static boolean validate(String arr[], String current, int num)throws IOException
    {
        for(int i=0; i<num; i++)
        {
            if(arr[i].equals(current)){
                return false; //returns false when entered account number already exists
            }
        }
        return true; //returns true when entered account number does not exist
    }

    public static int match(String curr_account, String curr_username)throws IOException
    {
        for(int i=0; i<number_of_users; i++)
        {
            if(account_num_arr[i].equals(curr_account) && username_arr[i].equals(curr_username))
            {
                return i;
            }
        }
        return -1;
    }

    public static void register()throws IOException
    {
        Scanner sc= new Scanner(System.in);

        String f_name,l_name,username="ud92u[0r20rq2euq2q9u290dq209du2",password="uwhfr83wyrp8q2equdu90aud0queihad8q2",r_password="afary893y8w3y598ry239rfgfwe",account_num="afwiawjd7242e82uda9";

        System.out.println("\n\nEnter Details Below: ");
        System.out.print("\nFirst Name: ");
        f_name= sc.nextLine();
        System.out.print("\nLast Name: ");
        l_name= sc.nextLine();
        
        //Account Number Validation
        do{
            System.out.print("\nAccount Number: ");
            account_num=sc.nextLine();
            if(validate(account_num_arr, account_num, number_of_users) == false){
                System.out.println("Account Number already registered!! Enter Again. \nEnter 1 to return to Main Menu, or Enter any other value to re-enter");
                if(sc.nextLine().equals("1"))
                    return;
                else
                    continue;
            }
            else{
                account_num_arr[number_of_users]= account_num;
                break;
            }
        }while(validate(account_num_arr,account_num,number_of_users) == false);      

        // Username Validation
        do{
            System.out.print("\nSelect Username: ");
            username= sc.nextLine();
            if(!validate(username_arr,username,number_of_users)){
                System.out.println("Username is already registered!! Select a different one. \nEnter 1 to return to Main Menu, or Enter any other value to re-enter");
                if(sc.nextLine().equals("1"))
                    return;
                else
                    continue;
            }
            else{
                username_arr[number_of_users]= username;
                break;
            }
        }while(validate(username_arr,username,number_of_users) == false);

        // Password Validation
        while(!password.equals(r_password))
        {
            System.out.print("\nSelect a Password: ");
            password= sc.nextLine();
            System.out.print("\nRe-type Password: ");
            r_password= sc.nextLine();

            if(!password.equals(r_password)){
                System.out.println("Passwords do not match!! Enter again. \nEnter 1 to return to Main Menu, or Enter any other value to re-enter");
                if(sc.nextLine().equals("1"))
                    return;
            }
        }       

        f_name_arr[number_of_users]= f_name;
        l_name_arr[number_of_users]= l_name;
        account_num_arr[number_of_users]= account_num;
        username_arr[number_of_users]= username;
        password_arr[number_of_users]= password;
        balance_arr[number_of_users]= 0.0; 
        number_of_users+=1;

        System.out.println("Congratulations!! You are Successfully Registered.\n\n");
    }

    public static void login()throws IOException
    {
        Scanner sc=new Scanner(System.in);

        boolean f= true;
        int index, tries=3,operation,curr_trans_num=0;
        String username,account,password="82483r83y3hdh3w9dh398398y3398ye";

        System.out.print("Enter Account Number: ");
        account= sc.nextLine();
        System.out.print("Enter Username: ");
        username= sc.nextLine();
        index= match(account,username);

        //Checking if registered user or not
        if(index==-1)
        {
            System.out.println("User does not exist!!! Redirecting to Main Menu...");
            return;
        }

        //Checking for the correct password
        while(tries>0 && !password_arr[index].equals(password))
        {
            System.out.print("\nEnter Password: ");
            password= sc.nextLine();

            if(!password_arr[index].equals(password) && tries>0)
            {
                tries-= 1;
                System.out.println("\nIncorrct Password!! Enter Again");
                System.out.println("You have "+tries+" tries left.");
            }
            if(tries==0)
            {
                System.out.println("\nYou have failed to enter the password correctly!! \nRedirecting to Main Menu...");
                return;
            }
        }

        System.out.println("\nWelcome Back!! "+f_name_arr[index]+" "+l_name_arr[index]);
        while(f)
        {
            System.out.println("\nSelect an Option: ");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. EXIT");
            System.out.print("\nEnter Option Here: ");
            operation= sc.nextInt();
            sc.nextLine();

            switch(operation)
            {
                case 1: //Deposit
                    System.out.print("\nEnter Amount to be Deposited: ");
                    double amount= sc.nextDouble();
                    
                    if(amount<200)
                    {
                        System.out.println("Cannnot Deposit Less than Rs. 200!! Enter a valid amount");
                    }
                    else{
                        balance_arr[index]+= amount;
                        transaction[index][curr_trans_num++]= "Deposited: Rs "+amount+"     Closing Balance: Rs "+balance_arr[index];
                        System.out.println("Your Transcation is Successful!! \nRs "+amount+ " was deposited to your account");
                    }

                    break;

                case 2: //Withdraw
                    System.out.print("\nEnter Amount to be withdrawn: ");
                    amount= sc.nextDouble();

                    if(amount<200)
                    {
                        System.out.println("Cannot Withdraw less than Rs 200!! Enter a valid amount");
                    }
                    else{
                        if(amount<=balance_arr[index])
                        {
                            balance_arr[index]-=amount;
                            transaction[index][curr_trans_num++]= "Withdrawl: Rs "+amount+"     Closing Balance: Rs "+balance_arr[index];
                            System.out.println("Your Transaction is Successful!! \nPlease receive cash of Rs "+amount);
                        }
                        else
                        {
                            System.out.println("Not enough balance available in your account");
                        }
                    }

                    break;
                
                case 3: //Transfer
                    System.out.println("Enter Account Number of receiver: ");
                    String receiver= sc.nextLine();
                    System.out.println("Enter Amount to be transferred: ");
                    amount= sc.nextDouble();
                    
                    if(receiver.equals(account_num_arr[index]) && receiver.equals(account))
                    {
                        System.out.println("Cannot transfer to same account. Try the Deposit Option!!!");
                    }
                    else{
                    if(amount<=0)
                    {
                        System.out.println("Enter valid transfer amount");
                    }
                    else 
                    {
                        if(amount<=balance_arr[index])
                        {
                            balance_arr[index]-= amount;
                            transaction[index][curr_trans_num++]= "Transferred: Rs "+amount+" to Account No.-  "+receiver+"  Closing Balance: Rs "+balance_arr[index];
                            System.out.println("Rs "+amount+" was transferred to Account No.- "+receiver);
                        }
                        else
                        {
                            System.out.println("Not enough balance available in your account");
                        }
                    }
                }
                    break;

                case 4: //Check Balance
                    System.out.println("Current Account Balance is: Rs "+balance_arr[index]);
                    break;

                case 5: //Transaction History
                    System.out.println("\n\n--TRANSACTION HISTORY--");
                    for(int k=0; k<curr_trans_num; k++)
                    {
                        System.out.println((k+1) + ". " + transaction[index][k]);
                    }
                    System.out.println("\n");
                    break;

                case 6:
                    f=false;
                    break;

                default:
                System.out.println("Sorry!!! Not a valid Input.");

            }

        }
    }

    public static void main(String[] args)throws IOException
    {
        Scanner sc= new Scanner(System.in);

        int choice= 3;
        boolean f= true;

        while(f){
            System.out.println("\n\n---WELCOME---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. EXIT");
            System.out.print("Enter Choice: ");
            choice= sc.nextInt();

            switch(choice)
            {
                case 1:
                    register();
                    break;
                
                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("THANKS FOR VISING!! COME BACK SOON");
                    f= false;
                    break;
                
                default:
                    System.out.println("Sorry!!! Not a valid Input.");
            }
        }
    } //End of main
} //End of class