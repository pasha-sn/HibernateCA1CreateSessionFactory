package com.hibernateinfo.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil 
{
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	
	//Static block is executed when class loads
	//sessionFactory is very expensive object
	//static block is common for all clients
	//Runs every time when the instance of the class is created
	static 
	{
		try {
			if (sessionFactory == null) {
				
				//create standardServiceRegistry
				standardServiceRegistry = new StandardServiceRegistryBuilder()
						.configure()
						.build();
				
				//create MetadataSources
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				
				//create Metadata
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				
				//create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} 
		} catch (Exception e) {
			if(standardServiceRegistry !=null)
			{
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
		
	}
	//create static Utility Factory method to return sessionFactory
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;		
	}
}
