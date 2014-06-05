package com.sedisys.autogen.generator.model.java.converter.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.Annotated;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;

public class DefaultConstraintAnnotationConverter implements ConstraintAnnotationConverter{
	private static Map<String, Class<? extends Annotation>[]> mappings;

	static {
		mappings = new LinkedHashMap<>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AnnotationModel> convertConstraint(Constraint toConvert) throws ConstraintConversionException{
		if (toConvert instanceof Annotated){
			return ((Annotated) toConvert).getAnnotations();
		}
		Class<? extends Annotation> annotationClasses[] = mappings.get(toConvert.getName());
		if (annotationClasses != null){
			annotationClasses = new Class[1];
			try{
				annotationClasses[0] = (Class<? extends Annotation>) Class.forName("javax.validation.constraints." + toConvert.getName());
			} catch (ClassNotFoundException e){
				try{
					annotationClasses[0] = (Class<? extends Annotation>) Class.forName("org.hibernate.validator.constraints." + toConvert.getName());
				} catch (ClassNotFoundException e1){
					throw new ConstraintConversionException(toConvert, this, e1);
				}
			}
			List<AnnotationModel> returnAnnotations = new ArrayList<>();
			for (int i=0;i<annotationClasses.length;i++){
				SimpleAnnotationModel annotationModel = new SimpleAnnotationModel();
				annotationModel.setQualifiedAnnotationName(annotationClasses.getClass().getCanonicalName());
				List<Field> annotationFields = Arrays.asList(annotationClasses[i].getDeclaredFields());
				List<String> annotationFieldNames = Lists.transform(annotationFields, new Function<Field, String>(){

					@Override
					public String apply(Field input){
						return input.getName();
					}
				});
				Set<String> convertProperties = toConvert.getPropertyNames();
				for (Object key : CollectionUtils.intersection(annotationFieldNames, convertProperties)){
					annotationModel.setAttribute(key.toString(), toConvert.getProperty(key.toString()));
				}
				returnAnnotations.add(annotationModel);
			}
			return returnAnnotations;
		}
		return new ArrayList<>();
	}
}
