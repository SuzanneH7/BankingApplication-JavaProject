package userInterfaceBanking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import workFlowBanking.TransactionObject;

public class Customer {
	int choice,cid,amount,accountID;
	String pswd,rpswd,display="";
	boolean find,bError=true;
	int select;
	Scanner in=new Scanner(System.in);
	Input input=new Input();
	workFlowBanking.Customer customer=new workFlowBanking.Customer();
	ArrayList<TransactionObject> tran;
	public void operations() {
		
			display="Enter Customer ID:";
		       cid=input.getInteger(display);
		       display="Enter Password";
	            pswd = input.getPassword(display);
		if(customer.login(cid,pswd))
		{
			do
			{
				if((customer.transactionCount(cid)%5)==0)
		    	{
				do{
					System.out.println("Change password for security reasons");
					 find=changePassword();
				  }while(!find);
				}
					display="SELECT\n1.Withdrawl\n2.Deposit\n3.AccountTransfer\n4.Check Balance\n5.View Transaction\n6.Change password";
				       choice=input.getInteger(display);
				switch(choice)
				{
				case 1:
				    bError=true;
				    while(bError)
				    {
					display="Enter the amount:";
					   amount=input.getAmount(display);
					   if(amount<customer.balance(cid)-1000)
					   {
						   if((customer.transactionCount(cid)%10)==0)
						   {
							   if(!maintanenceFee())
									  break;
						   }
						   if(amount>5000)
						   {
							  if(!operationalFee())
								  break;
						   }
						   if(customer.withdraw(cid,amount))
						   {
							   System.out.println("Received Amount:"+amount);
							   bError=false;
						   }
						   else
							   System.out.println("Amount cannot be withdrawn");
					   }
					   else
					   {
						   System.out.println("Insufficient Balance");
							display="Enter 1 to continue Withdraw ";
							choice=input.getInteger(display);
							if(choice==1)
								continue;
							else
								break;
					   }
				    }
				    System.out.println("Available Balance:"+customer.balance(cid));
					break;	
				case 2:	
					bError=true;
				    while(bError)
				    {
					display="Enter the amount:";
					   amount=input.getAmount(display);
					   if((customer.transactionCount(cid)%10)==0)
					   {
						   if(!maintanenceFee())
								  break;
					   }
					   if(amount>5000)
					   {
						  if(!operationalFee())
							  break;
					   }
					   if(customer.deposit(cid,amount))
					   {
						   System.out.println("Amount Deposited:"+amount);
					       bError=false;
					   }
					   else
						   System.out.println(amount+"Cannot be Deposited");
				    }
				    System.out.println("Available Balance:"+customer.balance(cid));
					break;
				case 3:
					 bError=true;
					    while(bError)
					    {
						display="Enter the amount:";
						   amount=input.getAmount(display);
						display="Enter the Account ID";
						accountID=input.getInteger(display);
						   if(amount<customer.balance(cid)-1000)
						   {
							   if((customer.transactionCount(cid)%10)==0)
							   {
								   if(!maintanenceFee())
										  break;
							   }
							   if(amount>5000)
							   {
								  if(!operationalFee())
									  break;
							   }
							   
							   if(customer.transfer(cid,amount,accountID))
							   {
								   System.out.println("Received Amount:"+amount);
								   bError=false;
							   }
							   else{
								   System.out.println("Invalid Account NO");
									display="Enter 1 to continue Transfer";
									choice=input.getInteger(display);
									if(choice==1)
										continue;
									else
										break;
							   }
						   }
						   else
						   {
							   System.out.println("Insufficient Balance");
								display="Enter 1 to continue Transfer ";
								choice=input.getInteger(display);
								if(choice==1)
									continue;
								else
									break;
						   }
					    }
					    System.out.println("Available Balance:"+customer.balance(cid));
					break;
				case 4:
					System.out.println("Available Balance:"+customer.balance(cid));
					break;
				case 5:
					tran=customer.viewTransaction(cid);
					Iterator<TransactionObject> iterator = tran.iterator();
					int count=0;
					System.out.println("");
					System.out.format("%-15s%-15s%-15s%-25s%-15s%-15s","TRANSACTION ID","REFERENCE ID","ACCOUNT ID","TYPE","AMOUNT","BALANCE");
					System.out.println("");
					while(iterator.hasNext()){
						count++;
						TransactionObject  next = iterator.next();
						display(next,count);
					}
					break;
				case 6:
					do{
						 find=changePassword();
					  }while(!find);
					break;
				default:
					System.out.println("Enter the correct choice");
				}
					display="Enter 1 to continue customer page otherwise to quit customer.";
				       choice=input.getInteger(display);
			}while(choice==1);
			System.out.println("You have been successfully logged out");
		}
		else
			System.out.println("Invalid ID or PASSWORD");
		
	}
	private boolean maintanenceFee() {
		if(!customer.topCustomer(cid))
		{
		   display="Rs.100 will be charged for Maintanence fee\n.Press 1 to Accept";
		   select=input.getInteger(display);
		   if(select!=1)
		   {
			  return false;
		   }
		   else
		   {
			   if(customer.maintanenceFee(cid))
			   {
			   System.out.println("Amount 100 charged fro Maintanence Fee\n Current Balance:"+customer.balance(cid));
			   return true;
			   }
			   else
			   {
				   System.out.println("Insufficient balance for maintanence fee");
				   return false;
			   }
		   }
		}
		else
		{
		   System.out.println("Youre an premium customer so Maintanence fee is not charged");
		   return true ;
		}
	}
	private boolean changePassword() {
		bError=true;
	    while(bError)
	    {
	display="Enter the new Password";
    pswd = input.getPassword(display);
    display="Retype Password";
    rpswd = input.getPassword(display);
	 String pattern = "(?=.*[0-9].{1,})(?=.*[a-z].{1,})(?=.*[A-Z].{1,}).{6,}";
	boolean check=pswd.matches(pattern);
	if(check&&(rpswd.equals(pswd)))
	{
		if(customer.checkPastPswd(pswd,cid))
		{
		if(customer.changePswd(cid,pswd))
		{
			System.out.println("password changed");
			return true;
		}
		return false;
		}
		else{
			System.out.println("password cannot be changed since password matches the last 3 password");
			display="Enter 1 to continue Password Change ";
			choice=input.getInteger(display);
			if(choice==1)
				continue;
			else
				return false;
	   }
	}
	else {
		System.out.println("Passwords does not match");
			display="Enter 1 to continue Password Change ";
			choice=input.getInteger(display);
			if(choice==1)
				continue;
			else
				return false;
	   }
	    }
		return bError;
	}
	private void display(TransactionObject next, int count) {
		String type=""+next.type;
		type=type.replaceAll("1","WITHDRAW").replaceAll("2","DEPOSIT").replaceAll("3","TRANSFER TO").replaceAll("4","OPENING").replaceAll("5","OPERATIONAL FEE").replaceAll("6","MAINTANECE FEE").replaceAll("7","TRANSFER FROM");
		if(next.type==3||next.type==7)
			type=type+" "+next.benefactor;
		System.out.format("%-15s%-15s%-15s%-25s%-15s%-15s",count,next.tid,next.accid,type,next.amount,next.balance);
		System.out.println("");
	}
	public boolean operationalFee()
	{
		System.out.println("Rs.10 will be charged for operational fee");
		   display="Press 1 to Accept";
		   select=input.getInteger(display);
		   if(select!=1)
		   {
			  return false;
		   }
		   if(customer.operationalFee(cid))
		   {
		   System.out.println("Operational Fee received\n Current Balance:"+customer.balance(cid));
		   return true;
		   }
		   else
		   {
			   System.out.println("Insufficient balance for operational fee");
			   return false;
		   }
	}
}
