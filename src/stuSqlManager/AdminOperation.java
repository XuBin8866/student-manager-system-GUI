package stuSqlManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class AdminOperation extends JFrame implements ActionListener
{
	//
	//显示成绩信息控件
	//
	//窗格
	JPanel jpshow=new JPanel();
	//文字
	JLabel jlnum=new JLabel("学号：");
	JLabel jlclass=new JLabel("班级：");
	JLabel jlname=new JLabel("姓名：");
	JLabel jlc=new JLabel("C语言：");
	JLabel jlcplus=new JLabel("C++：");
	JLabel jljava=new JLabel("Java：");
	//JLabel jlsum=new JLabel("总分");
	//文本框
	JTextField jtfnum=new JTextField(10);
	JTextField jtfclass=new JTextField(10);
	JTextField jtfname=new JTextField(10);
	JTextField jtfc=new JTextField(10);
	JTextField jtfcplus=new JTextField(10);
	JTextField jtfjava=new JTextField(10);
	
	//
	//确认和取消按钮
	//
	//添加功能下的确认键
	JButton jbcomfirmadd=new JButton("确定");
	//修改功能下的确认键
	JButton jbcomfirmupdate=new JButton("确定");
	JButton jbback=new JButton("返回");
	//数据库参数
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	//用于关闭成绩查询页面然后再次打开已达到刷新效果
	JFrame This=new JFrame();
	//添加
	public AdminOperation(JFrame jf)
	{
		//使用空布局
		//将上一个窗口的值传进来
		This=jf;
		//设置字体
		Font font = new Font("微软雅黑", Font.PLAIN,20);
		//
		//设置显示成绩信息控件
		//
		//窗格
		jpshow.setLayout(null);
		jpshow.setBounds(0, 0, 350, 260);
		this.add(jpshow);
		//学号
		jlnum.setBounds(55, 20, 70, 40);
		jlnum.setFont(font);
		jpshow.add(jlnum);
		
		jtfnum.setBounds(110, 30, 200, 25);
		jpshow.add(jtfnum);
		//班级
		jlclass.setBounds(55, 60,  70, 40);
		jlclass.setFont(font);
		jpshow.add(jlclass);
		
		jtfclass.setBounds(110, 70, 200, 25);
		jpshow.add(jtfclass);
		//姓名
		jlname.setBounds(55, 100,  60, 40);
		jlname.setFont(font);
		jpshow.add(jlname);
		
		jtfname.setBounds(110, 110, 200, 25);
		jpshow.add(jtfname);
		//C语言
		jlc.setBounds(42, 140,  75, 40);
		jlc.setFont(font);
		jpshow.add(jlc);
		
		jtfc.setText("0");
		jtfc.setBounds(110, 150, 200, 25);
		jpshow.add(jtfc);
		//面向对象的程序设计
		jlcplus.setBounds(52, 180,  70, 40);
		jlcplus.setFont(font);
		jpshow.add(jlcplus);
		
		jtfcplus.setText("0");
		jtfcplus.setBounds(110, 190, 200, 25);
		jpshow.add(jtfcplus);
		//Java
		jljava.setBounds(54, 220,  70, 40);
		jljava.setFont(font);
		jpshow.add(jljava);
		
		jtfjava.setText("0");
		jtfjava.setBounds(110, 230, 200, 25);
		jpshow.add(jtfjava);
		//确认、取消按钮
		jbcomfirmadd.setBounds(105,275,60,25);
		jbback.setBounds(175,275,60,25);
		jbcomfirmadd.addActionListener(this);
		jbback.addActionListener(this);
		this.add(jbcomfirmadd);
		this.add(jbback);
		//窗口
		this.setLayout(null);
		this.setTitle("添加学生成绩");
		this.setBounds(175,350,350,350);
		this.setVisible(true);
		//设置后会导致所有界面都退出
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	//更新数据
	public AdminOperation(JFrame jf,String[] args)
	{
		//使用空布局
		//将上一个窗口的值传进来
		This=jf;
		//设置字体
		Font font = new Font("微软雅黑", Font.PLAIN,20);
		//
		//设置显示成绩信息控件
		//
		//窗格
		jpshow.setLayout(null);
		jpshow.setBounds(0, 0, 350, 260);
		this.add(jpshow);
		//学号
		jlnum.setBounds(55, 20, 70, 40);
		jlnum.setFont(font);
		jpshow.add(jlnum);
		
		jtfnum.setText(args[0]);
		jtfnum.setEditable(false);
		jtfnum.setBackground(Color.LIGHT_GRAY);
		jtfnum.setBounds(110, 30, 200, 25);
		jpshow.add(jtfnum);
		//班级
		jlclass.setBounds(55, 60,  70, 40);
		jlclass.setFont(font);
		jpshow.add(jlclass);
		
		jtfclass.setText(args[1]);
		jtfclass.setBounds(110, 70, 200, 25);
		jpshow.add(jtfclass);
		//姓名
		jlname.setBounds(55, 100,  60, 40);
		jlname.setFont(font);
		jpshow.add(jlname);
		
		jtfname.setText(args[2]);
		jtfname.setBounds(110, 110, 200, 25);
		jpshow.add(jtfname);
		//C语言
		jlc.setBounds(42, 140,  75, 40);
		jlc.setFont(font);
		jpshow.add(jlc);
		
		jtfc.setText("0");
		jtfc.setText(args[3]);
		jtfc.setBounds(110, 150, 200, 25);
		jpshow.add(jtfc);
		//面向对象的程序设计
		jlcplus.setBounds(52, 180,  70, 40);
		jlcplus.setFont(font);
		jpshow.add(jlcplus);
		
		
		jtfcplus.setText("0");
		jtfcplus.setText(args[4]);
		jtfcplus.setBounds(110, 190, 200, 25);
		jpshow.add(jtfcplus);
		//Java
		jljava.setBounds(54, 220,  70, 40);
		jljava.setFont(font);
		jpshow.add(jljava);
		
		jtfjava.setText("0");
		jtfjava.setText(args[5]);
		jtfjava.setBounds(110, 230, 200, 25);
		jpshow.add(jtfjava);
		//确认、取消按钮
		jbcomfirmupdate.setBounds(105,275,60,25);
		jbback.setBounds(175,275,60,25);
		jbcomfirmupdate.addActionListener(this);
		jbback.addActionListener(this);
		this.add(jbcomfirmupdate);
		this.add(jbback);
		//窗口
		this.setLayout(null);
		this.setTitle("添加学生成绩");
		this.setBounds(175,350,350,350);
		this.setVisible(true);
		//设置后会导致所有界面都退出
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbcomfirmadd)
		{
			try {
				//读取文本框信息
				String num=jtfnum.getText();
				String name=jtfname.getText();
				String sclass=jtfclass.getText();
				int c=Integer.valueOf(jtfc.getText());
				int cplus=Integer.valueOf(jtfcplus.getText());
				int java=Integer.valueOf(jtfjava.getText());
				int sum=c+cplus+java;
				//System.out.println(sum);
				//
				//检查文本框内容是否有问题
				//
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入学号！");
				}
				else {
				//表里只有计科1701计科1702，暂时不允许输入别的班级
				if(sclass.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入班级！");
				}
				else if(!(sclass.equals("计科1701")||sclass.equals("计科1702"))) {
					JOptionPane.showMessageDialog(null, "请输入正确班级！");
				}
				else{if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入姓名！");
				}
				}
				}
				//将学生成绩插入数据库
				try{
					//插入数成绩操作
				SqlModelAdd sql_add=new SqlModelAdd(num,name,sclass,c,cplus,java,sum);
				//释放连接数据库对象
				/**/
				}catch(Exception arg1)
				{
					arg1.printStackTrace();
				}
				this.dispose();
				//关闭再打开成绩窗口达到刷新效果
				This.dispose();
				Admin adm=new Admin();
				
			
			}catch(Exception arg0)
			{
				arg0.printStackTrace();
				System.out.println("链接数据库失败！！!");
			}

		}
		if(e.getSource()==jbcomfirmupdate)
		{
				//读取文本框信息
				String num=jtfnum.getText();
				String name=jtfname.getText();
				String sclass=jtfclass.getText();
				int c=Integer.valueOf(jtfc.getText());
				int cplus=Integer.valueOf(jtfcplus.getText());
				int java=Integer.valueOf(jtfjava.getText());
				int sum=c+cplus+java;
				//System.out.println(sum);
				//
				//检查文本框内容是否有问题
				//
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入学号！");
				}
				else {
				//表里只有计科1701计科1702，暂时不允许输入别的班级
				if(sclass.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入班级！");
				}
				else if(!(sclass.equals("计科1701")||sclass.equals("计科1702"))) {
					JOptionPane.showMessageDialog(null, "请输入正确班级！");
				}
				else{if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入姓名！");
				}
				}
				}
				//将修改的学生成绩插入数据库
				SqlModelUpdate sqlUpdate=new SqlModelUpdate(num, name, sclass, c, cplus, java, sum);
				//完成修改关闭该页面
				this.dispose();
				//关闭再打开成绩窗口达到刷新效果
				This.dispose();
				Admin adm=new Admin();
		}
		if(e.getSource()==jbback)
		{
			this.dispose();
		}
	}
	public static void main(String[] args)
	{
		//AdminOperation admo=new AdminOperation();
	}

}
