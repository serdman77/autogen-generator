package com.sedisys.autogen.generator.model.java.converter.annotation.attribute;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.AttributedAnnotationModel;

public interface ConstraintPropertyToAnnotationAttributeConverter {

	public boolean convertConstraintProperties(AttributedAnnotationModel annotation, Constraint baseConstraint);
}
