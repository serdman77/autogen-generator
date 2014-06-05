package com.sedisys.autogen.generator.model.java.converter.clazz;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.domain.field.DomainFieldModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping.GroupingType;
import com.sedisys.autogen.generator.model.field.AccessLeveled.AccessLevel;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.clazz.EntityClassModel;
import com.sedisys.autogen.generator.model.java.clazz.SimpleEntityClassModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ClassConversionException;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.converter.field.FieldConverter;
import com.sedisys.autogen.generator.model.java.converter.type.TypeConverter;
import com.sedisys.autogen.generator.model.java.field.EntityClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.MutableAnnotationsClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.SimpleEntityClassFieldModel;
import com.sedisys.autogen.generator.model.java.interfaze.InterfaceModel;
import com.sedisys.autogen.generator.model.java.interfaze.SimpleInterfaceModel;
import com.sedisys.autogen.generator.model.java.type.SimpleClassTypeModel;
import com.sedisys.autogen.generator.model.method.BasicGetterMethod;
import com.sedisys.autogen.generator.model.method.BasicSetterMethod;
import com.sedisys.autogen.generator.model.type.QualifiedNameTypeModel;

public class DomainToEntityClassConverter implements ClassConverter<EntityClassModel, DomainModel> {
	private static List<InterfaceModel> interfaces;
	private static List<AnnotationModel> baseAnnotations;

	private ConstraintAnnotationConverter constraintConverter;
	private FieldConverter fieldConverter;
	private TypeConverter typeConverter;

	private String basePackage;

	static {
		interfaces = new ArrayList<>();
		interfaces.add(new SimpleInterfaceModel(new QualifiedNameTypeModel("java.io.Serializable")));

		baseAnnotations = new ArrayList<>();
		baseAnnotations.add(new SimpleAnnotationModel("javax.persistence.Entity"));
	}

	public DomainToEntityClassConverter(){
	}

	public DomainToEntityClassConverter(String basePackage){
		this();
		setBasePackage(basePackage);
	}

	public ConstraintAnnotationConverter getConstraintConverter(){
		return constraintConverter;
	}

	public void setConstraintConverter(ConstraintAnnotationConverter constraintConverter){
		this.constraintConverter = constraintConverter;
	}

	public FieldConverter getFieldConverter(){
		return fieldConverter;
	}

	public void setFieldConverter(FieldConverter fieldConverter){
		this.fieldConverter = fieldConverter;
	}

	public TypeConverter getTypeConverter(){
		return typeConverter;
	}

	public void setTypeConverter(TypeConverter typeConverter){
		this.typeConverter = typeConverter;
	}

	public String getBasePackage(){
		return basePackage;
	}

	public void setBasePackage(String basePackage){
		this.basePackage = basePackage;
	}

	@Override
	public EntityClassModel convertClass(DomainModel domainModel) throws ClassConversionException{
		SimpleEntityClassModel resultingClass = new SimpleEntityClassModel();
		resultingClass.setName(domainModel.getName());
		resultingClass.setBasePackage(getBasePackage());
		resultingClass.setFieldGroupings(domainModel.getFieldGroupings());
		for (AnnotationModel annotation : convertAnnotations(domainModel)){
			resultingClass.addAnnotation(annotation);
		}

		for (InterfaceModel interfaceType : interfaces){
			resultingClass.addInterface(interfaceType);
		}

		try{
			for (EntityClassFieldModel field : convertFields(domainModel)){
				resultingClass.addField(field);
			}
		} catch (FieldConversionException ex){
			throw new ClassConversionException(domainModel, this, ex);
		}

		buildBeanMethods(resultingClass);
		return resultingClass;
	}


	protected List<AnnotationModel> convertAnnotations(DomainModel baseModel){
		List<AnnotationModel> returnAnnotations = new ArrayList<>(baseAnnotations.size());
		for (AnnotationModel annotation : baseAnnotations){
			returnAnnotations.add(annotation);
		}

		final List<AnnotationModel> indexAnnotations = new ArrayList<>();
		for (final FieldGrouping grouping : baseModel.getFieldGroupings()){
			if ((grouping.getType() ==	GroupingType.UNIQUE) || (grouping.getType() == GroupingType.INDEXED)){
				SimpleAnnotationModel annotationModel = new SimpleAnnotationModel("javax.persistence.Index");
				if (grouping.getType() == GroupingType.UNIQUE){
					annotationModel.setAttribute("unique", true);
					annotationModel.setAttribute("columnList", Joiner.on(',').join(Lists.transform(grouping.getFields(), new Function<FieldModel, String>(){
						@Override
						public String apply(FieldModel input){
							return input.getName();
						}
					})));
					indexAnnotations.add(annotationModel);
				}
			}
		}
		if (indexAnnotations.size()>0){
			AnnotationModel tableAnnotation = new SimpleAnnotationModel("javax.persistence.Table");
			AnnotationModel[] annotationArray = new AnnotationModel[indexAnnotations.size()];
			annotationArray = indexAnnotations.toArray(annotationArray);
			tableAnnotation.getAttributes().put("indexes", annotationArray);
			returnAnnotations.add(tableAnnotation);
		}
		return returnAnnotations;
	}

	public List<InterfaceModel> getInterfaces(){
		return interfaces;
	}

	protected List<EntityClassFieldModel> convertFields(DomainModel baseModel) throws FieldConversionException{
		List<EntityClassFieldModel> returnFields = new ArrayList<>();
		FieldGrouping naturalIdGrouping = null;
		for (FieldGrouping grouping : baseModel.getFieldGroupings()){
			if (grouping.getType() == GroupingType.NATURALID){
				naturalIdGrouping = grouping;
			}
		}
		boolean foundIdField = false;
		boolean foundIdAnnotation = false;
		boolean foundGeneratedValue = false;
		for (DomainFieldModel baseField : baseModel.getFields()){
			MutableAnnotationsClassFieldModel transformedField;
			transformedField = getFieldConverter().convertField(baseField);
			if (naturalIdGrouping!=null && naturalIdGrouping.containsField(baseField.getName())){
				SimpleAnnotationModel naturalIdAnnotation = new SimpleAnnotationModel("org.hibernate.annotations.NaturalId");
				transformedField.addAnnotation(naturalIdAnnotation);
			}
			if (transformedField.getName().toLowerCase() == "id"){
				foundIdField = true;
				for (AnnotationModel annotation : transformedField.getAnnotations()){
					if (annotation.getSimpleAnnotationName().equals("Id")){
						foundIdAnnotation = true;
					} else if (annotation.getSimpleAnnotationName().equals("GeneratedValue")){
						foundGeneratedValue = true;
					}
				}
				if (!foundIdAnnotation){
					transformedField.addAnnotation(new SimpleAnnotationModel("javax.persistence.Id"));
				}
				if (!foundGeneratedValue){
					transformedField.addAnnotation(new SimpleAnnotationModel("javax.persistence.GeneratedValue"));
				}
			}
			returnFields.add((EntityClassFieldModel) transformedField);

		}
		if (!foundIdField){
			SimpleEntityClassFieldModel idField = new SimpleEntityClassFieldModel();
			idField.setName("id");
			idField.setAccessLevel(AccessLevel.PRIVATE);
			idField.setType(new SimpleClassTypeModel(java.lang.Long.class));
			idField.addAnnotation(new SimpleAnnotationModel("javax.persistence.Id"));
			idField.addAnnotation(new SimpleAnnotationModel("javax.persistence.GeneratedValue"));
			returnFields.add(idField);
		}
		return returnFields;
	}

	protected void buildBeanMethods(SimpleEntityClassModel resultingClass){
		for (EntityClassFieldModel field : resultingClass.getFields()){
			resultingClass.addMethod(new BasicSetterMethod(field));
			resultingClass.addMethod(new BasicGetterMethod(field));
		}
	}
}
