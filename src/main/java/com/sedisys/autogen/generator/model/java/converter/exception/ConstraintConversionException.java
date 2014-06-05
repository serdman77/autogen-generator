package com.sedisys.autogen.generator.model.java.converter.exception;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.java.converter.annotation.ConstraintAnnotationConverter;

public class ConstraintConversionException extends ConversionException {

	/**
	 * Serialization Version Id
	 */
	private static final long serialVersionUID = 1L;

	private Constraint attemptedConversionConstraint;

	private ConstraintAnnotationConverter attemptedConverter;

	private Exception attemptedConversionException;

	public ConstraintConversionException(Constraint attemptedConversionConstraint, ConstraintAnnotationConverter attemptedConverter, Exception attemptedConversionException){
		super();
		this.attemptedConversionConstraint = attemptedConversionConstraint;
		this.attemptedConverter = attemptedConverter;
		this.attemptedConversionException = attemptedConversionException;
	}

	public Constraint getAttemptedConversionConstraint(){
		return attemptedConversionConstraint;
	}

	public ConstraintAnnotationConverter getAttemptedConverter(){
		return attemptedConverter;
	}

	public Exception getAttemptedConversionException(){
		return attemptedConversionException;
	}

	@Override
	public String getMessage(){
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Constraint conversion failed. Attempted to convert constraint ");
		messageBuilder.append(getAttemptedConversionConstraint().getName());
		messageBuilder.append("with converter ").append(getAttemptedConverter().getClass().getCanonicalName());
		messageBuilder.append(" with Exception ");
		if (getAttemptedConversionException()!=null){
			messageBuilder.append(getAttemptedConversionException().getClass().getCanonicalName());
			messageBuilder.append(" and message ").append(getAttemptedConversionException().getMessage());
		} else{
			messageBuilder.append("null");
		}
		return messageBuilder.toString();
	}

	@Override
	public Throwable getCause(){
		return getAttemptedConversionException();
	}
}
