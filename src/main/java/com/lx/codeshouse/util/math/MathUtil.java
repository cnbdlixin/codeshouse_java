package com.lx.codeshouse.util.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * ��ѧ���㹤����
 * @author lx
 *
 */
public class MathUtil {

	private static final int DEF_DIV_SCALE = 10;
	
	private static NumberFormat format = NumberFormat.getPercentInstance(Locale.CHINA);

	/**
	 * �ӷ�����
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * ��������
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * �˷����� 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * ��������
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * ������������
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static String getPercentFormat(double v)
	{
			v = round(v,4);
			format.setMaximumFractionDigits(2);
			format.setMinimumFractionDigits(2);
			return format.format(v);
	}
	
	public static String getPercentFormat(double v,int v1)
	{
		v = round(v,v1+2);
		format.setMaximumFractionDigits(v1);
		format.setMinimumFractionDigits(v1);
		return format.format(v);
	}
	
	public static double add(double[] vs)
	{
		double v = 0;
		for(int i=0,n=vs.length;i<n;i++)
		{
			v = add(v,vs[i]);
		}
		return v;
	}
	
	public static double add(List<Double> vs)
	{
		double v = 0;
		for(int i=0,n=vs.size();i<n;i++)
		{
			v = add(v,vs.get(i).doubleValue());
		}
		return v;
	}

	public static String formatNormal(double v,int fraction)
	{
		StringBuffer sb = new StringBuffer("0.");
		DecimalFormat format = null;
		if(fraction == 0)
		{
			format = new DecimalFormat();
		}else
		{
			for(int i=0;i<fraction;i++)
			{
				sb.append("0");
			}
			format = new DecimalFormat(sb.toString());
		}
		format.setGroupingUsed(false);
		return format.format(v);
	}
	
	public static String formatNormal(double v)
	{
		StringBuffer sb = new StringBuffer("0.");
		DecimalFormat format = null;
		int fraction =3;
		if(fraction == 0)
		{
			format = new DecimalFormat();
		}else
		{
			for(int i=0;i<fraction;i++)
			{
				sb.append("0");
			}
			format = new DecimalFormat(sb.toString());
		}
		format.setGroupingUsed(false);
		return format.format(v);
	}
	
	public static double wanFomat(double d) {
		   
         String s = MathUtil.formatNormal((d/10000.0),2);
         Double wan = 0.0;
         try {
            wan = Double.parseDouble(s);
         } catch (Exception e) {
        	 
         }
		 return wan;
		 
	 }
	

	public static void main(String[] args) {
		double d = 1;
		System.out.println(MathUtil.getPercentFormat(d,0));
		System.out.println(MathUtil.div(2.0, 3.0, 2));
		System.out.println(MathUtil.formatNormal(6.333333, 3));
		System.out.println(MathUtil.getPercentFormat(0.33, 3));
		System.out.println(MathUtil.wanFomat(11133.44));
		System.out.println(MathUtil.round(137758.68,0));
		System.out.println(Math.round(137758.68));
		
	}
}
