package com.sedisys.autogen.generator.model.java.converter.clazz.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;
import com.sedisys.autogen.generator.model.field.AccessLeveled.AccessLevel;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.field.SimpleFieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.clazz.EntityClassModel;
import com.sedisys.autogen.generator.model.java.clazz.repository.RepositoryModel;
import com.sedisys.autogen.generator.model.java.clazz.repository.SimpleRepositoryModel;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.converter.field.FieldConverter;
import com.sedisys.autogen.generator.model.java.type.SimpleClassTypeModel;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.method.SimpleMethodModel;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;


public class EntityTypeToRepositoryConverter{
	private static List<AnnotationModel> baseAnnotations;

	private String basePackage;
	private Class<? extends Object> baseRepositoryType;
	private boolean useQueryDSL = true;

	@Autowired
	private FieldConverter fieldConverter;

	static {
		baseAnnotations = new ArrayList<>();
		baseAnnotations.add(new SimpleAnnotationModel("org.springframework.stereotype.Repository"));
	}

	public EntityTypeToRepositoryConverter(){
	}

	public EntityTypeToRepositoryConverter(String basePackage, Class<? extends Object> baseRepositoryType){
		setBasePackage(basePackage);
		setBaseRepositoryType(baseRepositoryType);
	}

	public void setBasePackage(String basePackage){
		this.basePackage = basePackage;
	}

	public String getBasePackage(){
		return basePackage;
	}

	public boolean isUseQueryDSL(){
		return useQueryDSL;
	}

	public void setUseQueryDSL(boolean useQueryDSL){
		this.useQueryDSL = useQueryDSL;
	}

	public Class<? extends Object> getBaseRepositoryType(){
		return baseRepositoryType;
	}

	public void setBaseRepositoryType(Class<? extends Object> baseRepositoryType){
		this.baseRepositoryType = baseRepositoryType;
	}

	public FieldConverter getFieldConverter(){
		return fieldConverter;
	}

	public void setFieldConverter(FieldConverter fieldConverter){
		this.fieldConverter = fieldConverter;
	}

	private List<AnnotationModel> convertAnnotations(DomainModel entityType){
		List<AnnotationModel> returnAnnotations = new ArrayList<>();
		for (AnnotationModel annotation : baseAnnotations){
			returnAnnotations.add(annotation);
		}
		return returnAnnotations;
	}

	private List<MethodModel> convertMethods(DomainModel entityType){
		List<MethodModel> returnMethods = new ArrayList<>();

		SimpleMethodModel method;
		SimpleMethodModel additionalMethod;
		QualifiedNameTypeModel methodType;
		StringBuilder methodNameBuilder;
		for (FieldGrouping grouping : entityType.getFieldGroupings()){
			method = new SimpleMethodModel();
			method.setAccessLevel(AccessLevel.PUBLIC);
			methodType = new QualifiedNameTypeModel();
			switch (grouping.getType()){
				case NATURALID:
					methodType.setQualifiedName(entityType.getName());
					break;
				case UNIQUE:
					methodType.setQualifiedName(entityType.getName());
					break;
				case INDEXED:
					methodType.setQualifiedName("java.util.List");
					methodType.addGeneric(new QualifiedNameTypeModel(entityType.getName()));
					break;
			}
			methodNameBuilder = new StringBuilder();
			methodNameBuilder.append("findBy");
			for (FieldModel field : grouping.getFields()){
				methodNameBuilder.append(WordUtils.capitalize(field.getName())).append("And");
				try{
					method.addParameter(getFieldConverter().convertField(field));
				} catch (FieldConversionException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			methodNameBuilder.setLength(methodNameBuilder.length() - 3);
			method.setName(methodNameBuilder.toString());
			method.setAccessLevel(AccessLevel.PUBLIC);
			method.setType(methodType);

			returnMethods.add(method);

			additionalMethod = new SimpleMethodModel(method);
			additionalMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
			returnMethods.add(additionalMethod);

			if (isUseQueryDSL()){
				additionalMethod = new SimpleMethodModel(method);
				additionalMethod.addParameter(new SimpleFieldModel("com.mysema.query.types.Predicate", "predicate"));
				returnMethods.add(additionalMethod);

				additionalMethod = new SimpleMethodModel(additionalMethod);
				additionalMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
				returnMethods.add(additionalMethod);
			}
		}
		return returnMethods;
	}

	public RepositoryModel convertEntityClassModel(EntityClassModel entityType){
		SimpleRepositoryModel resultingModel = new SimpleRepositoryModel();
		resultingModel.setName(entityType.getName() + "Repository");
		resultingModel.setBasePackage(getBasePackage());
		resultingModel.setEntityType(entityType);

		SimpleClassTypeModel repositoryInterfaceType = new SimpleClassTypeModel(getBaseRepositoryType());
		repositoryInterfaceType.addGeneric(entityType);
		resultingModel.addInterface(repositoryInterfaceType);

		for (AnnotationModel annotation : convertAnnotations(entityType)){
			resultingModel.addAnnotation(annotation);
		}
		for (FieldGrouping fieldGrouping : entityType.getFieldGroupings()){
			resultingModel.addFieldGrouping(fieldGrouping);
		}
		for (MethodModel method : convertMethods(entityType)){
			resultingModel.addMethod(method);
		}
		return resultingModel;
	}

}
