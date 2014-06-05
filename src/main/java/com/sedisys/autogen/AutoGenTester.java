package com.sedisys.autogen;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sedisys.autogen.generator.model.constraint.SimpleConstraint;
import com.sedisys.autogen.generator.model.domain.SimpleConstrainedDomainModel;
import com.sedisys.autogen.generator.model.domain.field.SimpleDomainFieldModel;
import com.sedisys.autogen.generator.model.field.AccessLeveled.AccessLevel;
import com.sedisys.autogen.generator.model.java.clazz.EntityClassModel;
import com.sedisys.autogen.generator.model.java.clazz.repository.RepositoryModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverterList;
import com.sedisys.autogen.generator.model.java.converter.annotation.PackageListConstraintAnnotationConverter;
import com.sedisys.autogen.generator.model.java.converter.annotation.attribute.MatchingConstraintPropertyToAnnotationAttributeConverter;
import com.sedisys.autogen.generator.model.java.converter.clazz.DomainToEntityClassConverter;
import com.sedisys.autogen.generator.model.java.converter.clazz.repository.EntityTypeToRepositoryConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ClassConversionException;
import com.sedisys.autogen.generator.model.java.converter.field.DefaultFieldConverter;
import com.sedisys.autogen.generator.model.java.converter.field.DomainFieldEntityClassFieldModelConverter;
import com.sedisys.autogen.generator.model.java.converter.field.FieldConverterList;
import com.sedisys.autogen.generator.model.java.converter.type.PackageListTypeConverter;
import com.sedisys.autogen.generator.model.java.converter.type.TypeConverterList;

public class AutoGenTester {

	public static void main(String[] args){
		AutoGenTester tester = new AutoGenTester();
		tester.runTest();
	}

	public void runTest(){

		TypeConverterList typeConverters = new TypeConverterList();
		PackageListTypeConverter packageTypeConverter = new PackageListTypeConverter();

		packageTypeConverter.addPackageToCheck("java.lang");
		packageTypeConverter.addPackageToCheck("java.util");
		packageTypeConverter.addPackageToCheck("java.math");

		typeConverters.addTypeConverter(packageTypeConverter);

		MatchingConstraintPropertyToAnnotationAttributeConverter propertyToAttributeConverter = new MatchingConstraintPropertyToAnnotationAttributeConverter();

		ConstraintAnnotationConverterList constraintConverters = new ConstraintAnnotationConverterList();

		PackageListConstraintAnnotationConverter packageConstraintConverter = new PackageListConstraintAnnotationConverter();
		packageConstraintConverter.setConstraintPropertyConverter(propertyToAttributeConverter);
		packageConstraintConverter.addPackageToCheck("javax.validation.constraints");
		packageConstraintConverter.addPackageToCheck("org.hibernate.validator.constraints");
		constraintConverters.addConstraintAnnotationConverter(packageConstraintConverter);


		FieldConverterList fieldConverters = new FieldConverterList();
		DomainFieldEntityClassFieldModelConverter domainFieldConverter = new DomainFieldEntityClassFieldModelConverter();
		domainFieldConverter.setConstraintConverter(constraintConverters);
		domainFieldConverter.setTypeConverter(typeConverters);
		fieldConverters.addConverter(domainFieldConverter);

		DefaultFieldConverter defaultFieldConverter = new DefaultFieldConverter();
		defaultFieldConverter.setConstraintConverter(constraintConverters);
		defaultFieldConverter.setTypeConverter(typeConverters);
		fieldConverters.addConverter(defaultFieldConverter);

		SimpleConstrainedDomainModel domainModel = new SimpleConstrainedDomainModel();
		domainModel.setName("Person");

		SimpleDomainFieldModel tempField = new SimpleDomainFieldModel("String", "firstName");
		tempField.setAccessLevel(AccessLevel.PRIVATE);
		SimpleConstraint tempConstraint = new SimpleConstraint();
		tempConstraint.setName("NotNull");
		tempField.addConstraint(tempConstraint);
		tempConstraint = new SimpleConstraint();
		tempConstraint.setName("Size");
		tempConstraint.addProperty("Max", 50);
		tempField.addConstraint(tempConstraint);
		domainModel.addField(tempField);

		tempField = new SimpleDomainFieldModel("String", "lastName");
		tempField.setAccessLevel(AccessLevel.PRIVATE);
		tempConstraint = new SimpleConstraint();
		tempConstraint.setName("NotNull");
		tempField.addConstraint(tempConstraint);
		tempConstraint = new SimpleConstraint();
		tempConstraint.setName("Size");
		tempConstraint.addProperty("Max", 50);
		tempField.addConstraint(tempConstraint);
		domainModel.addField(tempField);

    tempField = new SimpleDomainFieldModel("Date", "birthDate");
		tempField.setAccessLevel(AccessLevel.PRIVATE);
		tempConstraint = new SimpleConstraint();
		tempConstraint.setName("Past");
		tempField.addConstraint(tempConstraint);
		domainModel.addField(tempField);


		DomainToEntityClassConverter domainEntityConverter = new DomainToEntityClassConverter("com.sedi.domain");
		domainEntityConverter.setConstraintConverter(constraintConverters);
		domainEntityConverter.setTypeConverter(typeConverters);
		domainEntityConverter.setFieldConverter(fieldConverters);
		EntityClassModel entityType = null;
		try{
			entityType = domainEntityConverter.convertClass(domainModel);
		} catch (ClassConversionException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EntityTypeToRepositoryConverter entityConverter = new EntityTypeToRepositoryConverter("com.sedi.repository", JpaRepository.class);
		entityConverter.setFieldConverter(fieldConverters);

		URL url = this.getClass().getResource("/templates/");

		File file = new File(url.getFile());

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, file.getAbsolutePath());
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
		ve.init();

		RepositoryModel resultingRepository;

		resultingRepository = entityConverter.convertEntityClassModel(entityType);
		Template t = ve.getTemplate("interface.vm");
		VelocityContext context = new VelocityContext();
		context.put("interfaceModel", resultingRepository);
		context.put("package", "com.sedisys.domain");
		context.put("joiner", com.google.common.base.Joiner.on(", "));
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		System.out.println(writer);
	}
}
