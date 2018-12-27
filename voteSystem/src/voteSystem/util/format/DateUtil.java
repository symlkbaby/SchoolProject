package voteSystem.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڵ�һЩ��ʽ����
 * @author lk
 *
 */
public class DateUtil {

	//�ӳ�����ת�����ַ�����ʽ������
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.format(new Date(time));
	}
	public static String toLongtDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		return df.format(new Date(time));
	}
	//���ַ�����ʽ������ת���ɳ�����
	public static Long toLong(String time) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.parse(time).getTime();//�õ�һ�����ڶ���
	}
}






