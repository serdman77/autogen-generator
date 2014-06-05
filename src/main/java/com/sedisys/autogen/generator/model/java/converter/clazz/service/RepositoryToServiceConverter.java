package com.sedisys.autogen.generator.model.java.converter.clazz.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import com.sedisys.autogen.generator.model.constraint.SimpleConstraint;
import com.sedisys.autogen.generator.model.field.AccessLeveled.AccessLevel;
import com.sedisys.autogen.generator.model.field.SimpleFieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.clazz.repository.RepositoryModel;
import com.sedisys.autogen.generator.model.java.clazz.service.RepositoriedServiceModel;
import com.sedisys.autogen.generator.model.java.clazz.service.SimpleRepositoriedServiceModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;
import com.sedisys.autogen.generator.model.java.converter.field.FieldConverter;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.SimpleClassFieldModel;
import com.sedisys.autogen.generator.model.method.BasicGetterMethod;
import com.sedisys.autogen.generator.model.method.BasicSetterMethod;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.method.PassThroughMethodModel;
import com.sedisys.autogen.generator.model.method.SimpleMethodModel;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;


public class RepositoryToServiceConverter {
	private static List<AnnotationModel> baseAnnotations;

	private String servicePackage;

	private FieldConverter fieldConverter;
	private ConstraintAnnotationConverter constraintConverter;

	static {
		baseAnnotations = new ArrayList<>();
		baseAnnotations.add(new SimpleAnnotationModel("org.springframework.stereotype.Service"));
		baseAnnotations.add(new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional"));
	}

	public void setServicePackage(String servicePackage){
		this.servicePackage = servicePackage;
	}

	public String getServicePackage(){
		return servicePackage;
	}

	public FieldConverter getFieldConverter(){
		return fieldConverter;
	}

	public void setFieldConverter(FieldConverter fieldConverter){
		this.fieldConverter = fieldConverter;
	}



	public ConstraintAnnotationConverter getConstraintConverter(){
		return constraintConverter;
	}

	public void setConstraintConverter(ConstraintAnnotationConverter constraintConverter){
		this.constraintConverter = constraintConverter;
	}

	private List<AnnotationModel> convertAnnotations(RepositoryModel repository){
		List<AnnotationModel> returnAnnotations = new ArrayList<>(baseAnnotations.size());
		for (AnnotationModel annotation : baseAnnotations){
			returnAnnotations.add(annotation);
		}

		return returnAnnotations;
	}

	private List<MethodModel> createRepositoryMethods(RepositoryModel repository){
		List<MethodModel> repositoryMethods = new ArrayList<>();

		SimpleMethodModel tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setType(repository.getDomainModel().getNameSpace());
		tempMethod.setName("find" + WordUtils.capitalize(repository.getDomainModel().getName()));
		SimpleAnnotationModel tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.addParameter(new SimpleFieldModel("Long", "id"));
		tempMethod.setMethodBody("return getRepository().findOne(id);");
		repositoryMethods.add(tempMethod);

		if (repository.isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			tempMethod.setType(repository.getDomainModel().getNameSpace());
			tempMethod.setName("find" + WordUtils.capitalize(repository.getDomainModel().getName()));
			tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
			tempAnnotation.setAttribute("readOnly", true);
			tempMethod.addAnnotation(tempAnnotation);
			tempMethod.addParameter(new SimpleFieldModel("com.mysema.query.types.Predicate", "predicate"));
			tempMethod.setMethodBody("return getRepository().findOne(predicate);");
			repositoryMethods.add(tempMethod);
		}

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		QualifiedNameTypeModel returnType = new QualifiedNameTypeModel("java.util.Iterable");
		returnType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
		tempMethod.setType(returnType);
		tempMethod.setName("findAll" + WordUtils.capitalize(repository.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.setMethodBody("return getRepository().findAll();");
		repositoryMethods.add(tempMethod);

		if (repository.isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			returnType = new QualifiedNameTypeModel("java.util.Iterable");
			returnType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
			tempMethod.setType(returnType);
			tempMethod.setName("findAll" + WordUtils.capitalize(repository.getDomainModel().getName()));
			tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
			tempAnnotation.setAttribute("readOnly", true);
			tempMethod.addAnnotation(tempAnnotation);
			tempMethod.addParameter(new SimpleFieldModel("com.mysema.query.types.Predicate", "predicate"));
			tempMethod.setMethodBody("return getRepository().findAll(predicate);");
			repositoryMethods.add(tempMethod);
		}

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		returnType = new QualifiedNameTypeModel("java.util.List");
		returnType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
		tempMethod.setType(returnType);
		tempMethod.setName("findAll" + WordUtils.capitalize(repository.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		QualifiedNameTypeModel parameterType = new QualifiedNameTypeModel("java.util.Iterable");
		parameterType.addGeneric(new QualifiedNameTypeModel("Long"));
		tempMethod.addParameter(new SimpleFieldModel(parameterType, "ids"));
		tempMethod.setMethodBody("return getRepository().findAll(ids);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		returnType = new QualifiedNameTypeModel("org.springframework.data.domain.Page");
		returnType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
		tempMethod.setType(returnType);
		tempMethod.setName("findAll" + WordUtils.capitalize(repository.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
		tempMethod.setMethodBody("return getRepository().findAll(pageable);");
		repositoryMethods.add(tempMethod);

		if (repository.isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			returnType = new QualifiedNameTypeModel("org.springframework.data.domain.Page");
			returnType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
			tempMethod.setType(returnType);
			tempMethod.setName("findAll" + WordUtils.capitalize(repository.getDomainModel().getName()));
			tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
			tempAnnotation.setAttribute("readOnly", true);
			tempMethod.addAnnotation(tempAnnotation);
			tempMethod.addParameter(new SimpleFieldModel("com.mysema.query.types.Predicate", "predicate"));
			tempMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
			tempMethod.setMethodBody("return getRepository().findAll(predicate, pageable);");
			repositoryMethods.add(tempMethod);
		}

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setType("long");
		tempMethod.setName("get" + WordUtils.capitalize(repository.getDomainModel().getName()) + "Count");
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.setMethodBody("return getRepository().count();");
		repositoryMethods.add(tempMethod);

		if (repository.isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			tempMethod.setType("long");
			tempMethod.setName("get" + WordUtils.capitalize(repository.getDomainModel().getName()) + "Count");
			tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
			tempAnnotation.setAttribute("readOnly", true);
			tempMethod.addAnnotation(tempAnnotation);
			tempMethod.addParameter(new SimpleFieldModel("com.mysema.query.types.Predicate", "predicate"));
			tempMethod.setMethodBody("return getRepository().count(predicate);");
			repositoryMethods.add(tempMethod);
		}

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setType("boolean");
		tempMethod.setName("has" + WordUtils.capitalize(repository.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.addParameter(new SimpleFieldModel("Long", "id"));
		tempMethod.setMethodBody("return getRepository().exists(id);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setType(repository.getDomainModel().getNameSpace());
		tempMethod.setName("save");
		tempMethod.addParameter(new SimpleFieldModel(repository.getDomainModel().getNameSpace(), "entity"));
		tempMethod.setMethodBody("return getRepository().save(entity);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		returnType = new QualifiedNameTypeModel("java.util.List");
		returnType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
		tempMethod.setType(returnType);
		tempMethod.setName("save");
		parameterType = new QualifiedNameTypeModel("java.util.List");
		parameterType.addGeneric(new QualifiedNameTypeModel(repository.getDomainModel().getNameSpace()));
		tempMethod.addParameter(new SimpleFieldModel(parameterType, "entities"));
		tempMethod.setMethodBody("return getRepository().save(entities);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setName("delete");
		tempMethod.addParameter(new SimpleFieldModel("Long", "id"));
		tempMethod.setMethodBody("getRepository().delete(id);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setName("delete");
		tempMethod.addParameter(new SimpleFieldModel(repository.getDomainModel().getNameSpace(), "entity"));
		tempMethod.setMethodBody("getRepository().delete(entity);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setName("delete");
		tempMethod.addParameter(new SimpleFieldModel("java.util.Iterable", "entities"));
		tempMethod.setMethodBody("getRepository().deleteInBatch(entities);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setName("deleteAll");
		tempMethod.setMethodBody("getRepository().deleteAllInBatch();");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setName("flush");
		tempMethod.setMethodBody("getRepository().flush();");
		repositoryMethods.add(tempMethod);

		return repositoryMethods;
	}

	private List<MethodModel> convertMethods(RepositoryModel repository){
		List<MethodModel> returnMethods = new ArrayList<>(createRepositoryMethods(repository));

		for (MethodModel method : repository.getMethods()){
			returnMethods.add(new PassThroughMethodModel("getRepository().", method));
		}
		return returnMethods;
	}

	private List<ClassFieldModel> createFields(SimpleRepositoriedServiceModel resultingService, RepositoryModel repository){
		List<ClassFieldModel> fields = new ArrayList<>();
		SimpleClassFieldModel repositoryField = new SimpleClassFieldModel();
		repositoryField.setAccessLevel(AccessLevel.PROTECTED);
		repositoryField.setType(repository);
		repositoryField.setName("repository");
		SimpleConstraint constraint = new SimpleConstraint();
		constraint.setName("Autowired");
		try{
			for (AnnotationModel annotation : getConstraintConverter().convertConstraint(constraint)){
				repositoryField.addAnnotation(annotation);
			}
		} catch (ConstraintConversionException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fields.add(repositoryField);
		resultingService.insertMethod(0, new BasicSetterMethod(repositoryField));
		resultingService.insertMethod(0, new BasicGetterMethod(repositoryField));

		return fields;
	}

	public RepositoriedServiceModel convertRepository(RepositoryModel repository){
		SimpleRepositoriedServiceModel resultingServiceModel = new SimpleRepositoriedServiceModel();
		resultingServiceModel.setRepositoryModel(repository);
		resultingServiceModel.setName("Default" + repository.getDomainModel().getName() + "Service");
		TypeModel interfaceModel = new QualifiedNameTypeModel(getServicePackage() + '.' + repository.getDomainModel().getName() + "Service");
		resultingServiceModel.addInterface(interfaceModel);
		for (AnnotationModel annotation : convertAnnotations(repository)){
			resultingServiceModel.addAnnotation(annotation);
		}
		createFields(resultingServiceModel, repository);
		for (MethodModel method : convertMethods(repository)){
			resultingServiceModel.addMethod(method);
		}
		return resultingServiceModel;
	}
}
