package stuSqlManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/*
   只能对学生账号密码进行修改，管理员账号密码在数据库中修改
   本来想继承Login进行修改，但不知道如何禁用Login中部分不需要的功能而放弃
*/
public class PasswdUpdate extends JFrame implements ActionListener{
	JTextField jtf=new JTextField();
	JPasswordField jpf=new JPasswordField();
	JLabel jl1=new JLabel("用户名");
	JLabel jl2=new JLabel("密码");
	JButton jb1=new JButton("登录");
	JButton jb2=new JButton("修改密码");
	JPanel jp1=new JPanel();
	JLabel jl0=new JLabel();
	
	JLabel jlnewpasswd=new JLabel("新密码");
    JTextField jtfnewpasswd=new JTextField(10);
	public String passwdnew=new String();
	public String s1=new String();
	public String s2=new String();
	//获取数据库账号密码用于比对
	String sql_s1=new String();
	String sql_s2=new String();

	public PasswdUpdate()
	{
		this.setTitle("登录界面");
		jp1.setLayout(null);
		jl1.setBounds(30, 20, 80, 30);
		jp1.add(jl1);
		jl2.setBounds(40,70, 80, 30);
		jp1.add(jl2);
		jtf.setBounds(80,20,180,30);
		jpf.setBounds(80,70,180,30);
		jp1.add(jtf);
		jp1.add(jpf);
		jb1.setBounds(70,160,90,30);
		jb1.addActionListener(this);
		jp1.add(jb1);
		jb2.setBounds(170,160,90,30);
		jp1.add(jb2);
		jb2.addActionListener(this);
		jl0.setBounds(60,160,150,30);
		jp1.add(jl0);
		
		
		
		
		//
		//修改继承自Login的界面内容
		//
		jl2.setText("原密码");
		jl2.setBounds(30,70, 80, 30);
		jb1.setText("确定");
		jb2.setText("返回");
		//新密码文字
		jlnewpasswd.setBounds(30,120,80,30);
		jp1.add(jlnewpasswd);
		//新密码文本框
		jtfnewpasswd.setBounds(80,120,180,30);
		jp1.add(jtfnewpasswd);
		
		
		this.add(jp1);
		this.setBounds(800,400,350,250);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==jb1) {
			//创建数据库参数
			// System.out.println("sadasd");
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			//检查是否输入账号密码及新密码
			s1=jtf.getText();
			s2=jpf.getText();
			passwdnew=jtfnewpasswd.getText();
			//未输入账号密码
			if(s1.equals("")||s2.equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入用户名或密码");
				
				
			}
			//输入账号密码，未选择身份
			if(s1.toString().length()!=0&&s2.toString().length()!=0&&passwdnew.toString().length()==0)
			{
				JOptionPane.showMessageDialog(null, "请输入新密码");
			}
			//连接数据库
			else{
			try {
				//数据库登录参数
				String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
				String root="root";
				String passwd="123456";
				//连接数据库
				SqlModel sqlEnter=new SqlModel(url, root, passwd);
			
				//验证账号密码
				String sqlStu="select * from stuid where user=?";
				String sqlupdate="update stuid set passwd=? where user=?";

					ps=sqlEnter.conn.prepareStatement(sqlStu);
					ps.setString(1, s1);
					rs=ps.executeQuery();
					if(rs.next())
					{
						sql_s1=rs.getString(1);
						sql_s2=rs.getString(2);
					}
						//账号密码正确
						if(s1.equals(sql_s1)&&s2.equals(sql_s2)) {
							ps=sqlEnter.conn.prepareStatement(sqlupdate);
							ps.setString(1, passwdnew);
							ps.setString(2, s1);
							ps.execute();
							
							
							
							JOptionPane.showMessageDialog(null, "修改成功！");
							this.dispose();
							Login log=new Login();
							//System.exit(0);
							//this.setVisible(false);
							//显示不了表格线
							//DrawSee draw=new DrawSee(s1);
							
			
							
						}
						//账号或密码错误
						else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误");
						}
						rs.close();
						rs=null;
				        ps.close();
				        ps=null;
				        sqlEnter.conn.close();
				        sqlEnter.conn=null;			
					//System.exit(0);
					
					
					System.out.println("学生");
				
			}catch(Exception arg0)
			{
				arg0.printStackTrace();
				System.out.println("链接数据库失败！！!");
			}
			}
		System.out.println("账号"+s1+"\n密码"+s2);
		}
		if(e.getSource()==jb2)
		{
			this.dispose();
			Login log=new Login();
		}
	}
	public static void main(String[] args)
	{
		PasswdUpdate pu=new PasswdUpdate();
	}

}
