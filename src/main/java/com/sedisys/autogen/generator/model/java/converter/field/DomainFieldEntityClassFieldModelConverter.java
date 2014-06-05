package com.sedisys.autogen.generator.model.java.converter.field;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.domain.field.DomainFieldModel;
import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverter;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.converter.exception.TypeConversionException;
import com.sedisys.autogen.generator.model.java.converter.type.TypeConverter;
import com.sedisys.autogen.generator.model.java.field.EntityClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.MutableAnnotationsClassFieldModel;
import com.sedisys.autogen.generator.model.java.field.SimpleEntityClassFieldModel;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class DomainFieldEntityClassFieldModelConverter implements FieldConverter {
	private ConstraintAnnotationConverter constraintConverter;
	private TypeConverter typeConverter;

	public ConstraintAnnotationConverter getConstraintConverter(){
		return constraintConverter;
	}

	public void setConstraintConverter(ConstraintAnnotationConverter constraintConverter){
		this.constraintConverter = constraintConverter;
	}

	public TypeConverter getTypeConverter(){
		return typeConverter;
	}

	public void setTypeConverter(TypeConverter typeConverter){
		this.typeConverter = typeConverter;
	}

	protected List<AnnotationModel> convertFieldConstraints(DomainFieldModel field, MutableAnnotationsClassFieldModel resultField) throws ConstraintConversionException{
		List<AnnotationModel> returnAnnotations = new ArrayList<>();
		for (Constraint constraint : field.getConstraints()){
			returnAnnotations.addAll(getConstraintConverter().convertConstraint(constraint));
		}
		return returnAnnotations;
	}

	@Override
	public EntityClassFieldModel convertField(FieldModel field) throws FieldConversionException{
		if (!(field instanceof DomainFieldModel)){
			return null;
		}
		try{
			TypeModel type = getTypeConverter().convertType(field.getType(), true, true);
			SimpleEntityClassFieldModel resultField = new SimpleEntityClassFieldModel(field, type);
			for (AnnotationModel annotation : convertFieldConstraints(field)){
				resultField.addAnnotation(annotation);
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