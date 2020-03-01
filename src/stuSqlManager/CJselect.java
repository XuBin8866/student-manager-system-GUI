/*package demo;
//创建新弹窗进行查询
//类似例子，JTextField不可编辑试试
public class CJselect {
	

}*/
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
public class CJselect extends JFrame implements ActionListener{
	//private JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true);
	//查询行窗格
	JPanel jpselect=new JPanel();
	//查询相关控件
	JButton jbselect=new JButton("查询");
	JLabel jlselect=new JLabel("请输入学号或姓名：");
	JTextField jtfselect=new JTextField(10);
	JLabel test=new JLabel("这是一个测试");
	
	
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
	
	
	//返回窗格
	JPanel jpback=new JPanel();
	JButton jbback=new JButton("返回");
	
	//用于点击查询时传递姓名
	String strname=new String();
	//用于数据库查询的姓名
	String strnamesql=new String();
	//
	//传递学号
	String str=new String();
	public CJselect(String s,String sn)
	{
		//传递学号
		str=s;
		//strnamesql=sn;
		
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
		jbback.addActionListener(this);
		jpback.add(jbback);
		this.add(jpback);
		
		this.setLayout(null);
		this.setTitle("搜索结果");
		this.setBounds(400,100,800,600);
		this.setVisible(true);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		//PVector rowData=new Vector();
		System.out.println(strnamesql);
		System.out.println(str);
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
			String sql="select 序号,num,班级,name,C语言,面向对象的程序设计,Java,总分 from(select (@i:=@i+1) 序号,num,班级,name,C语言,面向对象的程序设计,Java,总分\r\n" + 
					"					from \r\n" + 
					"					(select * from 计科1701 union select * from 计科1702 order by 总分 desc) as c1 ,(SELECT @i:=0) AS 序号 \r\n" + 
					"					where 班级=\r\n" + 
					"					(select 班级 from (select * from 计科1701 union select * from 计科1702) as c2 where num=?)) as c3 where name=?or num=?";
			
			
			//执行语句
			ps=conn.prepareStatement(sql);
			ps.setString(1,s);
			ps.setString(2, sn);
			ps.setString(3, sn);
			
			//ps.setString(1, "20172361");
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
	}
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource()==jbselect)
		{
			this.dispose();
			//获取查询的姓名
			strnamesql=jtfselect.getText();
			//System.out.println(strnamesql);
			CJselect cjs=new CJselect(str,strnamesql);
		}
		if(arg0.getSource()==jbback)
		{
			//System.out.println("sadas");
			this.dispose();
			CJone cjone=new CJone(str);
		}
	}
	
	public static void main(String[] args) {
		String s1=new String();
		String s2=new String();
		CJselect cj=new CJselect(s1,s2);
	}
}
