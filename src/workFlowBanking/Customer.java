package workFlowBanking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customer {

	public int getAccountNO(int cid) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		return account.getAccid(cid);
			}

	public int balance(int cid) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		return account.cheackBalance(getAccountNO(cid));
	}

	public boolean login(int cid, String pswd) {
		dataBaseBanking.Customer customer=new dataBaseBanking.Customer();
		return customer.login(cid,encryptPwd(pswd));
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

	public boolean withdraw(int cid, int amount) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		int accid=account.getAccid(cid);
		int balance=balance(cid);
		if(balance>(amount+1000))
		{
			transaction(1,accid,amount,0,balance-amount);
			return account.withdraw(accid,balance-amount);
		}
		else
			return false;
	}

	private void transaction(int type, int accid, int amount, int benefactor,int balance) {
           dataBaseBanking.Transaction transaction=new dataBaseBanking.Transaction();   
		   transaction.addTransaction(type,accid,amount,benefactor,balance);
		
	}

	public boolean deposit(int cid, int amount) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		int accid=account.getAccid(cid);
		int balance=account.cheackBalance(accid);
		if(account.deposit(accid,amount+balance))
		{
			transaction(2,accid,amount,0,amount+balance);
			return true;
		}
		else
		return false;
	}

	public boolean transfer(int cid, int amount, int accountID) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		int accid=account.getAccid(cid);
		int balance=balance(cid);
		if(account.check(accountID))
		{
		if(balance>(amount+1000))
		{
			int balanceDeposit=account.cheackBalance(accountID);
				if(account.deposit(accountID,amount+balanceDeposit))
				{
					transaction(3,accid,amount,accountID,balance-amount);
					transaction(7,accountID,amount,accid,amount+balanceDeposit);
					return account.withdraw(accid,balance-amount);
				}
		}
		}
			return false;
	}

	public ArrayList<TransactionObject> viewTransaction(int cid) {
		dataBaseBanking.Transaction transaction=new dataBaseBanking.Transaction();   
		ResultSet rs=transaction.viewTransaction(getAccountNO(cid));
		ArrayList<TransactionObject> tran=new ArrayList<TransactionObject>();
		try {
			while(rs.next())
			{
				tran.add(new TransactionObject(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tran;
	}

	public boolean checkPastPswd(String pswd, int cid) {
		dataBaseBanking.CustPassword custPassword=new dataBaseBanking.CustPassword();
		return custPassword.checkPastPswd(encryptPwd(pswd),cid);
	}

	public boolean changePswd(int cid, String pswd) {
		pswd=encryptPwd(pswd);
		dataBaseBanking.Customer customer=new dataBaseBanking.Customer();
		dataBaseBanking.CustPassword custPassword=new dataBaseBanking.CustPassword();  
		if(custPassword.checkTimes(cid)<3)
		custPassword.addPswd(cid,pswd);
		else
		{
			custPassword.delPswd(cid);
			custPassword.addPswd(cid,pswd);
		}
		return customer.changePswd(cid,pswd);
	}

	public int transactionCount(int cid) {
		dataBaseBanking.Transaction transaction=new dataBaseBanking.Transaction();  
		return transaction.transactionCount(getAccountNO(cid));
	}

	public boolean operationalFee(int cid) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		int accid=account.getAccid(cid);
		int balance=balance(cid);
		if(!account.withdraw(accid,balance-10))
			return false;
		else
		{
			transaction(5,accid,10,0,balance-10);
			return true;
		}
	}

	public boolean topCustomer(int cid) {
		 dataBaseBanking.Account account=new dataBaseBanking.Account();
		 return account.CheckTopCustomer(getAccountNO(cid));
	}

	public boolean maintanenceFee(int cid) {
		dataBaseBanking.Account account=new dataBaseBanking.Account();
		int accid=account.getAccid(cid);
		int balance=balance(cid);
		if(!account.withdraw(accid,balance-100))
			return false;
		else
		{
			transaction(6,accid,100,0,balance-100);
			return true;
		}
	}

}
