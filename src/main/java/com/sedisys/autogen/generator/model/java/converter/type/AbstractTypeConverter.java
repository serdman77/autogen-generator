package com.sedisys.autogen.generator.model.java.converter.type;

import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.type.ClassTypeModel;
import com.sedisys.autogen.generator.model.java.type.MutableClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public abstract class AbstractTypeConverter implements TypeConverter {


	public MutableClassTypeModel convertType(TypeModel type) throws TypeConversionException{
		return convertType(type, false, false);
	}

	public MutableClassTypeModel topLevelConvertType(TypeModel type) throws TypeConversionException{
		return convertType(type, true, true);
	}

	@Override
	public MutableClassTypeModel convertType(TypeModel type, boolean recursivelyConvertContainedTypes, boolean failOnNoMatch) throws TypeConversionException{
		MutableClassTypeModel returnType = convertType(type, recursivelyConvertContainedTypes, false);
		if (failOnNoMatch && returnType==null){
			throw new TypeConversionException(type, this, new NullPointerException("Converter failed to find match"));
		}
		if (returnType!=null && recursivelyConvertContainedTypes){
			convertContainedTypes(type, returnType, recursivelyConvertContainedTypes, failOnNoMatch);
		}
		return returnType;
	}

	protected void convertContainedTypes(TypeModel type, MutableClassTypeModel convertedClassType, boolean recursivelyConvertContainedTypes) throws TypeConversionException{
		convertContainedTypes(type, convertedClassType, recursivelyConvertContainedTypes, true);
	}

	protected void convertContainedTypes(TypeModel type, MutableClassTypeModel convertedClassType, boolean recursivelyConvertContainedTypes, boolean failOnNoMatch) throws TypeConversionException{
		ClassTypeModel genericType;
		for (TypeModel containedType : type.getContainedTypes()){
			genericType = convertType(containedType, recursivelyConvertContainedTypes, failOnNoMatch);
			if (genericType!=null){
				convertedClassType.addGeneric(genericType);
			}
		}
	}
}
