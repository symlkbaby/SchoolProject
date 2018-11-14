package UI;
/***
 * @author 罗飘
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javafx.print.Collation;
import javafx.scene.layout.Border;
import jdk.net.NetworkPermission;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public  class studentInforManageFrame extends JFrame implements ActionListener{
	/**
	 * 学生信息管理界面
	 */
	private static final long serialVersionUID = -1879075955007555710L;
	
	private JTable jtStudents;
	private JLabel jlPageInfo;
	private JButton jbFirstPage,jbPageUp,jbPageDown,jbLastPage;
	private JButton jbAdd,jbDelete,jbQuery,jbUpdatea;
	private  JTextField jtfSttuNum;
	private JScrollPane pane;
	private int pageCount;
	private int pageCurrentPage;
	private static final Vector<String> columnNames=new Vector<String>();
	public static final int PAGE_SIZE = 15;
	public static ArrayList<StudentInfor> lstStudent=new ArrayList<StudentInfor>();
	public studentInforManageFrame()
	{
		initGUI();
		loadData();
	}
	private int getPageCount()
	{
		if(lstStudent==null)
			return 0;
		else
		{
			int count=lstStudent.size()/PAGE_SIZE;
			if(lstStudent.size()%PAGE_SIZE!=0)
			{
				count++;
			}
			return count;
		}
	}
	private void loadData()
	{
		columnNames.add("序号");
		columnNames.add("学号");
		columnNames.add("班级");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("平均分");
		jtStudents=new JTable(null,columnNames);
		pane.setViewportView(jtStudents);
		setTableFormat();
		showTable(null);
		StudentUhil.loadStudent(lstStudent);
		Collections.sort(lstStudent);
		loadPage(0);
	}
	private void loadPage(int pageIndex) {
		pageCurrentPage=pageIndex;
		pageCount=getPageCount();
		if(pageCurrentPage<0||pageCurrentPage>pageCount-1){
			//JOptionPane.showMessageDialog(this,"当前第"+(pageCurrentPage+1)+"页不存在","提示",JOptionPane.WARNING_MESSAGE);
		return;
		}
		jlPageInfo.setText("共"+pageCount+"页"+"当前第"+(pageCurrentPage+1)+"页");
		setButtonEnable();
		int beginIndex=pageCurrentPage*PAGE_SIZE;
		int endIndex=beginIndex+PAGE_SIZE;
		if(pageCurrentPage==pageCount-1){
			endIndex=lstStudent.size();
		}
		Vector<Vector<Object>> rowData=new Vector<Vector<Object>> ();
		for(int index=beginIndex;index<endIndex;index++)
		{
			Vector<Object> row=new Vector<Object>();
			row.add(index+1);
			StudentInfor stu=lstStudent.get(index);
			row.add(stu.getNumber());
			row.add(stu.getClassName());
			row.add(stu.getName());
			row.add(stu.getSex());
			row.add(stu.getAvgScore());
			rowData.add(row);
			
			showTable(rowData);
		}
		
	}
	private void showTable(Vector<Vector<Object>> rowData) {
		jtStudents=new JTable(rowData,columnNames);
		pane.setViewportView(jtStudents);
		setTableFormat();
		
	}
	private void setButtonEnable() {
		if(pageCurrentPage==0)
		{
			jbFirstPage.setEnabled(false);
			jbPageUp.setEnabled(false);
		}else
		{
			
			jbFirstPage.setEnabled(true);
			jbPageUp.setEnabled(true);
			jbFirstPage.setEnabled(true);
			jbPageDown.setEnabled(true);
		}
		if(pageCurrentPage==pageCount-1)
		{
			jbLastPage.setEnabled(false);
			jbPageDown.setEnabled(false);
		}else
		{
			jbLastPage.setEnabled(true);
			jbPageUp.setEnabled(true);
			jbFirstPage.setEnabled(true);
			jbPageDown.setEnabled(true);
		}
				
	}
	private void setTableFormat() {
		jtStudents.setFont(WordFont.CONTENT_FONT);
		jtStudents.getTableHeader().setFont(WordFont.TABLE_HEADEK_FONT);
		jtStudents.getColumn("序号").setMaxWidth(50);
		jtStudents.getColumn("性别").setMaxWidth(50);
		jtStudents.setRowHeight(30);
		//内容居中
		DefaultTableCellHeaderRenderer tcr=new DefaultTableCellHeaderRenderer();
		tcr.setHorizontalAlignment(DefaultTableCellHeaderRenderer.CENTER);
		jtStudents.setDefaultRenderer(Object.class, tcr);
		/**背景色和前景色*/
		jtStudents.setSelectionBackground(Color.YELLOW);
		jtStudents.setSelectionForeground(Color.RED);
	}
	private void initGUI()
	{
		this.setSize(800,600);
		this.setTitle("学生管理界面");
		this.setLayout(new BorderLayout());
		JLabel jlTitle=new JLabel("学生信息列表");
		jlPageInfo=new JLabel();
		jbFirstPage=new JButton("首页");
		jbPageUp=new JButton("上一页");
		jbPageDown=new JButton("下一页");
		jbLastPage=new JButton("末页");
		jbAdd=new JButton("添加");
		jbDelete=new JButton("删除");
		jbUpdatea=new JButton("修改");
		jbQuery=new JButton("查询");
		jtfSttuNum=new JTextField(20);
		
		JPanel panel0=new JPanel(new FlowLayout());
		JPanel panel1=new JPanel(new GridLayout(4,1));
		this.add(panel0,BorderLayout.NORTH);
		this.add(panel1,BorderLayout.SOUTH);
		pane=new JScrollPane();
		this.add(pane,BorderLayout.CENTER);
		panel0.add(jlTitle);
		JPanel[] panels=new JPanel[4];
		for(int i=0;i<panels.length;i++)
		{
			panels[i]=new JPanel(new FlowLayout());
			panel1.add(panels[i]);
		}
		panels[0].add(jlPageInfo);
		panels[1].add(jbFirstPage);
		panels[1].add(jbPageUp);
		panels[1].add(jbPageDown);
		panels[1].add(jbLastPage);
		panels[2].add(jbAdd);
		panels[2].add(jbDelete);
		panels[2].add(jbUpdatea);
		panels[3].add(jbQuery);
		panels[3].add(jtfSttuNum);
		
		jlTitle.setFont(WordFont.TITLE_FONT);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.setVisible(true);
		jbFirstPage.addActionListener(this);
		jbPageUp.addActionListener(this);
		jbPageDown.addActionListener(this);
		jbLastPage.addActionListener(this);
		jbAdd.addActionListener(this);
		jbDelete.addActionListener(this);
		jbQuery.addActionListener(this);
		jbUpdatea.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbFirstPage)
		{
			loadPage(0);
		}
		if(e.getSource()==jbPageUp)
		{
			loadPage(--pageCurrentPage);
		}
		if(e.getSource()==jbPageDown)
		{
			loadPage(++pageCurrentPage);
		}
		if(e.getSource()==jbLastPage)
		{
			loadPage(pageCount-1);
		}
		if(e.getSource()==jbDelete)
		{
			int index=getIndex();
			if(index==-1){
				JOptionPane.showMessageDialog(this, "请选择要删除的行","提示",JOptionPane.WARNING_MESSAGE);
			}else{
				StudentUhil.delect(index,lstStudent);
				int newPageCount=getPageCount();
				loadPage(pageCurrentPage);
				if(pageCount!=newPageCount)
				{
					loadPage(pageCurrentPage-1);
				}else
				{
					loadPage(pageCurrentPage);
				}
			}
		}
		if(e.getSource()==jbQuery)
		{
			String number=jtfSttuNum.getText().trim();
			if(number.isEmpty()){
				JOptionPane.showMessageDialog(this,"请输入您要查询的学生学号","提示",JOptionPane.WARNING_MESSAGE);
			}else
			{
				StudentInfor stu=StudentUhil.query(number, lstStudent);
				if(stu==null)
				{
					JOptionPane.showMessageDialog(this,"您要查询的学号"+stu.getNumber()+"学生不存在","查询结果",JOptionPane.WARNING_MESSAGE);;
				}
				else
				{
					String stuInfo="学号："+stu.getName()+"\n"+"班级："+stu.getClassName()+"\n"+"姓名："+stu.getName()+"\n"+"性别："+stu.getSex()+"\n"+"平均分："+stu.getAvgScore();
					JOptionPane.showMessageDialog(this,stuInfo,"查询结果",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		if(e.getSource()==jbAdd)
		{
			new AddUpdateStudentInforFrame(-1);
		}
		if(e.getSource()==jbUpdatea)
		{
			int index=getIndex();
			if(index==-1)
			{
				//提示信息
			}
			else
			{
				new AddUpdateStudentInforFrame(index);
				loadPage(index);
			}
		}
	}
	private int getIndex() {
		int rowIndex=jtStudents.getSelectedRow();
		if(rowIndex>=0&&rowIndex<PAGE_SIZE)
		return pageCurrentPage*PAGE_SIZE+rowIndex;
		else return 0;
	}
}
