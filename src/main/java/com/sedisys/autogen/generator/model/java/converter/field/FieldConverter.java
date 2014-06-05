package com.sedisys.autogen.generator.model.java.converter.field;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.field.MutableAnnotationsClassFieldModel;

public interface FieldConverter {

	public MutableAnnotationsClassFieldModel convertField(FieldModel field) throws FieldConversionException;
}
