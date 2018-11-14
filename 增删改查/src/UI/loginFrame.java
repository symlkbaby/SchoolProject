package UI;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//��ť

import javax.swing.JButton;//��ť
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginFrame extends JDialog implements ActionListener{
	
	/**
	 * ��¼����
	 */
	private static final long serialVersionUID = 3362959313081898188L;
	private JTextField jtfUserName;//�û�������
	private JPasswordField jpfPwd;
	private JCheckBox jcbIsRemember;
	private JButton jbLogin,jbCancel;
	
	private String mUserNsmr,mPassword;
	
	public loginFrame()
	{
		initGUI();
		loadData();
	}
	/**
	 * ��ʼ������
	 */
	
	
	
	private void loadData()
	{
		String[] str=FileUtil.read("login.dat").split("\n");
		mUserNsmr=str[0];
		mPassword=str[1];
		boolean isRember=Boolean.parseBoolean(str[2]);
		jcbIsRemember.setSelected(isRember);
		if(jcbIsRemember.isSelected()){
			jtfUserName.setText(mUserNsmr);
			jpfPwd.setText(mPassword);
		}
	}
	private void initGUI()
	{
		this.setSize(400,300);
		this.setTitle("��¼����");
		this.setLayout(new GridLayout(5,1));//���񲼾�
		JPanel[] panels=new JPanel[5];
		for(int i=0;i<panels.length;i++)
		{
			panels[i]=new JPanel(new FlowLayout());
			this.add(panels[i]);
		}
		
		/*���������еĿؼ�*/
		JLabel jITitle=new JLabel("ѧ����Ϣ����");
		JLabel jIUserName=new JLabel("�û�����");
		JLabel jIPwd=new JLabel("���룺  ");
		jtfUserName=new JTextField(20);//�û��������
		jpfPwd=new JPasswordField(20);//���������
		jcbIsRemember=new JCheckBox("�Ƿ��ס�û���������");
		jbLogin=new JButton("��¼");
		jbCancel=new JButton("ȡ��");
		
		/*���������еĿؼ�*/
		panels[0].add(jITitle);
		panels[1].add( jIUserName);
		panels[1].add(jtfUserName);
		panels[2].add(jIPwd);
		panels[2].add(jpfPwd);
		panels[3].add(jcbIsRemember);
		panels[4].add(jbLogin);
		panels[4].add(jbCancel);
		
		/*���ÿؼ�����*/
		jITitle.setFont(WordFont.TITLE_FONT);
		jIUserName.setFont(WordFont.CONTENT_FONT);
		jtfUserName.setFont(WordFont.CONTENT_FONT);
		jIPwd.setFont(WordFont.CONTENT_FONT);
		jpfPwd.setFont(WordFont.CONTENT_FONT);
		jbLogin.setFont(WordFont.CONTENT_FONT);
		jbCancel.setFont(WordFont.CONTENT_FONT);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		/**
		 * ��ť���¼�
		 */
		jbLogin.addActionListener(this);
		jbCancel.addActionListener(this);
			
	}
	
	public static void main(String[] args) {
		new loginFrame();

	}
	
	/**
	 * ��ť�¼�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbLogin)
		{
			login();
		}
		if(e.getSource()==jbCancel)
		{
			jtfUserName.setText("");
			jpfPwd.setText("");
		}
	}
	
	private void login() {
		String userName=jtfUserName.getText().trim();
		String passWord=new String(jpfPwd.getPassword());
		if(userName.isEmpty()||passWord.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "�������û���������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(userName.equals(mUserNsmr)&&passWord.equals(mPassword))
		{
			//��ס����
			String content=userName+"\n"+passWord+"\n"+jcbIsRemember.isSelected();
			FileUtil.write(content, "login.dat");
			
			new studentInforManageFrame();//��ѧ������
			this.dispose();//�رյ�¼����
		}
		else
		{
			JOptionPane.showMessageDialog(this, "�û��������벻��ȷ","��ʾ",JOptionPane.ERROR_MESSAGE);
		}
	}

}
