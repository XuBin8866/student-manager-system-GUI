package stuSqlManager;
import java.sql.*;
public class SqlModel {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public SqlModel(String url,String root,String	passwd)
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver works succed!");
			conn=DriverManager.getConnection(url,root,passwd);
			System.out.println("Connection successful!");
			
			/*if(conn!=null)
			{
				conn.close();
				conn=null;
			}
			if(ps!=null)
			{
				ps.close();
				ps=null;
			}
			if(rs!=null)
			{
				rs.close();
				rs=null;
			}
			*/
			System.out.println("Connection closed!");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC";
		String root="root";
		String passwd="123456";
		SqlModel sqlEnter=new SqlModel(url, root, passwd);
	}

}
