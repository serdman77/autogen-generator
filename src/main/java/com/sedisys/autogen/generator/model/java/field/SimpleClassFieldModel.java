package com.sedisys.autogen.generator.model.java.field;

import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.field.SimpleFieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleClassFieldModel extends SimpleFieldModel implements MutableAnnotationsClassFieldModel{
	private List<AnnotationModel> annotations;

	public SimpleClassFieldModel(){
		super();
	}

	public SimpleClassFieldModel(FieldModel field, TypeModel type){
		super(field, type);
	}

	@Override
	public List<AnnotationModel> getAnnotations(){
		return annotations;
	}

	@Override
	public AnnotationModel getAnnotation(int index){
		return getAnnotations().get(index);
	}

	@Override
	public boolean containsAnnotation(AnnotationModel annotation){
		return getAnnotations().contains(annotation);
	}

	@Override
	public void addAnnotation(AnnotationModel annotation){
		getAnnotations().add(annotation);
	}

	@Override
	public void insertAnnotation(int insertAt, AnnotationModel annotation){
		getAnnotations().add(insertAt, annotation);
	}

	@Override
	public void replaceAnnotation(int replaceAt, AnnotationModel annotation){
		getAnnotations().set(replaceAt, annotation);
	}

	@Override
	public AnnotationModel removeAnnotation(int removeAt){
		return getAnnotations().remove(removeAt);
	}

	@Override
	public void removeAnnotation(AnnotationModel annotation){
		getAnnotations().remove(annotation);
	}

	@Override
	public int getAnnotationCount(){
		return getAnnotations().size();
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> referencedTypes = super.getReferencedTypes();
		for (AnnotationModel annotation : getAnnotations()){
			referencedTypes.addAll(annotation.getReferencedTypes());
		}
		return referencedTypes;
	}
}
