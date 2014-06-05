package com.sedisys.autogen.generator.model.java.converter.domain.annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.Annotated;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;

public class AnnotationModelConstraint implements Constraint, Annotated {

	private AnnotationModel annotation;

	public AnnotationModelConstraint(AnnotationModel annotation){
		setAnnotation(annotation);
	}

	public AnnotationModel getAnnotation(){
		return annotation;
	}

	public void setAnnotation(AnnotationModel annotation){
		this.annotation = annotation;
	}

	@Override
	public String getName(){
		return getAnnotation().getSimpleAnnotationName();
	}

	@Override
	public List<AnnotationModel> getAnnotations(){
		List<AnnotationModel> returnAnnotations = new ArrayList<>();
		returnAnnotations.add(getAnnotation());
		return returnAnnotations;
	}

	public Map<String, Object> getProperties(){
		return getAnnotation().getAttributes();
	}

	@Override
	public Object getProperty(String propertyName){
		return getProperties().get(propertyName);
	}

	@Override
	public Set<String> getPropertyNames(){
		return getProperties().keySet();
	}
}
