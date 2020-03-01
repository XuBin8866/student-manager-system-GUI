package stuSqlManager;
import java.sql.*;

import javax.swing.JOptionPane;
public class SqlModelDelete {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public SqlModelDelete(String num,String sclass)
	{
		//数据库登录
				String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
				String root="root";
				String passwd="123456";
				try {
					//连接数据库
					SqlModel sqlEnter=new SqlModel(url, root, passwd);
					//删除语句
					String sqldelete="delete from "+sclass+" where num=?";
					//执行语句
					ps=sqlEnter.conn.prepareStatement(sqldelete);
					ps.setString(1, num);
					ps.execute();
					System.out.println("删除成功！！！");
					//释放数据库变量
					if(rs!=null)
					{
						rs.close();
						rs=null;
					}
					if(ps!=null)
					{
						ps.close();
						ps=null;
					}
					if(conn!=null)
					{
						conn.close();
						conn=null;
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "删除数据失败！");
				}
	}
	public static void main(String[] args)
	{
		String num="20172404";
		String sclass="计科1701";
		SqlModelDelete sql=new SqlModelDelete(num,sclass);
	}

}
