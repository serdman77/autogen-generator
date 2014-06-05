package com.sedisys.autogen.generator.model.java.converter.annotation;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.annotation.AnnotationModel;
import com.sedisys.autogen.generator.model.java.converter.exception.ConstraintConversionException;

public class ConstraintAnnotationConverterList implements ConstraintAnnotationConverter {

	private List<ConstraintAnnotationConverter> constraintConverters;

	public ConstraintAnnotationConverterList(){
		constraintConverters = new ArrayList<>();
	}

	public List<ConstraintAnnotationConverter> getConstraintAnnotationConverters(){
		return constraintConverters;
	}

	public void setConstraintAnnotationConverters(List<ConstraintAnnotationConverter> constraintConverters){
		this.constraintConverters = constraintConverters;
	}

	public ConstraintAnnotationConverter getConstraintAnnotationConverter(int index){
		return getConstraintAnnotationConverters().get(index);
	}

	public boolean containsConstraintAnnotationConverter(ConstraintAnnotationConverter constraintConverter){
		return getConstraintAnnotationConverters().contains(constraintConverter);
	}

	public void addConstraintAnnotationConverter(ConstraintAnnotationConverter constraintConverter){
		getConstraintAnnotationConverters().add(constraintConverter);
	}

	public void insertConstraintAnnotationConverter(int insertAt, ConstraintAnnotationConverter constraintConverter){
		getConstraintAnnotationConverters().add(insertAt, constraintConverter);
	}

	public void replaceConstraintAnnotationConverter(int replaceAt, ConstraintAnnotationConverter constraintConverter){
		getConstraintAnnotationConverters().set(replaceAt, constraintConverter);
	}

	public ConstraintAnnotationConverter removeConstraintAnnotationConverter(int removeAt){
		return getConstraintAnnotationConverters().remove(removeAt);
	}

	public void removeConstraintAnnotationConverter(ConstraintAnnotationConverter constraintConverter){
		getConstraintAnnotationConverters().remove(constraintConverter);
	}

	public int getConstraintAnnotationConverterCount(){
		return getConstraintAnnotationConverters().size();
	}

	public List<AnnotationModel> convertConstraint(Constraint toConvert, boolean stopOnSuccess) throws ConstraintConversionException{
		List<AnnotationModel> resultingAnnotationModels = new ArrayList<>();
		for (ConstraintAnnotationConverter converter : getConstraintAnnotationConverters()){
			resultingAnnotationModels.addAll(converter.convertConstraint(toConvert));
			if (resultingAnnotationModels.size() > 0 && stopOnSuccess){
				break;
			}
		}
		return resultingAnnotationModels;
	}

	@Override
	public List<AnnotationModel> convertConstraint(Constraint toConvert) throws ConstraintConversionException{
		return convertConstraint(toConvert, true);
	}

}
