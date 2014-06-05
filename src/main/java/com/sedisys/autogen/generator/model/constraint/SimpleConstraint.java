package com.sedisys.autogen.generator.model.constraint;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SimpleConstraint implements Constraint{
  String name;

	private Map<String, Object> properties;

	public SimpleConstraint(){
		properties = new LinkedHashMap<>();
	}

	public SimpleConstraint(Constraint constraint){
		this();
		setName(constraint.getName());
		for (String propertyName : getPropertyNames()){
			addProperty(propertyName, constraint.getProperty(propertyName));
		}
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public Set<String> getPropertyNames(){
		return getProperties().keySet();
	}

	public void addProperty(String key, Object value){
		getProperties().put(key, value);
	}

	@Override
	public Object getProperty(String key){
		return getProperties().get(key);
	}

	public Map<String, Object> getProperties(){
		return properties;
	}


}
