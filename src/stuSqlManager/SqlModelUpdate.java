package stuSqlManager;
import java.sql.*;

import javax.swing.JOptionPane;
public class SqlModelUpdate {
	//定义数据库变量
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public SqlModelUpdate(String num,String name,String sclass,int c,int cplus,int java,int sum)
	{
		//数据库登录参数
		String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
		String root="root";
		String passwd="123456";
		try {
			//连接数据库
			SqlModel sqlEnter=new SqlModel(url,root,passwd);
			//更新语句
			String sqlupdate="update "+sclass+" set 班级=?,name=?,C语言=?,面向对象的程序设计=?,Java=?,总分=? where num=?";
			//执行语句
			ps=sqlEnter.conn.prepareStatement(sqlupdate);
			ps.setString(1, sclass);
			ps.setString(2, name);
			ps.setInt(3, c);
			ps.setInt(4, cplus);
			ps.setInt(5, java);
			ps.setInt(6, sum);
			ps.setString(7, num);
			ps.execute();
			System.out.println("数据插入成功！");
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
			JOptionPane.showMessageDialog(null, "更新学生成绩失败！");
		}
	}
	

}
