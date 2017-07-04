package userInterfaceBanking;

import java.util.Scanner;
public class Home {
	
	public static void main(String args[])
	{
		Input input=new Input();
		int choice=0;
		String display="";
		@SuppressWarnings({ "resource", "unused" })
		Scanner in=new Scanner(System.in);
		do
		{
			display="SELECT\n1.Customer\n2.Admin";
			choice=input.getInteger(display);
		 		switch(choice)
		{
		case 1:
			Customer customer=new Customer();
			customer.operations();
			break;
		case 2:
			Admin admin=new Admin();
			admin.operations();
			break;
		default:
			System.out.println("Enter the correct choice");
		}
		
			display="Enter 1 to continue the application ";
		       choice=input.getInteger(display);
		}while(choice==1);
		System.out.println("Application is closed");
	}
}
