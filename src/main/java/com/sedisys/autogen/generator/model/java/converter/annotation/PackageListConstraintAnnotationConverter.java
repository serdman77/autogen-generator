package com.sedisys.autogen.generator.model.java.converter.annotation;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.annotation.SimpleAnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.attribute.ConstraintPropertyToAnnotationAttributeConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;

public class PackageListConstraintAnnotationConverter implements ConstraintAnnotationConverter {

	private ConstraintPropertyToAnnotationAttributeConverter constraintPropertyConverter;
	private List<String> packagesToCheck;

	public ConstraintPropertyToAnnotationAttributeConverter getConstraintPropertyConverter(){
		return constraintPropertyConverter;
	}

	public void setConstraintPropertyConverter(ConstraintPropertyToAnnotationAttributeConverter constraintPropertyConverter){
		this.constraintPropertyConverter = constraintPropertyConverter;
	}

	public PackageListConstraintAnnotationConverter(){
		packagesToCheck = new ArrayList<>();
	}

	public void setPackagesToCheck(List<String> packagesToCheck){
		this.packagesToCheck = packagesToCheck;
	}

	public List<String> getPackagesToCheck(){
		return packagesToCheck;
	}

	public String getPackageToCheck(int index){
		return getPackagesToCheck().get(index);
	}

	public boolean containsPackageToCheck(String listName){
		return getPackagesToCheck().contains(listName);
	}

	public void addPackageToCheck(String listName){
		getPackagesToCheck().add(listName);
	}

	public void insertPackageToCheck(int insertAt, String listName){
		getPackagesToCheck().add(insertAt, listName);
	}

	public void replacePackageToCheck(int replaceAt, String listName){
		getPackagesToCheck().set(replaceAt, listName);
	}

	public String removePackageToCheck(int removeAt){
		return getPackagesToCheck().remove(removeAt);
	}

	public void removePackageToCheck(String listName){
		getPackagesToCheck().remove(listName);
	}

	public int getPackageToCheckCount(){
		return getPackagesToCheck().size();
	}


	@Override
	public List<AnnotationModel> convertConstraint(Constraint toConvert) throws ConstraintConversionException{
		List<AnnotationModel> resultingConverting = new ArrayList<>();
		Class<? extends Object> matchingClass;
		for (String packageToCheck : getPackagesToCheck()){
			try{
				matchingClass = Class.forName(packageToCheck + '.' + toConvert.getName());
				if (matchingClass.isAnnotation()){
					SimpleAnnotationModel annotation = new SimpleAnnotationModel(matchingClass.getCanonicalName());
					if (getConstraintPropertyConverter().convertConstraintProperties(annotation, toConvert)){
						resultingConverting.add(annotation);
					}
				}
			} catch (ClassNotFoundException ex){
			}
		}
		return resultingConverting;
	}
}
