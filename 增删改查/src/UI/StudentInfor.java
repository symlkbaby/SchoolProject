package UI;

public class StudentInfor implements Comparable<StudentInfor>{
      private String number;
      private String className;
      private String name;
      private String sex;
      private float avgScore;
      
      public StudentInfor(String number,String className,String name,String sex,float avgScore)
      {
    	  this.number=number;
    	  this.className=className;
    	  this.name=name;
    	  this.sex=sex;
    	  this.avgScore=avgScore;
      }
      
      public String getNumber(){
    	  return number;
      }
      public void setNumber(String number){
    	 this.number=number;
      }
      public String getClassName(){
    	  return className;
      }
      public void setClassName(String className){
    	 this.className=className;
      }
      public String getName(){
    	  return name;
      }
      public void setName(String name){
    	 this.name=name;
      }
      public String getSex(){
    	  return sex;
      }
      public void setSex(String sex){
    	 this.sex=sex;
      }
      public float getAvgScore(){
    	  return avgScore;
      }
      public void setAvgScore(float avgScore){
    	 this.avgScore=avgScore;
      }
      
      public String toString(){
    	  return number+"\t"+className+"\t"+name+"\t"+sex+"\t"+avgScore+"\n";
      }

	@Override
	public int compareTo(StudentInfor stu) {
		if(this.className.equals(stu.number)){
			return this.number.compareTo(stu.number);
		}else{
			return this.className.compareTo(stu.className);
		}
	}
}
