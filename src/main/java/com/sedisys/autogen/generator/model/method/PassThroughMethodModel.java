package com.sedisys.autogen.generator.model.method;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class PassThroughMethodModel implements MethodModel {
	protected String passThroughTarget;
	protected MethodModel baseMethod;

	private List<AnnotationModel> annotations;
	private AccessLevel accessLevel;

	public PassThroughMethodModel(){
		super();
		annotations = new ArrayList<>();
		setAccessLevel(AccessLevel.PUBLIC);
	}

	public PassThroughMethodModel(String passThroughTarget, MethodModel baseMethod){
		this();
		setPassThroughTarget(passThroughTarget);
		setBaseMethod(baseMethod);
	}

	public String getPassThroughTarget(){
		return passThroughTarget;
	}

	public void setPassThroughTarget(String passThroughTarget){
		this.passThroughTarget = passThroughTarget;
	}

	public MethodModel getBaseMethod(){
		return baseMethod;
	}

	public void setBaseMethod(MethodModel baseMethod){
		this.baseMethod = baseMethod;

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
	@Override
	public TypeModel getType(){
		return getBaseMethod().getType();
	}

	public void setAccessLevel(AccessLevel accessLevel){
		this.accessLevel = accessLevel;
	}

	@Override
	public AccessLevel getAccessLevel(){
		return accessLevel;
	}

	@Override
	public String getName(){
		return getBaseMethod().getName();
	}

	@Override
	public List<FieldModel> getParameters(){
		return getBaseMethod().getParameters();
	}

	@Override
	public String getMethodBody(){
		return getPassThroughTarget() + '.' + getBaseMethod().getMethodBody();
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
