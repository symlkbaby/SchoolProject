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
		JLabel jlNumber=new JLabel("ѧ   �ţ�");
		JLabel jlClassNName=new JLabel("��   ����");
		JLabel jlName=new JLabel("��   ����");
		JLabel jlSex=new JLabel("��   ��");
		JLabel jlAvgScore=new JLabel("ƽ���֣�");
		jtfStuNum=new JTextField(20);
		jtfAvgScore=new JTextField(20);
		jtfStuName=new JTextField(20);
		Vector<String> classNames=new Vector<String>();
		for(int i=1;i<16;i++)
		{
			classNames.add("���"+(1600+i)+"��");
		}
		jcbClassName=new JComboBox(classNames);
		jrbBoy=new JRadioButton("��",true);
		jrbGirl=new JRadioButton("Ů");
		ButtonGroup bg=new ButtonGroup();
		bg.add(jrbBoy);
		bg.add(jrbGirl);
		jbOk=new JButton("ȷ��");
		jbCannel=new JButton("ȡ��");
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
			this.setTitle("��ӽ���");
		}else{
			this.setTitle("�޸�ѧ������");
			jtfStuNum.setEditable(false);
			
			jtfStuNum.setText(studentInforManageFrame.lstStudent.get(index).getNumber());
			jcbClassName.setSelectedItem(studentInforManageFrame.lstStudent.get(index).getClassName());
			jtfStuName.setText(studentInforManageFrame.lstStudent.get(index).getName());
			if(studentInforManageFrame.lstStudent.get(index).getSex().trim()=="��")
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
				String sSex="��";
				if(!jrbBoy.isSelected())
				{
					sSex="Ů";
				}
				float avgScore=Float.parseFloat(jtfAvgScore.getText().trim());
			    	
			    StudentInfor stu=new StudentInfor(num, className, sName, sSex, avgScore);
			    update_addResultIndex=StudentUhil.add(stu, studentInforManageFrame.lstStudent);
			    if(update_addResultIndex==-1)
			    {
			    	JOptionPane.showMessageDialog(this,"�������ʧ�ܣ�");
			    	this.dispose();
			    }
				JOptionPane.showMessageDialog(this,"��ӵ���"+(update_addResultIndex+1)+"�����ݳɹ�",  "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
			
			else
			{
				//���������޸�
				String num=jtfStuNum.getText();
				String className=jcbClassName.getSelectedItem().toString();
				String sName=jtfStuName.getText();
				String sSex="��";
				if(!jrbBoy.isSelected())
				{
					sSex="Ů";
					
				}
				float avgScore=Float.parseFloat(jtfAvgScore.getText().trim());
			    	
			    StudentInfor stu=new StudentInfor(num, className, sName, sSex, avgScore);
			    StudentUhil.update(index, stu,studentInforManageFrame.lstStudent );
			    JOptionPane.showMessageDialog(this,"���ݸ��³ɹ���");
			    this.dispose();
			    
			}
		}
		else
		{
			this.dispose();
		}
		
	}
}
