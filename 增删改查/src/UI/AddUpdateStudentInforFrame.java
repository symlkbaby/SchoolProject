package UI;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
public class AddUpdateStudentInforFrame extends JDialog implements ActionListener{
	private JTextField jtfStuNum,jtfStuName,jtfAvgScore;
	private JComboBox jcbClassName;
	private JRadioButton jrbBoy,jrbGirl;
	private JButton jbOk,jbCannel;
	private int index;
	public static int update_addResultIndex=-1;
	public AddUpdateStudentInforFrame(int index){
		this.index=index;
		initGUI(index);
	}
	
	private void initGUI(int index)
	{
		this.setSize(400, 300);
		this.setLayout(new GridLayout(6,6));
		JPanel[] panels=new JPanel[6];
		for(int i=0;i<panels.length;i++)
		{
			panels[i]=new JPanel(new FlowLayout(FlowLayout.LEFT));
			this.add(panels[i]);
		}
		panels[5].setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel jlNumber=new JLabel("学   号：");
		JLabel jlClassNName=new JLabel("班   级：");
		JLabel jlName=new JLabel("姓   名：");
		JLabel jlSex=new JLabel("性   别：");
		JLabel jlAvgScore=new JLabel("平均分：");
		jtfStuNum=new JTextField(20);
		jtfAvgScore=new JTextField(20);
		jtfStuName=new JTextField(20);
		Vector<String> classNames=new Vector<String>();
		for(int i=1;i<16;i++)
		{
			classNames.add("软件"+(1600+i)+"班");
		}
		jcbClassName=new JComboBox(classNames);
		jrbBoy=new JRadioButton("男",true);
		jrbGirl=new JRadioButton("女");
		ButtonGroup bg=new ButtonGroup();
		bg.add(jrbBoy);
		bg.add(jrbGirl);
		jbOk=new JButton("确定");
		jbCannel=new JButton("取消");
		panels[0].add(jlNumber);
		panels[0].add(jtfStuNum);
		panels[1].add(jlClassNName);
		panels[1].add(jcbClassName);
		panels[2].add(jlName);
		panels[2].add(jtfStuName);
		panels[3].add(jlSex);
		panels[3].add(jrbBoy);
		panels[3].add(jrbGirl);
		panels[4].add(jlAvgScore);
		panels[4].add(jtfAvgScore);
		panels[5].add(jbOk);
		panels[5].add(jbCannel);
		
		if(index==-1){
			this.setTitle("添加界面");
		}else{
			this.setTitle("修改学生界面");
			jtfStuNum.setEditable(false);
			
			jtfStuNum.setText(studentInforManageFrame.lstStudent.get(index).getNumber());
			jcbClassName.setSelectedItem(studentInforManageFrame.lstStudent.get(index).getClassName());
			jtfStuName.setText(studentInforManageFrame.lstStudent.get(index).getName());
			if(studentInforManageFrame.lstStudent.get(index).getSex().trim()=="男")
			{
				jrbBoy.setSelected(true);
			}
			else
			{
				jrbGirl.setSelected(true);
			}
			jtfAvgScore.setText(studentInforManageFrame.lstStudent.get(index).getAvgScore()+"");
			
		}
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		jbOk.addActionListener(this);
		jbCannel.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==jbOk)
		{
			if(index==-1)
			{
				String num=jtfStuNum.getText();
				String className=jcbClassName.getSelectedItem().toString();
				String sName=jtfStuName.getText();
				String sSex="男";
				if(!jrbBoy.isSelected())
				{
					sSex="女";
				}
				float avgScore=Float.parseFloat(jtfAvgScore.getText().trim());
			    	
			    StudentInfor stu=new StudentInfor(num, className, sName, sSex, avgScore);
			    update_addResultIndex=StudentUhil.add(stu, studentInforManageFrame.lstStudent);
			    if(update_addResultIndex==-1)
			    {
			    	JOptionPane.showMessageDialog(this,"数据添加失败！");
			    	this.dispose();
			    }
				JOptionPane.showMessageDialog(this,"添加到第"+(update_addResultIndex+1)+"行数据成功",  "提示",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			
			else
			{
				//进行数据修改
				String num=jtfStuNum.getText();
				String className=jcbClassName.getSelectedItem().toString();
				String sName=jtfStuName.getText();
				String sSex="男";
				if(!jrbBoy.isSelected())
				{
					sSex="女";
					
				}
				float avgScore=Float.parseFloat(jtfAvgScore.getText().trim());
			    	
			    StudentInfor stu=new StudentInfor(num, className, sName, sSex, avgScore);
			    StudentUhil.update(index, stu,studentInforManageFrame.lstStudent );
			    JOptionPane.showMessageDialog(this,"数据更新成功！");
			    this.dispose();
			    
			}
		}
		else
		{
			this.dispose();
		}
		
	}
}
