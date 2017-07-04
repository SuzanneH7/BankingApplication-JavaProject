package dataBaseBanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String URL="jdbc:mysql://localhost:3306/banking";
	
	public ResultSet getDetails() {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from customer");
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

	public boolean addCustomer(String name, String pswd) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("insert into customer(name,pswd) values(?,?)");
			ps.setString(1,name);
			ps.setString(2,pswd);
			ps.executeUpdate();
			ps.close();
			con.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e+"swsd");
			return false;
		}
		}

	public int getCid(String name) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select cid from customer where name=? order by cid desc limit 1");
			ps.setString(1,name);
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

	public boolean login(int cid, String encryptPwd) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from customer where cid=? and pswd=?");
			ps.setLong(1,cid);
			ps.setString(2,encryptPwd);
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

	public boolean changePswd(int cid, String pswd) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("update  customer set pswd=? where cid=?");
			ps.setString(1,pswd);
			ps.setLong(2,cid);
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
	

}
