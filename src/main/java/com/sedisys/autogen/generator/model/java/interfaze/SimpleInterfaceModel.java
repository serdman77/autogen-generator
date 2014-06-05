package com.sedisys.autogen.generator.model.java.interfaze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.type.AbstractTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class SimpleInterfaceModel extends AbstractTypeModel implements InterfaceModel{
	private String name;
	private String basePackage;
	private List<AnnotationModel> annotations;
	private List<TypeModel> generics;
	private List<TypeModel> interfaces;
	private List<MethodModel> methods;

	public SimpleInterfaceModel(){
		annotations = new ArrayList<>();
		generics = new ArrayList<>();
		interfaces = new ArrayList<>();
		methods = new ArrayList<>();
	}

	public SimpleInterfaceModel(TypeModel baseType){
		this();
		setName(baseType.getName());
		setBasePackage(baseType.getNameSpace());
	}

	@Override
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getBasePackage(){
		return basePackage;
	}

	public void setBasePackage(String packageName){
		basePackage = packageName;
	}

	@Override
	public String getNameSpace(){
		return getBasePackage();
	}

	@Override
	public String getQualifiedName(){
		return getBasePackage() + '.' + getName();
	}

	@Override
	public List<AnnotationModel> getAnnotations(){
		return annotations;
	}

	public void setAnnotations(List<AnnotationModel> annotations){
		this.annotations = annotations;
	}

	public AnnotationModel getAnnotation(int index){
		return getAnnotations().get(index);
	}

	public boolean containsAnnotation(AnnotationModel annotation){
		return getAnnotations().contains(annotation);
	}

	public void addAnnotation(AnnotationModel annotation){
		getAnnotations().add(annotation);
	}

	public void insertAnnotation(int insertAt, AnnotationModel annotation){
		getAnnotations().add(insertAt, annotation);
	}

	public void replaceAnnotation(int replaceAt, AnnotationModel annotation){
		getAnnotations().set(replaceAt, annotation);
	}

	public AnnotationModel removeAnnotation(int removeAt){
		return getAnnotations().remove(removeAt);
	}

	public void removeAnnotation(AnnotationModel annotation){
		getAnnotations().remove(annotation);
	}

	public int getAnnotationCount(){
		return getAnnotations().size();
	}

	@Override
	public List<TypeModel> getGenerics(){
		return generics;
	}

	public void setGenerics(List<TypeModel> generics){
		this.generics = generics;
	}

	public TypeModel getGeneric(int index){
		return getGenerics().get(index);
	}

	public boolean containsGeneric(TypeModel generic){
		return getGenerics().contains(generic);
	}

	public void addGeneric(TypeModel generic){
		getGenerics().add(generic);
	}

	public void insertGeneric(int insertAt, TypeModel generic){
		getGenerics().add(insertAt, generic);
	}

	public void replaceGeneric(int replaceAt, TypeModel generic){
		getGenerics().set(replaceAt, generic);
	}

	public TypeModel removeGeneric(int removeAt){
		return getGenerics().remove(removeAt);
	}

	public void removeGeneric(TypeModel generic){
		getGenerics().remove(generic);
	}

	public int getGenericCount(){
		return getGenerics().size();
	}

	@Override
	public List<TypeModel> getInterfaces(){
		return interfaces;
	}

	public void setInterfaces(List<TypeModel> interfaces){
		this.interfaces = interfaces;
	}

	public TypeModel getInterface(int index){
		return getInterfaces().get(index);
	}

	public boolean containsInterface(TypeModel interfaze){
		return getInterfaces().contains(interfaze);
	}

	public void addInterface(TypeModel interfaze){
		getInterfaces().add(interfaze);
	}

	public void insertInterface(int insertAt, TypeModel interfaze){
		getInterfaces().add(insertAt, interfaze);
	}

	public void replaceInterface(int replaceAt, TypeModel interfaze){
		getInterfaces().set(replaceAt, interfaze);
	}

	public TypeModel removeInterface(int removeAt){
		return getInterfaces().remove(removeAt);
	}

	public void removeInterface(TypeModel interfaze){
		getInterfaces().remove(interfaze);
	}

	public int getInterfaceCount(){
		return getInterfaces().size();
	}

	@Override
	public List<MethodModel> getMethods(){
		return methods;
	}

	public void setMethods(List<MethodModel> methods){
		this.methods = methods;
	}

	public MethodModel getMethod(int index){
		return getMethods().get(index);
	}

	public boolean containsMethod(MethodModel method){
		return getMethods().contains(method);
	}

	public void addMethod(MethodModel method){
		getMethods().add(method);
	}

	public void insertMethod(int insertAt, MethodModel method){
		getMethods().add(insertAt, method);
	}

	public void replaceMethod(int replaceAt, MethodModel method){
		getMethods().set(replaceAt, method);
	}

	public MethodModel removeMethod(int removeAt){
		return getMethods().remove(removeAt);
	}

	public void removeMethod(MethodModel method){
		getMethods().remove(method);
	}

	public int getMethodCount(){
		return getMethods().size();
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> referencedTypes = new HashSet<>();
		for (AnnotationModel annotation : getAnnotations()){
			referencedTypes.addAll(annotation.getReferencedTypes());
		}
		for (TypeModel generic : getGenerics()){
			referencedTypes.add(generic);
			referencedTypes.addAll(generic.getContainedTypeReferences());
		}
		for (TypeModel interfaze : getInterfaces()){
			referencedTypes.addAll(interfaze.getReferencedTypes());
		}
		for (MethodModel method : getMethods()){
			referencedTypes.addAll(method.getReferencedTypes());
		}
		return referencedTypes;
	}

	@Override
	public List<? extends TypeModel> getContainedTypes(){
		return getAnnotations();
	}

	@Override
	public String toString(){
		return getName();
	}
}
