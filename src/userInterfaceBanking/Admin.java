package userInterfaceBanking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import workFlowBanking.CustomerObject;

public class Admin {
	int choice,ID=111,PASSWORD=111;
	int id,cid,accid;
	String name,pswd,rpswd,display="";
	boolean bError=true;
	Scanner in=new Scanner(System.in);
	Input input=new Input();
	workFlowBanking.Admin admin=new workFlowBanking.Admin();
	ArrayList<CustomerObject> cust;
	public void operations() {
		display="Enter Admin ID:";
	       id = input.getInteger(display);
	    display="Enter Password";
	            pswd = input.getPassword(display);
	        if(id==111&&pswd.equals("ADmin11"))
		{
			do
			{
					display="SELECT\n1.View Customer\n2.Add Customers\n3.View Top N Customers";
                    choice = input.getInteger(display);
			switch(choice)
				{
				case 1:
					cust=admin.viewCutomers();
					System.out.println("");
					System.out.format("%-15s %-15s%-25s%-15s%-25s","CUSTOMER ID","ACCOUNT NO","CUSTOMER NAME","BALANCE","ENCRYPTED PASSWORD");
					System.out.println("");
					Iterator<CustomerObject> iterator = cust.iterator();
					while(iterator.hasNext()){
						CustomerObject  next = iterator.next();
						display(next);
					}
					break;
				case 2:	
					bError=true;
					while(bError)
					{
					System.out.println("Enter Customer Name:");
				            //in.nextLine();
				            name=in.nextLine();
				    display="Enter Password";
				            pswd = input.getPassword(display);
		            display="Retype Password";
				            rpswd = input.getPassword(display);
					if(rpswd.equals(pswd))
					{
					if(admin.addCustomer(name,pswd))
					{
						System.out.println("Customer added");
						cid=admin.getCid(name);
						System.out.println("Customer ID:"+cid);
						System.out.println("Account ID:"+admin.getAccid(cid));
						bError=false;
					}
					else
						System.out.println("Customer cannot be added");
					}
						else
						{
							System.out.println("Password does not match");
							display="Enter 1 to continue Add Customer ";
							choice=input.getInteger(display);
							if(choice==1)
								bError=true;
							else
								bError=false;
						}
					}
					break;
				case 3:
					bError=true;
					while(bError)
					{
					display="Enter the number of top customers to be searched:";
					int no=input.getInteger(display);
					int cusNO=admin.noOfCustomers();
					if(no>=1&&no<=cusNO)
					{
					cust=admin.report(no);
					Iterator<CustomerObject> iterator1 = cust.iterator();
					System.out.println("");
					System.out.format("%-15s %-15s %-15s","ACCOUNT NO","CUSTOMER ID","BALANCE");
					System.out.println("");
					while(iterator1.hasNext()){
						CustomerObject  next = iterator1.next();
						displayReport(next);
					}
					bError=false;
					}
					else
					{
						System.out.println("Only "+cusNO+" are present");
						display="Enter 1 to continue Report ";
						choice=input.getInteger(display);
						if(choice==1)
							bError=true;
						else
							bError=false;
					}
					}
					break;
				default:
					System.out.println("Enter the correct choice");
				}
			display="Enter 1 to continue admin page otherwise to quit admin.";
            choice = input.getInteger(display);
					}while(choice==1);
			System.out.println("You have been successfully logged out");
		}
		else
			System.out.println("Invalid ID or PASSWORD");
		
	}
private void displayReport(CustomerObject next) {
	System.out.format("%-15s %-15s %-15s",next.accid ,next.cid ,next.balance);
	System.out.println("");
}
private void display(CustomerObject next) {
	System.out.format("%-15s %-15s%-25s%-15s%-25s",next.cid,next.accid,next.name,next.balance,next.pswd);
	System.out.println("");
}

}
