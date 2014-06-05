package com.sedisys.autogen.generator.model.java.converter.type;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.type.MutableClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class TypeConverterList extends AbstractTypeConverter {

	private List<TypeConverter> typeConverters;

	public TypeConverterList(){
		typeConverters = new ArrayList<>();
	}

	public List<TypeConverter> getTypeConverters(){
		return typeConverters;
	}


	public void setTypeConverters(List<TypeConverter> typeConverters){
		this.typeConverters = typeConverters;
	}

	public TypeConverter getTypeConverter(int index){
		return getTypeConverters().get(index);
	}

	public boolean containsTypeConverter(TypeConverter typeConverter){
		return getTypeConverters().contains(typeConverter);
	}

	public void addTypeConverter(TypeConverter typeConverter){
		getTypeConverters().add(typeConverter);
	}

	public void insertTypeConverter(int insertAt, TypeConverter typeConverter){
		getTypeConverters().add(insertAt, typeConverter);
	}

	public void replaceTypeConverter(int replaceAt, TypeConverter typeConverter){
		getTypeConverters().set(replaceAt, typeConverter);
	}

	public TypeConverter removeTypeConverter(int removeAt){
		return getTypeConverters().remove(removeAt);
	}

	public void removeTypeConverter(TypeConverter typeConverter){
		getTypeConverters().remove(typeConverter);
	}

	public int getTypeConverterCount(){
		return getTypeConverters().size();
	}

	@Override
	public MutableClassTypeModel convertType(TypeModel type, boolean convertContainedTypes, boolean failOnNoMatch) throws TypeConversionException{
		MutableClassTypeModel returnType = null;
		for (TypeConverter converter : getTypeConverters()){
			returnType = converter.convertType(type, false, false);
			if (returnType!=null){
				break;
			}
		}
		if (convertContainedTypes){
			for (TypeModel containedType : type.getContainedTypes()){
				returnType.addGeneric(convertType(containedType, true, failOnNoMatch));
			}
		}
		if (returnType==null && failOnNoMatch){
			throw new TypeConversionException(type, this, new ClassNotFoundException());
		}
		return returnType;
	}

}
