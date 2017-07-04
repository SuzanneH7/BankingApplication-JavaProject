package userInterfaceBanking;

import java.util.Scanner;

public class Input {
	Scanner in=new Scanner(System.in);
	public int getInteger(String display)
	{
		int value=0;
		 boolean bError=true;
			while (bError) {
				System.out.println(display);
			        if (in.hasNextInt())
			        	 value = Integer.parseInt(in.nextLine());
			        else {
			        	System.out.println("Enter proper value\nEnter only digits- alphabets and special characters are not allowed");
			            in.nextLine();
			            continue;
			        }
			        bError = false;
			}
			return value;
	}
public String getPassword(String display)
{

	String pswd="";
	 boolean bError=true;
	 String pattern = "(?=.*[0-9].{1,})(?=.*[a-z].{1,})(?=.*[A-Z].{1,}).{6,}";
		while (bError) {
			System.out.println(display);
		            pswd = in.nextLine();
		        if(pswd.matches(pattern))
		        bError = false;
		        else
		        	System.out.println("Password must contain\n*Atleast 6 characters\n*2 Lower case Alphabets\n*2 Upper case Alphabets\n*2 Digits");
		}
		return pswd;
}
public int getAmount(String display) {
	int value=0;
	 boolean bError=true;
		while (bError) {
			System.out.println(display);
		        if (in.hasNextInt())
		        {
		        	 value = Integer.parseInt(in.nextLine());
		        	 if(value<=0)
		        	 {
		        		 System.out.println("Enter Valid Amount");
		        		 continue;
		        	 }
		        }
		        else {
		        	System.out.println("Enter proper value\nEnter only digits- alphabets and special characters are not allowed");
		            in.nextLine();
		            continue;
		        }
		        bError = false;
		}
		return value;
}
}
