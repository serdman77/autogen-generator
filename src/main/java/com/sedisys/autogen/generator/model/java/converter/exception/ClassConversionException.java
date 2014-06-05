package com.sedisys.autogen.generator.model.java.converter.exception;

import com.sedisys.autogen.generator.model.java.clazz.ClassModel;
import com.sedisys.autogen.generator.model.java.converter.clazz.ClassConverter;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;

public class ClassConversionException extends ConversionException {

	/**
	 * Serialization Version Id
	 */
	private static final long serialVersionUID = 1L;

	private Object attemptedConversionClassSource;

	private ClassConverter<? extends ClassModel<? extends ClassFieldModel>, ?> attemptedConverter;

	private Exception attemptedConversionException;

	public ClassConversionException(Object attemptedConversionClassSource, ClassConverter<? extends ClassModel<? extends ClassFieldModel>, ?> attemptedConverter, Exception attemptedConversionException){
		super();
		this.attemptedConversionClassSource = attemptedConversionClassSource;
		this.attemptedConverter = attemptedConverter;
		this.attemptedConversionException = attemptedConversionException;
	}

	public Object getAttemptedConversionClassSource(){
		return attemptedConversionClassSource;
	}

	public ClassConverter<? extends ClassModel<? extends ClassFieldModel>, ?> getAttemptedConverter(){
		return attemptedConverter;
	}

	public Exception getAttemptedConversionException(){
		return attemptedConversionException;
	}

	@Override
	public String getMessage(){
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Field conversion failed. Attempted to convert class ");
		messageBuilder.append(getAttemptedConversionClassSource().getClass().getName());
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
