package dataBaseBanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Account {
 	 
	    Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/banking";
		
	public ResultSet getDetails() {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from account");
			rs=(ResultSet) ps.executeQuery();
			//ps.close();
			//con.close();
			return rs;
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		
	}

	public boolean addAccount(int cid) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("insert into account(cid) values(?)");
			ps.setLong(1,cid);
			ps.executeUpdate();
			ps.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	public int getAccid(int cid) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select accid from account where cid=? order by accid desc limit 1");
			ps.setLong(1,cid);
			rs=(ResultSet) ps.executeQuery();
			rs.next();
			return rs.getInt(1);
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}

	public int noOfCustmers() {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select count(*) from account");
			rs=(ResultSet) ps.executeQuery();
			rs.next();
			return rs.getInt(1);
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}

	public ResultSet report(int no) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from account order by balance desc limit ?");
			ps.setLong(1,no);
			rs=(ResultSet) ps.executeQuery();
			return rs;
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	public int cheackBalance(int accountNO) {
		int balance=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select balance from account where accid=?");
			ps.setLong(1,accountNO);
			rs=(ResultSet) ps.executeQuery();
			if(rs.next())
			{
			balance = Integer.parseInt(rs.getString(1));
			}
			ps.close();
			con.close();
			return balance;
			}
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}

	public boolean withdraw(int accid, int amount) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("update account set balance=? where accid=?");
			ps.setLong(1,amount);
			ps.setLong(2,accid);
			ps.executeUpdate();
			ps.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}

	public boolean deposit(int accid, int amount) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("update account set balance=? where accid=?");
			ps.setLong(1,amount);
			ps.setLong(2,accid);
			ps.executeUpdate();
			ps.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	
	}

	public boolean check(int accountID) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from account where accid=?");
			ps.setLong(1,accountID);
			rs=(ResultSet) ps.executeQuery();
	    }
		catch(Exception e)
		{
			System.out.println(e);
		}
				try {
					if(rs.next())
					{
						ps.close();
						con.close();
						return true;
					}
					else
					{
						ps.close();
						con.close();
						return false;
}
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
	}

	public boolean CheckTopCustomer(int accountNO) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select accid from account order by balance desc limit 3");
			rs=(ResultSet) ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1)==accountNO)
				{
					ps.close();
					con.close();
					return true;
				}
			}
			ps.close();
			con.close();
			return false;
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
			}
	

}
