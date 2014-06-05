package com.sedisys.autogen.generator.model.java.converter.type;

import java.util.HashMap;
import java.util.Map;

import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.type.MutableClassTypeModel;
import com.sedisys.autogen.generator.model.java.type.SimpleClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class TypeMappingTypeConverter extends AbstractTypeConverter{
	private Map<TypeModel, Class<? extends Object>> typeModelToClassMappings;

	public TypeMappingTypeConverter(){
		typeModelToClassMappings = new HashMap<>();
	}

	public Map<TypeModel, Class<? extends Object>> getTypeModelToClassMappings(){
		return typeModelToClassMappings;
	}

	public void setTypeModelToClassMappings(Map<TypeModel, Class<? extends Object>> typeModelToClassMappings){
		this.typeModelToClassMappings = typeModelToClassMappings;
	}

	public Class<? extends Object> getTypeModelToClassMapping(TypeModel key){
		return getTypeModelToClassMappings().get(key);
	}

	public boolean containsTypeModelToClassMappingKey(TypeModel key){
		return getTypeModelToClassMappings().containsKey(key);
	}

	public boolean containsTypeModelToClassMappingValue(Class<? extends Object> value){
		return getTypeModelToClassMappings().containsValue(value);
	}

	public void addTypeModelToClassMapping(TypeModel key, Class<? extends Object> value){
		getTypeModelToClassMappings().put(key, value);
	}

	public Class<? extends Object> removeTypeModelToClassMapping(TypeModel key){
		return getTypeModelToClassMappings().remove(key);
	}

	public int getTypeModelToClassMappingCount(){
		return getTypeModelToClassMappings().size();
	}

	@Override
	public MutableClassTypeModel convertType(TypeModel type, boolean recursivelyConvertContainedTypes, boolean failOnNoMatch) throws TypeConversionException{
		Class<? extends Object> mappedClass = getTypeModelToClassMapping(type);
		if (mappedClass!=null){
			SimpleClassTypeModel returnType = new SimpleClassTypeModel(mappedClass);
			if (recursivelyConvertContainedTypes){
				convertContainedTypes(type, returnType, recursivelyConvertContainedTypes);
			}
			return returnType;
		}
		if (failOnNoMatch){
			throw new TypeConversionException(type, this, new ClassNotFoundException());
		}
		return null;
	}
}
