package UI;
/***
 * @author 罗飘
 */
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class StudentUhil {
	private StudentUhil(){	
	}
	final static String fileName="students.dat"; 
	public static void loadStudent(ArrayList<StudentInfor> lstStudent){
		String[] strStus=FileUtil.read("students.dat").split("\n");
		if(strStus==null||strStus.length==0)
		{
			return;
		}
		for(int i=0;i<strStus.length;i++)
		{
			String[] strStu=strStus[i].split("\t");
			if(strStu==null||strStu.length!=5)
			{
				JOptionPane.showMessageDialog(null, "学生信息有误，请核对","提示",JOptionPane.WARNING_MESSAGE);
				continue;
			}
			StudentInfor stu=new StudentInfor(strStu[0], strStu[1], strStu[2], strStu[3],Float.parseFloat(strStu[4]));
			lstStudent.add(stu);
		}
	}
	public static void delect(int index,ArrayList<StudentInfor> lsStudents)
	{
		lsStudents.remove(index);
		save(lsStudents);
	}
	public static StudentInfor query(String number,ArrayList<StudentInfor> lsStudents){
		for(StudentInfor stu:lsStudents){
			if(number.equals(stu.getNumber())){
				return stu;
			}
		}
		 return null;
	}
	public static int add(StudentInfor stu,ArrayList<StudentInfor> lsStudents){
		if(query(stu.getNumber(), lsStudents)==null){
			lsStudents.add(stu);
			Collections.sort(lsStudents);
			save(lsStudents);//写回文件里面去
			return lsStudents.indexOf(stu);
		}
		else{
			return -1;
		}
	}
	public static void update(int index,StudentInfor stu,ArrayList<StudentInfor> lsStudents)
	{
		lsStudents.set(index, stu);
		Collections.sort(lsStudents);
		save(lsStudents);
	}
	private static void save(ArrayList<StudentInfor> lsStudents)
	{
		StringBuffer sb=new StringBuffer();
		for(StudentInfor stu:lsStudents){
			sb.append(stu.toString());
		}
		FileUtil.write(sb.toString(),fileName);
	}
}
