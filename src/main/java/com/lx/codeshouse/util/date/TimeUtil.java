package com.lx.codeshouse.util.date;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {
		  
	  /** �����磺"yyyy-MM-dd HH:mm:ss"�ַ�����ʽת����ΪDate���� **/
      public static Date parseDateTime(String source) {
    	  Date date = null;
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  try {
			date = sdf.parse(source);
		  } catch (ParseException e) {
			e.printStackTrace();
		  }
		  return date;
      }
      
      
      /** �����磺"yyyy-MM-dd HH:mm"�ַ�����ʽת����ΪDate���� **/
      public static Date parseDateTime1(String source) {
    	  Date date = null;
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	  try {
			date = sdf.parse(source);
		  } catch (ParseException e) {
			e.printStackTrace();
		  }
		  return date;
      }
      
      /** �����磺"yyyyMMdd HH:mm:ss"�ַ�����ʽת����ΪDate���� **/
      public static Date parseDateTime2(String source) {
    	  Date date = null;
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    	  try {
			date = sdf.parse(source);
		  } catch (ParseException e) {
			e.printStackTrace();
		  }
		  return date;
      }
      
      /** �����磺"yyyy-MM-dd HHmm"�ַ�����ʽת����ΪDate���� **/
      public static Date parseDateTime3(String source) {
    	  Date date = null;
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
    	  try {
			date = sdf.parse(source);
		  } catch (ParseException e) {
			e.printStackTrace();
		  }
		  return date;
      }
 
      
      /**���������͸�ʽ�������� yyyy-MM-dd HH:mm:ss ���ַ���**/
      public static String formatDateTime(Date date) {
    	  
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  String s = sdf.format(date);
    	  return s;
    	  
      }
      /**���������͸�ʽ�������� yyyy-MM-dd HH:mm ���ַ���**/
      public static String formatDateTime1(Date date) {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	  String s = sdf.format(date);
    	  return s;
      }
      
      
      /**���������͸�ʽ�������� yyyyMMddHHmm ���ַ���**/
      public static String formatDateTime2(Date date) {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    	  String s = sdf.format(date);
    	  
    	  return s;
      }
      
      /**���������͸�ʽ�������� yyyyMMdd ���ַ���**/
      public static String formatDate(Date date) {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	  String s = sdf.format(date);
    	  
    	  return s;
      }
      
      /**���������͸�ʽ�������� yyyy-MM-dd ���ַ���**/
      public static String formatDate1(Date date) {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	  String s = sdf.format(date);
    	  
    	  return s;
      }
      
      /**���������͸�ʽ�������� HH:mm ���ַ���**/
      public static String formatTime(Date date) {
    	  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	  String s = sdf.format(date);
    	  return s;
      }
      
      /**������ yyyy-MM-dd ���ַ�����ʽ������������**/
      public static Date parseDate1(String s) {
    	  Date date = null;
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	  
    	  try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  return date;
      }
      
      /**������ yyyyMMdd ���ַ�����ʽ������������**/
      public static Date parseDate(String s) {
    	  Date date = null;
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	  
    	  try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  return date;
      }

        
      
	 /**
	  *  ��ȡ�����ѹ�������
	  * @return
	  */
  	public static List<Date> getPassedDaysOfThisWeek()
  	{
  		List<Date> result = new ArrayList<Date>();
  		Calendar c = Calendar.getInstance();
  		int daynum = c.get(Calendar.DAY_OF_WEEK);
  		for(int i=0;i<daynum;i++)
  		{
  			Date d = new Date(c.getTimeInMillis()-i*24*60*60*1000);
  			Calendar thec = Calendar.getInstance();
  			thec.setTime(d);
  			int day_of_week = thec.get(Calendar.DAY_OF_WEEK);
  			if(day_of_week == 1 || day_of_week == 7 )
  			{
  				continue;
  			}
  			result.add(d);
  		}
  		return result;
  	}
  	/**
  	 * ��ȡ���������ܵ�����
  	 * @param date
  	 * @return
  	 */
  	public static List<Date> getDaysOfThisWeek(Date date)
  	{
  		List<Date> result = new ArrayList<Date>();
  		Calendar c = Calendar.getInstance();
  		c.setTime(date);
  		int daynum = c.get(Calendar.DAY_OF_WEEK);
  		long time = c.getTimeInMillis();
  		for(int i=2;i<=6;i++)
  		{
  			long sub = (i-daynum)*24*60*60*1000;
  			Date d = new Date(time + sub);
  			result.add(d);
  		}
  		return result;
  	}
  	
  	/**
  	 * ��ȡ�����ѹ�������
  	 * @return
  	 */
  	public static List<Date> getPassedDaysOfThisMonth()
  	{
  		List<Date> result = new ArrayList<Date>();
  		Calendar c = Calendar.getInstance();
  		int daynum = c.get(Calendar.DAY_OF_MONTH);
  		for(int i=0;i<daynum;i++)
  		{
  			long sub = i*24*60*60*1000;
  			Date d = new Date(c.getTimeInMillis() - sub);
  			Calendar thec = Calendar.getInstance();
  			thec.setTime(d);
  			int day_of_week = thec.get(Calendar.DAY_OF_WEEK);
  			if(day_of_week == 1 || day_of_week == 7 )
  			{
  				continue;
  			}
  			result.add(d);
  		}
  		return result;
  	}

	/**
  	 * ��ȡ���������·ݵ�����
  	 * @param date
  	 * @return
  	 */
  	public static List<Date> getDaysOfThisMonth(Date date)
  	{
  		//TODO 
  		List<Date> result = new ArrayList<Date>();
  	  	    
  	    Date firstDay = TimeUtil.getFirstDayOfMonth(date);
  	    Date lastDay = TimeUtil.getLastDayOfMonth(date);
  	    
  	    for(long k = firstDay.getTime();k <= lastDay.getTime();k = k+ 24*60*60*1000) {
  	    	result.add(new Date(k));
  	    }
  	    
  		return result;
  	}
  	/**
  	 * ��ȡ�ϸ��µ�һ��
  	 * @param date
  	 * @return
  	 */
    public static Date getFirstDayOfPreviousMonth(Date date){     

        Calendar c = Calendar.getInstance();   
        c.setTime(date);
        c.set(Calendar.DATE,1);//��Ϊ��ǰ�µ�1��   
        c.add(Calendar.MONTH,-1);//��һ���£���Ϊ���µ�1��   
        //lastDate.add(Calendar.DATE,-1);//��ȥһ�죬��Ϊ�������һ��   
          
        return c.getTime();     
     }   
        
     /**
      * ��ȡ��ǰ�µ�һ��   
      * @param date
      * @return
      */
     public static Date getFirstDayOfMonth(Date date){     

    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
            
        String s = sdf.format(c.getTime());
        Date d = null;
        try {
			d = sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
       
        return d;
         
     } 
     
     /**
      * ��ȡ��ǰ�����һ��   
      * @param date
      * @return
      */
     public static Date getLastDayOfMonth(Date date){     
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE,-1);
       
        String s = sdf.format(c.getTime());
        Date d = null;
        try {
			d = sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
       
        return d;
     }  
     
   	/**
   	 * ��ȡÿ�ܵ�һ��
   	 * @param date
   	 * @return
   	 */
     public static Date getFirstDayOfWeek(Date date){     

         Calendar c = Calendar.getInstance();   
         c.setTime(date);
         c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//ÿ������һ
         
           
         return c.getTime();     
      } 
     
    	/**
    	 * ��ȡ���ܵ�һ��
    	 * @param date
    	 * @return
    	 */
      public static Date getFirstDayOfPreviousWeek(Date date){     

          Calendar c = Calendar.getInstance();   
          c.setTime(date);
          c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//ÿ������һ
          c.add(Calendar.WEEK_OF_YEAR, -1);//��һ����
          
            
          return c.getTime();     
       } 
      
      public static Date getDayOfDayDiff(Date date,int diff) {
    	   Calendar c = Calendar.getInstance();   
           c.setTime(date);
           c.add(Calendar.DATE, diff);
           
           return c.getTime();
      }
      
      public static Date getDayOfMonthDiff(Date date,int diff) {
    	   Calendar c = Calendar.getInstance();   
           c.setTime(date);
           c.add(Calendar.MONTH, diff);
           
           return c.getTime();
      }
      
      public static Date getDayOfYearDiff(Date date,int diff) {
    	  Calendar c = Calendar.getInstance();   
          c.setTime(date);
          c.add(Calendar.YEAR, diff);
          
          return c.getTime();
      }
      
     public static void main(String args[]) throws ParseException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date d = sdf.parse("2011-2-7");
    	/*System.out.println(TimeUtil.getFirstDayOfPreviousMonth(d));
    	System.out.println(TimeUtil.getFirstDayOfMonth(d));
    	
    	System.out.println(TimeUtil.getFirstDayOfPreviousWeek(d));
    	System.out.println(TimeUtil.getLastDayOfMonth(d));
    	System.out.println(TimeUtil.getLastDayOfMonth(new Date())); */
    	
    	/*List<Date> dateList = TimeUtil.getDaysOfThisMonth(new Date()) ;
    	for(Date date: dateList) {
    		System.out.println(date);
    	}*/
    	
    	System.out.println(TimeUtil.getDayOfDayDiff(new Date(), -5));
    	System.out.println(TimeUtil.getDayOfMonthDiff(new Date(), -3));
    	System.out.println(TimeUtil.getDayOfYearDiff(new Date(), -1));
     }

     public static Date getYestorday(Date date) {
    	  /*Calendar c =Calendar.getInstance();
    	  c.setTime(date);
    	  c.roll(Calendar.DATE,-1);
    	  
    	  return c.getTime();????*/
    	 return new Date(date.getTime()-24*60*60*1000);
     }
      
}
