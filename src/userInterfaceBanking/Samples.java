package userInterfaceBanking;

import java.util.Scanner;

public class Samples {
public static void main(String args[])
{
	//System.out.format("%-30s %-10s %-20s %-20s %-30s", "name", "size", "created date","modified date", "path");
	//System.out.println();
	/*int temp;
	String pswd="ZZaa99AAbb00";
	char password[]=pswd.toCharArray();
	for(int i=0;i<pswd.length();i++)
	{
		if(password[i]=='z')
			password[i]='a';
		else if(password[i]=='Z')
			password[i]='A';
		else if(password[i]=='9')
			password[i]='0';
		else
		{
		temp=(int)password[i];
		temp++;
	    password[i]=(char)temp;
		}
	}
	String pass=new String(password);
System.out.println(pass);*/
/*	int i,j,k;
	String a,b,c;
	Scanner in=new Scanner(System.in);
	i=Integer.parseInt(in.nextLine());
	//in.nextLine();
	int value=0;
	 boolean bError=true;
		while (bError) {
			System.out.println("Enter integer");
		        if (in.hasNextInt())
		            value = Integer.parseInt(in.nextLine());
		        else {
		        	System.out.println("Enter proper value\nEnter only digits- alphabets and special characters are not allowed");
		            in.nextLine();
		            continue;
		        }
		        bError = false;
		        System.out.println(value);
		}
		a=in.nextLine();
		System.out.println(i+" "+a);
		*/
	String type=""+1;
	System.out.println(type+""+"  "+type.equals("1"));
	type=type.replaceAll("1","WITHDRAW").replaceAll("2","DEPOSIT").replaceAll("3","TRANSFER TO").replaceAll("4","OPENING").replaceAll("5","OPERATIONAL FEE").replaceAll("6","MAINTANECE FEE").replaceAll("7","TRANSFER FROM");
	System.out.println(type);
}

}
