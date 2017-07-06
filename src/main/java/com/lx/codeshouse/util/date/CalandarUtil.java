package com.lx.codeshouse.util.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ��Calandar������չ�������ض�����Ҫ
 * @author yangangzhao
 *
 */
public class CalandarUtil {

	/**
	 * ��ȡ�����ѹ�������
	 * @return
	 */
	public static List<Date> getPassedInWeek()
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
	
	public static List<Date> getDateInWeek(Date date)
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
	public static List<Date> getPassedInMonth()
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
	 * ��ȡ���daynum��date
	 * @param d
	 * @param daynum
	 * @return
	 */
	public static Date subDate(Date d,int daynum)
	{
		long sub = daynum*24*60*60*1000;
		return new Date(d.getTime()-sub);
	}
	
	public static void main(String[] args)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		CalandarUtil cu = new CalandarUtil();
		Calendar c = Calendar.getInstance();
		List<Date> list = cu.getPassedInWeek();
		
		for(Date d : list)
		{
			System.out.println(format.format(d));
		}
		System.out.println("==================================");
		Date current = new Date();
		List<Date> list1 = cu.getDateInWeek(current);
		for(Date d : list1) {
			System.out.println(format.format(d));
		}
		System.out.println("==================================");
		List<Date> list2 = cu.getPassedInMonth();
		for(Date d : list2) {
			System.out.println(format.format(d));
		}
		
	}

}
