package stuSqlManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
	private String[] position= {"请选择身份","学生","管理员"};
	JTextField jtf=new JTextField(15);
	JPasswordField jpf=new JPasswordField(15);
	JLabel jl1=new JLabel("用户名");
	JLabel jl2=new JLabel("密码");
	JButton jb1=new JButton("登录");
	JButton jb2=new JButton("修改密码");
	JComboBox jcb=new JComboBox(position);
	JPanel jp1=new JPanel();
	JLabel jl0=new JLabel();
	//获取文本框账号密码用于比对
	public String s1=new String();
	public String s2=new String();
	//获取数据库账号密码用于比对
	public String sql_s1=new String();
	public String sql_s2=new String();

	public Login()
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
		jcb.setBounds(90,115,150,30);
		jp1.add(jcb);
		jl0.setBounds(60,160,150,30);
		jp1.add(jl0);
		
		jcb.addActionListener(this);
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
			
			//检查是否输入账号密码
			s1=jtf.getText();
			s2=jpf.getText();
			
			//未输入账号密码
			if(s1.equals("")||s2.equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入用户名或密码");
				
				
			}
			//输入账号密码，未选择身份
			if(s1.toString().length()!=0&&s2.toString().length()!=0&&jcb.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(null, "请选择身份");
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
				String sqlAdm="select * from admid where user=?";
				if(jcb.getSelectedIndex()==1)
				{
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
							//JOptionPane.showMessageDialog(null, "欢迎您!");
							//System.exit(0);
							//this.setVisible(false);
							
							this.dispose();
							//显示不了表格线
							DrawSee draw=new DrawSee(s1);
							
						}
						else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误");
						}
						//账号或密码错误
						
						//if(){
							
						//}
						rs.close();
						rs=null;
				        ps.close();
				        ps=null;
				        sqlEnter.conn.close();
				        sqlEnter.conn=null;				
					//System.exit(0);
					
					
					System.out.println("学生");
				}
				//管理员登录
				if(jcb.getSelectedIndex()==2)
				{
					ps=sqlEnter.conn.prepareStatement(sqlAdm);
					ps.setString(1, s1.toString());
					rs=ps.executeQuery();
					if(rs.next()) {
						sql_s1=rs.getString(1);
						sql_s2=rs.getString(2);
					}
						//账号密码正确
						if(s1.equals(sql_s1)&&s2.equals(sql_s2)) {
							//JOptionPane.showMessageDialog(null, "欢迎您!");
							this.dispose();
							Admin adm=new Admin();
						}
						//账号或密码错误
						else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误");
						}
						rs.close();
						rs=null;
				        ps.close();
				        ps=null;
				        sqlEnter. conn.close();
				        sqlEnter.conn=null;			
					//System.exit(0);
					
					System.out.println("管理员");
				}
				
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
			PasswdUpdate pu=new PasswdUpdate();
		}
	}
	public static void main(String[] args)
	{
		Login log=new Login();
	
		
	}

}
