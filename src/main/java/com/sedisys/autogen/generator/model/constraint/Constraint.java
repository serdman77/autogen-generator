package com.sedisys.autogen.generator.model.constraint;

import java.util.Set;

import com.sedisys.autogen.generator.model.Named;

public interface Constraint extends Named{
	public Object getProperty(String propertyName);

	public Set<String> getPropertyNames();
}
