package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *文件工具类
 *@author 罗飘
 */
public class FileUtil {
private FileUtil(){}
/**
 * 读取文件
 * @param fileName 要读取的文件名
 * @return 文件内容
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
 * 向文件写入内容
 * @param content 要写入的内容
 * @param fileName 要写入的文件名
 * @return 是否写入成功
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
