package com.sedisys.autogen.generator.model.java.converter.clazz.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import com.sedisys.autogen.generator.model.constraint.SimpleConstraint;
import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.domain.field.DomainFieldModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;
import com.sedisys.autogen.generator.model.field.AccessLeveled.AccessLevel;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.field.SimpleFieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.clazz.controller.ControllerModel;
import com.sedisys.autogen.generator.model.java.clazz.controller.SimpleControllerModel;
import com.sedisys.autogen.generator.model.java.clazz.service.RepositoriedServiceModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.converter.field.FieldConverter;
import com.sedisys.autogen.generator.model.java.field.SimpleClassFieldModel;
import com.sedisys.autogen.generator.model.java.interfaze.SimpleInterfaceModel;
import com.sedisys.autogen.generator.model.method.BasicGetterMethod;
import com.sedisys.autogen.generator.model.method.BasicSetterMethod;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.method.SimpleMethodModel;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;


public class RepositoriedServiceToControllerConverter {
	private static List<AnnotationModel> baseAnnotations;

	private String controllerPackage;

	private FieldConverter fieldConverter;
	private ConstraintAnnotationConverter constraintConverter;

	static {
		baseAnnotations = new ArrayList<>();
		baseAnnotations.add(new SimpleAnnotationModel("org.springframework.stereotype.Controller"));
	}

	public void setControllerPackage(String controllerPackage){
		this.controllerPackage = controllerPackage;
	}

	public String getControllerPackage(){
		return controllerPackage;
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

	private TypeModel extractServiceInterface(RepositoriedServiceModel serviceModel){
		for (TypeModel interfaze : serviceModel.getInterfaces()){
			if (interfaze.getName().endsWith("Service")){
				return interfaze;
			}
		}
		return null;
	}

	public List<AnnotationModel> convertAnnotations(RepositoriedServiceModel serviceModel){
		List<AnnotationModel> returnAnnotations = new ArrayList<>();
		for (AnnotationModel annotation : baseAnnotations){
			returnAnnotations.add(annotation);
		}

		SimpleAnnotationModel requestMappingAnnotation = new SimpleAnnotationModel("org.springframework.web.bind.annotation.RequestMapping");
		requestMappingAnnotation.setAttribute("value", "/" + serviceModel.getName().toLowerCase());
		return returnAnnotations;
	}

	private List<MethodModel> convertCrudMethods(RepositoriedServiceModel serviceModel){
		List<MethodModel> repositoryMethods = new ArrayList<>();

		SimpleMethodModel tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setType(serviceModel.getDomainModel().getQualifiedName());
		tempMethod.setName("find" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
		SimpleAnnotationModel tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.addParameter(new SimpleFieldModel("Long", "id"));
		tempMethod.setMethodBody("return getRepository().findOne(id);");
		repositoryMethods.add(tempMethod);

		if (serviceModel.getRepositoryModel().isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			tempMethod.setType(serviceModel.getDomainModel().getQualifiedName());
			tempMethod.setName("find" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
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
		returnType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
		tempMethod.setType(returnType);
		tempMethod.setName("findAll" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.setMethodBody("return getRepository().findAll();");
		repositoryMethods.add(tempMethod);

		if (serviceModel.getRepositoryModel().isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			returnType = new QualifiedNameTypeModel("java.util.Iterable");
			returnType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
			tempMethod.setType(returnType);
			tempMethod.setName("findAll" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
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
		returnType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
		tempMethod.setType(returnType);
		tempMethod.setName("findAll" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
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
		returnType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
		tempMethod.setType(returnType);
		tempMethod.setName("findAll" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
		tempMethod.setMethodBody("return getRepository().findAll(pageable);");
		repositoryMethods.add(tempMethod);

		if (serviceModel.getRepositoryModel().isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			returnType = new QualifiedNameTypeModel("org.springframework.data.domain.Page");
			returnType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
			tempMethod.setType(returnType);
			tempMethod.setName("findAll" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
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
		tempMethod.setName("get" + WordUtils.capitalize(serviceModel.getDomainModel().getName()) + "Count");
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.setMethodBody("return getRepository().count();");
		repositoryMethods.add(tempMethod);

		if (serviceModel.getRepositoryModel().isUseQueryDSL()){
			tempMethod = new SimpleMethodModel();
			tempMethod.setAccessLevel(AccessLevel.PUBLIC);
			tempMethod.setType("long");
			tempMethod.setName("get" + WordUtils.capitalize(serviceModel.getDomainModel().getName()) + "Count");
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
		tempMethod.setName("has" + WordUtils.capitalize(serviceModel.getDomainModel().getName()));
		tempAnnotation = new SimpleAnnotationModel("org.springframework.transaction.annotation.Transactional");
		tempAnnotation.setAttribute("readOnly", true);
		tempMethod.addAnnotation(tempAnnotation);
		tempMethod.addParameter(new SimpleFieldModel("Long", "id"));
		tempMethod.setMethodBody("return getRepository().exists(id);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		tempMethod.setType(serviceModel.getDomainModel().getQualifiedName());
		tempMethod.setName("save");
		tempMethod.addParameter(new SimpleFieldModel(serviceModel.getDomainModel().getQualifiedName(), "entity"));
		tempMethod.setMethodBody("return getRepository().save(entity);");
		repositoryMethods.add(tempMethod);

		tempMethod = new SimpleMethodModel();
		tempMethod.setAccessLevel(AccessLevel.PUBLIC);
		returnType = new QualifiedNameTypeModel("java.util.List");
		returnType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
		tempMethod.setType(returnType);
		tempMethod.setName("save");
		parameterType = new QualifiedNameTypeModel("java.util.List");
		parameterType.addGeneric(new QualifiedNameTypeModel(serviceModel.getDomainModel().getQualifiedName()));
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
		tempMethod.addParameter(new SimpleFieldModel(serviceModel.getDomainModel().getQualifiedName(), "entity"));
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

	public List<MethodModel> convertMethods(RepositoriedServiceModel serviceModel){
		List<MethodModel> returnMethods = new ArrayList<>();


		returnMethods.addAll(convertCrudMethods(serviceModel));
		DomainModel domain = serviceModel.getDomainModel();

		SimpleMethodModel method;
		SimpleMethodModel additionalMethod;
		QualifiedNameTypeModel methodType;
		StringBuilder methodNameBuilder;
		StringBuilder methodBodyBuilder;
		StringBuilder additionalMethodBodyBuilder;
		for (FieldGrouping grouping : domain.getFieldGroupings()){
			method = new SimpleMethodModel();
			method.setAccessLevel(AccessLevel.PUBLIC);
			methodType = new QualifiedNameTypeModel();
			switch (grouping.getType()){
				case NATURALID:
					methodType.setQualifiedName(domain.getName());
					break;
				case UNIQUE:
					methodType.setQualifiedName(domain.getName());
					break;
				case INDEXED:
					methodType.setQualifiedName("java.util.List");
					methodType.addGeneric(new QualifiedNameTypeModel(domain.getName()));
					break;
			}
			methodNameBuilder = new StringBuilder();
			methodBodyBuilder = new StringBuilder();
			methodNameBuilder.append("findBy");
			for (FieldModel field : grouping.getFields()){
				methodNameBuilder.append(WordUtils.capitalize(field.getName())).append("And");
				try{
					method.addParameter(getFieldConverter().convertField(field));
				} catch (FieldConversionException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				methodBodyBuilder.append(field.getName()).append(", ");
			}
			methodNameBuilder.setLength(methodNameBuilder.length() - 3);
			method.setName(methodNameBuilder.toString());
			methodBodyBuilder.setLength(methodBodyBuilder.length() - 2);
			methodBodyBuilder.insert(0, '(').insert(0, method.getName()).insert(0, "return getRepository().").append(");");
			method.setMethodBody(methodBodyBuilder.toString());
			method.setAccessLevel(AccessLevel.PUBLIC);
			method.setType(methodType);

			returnMethods.add(method);

			additionalMethod = new SimpleMethodModel(method);
			additionalMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
			additionalMethodBodyBuilder = new StringBuilder(methodBodyBuilder);
			methodBodyBuilder.insert(additionalMethodBodyBuilder.length() - 1, ", pageable");
			additionalMethod.setMethodBody(additionalMethodBodyBuilder.toString());
			returnMethods.add(additionalMethod);

			if (serviceModel.getRepositoryModel().isUseQueryDSL()){
				additionalMethod = new SimpleMethodModel(method);
				additionalMethod.addParameter(new SimpleFieldModel("com.mysema.query.types.Predicate", "predicate"));
				additionalMethodBodyBuilder = new StringBuilder(methodBodyBuilder);
				methodBodyBuilder.insert(additionalMethodBodyBuilder.length() - 1, ", predicate");
				additionalMethod.setMethodBody(additionalMethodBodyBuilder.toString());
				returnMethods.add(additionalMethod);

				additionalMethod = new SimpleMethodModel(additionalMethod);
				additionalMethod.addParameter(new SimpleFieldModel("org.springframework.data.domain.Pageable", "pageable"));
				additionalMethodBodyBuilder = new StringBuilder(additionalMethodBodyBuilder);
				methodBodyBuilder.insert(additionalMethodBodyBuilder.length() - 1, ", pageable");
				additionalMethod.setMethodBody(additionalMethodBodyBuilder.toString());
				returnMethods.add(additionalMethod);
			}
		}
		return returnMethods;
	}

	private void createServiceField(SimpleControllerModel resultingController, TypeModel serviceInterface){
		SimpleClassFieldModel serviceField = new SimpleClassFieldModel();
		serviceField.setAccessLevel(AccessLevel.PROTECTED);
		serviceField.setName(WordUtils.uncapitalize(serviceInterface.getName()));
		serviceField.setType(serviceInterface);
		SimpleConstraint constraint = new SimpleConstraint();
		constraint.setName("AutoWired");
		try{
			for (AnnotationModel annotation : getConstraintConverter().convertConstraint(constraint)){
				serviceField.addAnnotation(annotation);
			}
		} catch (ConstraintConversionException e){
			e.printStackTrace();
		}
		resultingController.insertMethod(0, new BasicSetterMethod(serviceField));
		resultingController.insertMethod(0, new BasicGetterMethod(serviceField));
	}

	private void createFields(SimpleControllerModel resultingController, RepositoriedServiceModel serviceModel){
		TypeModel serviceInterface = extractServiceInterface(serviceModel);
		createServiceField(resultingController, serviceInterface);
		for (DomainFieldModel field : serviceModel.getDomainModel().getFields()){
			if (field.isEntityField()){
				SimpleInterfaceModel interfaceModel = new SimpleInterfaceModel();
				interfaceModel.setBasePackage(serviceInterface.getNameSpace());
				interfaceModel.setName(WordUtils.capitalize(field.getName()) + "Service");
				createServiceField(resultingController, interfaceModel);
			}
		}
	}

	public ControllerModel convertService(RepositoriedServiceModel serviceModel){
		SimpleControllerModel resultingController = new SimpleControllerModel();
		resultingController.setName(WordUtils.capitalize(serviceModel.getDomainModel().getName()) + "Controller");
		resultingController.setBasePackage(getControllerPackage());
		resultingController.setEntityType(serviceModel.getDomainModel());
		for (AnnotationModel annotation : convertAnnotations(serviceModel)){
			resultingController.addAnnotation(annotation);
		}
		createFields(resultingController, serviceModel);
		for (MethodModel method : convertMethods(serviceModel)){
			resultingController.addMethod(method);
		}
		return resultingController;
	}


}
