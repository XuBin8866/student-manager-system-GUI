package stuSqlManager;
/*package demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JLtable extends JFrame{
	public JLtable() 
	{
	JPanel JLjp=new JPanel();
	Graphics jg=this.getGraphics();
	jg.setColor(Color.BLACK);
	jg.drawRect(20, 15, 760, 420);
	this.setTitle("");
	this.setBounds(550, 250, 800, 500);
	this.setVisible(true);
	
	}
	public static void main(String[] args) {
		JLtable jj=new JLtable();
	}
}*/
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.sql.*;
public class DrawSee extends JFrame implements ActionListener{
    JLabel test=new JLabel("这是一个测试",JLabel.CENTER);
	Container p = getContentPane();
    JButton jb1=new JButton("按班级查看成绩");
    JButton jb2=new JButton("按院级查看成绩");
    JButton jb3=new JButton("登出"); 
    //绘制简历表格
    private Graphics jg;
    private Color rectColor = new Color(0xf5f5f5);
   //忘记了作用
    //String s3=new String();
    //简历表格内容控件
    JLabel jlname=new JLabel("姓名");
    JLabel jlnamesql=new JLabel("姓名");
    JLabel jlsex=new JLabel("性别");
    JLabel jlsexsql=new JLabel("性别");
    JLabel jlrelationship=new JLabel("组织关系");
    JLabel jlrelationshipsql=new JLabel("组织关系");
    JLabel jlclass=new JLabel("班级");
    JLabel jlclasssql=new JLabel("班级");
    JLabel jlcollege=new JLabel("所在院系");
    JLabel jlcollegesql=new JLabel("计算机与信息工程学院");
    JLabel jlexperience=new JLabel("获得荣誉");
    JLabel jlexperiencesql1=new JLabel("这是个一条是都少学习经历1");
    JLabel jlexperiencesql2=new JLabel("学习经历2");
    JLabel jlexperiencesql3=new JLabel("学习经历3");
    JLabel jlscore=new JLabel("成绩");
    //使简历文本内容居中建立窗格
    JPanel jpname=new JPanel();
    JPanel jpnamesql=new JPanel();
    JPanel jpsex=new JPanel();
    JPanel jpsexsql=new JPanel();
    JPanel jprelationship=new JPanel();
    JPanel jprelationshipsql=new JPanel();
    JPanel jpclass=new JPanel();
    JPanel jpclasssql=new JPanel();
    JPanel jpcollege=new JPanel();
    JPanel jpcollegesql=new JPanel();
    JPanel jpexperience=new JPanel();
    JPanel jpexperiencesql1=new JPanel();
    JPanel jpexperiencesql2=new JPanel();
    JPanel jpexperiencesql3=new JPanel();
    JPanel jpscore=new JPanel();
    
    
    
    
    
    
    //显示成绩表
    JScrollPane jsc=new JScrollPane();
    JPanel jpsc=new JPanel();
   //设置表格不可编辑
    JTable jtsc=new JTable(new DefaultTableModel() {
		public boolean isCellEditable(int row, int column)
        {
                   return false;//表格不允许被编辑
        }	
	});
	DefaultTableModel tableModel = (DefaultTableModel) jtsc.getModel();
    //创建对象用于保存学号进行班级排名搜索
	 public String str=new String();
	/**
     * DrawSee构造方法
     */
    public DrawSee(String s) {
    	//保存学号
    	str=s;
    	//设置字体
    	 Font font = new Font("微软雅黑", Font.PLAIN, 25);
    	 Font fontlong =new Font("微软雅黑", Font.PLAIN, 20);
    	//
    	//设置查询和登出按钮
    	//
        jb1.setBounds(200,510,150,40);
        jb2.setBounds(400,510,150,40);
        jb3.setBounds(650,510,80,40);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        p.add(jb1);
        p.add(jb2);
        p.add(jb3);;
       
        //
        //简历展示内布局，内容在数据库操作
        //
       //姓名
        jpname.setBounds(140, 45, 106, 40);
        jlname.setFont(font);
        jpname.add(jlname);
        p.add(jpname);
        jpnamesql.setBounds(246, 45, 106, 40);
        jlnamesql.setFont(font);
        jpnamesql.add(jlnamesql);
        p.add(jpnamesql);
        //性别
        jpsex.setBounds(352, 45, 106, 40);
        jlsex.setFont(font);
        jpsex.add(jlsex);
        p.add(jpsex);
        jpsexsql.setBounds(458, 45, 106, 40);
        jlsexsql.setFont(font);
        jpsexsql.add(jlsexsql);
        p.add(jpsexsql);
        //组织关系
        jprelationship.setBounds(564, 45, 106, 40);
        jlrelationship.setFont(font);
        jprelationship.add(jlrelationship);
        p.add(jprelationship);
        jprelationshipsql.setBounds(670, 45, 106, 40);
        jlrelationshipsql.setFont(font);
        jprelationshipsql.add(jlrelationshipsql);
        p.add(jprelationshipsql);
        //班级
        jpclass.setBounds(140, 115, 106, 40);
        jlclass.setFont(font);
        jpclass.add(jlclass);
        p.add(jpclass);
        jpclasssql.setBounds(246, 115, 106, 40);
        jlclasssql.setFont(fontlong);
        jpclasssql.add(jlclasssql);
        p.add(jpclasssql);
        //学院
        jpcollege.setBounds(352, 115, 212, 40);
        jlcollege.setFont(font);
        jpcollege.add(jlcollege);
        p.add(jpcollege);
        jpcollegesql.setBounds(564, 115, 212, 40);
        jlcollegesql.setFont(fontlong);
        jpcollegesql.add(jlcollegesql);
        p.add(jpcollegesql);
        //经历
        jpexperience.setBounds(20, 185, 106, 40);
        jlexperience.setFont(font);
        jpexperience.add(jlexperience);
        p.add(jpexperience);
        jpexperiencesql1.setBounds(140, 185, 640, 40);
        jlexperiencesql1.setFont(fontlong);
        jpexperiencesql1.add(jlexperiencesql1);
        p.add(jpexperiencesql1);
        jpexperiencesql2.setBounds(140, 255, 640, 40);
        jlexperiencesql2.setFont(fontlong);
        jpexperiencesql2.add(jlexperiencesql2);
        p.add(jpexperiencesql2);
        jpexperiencesql3.setBounds(140, 325, 640, 40);
        jlexperiencesql3.setFont(fontlong);
        jpexperiencesql3.add(jlexperiencesql3);
        p.add(jpexperiencesql3);
        //成绩
        jpscore.setBounds(20, 395, 106, 40);
        jlscore.setFont(font);
        jpscore.add(jlscore);
        p.add(jpscore);
        
        
        
        //
        //显示成绩表布局，内容在数据库操作
        //
        tableModel.addColumn("C语言");
        tableModel.addColumn("面向对象的程序设计");
        tableModel.addColumn("Java");
        tableModel.addColumn("总分");
        jpsc.setBounds(137, 363, 640, 140);
       // jpsc.add(test);
        JScrollPane jssc=new JScrollPane(jtsc);
        jpsc.setLayout(null);
        jssc.setBounds(1, 0,641,142);
        jpsc.add(jssc);
        p.add(jpsc);
        //设置列宽、行高
        for(int i=0;i<3;i++)
        {
        TableColumn column = jtsc.getColumnModel().getColumn(i);// 取第一列
		column.setPreferredWidth(160);
		column.setMaxWidth(160);
		column.setMinWidth(160);
        }
        jtsc.setRowHeight(20);
      //设置内容居中对齐
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtsc.setDefaultRenderer(Object.class, r);

        
        //
        //数据库操作
        //
        
        //创建数据库参数
        //获取成绩参数
		Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        //获取简历参数
        //Connection conn1=null;
        PreparedStatement ps1=null;
        ResultSet rs1=null;
        
        try {
        	
        	//连接数据库
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	System.out.println("驱动加载成功");
        	String url="jdbc:mysql://localhost:3306/stu?useSSL=false&serverTimezone=UTC&&&characterEncording=utf-8&allowPublicKeyRetrieval=true";
        	String root="root";
        	String passwd="123456";
        	conn=DriverManager.getConnection(url,root,passwd);
        	//conn1=DriverManager.getConnection(url,root,passwd);
        	System.out.println("连接成功");
        	
        	//查询语句
        	
        	//查询成绩
        	String sql="select C语言,面向对象的程序设计,Java,总分 from (select * from 计科1701 union select * from 计科1702) as c where num=?";
        	//查询简历
        	String sql1="select name,sex,relationship,class,college,experience1,experience2,experience3 from sturesume where num=?";
        	
        	//执行语句
        	
        	//查询成绩
        	ps=conn.prepareStatement(sql);
        	ps.setString(1, s);
			rs=ps.executeQuery();
			while(rs.next())
			{
				String[] score= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
				tableModel.addRow(score);
				System.out.println(score[0]);
			}
			
			//查询简历
			ps1=conn.prepareStatement(sql1);
        	ps1.setString(1, s);
			rs1=ps1.executeQuery();
			while(rs1.next())
			{
				String[] resume= {rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8)};
				//System.out.println(resume[0]);
				jlnamesql.setText(resume[0]);
				jlsexsql.setText(resume[1]);
				jlrelationshipsql.setText(resume[2]);
				jlclasssql.setText(resume[3]);
				jlcollegesql.setText(resume[4]);
				jlexperiencesql1.setText(resume[5]);
				jlexperiencesql2.setText(resume[6]);
				jlexperiencesql3.setText(resume[7]);
			}
			
        }catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("连接失败!");
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
				if(rs1!=null)
				{
					rs1.close();
					rs1=null;
				}
				if(ps1!=null)
				{
					ps1.close();
					ps1=null;
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
        //设置窗格
        setLayout(null);
        setBounds(550, 250, 800, 600);
        setVisible(true);  
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
       try {
            
            
            
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        } 

       // 获取专门用于在窗口界面上绘图的对象
        jg =  this.getGraphics();
        
        // 绘制游戏区域
        paintComponents(jg);
       //setVisible(false);
        //
        //连接数据库
        //
        
    }
   //监听按钮控件
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==jb1)
    	{
    		//this.dispose();
    		CJone cj=new CJone(str);
    	}
    	if(e.getSource()==jb2)
    	{
    		//this.dispose();
    		CJall cj=new CJall();
    	}
    	if(e.getSource()==jb3)
    	{
    		this.dispose();
    		Login log=new Login();
    	}
    	
    }
    
    
    
    public void paintComponents(Graphics g) {
        try {
            
        	Timer t = new Timer(50, new ActionListener() {
        		 
                @Override
                public void actionPerformed(ActionEvent e) {
                	// 设置线条颜色为黑色
                    g.setColor(Color.black);
                    
                    // 绘制表格 单位格宽106 高70
                    g.drawRect(20, 45, 760, 490);
                    //第一行
                    g.drawLine(140,115,780,115);
                    //第二行
                    g.drawLine(20,185,780,185);
                    //第三行
                    g.drawLine(140,255,780,255);
                    //第四行
                    g.drawLine(140,325,780,325);
                    //第五行
                    g.drawLine(20,390,780,390);
                    //第六行
                    //g.drawLine(140,465,780,465);
                    //第一列
                    g.drawLine(140,45,140,535);
                   //第二列
                    g.drawLine(246,45,246,185);
                   //第三列
                    g.drawLine(352,45,352,185);
                    //第四列
                    g.drawLine(458,45,458,115);
                    //第五列
                    g.drawLine(564,45,564,185);
                    //第六列
                    g.drawLine(670,45,670,115);
                }
            });
            t.start();//开始执行Timer
        	
           /*
            * 
            *  
        	//super自调用无法解决由Login登录进入绘制不出表格的问题
        	//super.paintComponents(g);
            // 设置线条颜色为黑色
            g.setColor(Color.black);
            
            // 绘制表格 单位格宽95 高70
            g.drawRect(20, 45, 760, 490);
            //第一行
            g.drawLine(140,115,780,115);
            //第二行
            g.drawLine(20,185,780,185);
            //第三行
            g.drawLine(140,255,780,255);
            //第四行
            g.drawLine(140,325,780,325);
            //第五行
            g.drawLine(20,395,780,395);
            //第六行
            g.drawLine(140,465,780,465);
            //第一列
            g.drawLine(140,45,140,535);
           //第二列
            g.drawLine(246,45,246,185);
           //第三列
            g.drawLine(352,45,352,185);
            //第四列
            g.drawLine(458,45,458,115);
            //第五列
            g.drawLine(564,45,564,185);
            //第六列
            g.drawLine(670,45,670,115);
            *
            *
            */
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {   
        String ss=new String();
    	DrawSee draw=new DrawSee(ss);
       // draw.setVisible(true);

        
        
       }
    
}