package UI;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//按钮

import javax.swing.JButton;//按钮
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginFrame extends JDialog implements ActionListener{
	
	/**
	 * 登录界面
	 */
	private static final long serialVersionUID = 3362959313081898188L;
	private JTextField jtfUserName;//用户名输入
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
	 * 初始化界面
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
		this.setTitle("登录界面");
		this.setLayout(new GridLayout(5,1));//网格布局
		JPanel[] panels=new JPanel[5];
		for(int i=0;i<panels.length;i++)
		{
			panels[i]=new JPanel(new FlowLayout());
			this.add(panels[i]);
		}
		
		/*创建界面中的控件*/
		JLabel jITitle=new JLabel("学生信息管理");
		JLabel jIUserName=new JLabel("用户名：");
		JLabel jIPwd=new JLabel("密码：  ");
		jtfUserName=new JTextField(20);//用户名输入框
		jpfPwd=new JPasswordField(20);//密码输入框
		jcbIsRemember=new JCheckBox("是否记住用户名和密码");
		jbLogin=new JButton("登录");
		jbCancel=new JButton("取消");
		
		/*创建界面中的控件*/
		panels[0].add(jITitle);
		panels[1].add( jIUserName);
		panels[1].add(jtfUserName);
		panels[2].add(jIPwd);
		panels[2].add(jpfPwd);
		panels[3].add(jcbIsRemember);
		panels[4].add(jbLogin);
		panels[4].add(jbCancel);
		
		/*设置控件字体*/
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
		 * 按钮绑定事件
		 */
		jbLogin.addActionListener(this);
		jbCancel.addActionListener(this);
			
	}
	
	public static void main(String[] args) {
		new loginFrame();

	}
	
	/**
	 * 按钮事件
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
			JOptionPane.showMessageDialog(this, "请输入用户名和密码","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(userName.equals(mUserNsmr)&&passWord.equals(mPassword))
		{
			//记住密码
			String content=userName+"\n"+passWord+"\n"+jcbIsRemember.isSelected();
			FileUtil.write(content, "login.dat");
			
			new studentInforManageFrame();//打开学生界面
			this.dispose();//关闭登录界面
		}
		else
		{
			JOptionPane.showMessageDialog(this, "用户名或密码不正确","提示",JOptionPane.ERROR_MESSAGE);
		}
	}

}
