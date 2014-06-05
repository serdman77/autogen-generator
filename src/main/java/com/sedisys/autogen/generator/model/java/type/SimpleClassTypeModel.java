package com.sedisys.autogen.generator.model.java.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.type.AbstractTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleClassTypeModel extends AbstractTypeModel implements MutableClassTypeModel {
	private Class<? extends Object> clazz;
	private List<TypeModel> generics;

	public SimpleClassTypeModel(){
		setGenerics(new ArrayList<TypeModel>());
	}

	public SimpleClassTypeModel(Class<? extends Object> clazz){
		this();
		setClazz(clazz);
	}

	public void setGenerics(List<TypeModel> generics){
		this.generics = generics;
	}

	@Override
	public List<TypeModel> getGenerics(){
		return generics;
	}

	@Override
	public TypeModel getGeneric(int index){
		return getGenerics().get(index);
	}

	@Override
	public boolean containsGeneric(TypeModel generic){
		return getGenerics().contains(generic);
	}

	@Override
	public void addGeneric(TypeModel generic){
		getGenerics().add(generic);
	}

	@Override
	public void insertGeneric(int insertAt, TypeModel generic){
		getGenerics().add(insertAt, generic);
	}

	@Override
	public void replaceGeneric(int replaceAt, TypeModel generic){
		getGenerics().set(replaceAt, generic);
	}

	@Override
	public TypeModel removeGeneric(int removeAt){
		return getGenerics().remove(removeAt);
	}

	@Override
	public void removeGeneric(TypeModel generic){
		getGenerics().remove(generic);
	}

	public int getGenericCount(){
		return getGenerics().size();
	}

	public Class<? extends Object> getClazz(){
		return clazz;
	}

	public void setClazz(Class<? extends Object> clazz){
		this.clazz = clazz;
	}

	@Override
	public String getNameSpace(){
		return getClazz().getPackage().getName();
	}

	@Override
	public String getQualifiedName(){
		return getClazz().getCanonicalName();
	}

	@Override
	public List<? extends TypeModel> getContainedTypes(){
		return getGenerics();
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> referencedTypes = new HashSet<>();
		referencedTypes.add(new SimpleClassTypeModel(getClazz()));
		for (TypeModel type : getContainedTypes()){
			referencedTypes.add(type);
			referencedTypes.addAll(type.getContainedTypeReferences());
		}
		return referencedTypes;
	}

	@Override
	public String getName(){
		return getClazz().getSimpleName();
	}

	@Override
	public Class<? extends Object> getTypeClass(){
		return getClazz();
	}

	@Override
	public String toString(){
		return getName();
	}
}
