package com.sedisys.autogen.generator.model.java.converter.clazz;

import com.sedisys.autogen.generator.model.java.clazz.ClassModel;
import com.sedisys.autogen.generator.model.java.converter.exception.ClassConversionException;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;

public interface ClassConverter<ResultingClass extends ClassModel<? extends ClassFieldModel>, BaseClass> {
	public ResultingClass convertClass(BaseClass baseClass) throws ClassConversionException;
}
