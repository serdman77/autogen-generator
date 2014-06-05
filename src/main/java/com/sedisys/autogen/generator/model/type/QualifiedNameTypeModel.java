package com.sedisys.autogen.generator.model.type;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QualifiedNameTypeModel extends AbstractTypeModel {
	private String qualifiedName;
	private List<TypeModel> generics;

	private String nameSpaceSeparator = ".";

	public QualifiedNameTypeModel(){
		generics = new ArrayList<>();
	}

	public QualifiedNameTypeModel(String qualifiedName){
		this();
		setQualifiedName(qualifiedName);
	}

	public QualifiedNameTypeModel(TypeModel baseType){
		this();
		setQualifiedName(baseType.getNameSpace() + getNameSpaceSeparator() + baseType.getName());
		for (TypeModel generic : generics){
			addGeneric(new QualifiedNameTypeModel(generic));
		}
	}

	@Override
	public String getName(){
		return qualifiedName.substring(getQualifiedName().lastIndexOf(getNameSpaceSeparator()) + 1);
	}

	@Override
	public String getNameSpace(){
		String qualifiedName = getQualifiedName();
		int lastSeparator = qualifiedName.lastIndexOf(getNameSpaceSeparator());
		if (lastSeparator == -1){
			return "";
		} else{
			return qualifiedName.substring(0, lastSeparator);
		}
	}

	public void setQualifiedName(String qualifiedName){
		this.qualifiedName = qualifiedName;
	}

	@Override
	public String getQualifiedName(){
		return qualifiedName;
	}

	public String getNameSpaceSeparator(){
		return nameSpaceSeparator;
	}

	public void setNameSpaceSeparator(String nameSpaceSeparator){
		this.nameSpaceSeparator = nameSpaceSeparator;
	}

	@Override
	public List<TypeModel> getContainedTypes(){
		return generics;
	}

	public TypeModel getGeneric(int index){
		return getContainedTypes().get(index);
	}

	public boolean containsGeneric(TypeModel Generic){
		return getContainedTypes().contains(Generic);
	}

	public void addGeneric(TypeModel Generic){
		getContainedTypes().add(Generic);
	}

	public void insertGeneric(int insertAt, TypeModel Generic){
		getContainedTypes().add(insertAt, Generic);
	}

	public void replaceGeneric(int replaceAt, TypeModel Generic){
		getContainedTypes().set(replaceAt, Generic);
	}

	public TypeModel removeGeneric(int removeAt){
		return getContainedTypes().remove(removeAt);
	}

	public void removeGeneric(TypeModel Generic){
		getContainedTypes().remove(Generic);
	}

	public int getGenericCount(){
		return getContainedTypes().size();
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> returnReferencedClasses = new LinkedHashSet<>();
		returnReferencedClasses.add(this);
		for (TypeModel generic : getContainedTypes()){
			returnReferencedClasses.addAll(generic.getReferencedTypes());
		}
		return returnReferencedClasses;
	}
}