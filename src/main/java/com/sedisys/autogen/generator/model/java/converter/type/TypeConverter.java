package com.sedisys.autogen.generator.model.java.converter.type;

import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.type.MutableClassTypeModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public interface TypeConverter {
	public MutableClassTypeModel convertType(TypeModel type, boolean recursiveConvertContainedTypes, boolean failOnNoMatch) throws TypeConversionException;
}
