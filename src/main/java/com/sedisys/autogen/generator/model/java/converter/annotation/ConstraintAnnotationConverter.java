package com.sedisys.autogen.generator.model.java.converter.annotation;

import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;

public interface ConstraintAnnotationConverter {

	public List<AnnotationModel> convertConstraint(Constraint toConvert) throws ConstraintConversionException;
}
