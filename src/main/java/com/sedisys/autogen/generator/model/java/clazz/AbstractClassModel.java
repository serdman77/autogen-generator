package com.sedisys.autogen.generator.model.java.clazz;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.type.AbstractTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public abstract class AbstractClassModel<TFieldType extends ClassFieldModel> extends AbstractTypeModel implements ClassModel<TFieldType> {

	protected String basePackage;
	protected String name;

	protected List<TypeModel> generics;
	protected List<AnnotationModel> annotations;
	protected List<TypeModel> interfaces;
	protected List<TFieldType> fields;
	protected List<MethodModel> methods;

	public AbstractClassModel(){
		generics = new ArrayList<>();
		annotations = new ArrayList<>();
		interfaces = new ArrayList<>();
		methods = new ArrayList<>();
	}

	public String getBasePackage(){
		return basePackage;
	}

	public void setBasePackage(String basePackage){
		this.basePackage = basePackage;
	}

	@Override
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
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
	public List<TypeModel> getContainedTypes(){
		return generics;
	}

	public List<TypeModel> getGenerics(){
		return generics;
	}

	public void setGenerics(List<TypeModel> generics){
		this.generics = generics;
	}

	public TypeModel getGeneric(int index){
		return getContainedTypes().get(index);
	}

	public boolean containsGeneric(TypeModel generic){
		return getContainedTypes().contains(generic);
	}

	public void addGeneric(TypeModel generic){
		getContainedTypes().add(generic);
	}

	public void insertGeneric(int insertAt, TypeModel generic){
		getContainedTypes().add(insertAt, generic);
	}

	public void replaceGeneric(int replaceAt, TypeModel generic){
		getContainedTypes().set(replaceAt, generic);
	}

	public TypeModel removeGeneric(int removeAt){
		return getContainedTypes().remove(removeAt);
	}

	public void removeGeneric(TypeModel generic){
		getContainedTypes().remove(generic);
	}

	public int getGenericCount(){
		return getContainedTypes().size();
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
	public List<TypeModel> getInterfaces(){
		return interfaces;
	}

	public void setInterfaces(List<TypeModel> interfaces){
		this.interfaces = interfaces;
	}

	public TypeModel getInterface(int index){
		return getInterfaces().get(index);
	}

	public boolean containsInterface(TypeModel Interface){
		return getInterfaces().contains(Interface);
	}

	public void addInterface(TypeModel Interface){
		getInterfaces().add(Interface);
	}

	public void insertInterface(int insertAt, TypeModel Interface){
		getInterfaces().add(insertAt, Interface);
	}

	public void replaceInterface(int replaceAt, TypeModel Interface){
		getInterfaces().set(replaceAt, Interface);
	}

	public TypeModel removeInterface(int removeAt){
		return getInterfaces().remove(removeAt);
	}

	public void removeInterface(TypeModel Interface){
		getInterfaces().remove(Interface);
	}

	public int getInterfaceCount(){
		return getInterfaces().size();
	}

	public List<String> getFieldNames(){
		List<String> fieldNames = new ArrayList<>();
		for (ClassFieldModel field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	public ClassFieldModel getField(String fieldName){
		for (ClassFieldModel field : fields){
			if (field.getName().equals(fieldName)){
				return field;
			}
		}
		return null;
	}

	@Override
	public List<TFieldType> getFields(){
		return fields;
	}

	public void setFields(List<TFieldType> fields){
		this.fields = fields;
	}

	public TFieldType getField(int index){
		return getFields().get(index);
	}

	public boolean containsField(TFieldType Field){
		return getFields().contains(Field);
	}

	public void addField(TFieldType Field){
		getFields().add(Field);
	}

	public void insertField(int insertAt, TFieldType Field){
		getFields().add(insertAt, Field);
	}

	public void replaceField(int replaceAt, TFieldType Field){
		getFields().set(replaceAt, Field);
	}

	public TFieldType removeField(int removeAt){
		return getFields().remove(removeAt);
	}

	public void removeField(TFieldType Field){
		getFields().remove(Field);
	}

	public int getFieldCount(){
		return getFields().size();
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

	public boolean containsMethod(MethodModel Method){
		return getMethods().contains(Method);
	}

	public void addMethod(MethodModel Method){
		getMethods().add(Method);
	}

	public void insertMethod(int insertAt, MethodModel Method){
		getMethods().add(insertAt, Method);
	}

	public void replaceMethod(int replaceAt, MethodModel Method){
		getMethods().set(replaceAt, Method);
	}

	public MethodModel removeMethod(int removeAt){
		return getMethods().remove(removeAt);
	}

	public void removeMethod(MethodModel Method){
		getMethods().remove(Method);
	}

	public int getMethodCount(){
		return getMethods().size();
	}

	@Override
	public Set<TypeModel> getReferencedTypes(){
		Set<TypeModel> returnReferencedClasses = new LinkedHashSet<>();
		for (AnnotationModel annotation : getAnnotations()){
			returnReferencedClasses.addAll(annotation.getReferencedTypes());
		}
		for (TypeModel generic : getGenerics()){
			returnReferencedClasses.add(generic);
			returnReferencedClasses.addAll(generic.getContainedTypeReferences());
		}
		for (TFieldType field : getFields()){
			returnReferencedClasses.addAll(field.getReferencedTypes());
		}
		for (TypeModel interfaceModel : getInterfaces()){
			returnReferencedClasses.addAll(interfaceModel.getReferencedTypes());
		}
		for (MethodModel method : getMethods()){
			returnReferencedClasses.addAll(method.getReferencedTypes());
		}

		return returnReferencedClasses;
	}
}
