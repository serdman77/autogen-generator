package com.sedisys.autogen.generator.model.java.converter.annotation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.attribute.ConstraintPropertyToAnnotationAttributeConverter;

public class MappingConstraintAnnotationConverter implements ConstraintAnnotationConverter{
	private ConstraintPropertyToAnnotationAttributeConverter constraintPropertyConverter;

	private Map<String, String> constraintToAnnotationMappings;

	public ConstraintPropertyToAnnotationAttributeConverter getConstraintPropertyConverter(){
		return constraintPropertyConverter;
	}

	public void setConstraintPropertyConverter(ConstraintPropertyToAnnotationAttributeConverter constraintPropertyConverter){
		this.constraintPropertyConverter = constraintPropertyConverter;
	}

	public MappingConstraintAnnotationConverter(){
		setConstraintToAnnotationMappings(new LinkedHashMap<String, String>());
	}

	public Map<String, String> getConstraintToAnnotationMappings(){
		return constraintToAnnotationMappings;
	}

	public void setConstraintToAnnotationMappings(Map<String, String> constraintToAnnotationMappings){
		this.constraintToAnnotationMappings = constraintToAnnotationMappings;
	}

	public String getConstraintToAnnotationMapping(String key){
		return getConstraintToAnnotationMappings().get(key);
	}

	public boolean containsConstraintToAnnotationMappingKey(String key){
		return getConstraintToAnnotationMappings().containsKey(key);
	}

	public boolean containsConstraintToAnnotationMappingValue(String value){
		return getConstraintToAnnotationMappings().containsValue(value);
	}

	public void addConstraintToAnnotationMapping(String key, String value){
		getConstraintToAnnotationMappings().put(key, value);
	}

	public String removeConstraintToAnnotationMapping(String key){
		return getConstraintToAnnotationMappings().remove(key);
	}

	public int getConstraintToAnnotationMappingCount(){
		return getConstraintToAnnotationMappings().size();
	}

	@Override
	public List<AnnotationModel> convertConstraint(Constraint constraint){
		List<AnnotationModel> resultingAnnotations = new ArrayList<>();
		String annotationQualifiedName;
		for (String propertyName : constraint.getPropertyNames()){
			annotationQualifiedName = getConstraintToAnnotationMapping(propertyName);
			if (annotationQualifiedName!=null){
				SimpleAnnotationModel annotation = new SimpleAnnotationModel(annotationQualifiedName);
				if (getConstraintPropertyConverter().convertConstraintProperties(annotation, constraint)){
					resultingAnnotations.add(annotation);
				}
			}
		}
		return resultingAnnotations;
	}


}
