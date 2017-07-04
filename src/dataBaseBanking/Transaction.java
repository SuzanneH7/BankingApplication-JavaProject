package dataBaseBanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaction {
	 Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/banking";
	public boolean addTransaction(int type, int accid, int amount, int benefactor,int balance) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("insert into transaction(accid,type,amount,benefactor,balance) values(?,?,?,?,?)");
			ps.setLong(1,accid);
			ps.setLong(2,type);
			ps.setLong(3,amount);
			ps.setLong(4,benefactor);
			ps.setLong(5,balance);
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
	public ResultSet viewTransaction(int accountNO) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from transaction where accid=?");
			ps.setInt(1,accountNO);
			rs=(ResultSet) ps.executeQuery();
			return rs;
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	public int transactionCount(int accountNO) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select count(*) from transaction where accid=? and type!=5 and type!=6 and type!=4");
			ps.setInt(1,accountNO);
			rs=(ResultSet) ps.executeQuery();
			rs.next();
			int count= rs.getInt(1);
			ps.close();
			con.close();
			return count;
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}

}
