package Student;

import java.text.DecimalFormat;
import java.util.Random;
import UI.*;

public class Test {

public static void main(String[] args) {
	int count = 586;
	Random r=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<count;i++)
		{
			String number=(20160101+i)+"";
			String className="软件"+(1601+r.nextInt(15))+"班";
			String name="李四";
			String sex="女";
			if(r.nextInt()%2==0)
			{
				sex="男";
			}
			DecimalFormat df=new DecimalFormat("#.#");
			float avgScore=Float.parseFloat(df.format(100*Math.random()));
			StudentInfor stu=new StudentInfor(number, className, name, sex, avgScore);
			sb.append(stu.toString());
		}
		FileUtil.write(sb.toString(),"students.dat");
	}

}
