package com.hibernateinfo.client;

import org.hibernate.Session;

import com.hibernateinfo.util.HibernateUtil;

public class HibernateCreateSessionFactoryClientTest {

	public static void main(String[] args) 
	{
		//Session object implements autoCloseable and -after java1.7- every object implements 
		//autoCloseable can be used as private resources in try catch block
		//you Don't need to close session in finally block 
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			//nativeQuery in oracle database
			String sql = "SELECT version FROM V$INSTANCE"; 
			String result = (String) session.createNativeQuery(sql).getSingleResult();
			System.out.println("My Oracle version is ::: ");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
