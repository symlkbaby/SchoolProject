package UI;
/***
 * @author ��Ʈ
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
	 * ѧ����Ϣ�������
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
		columnNames.add("���");
		columnNames.add("ѧ��");
		columnNames.add("�༶");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("ƽ����");
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
			//JOptionPane.showMessageDialog(this,"��ǰ��"+(pageCurrentPage+1)+"ҳ������","��ʾ",JOptionPane.WARNING_MESSAGE);
		return;
		}
		jlPageInfo.setText("��"+pageCount+"ҳ"+"��ǰ��"+(pageCurrentPage+1)+"ҳ");
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
		jtStudents.getColumn("���").setMaxWidth(50);
		jtStudents.getColumn("�Ա�").setMaxWidth(50);
		jtStudents.setRowHeight(30);
		//���ݾ���
		DefaultTableCellHeaderRenderer tcr=new DefaultTableCellHeaderRenderer();
		tcr.setHorizontalAlignment(DefaultTableCellHeaderRenderer.CENTER);
		jtStudents.setDefaultRenderer(Object.class, tcr);
		/**����ɫ��ǰ��ɫ*/
		jtStudents.setSelectionBackground(Color.YELLOW);
		jtStudents.setSelectionForeground(Color.RED);
	}
	private void initGUI()
	{
		this.setSize(800,600);
		this.setTitle("ѧ���������");
		this.setLayout(new BorderLayout());
		JLabel jlTitle=new JLabel("ѧ����Ϣ�б�");
		jlPageInfo=new JLabel();
		jbFirstPage=new JButton("��ҳ");
		jbPageUp=new JButton("��һҳ");
		jbPageDown=new JButton("��һҳ");
		jbLastPage=new JButton("ĩҳ");
		jbAdd=new JButton("���");
		jbDelete=new JButton("ɾ��");
		jbUpdatea=new JButton("�޸�");
		jbQuery=new JButton("��ѯ");
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
				JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ������","��ʾ",JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(this,"��������Ҫ��ѯ��ѧ��ѧ��","��ʾ",JOptionPane.WARNING_MESSAGE);
			}else
			{
				StudentInfor stu=StudentUhil.query(number, lstStudent);
				if(stu==null)
				{
					JOptionPane.showMessageDialog(this,"��Ҫ��ѯ��ѧ��"+stu.getNumber()+"ѧ��������","��ѯ���",JOptionPane.WARNING_MESSAGE);;
				}
				else
				{
					String stuInfo="ѧ�ţ�"+stu.getName()+"\n"+"�༶��"+stu.getClassName()+"\n"+"������"+stu.getName()+"\n"+"�Ա�"+stu.getSex()+"\n"+"ƽ���֣�"+stu.getAvgScore();
					JOptionPane.showMessageDialog(this,stuInfo,"��ѯ���",JOptionPane.INFORMATION_MESSAGE);
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
				//��ʾ��Ϣ
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
