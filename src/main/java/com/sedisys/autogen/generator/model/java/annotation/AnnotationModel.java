package com.sedisys.autogen.generator.model.java.annotation;

import java.util.Map;

import com.sedisys.autogen.generator.model.type.References;
import com.sedisys.autogen.generator.model.type.TypeModel;

public interface AnnotationModel extends References, TypeModel{
	public String getQualifiedAnnotationName();

	public String getSimpleAnnotationName();

	public Map<String, Object> getAttributes();

	public Map<String, String> getDisplayAttributes();
}
