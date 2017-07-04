package workFlowBanking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin {

	public ArrayList<CustomerObject> viewCutomers() {
		dataBaseBanking.Customer customer=new dataBaseBanking.Customer();
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		ResultSet rs=customer.getDetails();
		ResultSet rs1=account.getDetails();
		ArrayList<CustomerObject> cust=new ArrayList<CustomerObject>();
		//System.out.println("CUSTOMER ID\tACCOUNT NO\tCUSTOMER NAME\tBALANCE\tENCRYPTED PASSWORD");
		try {
			while(rs.next()&&rs1.next())
			{
				cust.add(new CustomerObject(rs.getInt(1),rs1.getInt(1),rs.getString(2),rs1.getInt(3),rs.getString(3)));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
       return cust;
}

	public boolean addCustomer(String name, String pswd) {
		dataBaseBanking.Customer customer=new dataBaseBanking.Customer();
		dataBaseBanking.Account account=new dataBaseBanking.Account(); 
		dataBaseBanking.CustPassword custPassword=new dataBaseBanking.CustPassword();
		dataBaseBanking.Transaction transaction=new dataBaseBanking.Transaction();   
		pswd=encryptPwd(pswd);
		if(customer.addCustomer(name,pswd))
		{
			int cid=customer.getCid(name);  
			custPassword.addPswd(cid,pswd);
               if(account.addAccount(cid))
               return transaction.addTransaction(4,account.getAccid(cid),10000,0,10000);

		}
		return false;
	}

	private String encryptPwd(String pswd) {
		int temp;
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
		return pass;
	}

	public int getCid(String name) {
		dataBaseBanking.Customer customer=new dataBaseBanking.Customer();
		return customer.getCid(name);
	}

	public int getAccid(int cid) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		return account.getAccid(cid);
	}

	public int noOfCustomers() {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		return account.noOfCustmers();
	}

	public ArrayList<CustomerObject> report(int no) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		ResultSet rs=account.report(no);
		ArrayList<CustomerObject> cust=new ArrayList<CustomerObject>();
		try {
			while(rs.next())
			{
				cust.add(new CustomerObject(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}
			return cust;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}
}
