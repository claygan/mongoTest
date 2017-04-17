package com.quest.commons;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceFactory implements ApplicationContextAware{
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ServiceFactory.context = applicationContext;
	}

	public static ApplicationContext getContext() {
		return ServiceFactory.context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName, Class<T> clazz){
		return (T)context.getBean(beanName);
	}
	
}
