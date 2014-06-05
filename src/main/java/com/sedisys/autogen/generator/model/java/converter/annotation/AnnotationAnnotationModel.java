package com.sedisys.autogen.generator.model.java.converter.annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;

import com.sedisys.autogen.generator.model.java.annotation.AbstractAnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.type.SimpleClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class AnnotationAnnotationModel extends AbstractAnnotationModel {
	private Annotation annotation;

	public AnnotationAnnotationModel(Annotation annotation){
		this.annotation = annotation;
	}

	public Annotation getAnnotation(){
		return annotation;
	}

	public void setAnnotation(Annotation annotation){
		this.annotation = annotation;
	}

	@Override
	public String getQualifiedAnnotationName(){
		return annotation.annotationType().getCanonicalName();
	}

	@Override
	public String getSimpleAnnotationName(){
		return annotation.annotationType().getSimpleName();
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> referencedClasses = new LinkedHashSet<>();
		referencedClasses.add(new SimpleClassTypeModel(getAnnotation().getClass()));
		Collection<Object> attributes = getAttributes().values();
		for (Object value : attributes){
			if (value instanceof Class){
				referencedClasses.add(new SimpleClassTypeModel(((Class<?>) value).getClass()));
			} else if (value instanceof Annotation){
				AnnotationAnnotationModel model = new AnnotationAnnotationModel((Annotation) value);
				referencedClasses.addAll(model.getReferencedTypes());
			} else if (value instanceof AnnotationModel){
				referencedClasses.addAll(((AnnotationModel) value).getReferencedTypes());
			}
		}
		return referencedClasses;
	}

	@Override
	public Map<String, Object> getAttributes(){
		return AnnotationUtils.getAnnotationAttributes(annotation);
	}

	@Override
	public String getNameSpace(){
		return getAnnotation().annotationType().getPackage().toString();
	}

	@Override
	public String getQualifiedName(){
		return getQualifiedAnnotationName();
	}

	@Override
	public List<? extends TypeModel> getContainedTypes(){
		List<TypeModel> containedTypes = new ArrayList<>();
		containedTypes.add(new SimpleClassTypeModel(getAnnotation().getClass()));
		Collection<Object> attributes = getAttributes().values();
		for (Object value : attributes){
			if (value instanceof Class){
				containedTypes.add(new SimpleClassTypeModel(((Class<?>) value).getClass()));
			} else if (value instanceof Annotation){
				AnnotationAnnotationModel model = new AnnotationAnnotationModel((Annotation) value);
				containedTypes.addAll(model.getReferencedTypes());
			} else if (value instanceof AnnotationModel){
				containedTypes.addAll(((AnnotationModel) value).getReferencedTypes());
			}
		}
		return containedTypes;
	}

	@Override
	public String getName(){
		return getSimpleAnnotationName();
	}

}
