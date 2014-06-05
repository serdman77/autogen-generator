package com.sedisys.autogen.generator.model.java.converter.field;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constrained;
import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.field.EntityFieldModel;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.converter.type.TypeConverter;
import com.sedisys.autogen.generator.model.java.field.MutableAnnotationsClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.SimpleClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.SimpleEntityClassFieldModel;
import com.sedisys.autogen.generator.model.java.type.ClassTypeModel;

public class DefaultFieldConverter implements FieldConverter {

	private TypeConverter typeConverter;
	private ConstraintAnnotationConverter constraintConverter;

	public TypeConverter getTypeConverter(){
		return typeConverter;
	}

	public void setTypeConverter(TypeConverter typeConverter){
		this.typeConverter = typeConverter;
	}

	public ConstraintAnnotationConverter getConstraintConverter(){
		return constraintConverter;
	}

	public void setConstraintConverter(ConstraintAnnotationConverter constraintConverter){
		this.constraintConverter = constraintConverter;
	}

	@Override
	public MutableAnnotationsClassFieldModel convertField(FieldModel field) throws FieldConversionException{
		try{
			ClassTypeModel converterType = getTypeConverter().convertType(field.getType(), true, true);
			MutableAnnotationsClassFieldModel resultField;
			if (field instanceof EntityFieldModel){
				resultField = new SimpleEntityClassFieldModel(field, converterType);
			} else{
				resultField = new SimpleClassFieldModel(field, converterType);
			}
			if (field instanceof Constrained){
				for (AnnotationModel annotation : convertFieldConstraints(field)){
					resultField.addAnnotation(annotation);
				}
			}
			return resultField;
		} catch (TypeConversionException | ConstraintConversionException e){
			throw new FieldConversionException(field, this, e);
		}
	}

	protected List<AnnotationModel> convertFieldConstraints(FieldModel baseModel) throws ConstraintConversionException{
		List<AnnotationModel> returnAnnotations = new ArrayList<>();
		for (Constraint constraint : baseModel.getConstraints()){
			returnAnnotations.addAll(getConstraintConverter().convertConstraint(constraint));
		}
		return returnAnnotations;
	}
}
