package com.sedisys.autogen.generator.model.java.annotation;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleAnnotationModel extends AbstractAnnotationModel implements AttributedAnnotationModel{
	private String qualifiedAnnotationName;
	private Map<String, Object> attributes;
	private Set<TypeModel> referencedClasses;

	public SimpleAnnotationModel(){
		super();
		attributes = new LinkedHashMap<>();
		referencedClasses = new LinkedHashSet<>();
	}

	public SimpleAnnotationModel(String qualifiedAnnoationName){
		this();
		setQualifiedAnnotationName(qualifiedAnnoationName);
	}

	public SimpleAnnotationModel(AnnotationModel baseAnnotation){
		this();
		setQualifiedAnnotationName(baseAnnotation.getQualifiedAnnotationName());
		Map<String, Object> baseAttributes = baseAnnotation.getAttributes();
		for (String key : baseAttributes.keySet()){
			setAttribute(key, baseAttributes.get(key));
		}
		for (TypeModel referencedClass : baseAnnotation.getReferencedTypes()){
			addReferencedClass(referencedClass);
		}
	}

	@Override
	public String getQualifiedAnnotationName(){
		return qualifiedAnnotationName;
	}

	public void setQualifiedAnnotationName(String qualifiedAnnotationName){
		this.qualifiedAnnotationName = qualifiedAnnotationName;
		TypeModel annotationType = new QualifiedNameTypeModel(qualifiedAnnotationName);
		if (!getReferencedTypes().contains(annotationType)){
			addReferencedClass(annotationType);
		}
	}

	@Override
	public String getSimpleAnnotationName(){
		return qualifiedAnnotationName.substring(qualifiedAnnotationName.lastIndexOf('.') + 1);
	}

	@Override
	public void setAttribute(String key, Object value){
		attributes.put(key, value);
	}

	@Override
	public Object getAttribute(String key){
		return getAttributes().get(key);
	}

	@Override
	public Map<String, Object> getAttributes(){
		return attributes;
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		return referencedClasses;
	}

	public boolean containsReferencedClass(String ReferencedClass){
		return getReferencedTypes().contains(ReferencedClass);
	}

	public void addReferencedClass(TypeModel referencedClass){
		getReferencedTypes().add(referencedClass);
	}

	public void removeReferencedClass(String ReferencedClass){
		getReferencedTypes().remove(ReferencedClass);
	}

	public int getReferencedClassCount(){
		return getReferencedTypes().size();
	}

	@Override
	public String getNameSpace(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQualifiedName(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends TypeModel> getContainedTypes(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(){
		// TODO Auto-generated method stub
		return null;
	}
}
