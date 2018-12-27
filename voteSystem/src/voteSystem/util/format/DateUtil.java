package voteSystem.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期的一些格式处理
 * @author lk
 *
 */
public class DateUtil {

	//从长整型转换成字符串格式的日期
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.format(new Date(time));
	}
	public static String toLongtDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		return df.format(new Date(time));
	}
	//将字符串格式的日期转换成长整型
	public static Long toLong(String time) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.parse(time).getTime();//得到一个日期对象
	}
}






