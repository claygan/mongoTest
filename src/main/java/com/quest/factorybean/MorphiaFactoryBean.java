package com.quest.factorybean;

import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class MorphiaFactoryBean extends AbstractFactoryBean<Morphia> {
	/**
	 * 要扫描并映射的包
	 */
	private String[] mapPackages;

	/**
	 * 要映射的类
	 */
	private String[] mapClasses;

	/**
	 * 扫描包时，是否忽略不映射的类 这里按照Morphia的原始定义，默认设为false
	 */
	private boolean ignoreInvalidClasses;

	private String[] converters;

	@Override
	protected Morphia createInstance() throws Exception {
		Morphia m = new Morphia();
		if (mapPackages != null) {
			for (String packageName : mapPackages) {
				//我们告诉Morphia去指定的package中寻找所有标记了@Entity的类，以及所有在类中的映射元数据
				m.mapPackage(packageName, ignoreInvalidClasses);
			}
		}
		if (mapClasses != null) {
			for (String entityClass : mapClasses) {
				m.map(Class.forName(entityClass));
			}
		}
		if(converters!=null){
			for (String converterClazz : converters) {
				Class clazz = Class.forName(converterClazz);
				m.getMapper().getConverters().addConverter(clazz);
			}
		}
		return m;
	}

	@Override
	public Class<?> getObjectType() {
		return Morphia.class;
	}

	public String[] getMapPackages() {
		return mapPackages;
	}

	public void setMapPackages(String[] mapPackages) {
		this.mapPackages = mapPackages;
	}

	public String[] getMapClasses() {
		return mapClasses;
	}

	public void setMapClasses(String[] mapClasses) {
		this.mapClasses = mapClasses;
	}

	public boolean isIgnoreInvalidClasses() {
		return ignoreInvalidClasses;
	}

	public void setIgnoreInvalidClasses(boolean ignoreInvalidClasses) {
		this.ignoreInvalidClasses = ignoreInvalidClasses;
	}

	public String[] getConverters() {
		return converters;
	}

	public void setConverters(String[] converters) {
		this.converters = converters;
	}
}