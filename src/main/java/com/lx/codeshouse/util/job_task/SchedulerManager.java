package com.lx.codeshouse.util.job_task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.Date;

public class SchedulerManager {

	private static Scheduler scheduler = null;
	
	private static SchedulerManager instance = null;

	private SchedulerManager() {
		SchedulerFactory schedFact = new StdSchedulerFactory();
		try {
			scheduler = (Scheduler) schedFact.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SchedulerManager getInstance()
	{
		if(null == instance)
		{
			instance = new SchedulerManager();
		}
		return instance;
	}
	
	/**
	 * ���ӵ�������������ʼ���ȣ�
	 * @param jobDetail
	 * @param trigger
	 * @return
	 */
	public Date scheduleJob(JobDetail jobDetail,Trigger trigger)
	{
		if(null != scheduler)
		{
			try {
				return scheduler.scheduleJob(jobDetail,trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ���ӵ�������������ʼ���ȣ�
	 * @param jobname
	 * @param jobclass
	 * @param expression
	 * @return
	 */
	public Date scheduleJob(String jobname,Class jobclass,String expression)
	{
		try {
			JobDetail jobDetail = new JobDetail(jobname,Scheduler.DEFAULT_GROUP,jobclass);
			CronTrigger trigger = new CronTrigger(jobname + "trigger",Scheduler.DEFAULT_GROUP,expression);
			return scheduleJob(jobDetail,trigger);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * ���ӵ�������������ʼ���ȣ�
	 * @param jobname
	 * @param jobclass
	 * @param expression
	 * @return
	 */
	public Date scheduleJob(Class jobclass,String expression)
	{
		try {
			JobDetail jobDetail = new JobDetail(jobclass.toString(),Scheduler.DEFAULT_GROUP,jobclass);
			CronTrigger trigger = new CronTrigger(jobclass.toString() + "trigger",Scheduler.DEFAULT_GROUP,expression);
			return scheduleJob(jobDetail,trigger);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ɾ����������
	 * @param jobDetail
	 * @return
	 */
	public boolean deleteJob(String jobDetail)
	{
		try {
			return scheduler.deleteJob(jobDetail,Scheduler.DEFAULT_GROUP);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void start()
	{
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �رյ�ǰ���н���
	 */
	public void end()
	{
		try {
			scheduler.shutdown(true);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void instanceSchedule(Class jobclass)
	{
			JobDetail jobDetail = new JobDetail(jobclass.toString(),Scheduler.DEFAULT_GROUP,jobclass);
			SimpleTrigger trigger = new SimpleTrigger(jobclass.toString()+"trigger",null,new Date(),null,0,0L);
			scheduleJob(jobDetail,trigger);
	}
	
}

