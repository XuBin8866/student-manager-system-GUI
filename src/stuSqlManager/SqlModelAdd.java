package stuSqlManager;
import java.sql.*;

import javax.swing.JOptionPane;
public class SqlModelAdd {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public SqlModelAdd(String num,String name,String sclass,int c,int cplus,int java,int sum)
	{
		//数据库登录
		String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
		String root="root";
		String passwd="123456";
		try{
			//连接数据库
			SqlModel sqlEnter=new SqlModel(url, root, passwd);
			//添加语句
			String sqlAdd="insert "+sclass+" values(?,?,?,?,?,?,?)";
			//String sqlinsert="";
			//执行语句
			ps=sqlEnter.conn.prepareStatement(sqlAdd);
			ps.setString(1, num);
			ps.setString(2, sclass);
			ps.setString(3, name);
			ps.setInt(4, c);
			ps.setInt(5, cplus);
			ps.setInt(6, java);
			ps.setInt(7, sum);
			ps.execute();
			System.out.println("数据插入成功！");
			if(conn!=null)
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
		}catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加数据失败！");
		}
	}
	public static void main(String[] args) {
		
		String num="20172404";
		String name="孙悟天";
		String sclass="计科1701";
		int c=82;
		int cplus=82;
		int java=82;
		int sum=246;
		SqlModelAdd sql_add=new SqlModelAdd(num,name,sclass,c,cplus,java,sum);
	}
}
