package com.sedisys.autogen.generator.model.method;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleMethodModel implements MethodModel {

	private List<AnnotationModel> annotations;
	private AccessLevel accessLevel;
	private TypeModel type;
	private String name;
	private List<FieldModel> parameters;
	private String methodBody;

	public SimpleMethodModel(){
		annotations = new ArrayList<>();
		parameters = new ArrayList<>();
	}

	public SimpleMethodModel(MethodModel methodModel){
		this();
		for (AnnotationModel annotation : methodModel.getAnnotations()){
			addAnnotation(annotation);
		}
		setAccessLevel(methodModel.getAccessLevel());
		setType(new QualifiedNameTypeModel(methodModel.getType()));
		setName(methodModel.getName());
		for (FieldModel parameter : methodModel.getParameters()){
			addParameter(parameter);
		}
		setMethodBody(methodModel.getMethodBody());
	}

	@Override
	public List<AnnotationModel> getAnnotations(){
		return annotations;
	}

	public AnnotationModel getAnnotation(int index){
		return getAnnotations().get(index);
	}

	public boolean containsAnnotation(AnnotationModel Annotation){
		return getAnnotations().contains(Annotation);
	}

	public void addAnnotation(AnnotationModel Annotation){
		getAnnotations().add(Annotation);
	}

	public void insertAnnotation(int insertAt, AnnotationModel Annotation){
		getAnnotations().add(insertAt, Annotation);
	}

	public void replaceAnnotation(int replaceAt, AnnotationModel Annotation){
		getAnnotations().set(replaceAt, Annotation);
	}

	public AnnotationModel removeAnnotation(int removeAt){
		return getAnnotations().remove(removeAt);
	}

	public void removeAnnotation(AnnotationModel Annotation){
		getAnnotations().remove(Annotation);
	}

	public int getAnnotationCount(){
		return getAnnotations().size();
	}

	public void setType(TypeModel type){
		this.type = type;
	}

	public void setType(String qualifiedType){
		setType(new QualifiedNameTypeModel(qualifiedType));
	}

	@Override
	public TypeModel getType(){
		return type;
	}

	public void setAccessLevel(AccessLevel accessLevel){
		this.accessLevel = accessLevel;
	}

	@Override
	public AccessLevel getAccessLevel(){
		return accessLevel;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public List<FieldModel> getParameters(){
		return parameters;
	}

	public FieldModel getParameter(int index){
		return getParameters().get(index);
	}

	public boolean containsParameter(FieldModel parameter){
		return getParameters().contains(parameter);
	}

	public void addParameter(FieldModel parameter){
		getParameters().add(parameter);
	}

	public void insertParameter(int insertAt, FieldModel parameter){
		getParameters().add(insertAt, parameter);
	}

	public void replaceParameter(int replaceAt, FieldModel parameter){
		getParameters().set(replaceAt, parameter);
	}

	public FieldModel removeParameter(int removeAt){
		return getParameters().remove(removeAt);
	}

	public void removeParameter(FieldModel parameter){
		getParameters().remove(parameter);
	}

	public int getParameterCount(){
		return getParameters().size();
	}

	public void setMethodBody(String methodBody){
		this.methodBody = methodBody;
	}

	@Override
	public String getMethodBody(){
		return methodBody;
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> returnReferencedClasses = new LinkedHashSet<>();
		for (AnnotationModel annotation : getAnnotations()){
			returnReferencedClasses.addAll(annotation.getReferencedTypes());
		}
		if (getType() != null){
			returnReferencedClasses.addAll(getType().getReferencedTypes());
		}
		for (FieldModel parameter : getParameters()){
			returnReferencedClasses.addAll(parameter.getReferencedTypes());
		}
		return returnReferencedClasses;
	}
}