package dataBaseBanking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustPassword {
	 Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/banking";
	public void addPswd(int cid, String pswd) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("insert into cust_pswd(cid,pswd) values(?,?)");
			ps.setLong(1,cid);
			ps.setString(2,pswd);
			ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public boolean checkPastPswd(String encryptPwd, int cid) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select * from cust_pswd where pswd=? and cid=?");
			ps.setString(1,encryptPwd);
			ps.setLong(2, cid);
			rs=(ResultSet) ps.executeQuery();
			if(rs.next())
			{
				ps.close();
				con.close();
			return false;
			}
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
	public int checkTimes(int cid) {

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("select count(*) from cust_pswd where cid=?");
			ps.setLong(1, cid);
			rs=(ResultSet) ps.executeQuery();
			while(rs.next())
			{
				int count= rs.getInt(1);
				ps.close();
				con.close();
				return count;
			}
			return 0;
	    }
		catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
	}
	public void delPswd(int cid) {
		try
		{
			con=DriverManager.getConnection(URL,"root","sathya97#");
			ps=con.prepareStatement("delete  from cust_pswd where cid=? order by pid limit 1");
			ps.setLong(1,cid);
			ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
