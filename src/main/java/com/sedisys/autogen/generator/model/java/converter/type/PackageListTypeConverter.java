package com.sedisys.autogen.generator.model.java.converter.type;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.type.MutableClassTypeModel;
import com.sedisys.autogen.generator.model.java.type.SimpleClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class PackageListTypeConverter implements TypeConverter {

	private List<String> packagesToCheck;

	public PackageListTypeConverter(){
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
	public MutableClassTypeModel convertType(TypeModel type, boolean recursiveConvertContainedTypes, boolean failOnNoMatch) throws TypeConversionException{
		Class<? extends Object> matchedClass;
		for (String packageToCheck : getPackagesToCheck()){
			try{
				matchedClass = Class.forName(packageToCheck + '.' + WordUtils.capitalize(type.getName()));
				if (matchedClass!=null){
					return new SimpleClassTypeModel(matchedClass);
				}
			} catch (ClassNotFoundException e){
			}
		}
		if (failOnNoMatch){
			throw new TypeConversionException(type, this, new ClassNotFoundException());
		}
		return null;
	}

}
