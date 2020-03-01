package stuSqlManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class Admin extends JFrame implements ActionListener{
	//private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	//查询行窗格
	JPanel jpselect=new JPanel();
	//查询相关控件
	JButton jbselect=new JButton("查询");
	JLabel jlselect=new JLabel("请输入学号姓名或班级：");
	JTextField jtfselect=new JTextField(10);
	JLabel test=new JLabel("这是一个测试");
	//存放查询的姓名
	String strname=new String();
	
	//显示成绩表窗格
	JPanel jpshow=new JPanel();
	//成绩表
	/*设置表格不可编辑
	 *  DefaultTableModel tableModel0=new DefaultTableModel();
	JTable jt=new JTable(tableModel0) {
		 public boolean isCellEditable(int row, int column)
         {
                    return false;//表格不允许被编辑
         }
	};*/
	//设置表格不可编辑
	JTable jt=new JTable(new DefaultTableModel() {
		public boolean isCellEditable(int row, int column)
        {
                   return false;//表格不允许被编辑
        }	
	});
	
	DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
	
	//
	//返回窗格
	//
	JPanel jpback=new JPanel();
	//查询单个学生页面的返回键回到全体学生界面
	JButton jbbacktoall=new JButton("返回");
	//查询全体学生页面的返回键回到登录界面
	JButton jbbacktolog=new JButton("返回");
	//添加、修改 删除
	JButton jbadd=new JButton("添加");
	JButton jbupdata=new JButton("修改");
	JButton jbdelete=new JButton("删除");
	//用于保存该窗口进行传值
	JFrame This=new JFrame();
	//建立数组用于传递选中行信息
	String[] score=new String[7];
	String num=new String();
	String sclass=new String();
	String name=new String();
	String c=new String();
	String cplus=new String();
	String java=new String();
	String sum=new String();
	//用于鼠标监听事件获取行数
	int index=0;
	//创建数据库参数
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	//查询全体成绩
	public Admin()
	{
		//
		//使用空布局
		//
		//
		//设置表格内容居中
		//
		//重写返回按钮改为刷新 
		
		//
		//查询行
		//
		jpselect.setBounds(0, 0, 780, 50);
		//设置查询按钮大小
		jbselect.setPreferredSize(new Dimension(60,20));
		jpselect.add(jlselect);
		jpselect.add(jtfselect);
		jpselect.add(jbselect);		
		jtfselect.addActionListener(this);
		jbselect.addActionListener(this);
		this.add(jpselect);
		//
		//成绩表格
		//
		jpshow.setBounds(10,50,770,460);
		jpshow.setLayout(null);
		JScrollPane scroll= new JScrollPane(jt);
		scroll.setBounds(0,0,760,460);
		jpshow.add(scroll);
		jpshow.add(test);
		this.add(jpshow);
		
		//
		//返回窗格
		//
		jpback.setBounds(0, 515, 780,40);
		jpback.add(jbadd);
		jpback.add(jbupdata);
		jpback.add(jbdelete);
		jpback.add(jbbacktolog);
		jbadd.addActionListener(this);
		jbupdata.addActionListener(this);
		jbdelete.addActionListener(this);
		jbbacktolog.addActionListener(this);
		
		this.add(jpback);
		
		this.setLayout(null);
		this.setTitle("班级排名");
		this.setBounds(550,200,800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//
		//表格内容
		//
		tableModel.addColumn("序号");
		tableModel.addColumn("学号");
		tableModel.addColumn("班级");
		tableModel.addColumn("姓名");
		tableModel.addColumn("C语言");
		tableModel.addColumn("C++");
		tableModel.addColumn("Java");
		tableModel.addColumn("总分");
		
		//设置内容居中对齐
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jt.setDefaultRenderer(Object.class, r);

		//设置列宽
		//第一列
		TableColumn column = jt.getColumnModel().getColumn(0);// 取第一列
		column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setMinWidth(50);
		//后六列
		for(int i=1;i<8;i++) {
		TableColumn column1 = jt.getColumnModel().getColumn(i);// 取第一列
		column1.setPreferredWidth(101);
		column1.setMaxWidth(101);
		column1.setMinWidth(101);
		}
		//行高
		jt.setRowHeight(20);
		
		//
		//添加数据连接数据库
		//
	
		//PVector rowData=new Vector();
		
		//rowData可以存放多行,开始从数据库里取
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");
			String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
			String root="root";
			String passwd="123456";
			conn=DriverManager.getConnection(url,root,passwd);
			System.out.println("连接成功");
			
			//查询语句
			String sql="select (@i:=@i+1) 序号,num,班级,name,C语言,面向对象的程序设计,Java,总分 from (select * from 计科1701 union select * from 计科1702 order by 总分 desc) as c ,(SELECT @i:=0) AS 序号";
					
			
			
			//执行语句
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				    String[] data= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
					tableModel.addRow(data);
			}
			//
			//读取选中行内容进行传值
			//
			
			//获取列个数，由于在select语句中多加了一列排名，故循环时要减少一列
			//column已使用
			//int column1=jt.getSelectedColumnCount();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("连接失败");
		}finally {
			try {
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
			}
		}
		
		//鼠标点击获取选中行
		jt.addMouseListener(new MouseAdapter(){
			  @Override
			  public void mouseClicked(MouseEvent arg0) {
				  index= jt.getSelectedRow();
				  System.out.println(index);
			  }
		});
	}
  //查询个人成绩
	public Admin(String str)
	{
		//
		//使用空布局
		//
		//
		//设置表格内容居中
		//
		
		//
		//查询行
		//
		jpselect.setBounds(0, 0, 780, 50);
		//设置查询按钮大小
		jbselect.setPreferredSize(new Dimension(60,20));
		jpselect.add(jlselect);
		jpselect.add(jtfselect);
		jpselect.add(jbselect);		
		jtfselect.addActionListener(this);
		jbselect.addActionListener(this);
		this.add(jpselect);
		//
		//成绩表格
		//
		jpshow.setBounds(10,50,770,460);
		jpshow.setLayout(null);
		JScrollPane scroll= new JScrollPane(jt);
		scroll.setBounds(0,0,760,460);
		jpshow.add(scroll);
		jpshow.add(test);
		this.add(jpshow);
		
		//
		//返回窗格
		//
		jpback.setBounds(0, 515, 780,40);
		jpback.add(jbadd);
		jpback.add(jbupdata);
		jpback.add(jbdelete);
		jpback.add(jbbacktoall);
		jbadd.addActionListener(this);
		jbupdata.addActionListener(this);
		jbdelete.addActionListener(this);
		jbbacktoall.addActionListener(this);
		this.add(jpback);
		
		this.setLayout(null);
		this.setTitle("搜索结果");
		this.setBounds(550,200,800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//
		//表格内容
		//
		tableModel.addColumn("序号");
		tableModel.addColumn("学号");
		tableModel.addColumn("班级");
		tableModel.addColumn("姓名");
		tableModel.addColumn("C语言");
		tableModel.addColumn("C++");
		tableModel.addColumn("Java");
		tableModel.addColumn("总分");
		
		//设置内容居中对齐
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jt.setDefaultRenderer(Object.class, r);

		//设置列宽
		//第一列
		TableColumn column = jt.getColumnModel().getColumn(0);// 取第一列
		column.setPreferredWidth(50);
		column.setMaxWidth(50);
		column.setMinWidth(50);
		//后六列
		for(int i=1;i<8;i++) {
		TableColumn column1 = jt.getColumnModel().getColumn(i);// 取第一列
		column1.setPreferredWidth(101);
		column1.setMaxWidth(101);
		column1.setMinWidth(101);
		}
		//行高
		jt.setRowHeight(20);
		
		//
		//添加数据连接数据库
		//
		//创建数据库参数
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功");
			String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
			String root="root";
			String passwd="123456";
			conn=DriverManager.getConnection(url,root,passwd);
			System.out.println("连接成功");
			
			//查询语句
			String sql="select 序号,num,班级,name,C语言,面向对象的程序设计,Java,总分 from\r\n" + 
					"(select (@i:=@i+1) 序号,num,班级,name,C语言,面向对象的程序设计,Java,总分 from (select * from 计科1701 union select * from 计科1702 order by 总分 desc) as c ,(SELECT @i:=0) AS 序号)\r\n" + 
					" as c3 where num=? or name=? or 班级=?  ";
					
			
			
			//执行语句
			ps=conn.prepareStatement(sql);
			ps.setString(1, str);
			ps.setString(2, str);
			ps.setString(3, str);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				    String[] data= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
					tableModel.addRow(data);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("连接失败");
		}finally {
			try {
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
			}
		}
		//鼠标点击获取选中行
				jt.addMouseListener(new MouseAdapter(){
					  @Override
					  public void mouseClicked(MouseEvent arg0) {
						  index= jt.getSelectedRow();
						  System.out.println(index);
					  }
				});
	}
	public void actionPerformed(ActionEvent arg0)
	{
		//查询
		if(arg0.getSource()==jbselect)
		{
			this.dispose();
			strname=jtfselect.getText();
			Admin Admin=new Admin(strname);
		}
		//添加
		if(arg0.getSource()==jbadd)
		{
			//将显示成绩表的窗口进行传值，在AdminOperation中点击确定则关闭再打开一个新的成绩表以达到刷新效果
			This=this;
			AdminOperation admo=new AdminOperation(This);

		}
		//修改
		if(arg0.getSource()==jbupdata)
		{

			//获取选中行
			//int index=jt.getSelectedRowCount();
			num=jt.getValueAt(index, 1).toString();
			 sclass=jt.getValueAt(index, 2).toString();
			 name=jt.getValueAt(index, 3).toString();
			 c=jt.getValueAt(index, 4).toString();
			 cplus=jt.getValueAt(index, 5).toString();
			 java=jt.getValueAt(index, 6).toString();
			 sum=jt.getValueAt(index, 7).toString();
			String[] score= {num,sclass,name,c,cplus,java,sum};
			
			//将显示成绩表的窗口进行传值，在AdminOperation中点击确定则关闭再打开一个新的成绩表以达到刷新效果
			This=this;
			AdminOperation admo=new AdminOperation(This,score);
		}
		//删除
		if(arg0.getSource()==jbdelete)
		{
			//获取选中表格中的学号，班级信息
			num=jt.getValueAt(index, 1).toString();
			sclass=jt.getValueAt(index, 2).toString();
			//数据库删除语句
			SqlModelDelete del=new SqlModelDelete(num,sclass);
			this.dispose();
			Admin admnew=new Admin();
		}
		
		//返回至总成绩表
		if(arg0.getSource()==jbbacktoall)
		{
			this.dispose();
			//回到全体成绩表页面
			Admin Admin=new Admin();
		}
		//返回至登录界面
		if(arg0.getSource()==jbbacktolog)
		{
			//返回
			this.dispose();
			Login log=new Login();
		}
	}
	
	public static void main(String[] args) {
		Admin cj=new Admin();
	}
}


