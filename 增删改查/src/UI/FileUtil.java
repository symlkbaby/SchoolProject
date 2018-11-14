package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *�ļ�������
 *@author ��Ʈ
 */
public class FileUtil {
private FileUtil(){}
/**
 * ��ȡ�ļ�
 * @param fileName Ҫ��ȡ���ļ���
 * @return �ļ�����
 */

public static String read(String fileName)
{
	StringBuffer sbContent=new StringBuffer();
	BufferedReader br=null;
	try {
		 br=new BufferedReader(new FileReader(fileName));
		 String line=null;
		 try {
			while((line=br.readLine())!=null)
			 {
				 sbContent.append(line+"\n");
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}finally{
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return sbContent.toString();
}
/*
 * ���ļ�д������
 * @param content Ҫд�������
 * @param fileName Ҫд����ļ���
 * @return �Ƿ�д��ɹ�
 */
public static boolean write(String content,String fileName){
	BufferedWriter bw=null;
	try {
		bw = new BufferedWriter(new FileWriter(fileName));
		bw.write(content);
		return true;
	} catch (IOException e) {
		e.printStackTrace();
	}finally
	{
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return false;
}
}
