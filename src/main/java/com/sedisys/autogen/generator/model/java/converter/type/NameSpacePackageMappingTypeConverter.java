package com.sedisys.autogen.generator.model.java.converter.type;

import java.util.LinkedHashMap;
import java.util.Map;

import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.type.MutableClassTypeModel;
import com.sedisys.autogen.generator.model.java.type.SimpleClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class NameSpacePackageMappingTypeConverter extends AbstractTypeConverter{
	private Map<String, String> nameSpaceToPackageMappings;
	private String separator = ".";

	public NameSpacePackageMappingTypeConverter(){
		setNameSpaceToPackageMappings(new LinkedHashMap<String, String>());
	}

	public Map<String, String> getNameSpaceToPackageMappings(){
		return nameSpaceToPackageMappings;
	}

	public void setNameSpaceToPackageMappings(Map<String, String> nameSpaceToPackageMappings){
		this.nameSpaceToPackageMappings = nameSpaceToPackageMappings;
	}

	public String getNameSpaceToPackageMapping(String key){
		return getNameSpaceToPackageMappings().get(key);
	}

	public boolean containsNameSpaceToPackageMappingKey(String key){
		return getNameSpaceToPackageMappings().containsKey(key);
	}

	public boolean containsNameSpaceToPackageMappingValue(String value){
		return getNameSpaceToPackageMappings().containsValue(value);
	}

	public void addNameSpaceToPackageMapping(String key, String value){
		getNameSpaceToPackageMappings().put(key, value);
	}

	public String removeNameSpaceToPackageMapping(String key){
		return getNameSpaceToPackageMappings().remove(key);
	}

	public int getNameSpaceToPackageMappingCount(){
		return getNameSpaceToPackageMappings().size();
	}

	public String getSeparator(){
		return separator;
	}

	public void setSeparator(String separator){
		this.separator = separator;
	}

	@Override
	public MutableClassTypeModel convertType(TypeModel type, boolean recursivelyConvertContainedTypes, boolean failOnNoMatch) throws TypeConversionException{
		String nameSpace = type.getNameSpace();
		int separatorIndex = nameSpace.lastIndexOf(getSeparator());
		String nameSpacePiece;
		String matchingPackage;
		String classString;
		SimpleClassTypeModel returnType;
		while (separatorIndex!=-1){
			nameSpacePiece = nameSpace.substring(0, separatorIndex);
			matchingPackage = getNameSpaceToPackageMapping(nameSpacePiece);
			if (matchingPackage!=null){
				classString = matchingPackage + '.' + nameSpace.substring(separatorIndex + 1);
				try {
					returnType = new SimpleClassTypeModel(Class.forName(classString));
					if (recursivelyConvertContainedTypes){
						convertContainedTypes(type, returnType, recursivelyConvertContainedTypes);
					}
					return returnType;
				} catch (ClassNotFoundException ex){
				}
			}
		}
		if (failOnNoMatch){
			throw new TypeConversionException(type, this, new ClassNotFoundException());
		}
		return null;
	}


}
