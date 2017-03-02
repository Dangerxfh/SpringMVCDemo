package com.ldu.blog.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionfactory;
	private static Session session;
	
	static{
		Configuration config=new Configuration().configure();
		StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		StandardServiceRegistry ssr=ssrb.build();
		config.buildSessionFactory(ssr);
		
	}
	//获取会话工厂sessionfactory
	public static SessionFactory getSessionfactory(){
		return sessionfactory;
	}
	//获取会话session
	public static Session getSession(){
		session=sessionfactory.openSession();
		return session;
	}
	
	//关闭session
	public static void  closeSession(Session session){
		if(session!=null)
			session.close();
	}
	
}
