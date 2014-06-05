package com.sedisys.autogen.generator.model.java.converter.annotation.attribute;

import java.lang.reflect.Field;
import java.util.Set;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.AttributedAnnotationModel;

public class MatchingConstraintPropertyToAnnotationAttributeConverter implements ConstraintPropertyToAnnotationAttributeConverter {

	@Override
	public boolean convertConstraintProperties(AttributedAnnotationModel annotation, Constraint baseConstraint){
		try{
			Class<? extends Object> annotationClass = Class.forName(annotation.getQualifiedAnnotationName());
			Set<String> propertyNames = baseConstraint.getPropertyNames();
			for (Field field : annotationClass.getFields()){
				if (!propertyNames.contains(field.getName())){
					return false;
				}
				annotation.setAttribute(field.getName(), baseConstraint.getProperty(field.getName()));
			}
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
