package com.sedisys.autogen.generator.model.java.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.field.SimpleAbstractEntityFieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleEntityClassFieldModel extends SimpleAbstractEntityFieldModel implements EntityClassFieldModel{
	private List<AnnotationModel> annotations;

	public SimpleEntityClassFieldModel(){
		super();
		annotations = new ArrayList<>();
	}

	public SimpleEntityClassFieldModel(TypeModel type, String name){
		this();
		setType(type);
		setName(name);
	}

	public SimpleEntityClassFieldModel(String qualifiedType, String name){
		this(new QualifiedNameTypeModel(qualifiedType), name);
	}

	public SimpleEntityClassFieldModel(FieldModel baseModel, TypeModel type){
		super(baseModel, type);
		annotations = new ArrayList<>();
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
		Set<TypeModel> referencedClasses = super.getReferencedTypes();
		for (AnnotationModel annotation : getAnnotations()){
			referencedClasses.addAll(annotation.getReferencedTypes());
		}
		return referencedClasses;
	}
}
