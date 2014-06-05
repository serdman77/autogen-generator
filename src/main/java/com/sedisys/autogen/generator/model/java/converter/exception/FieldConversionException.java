package com.sedisys.autogen.generator.model.java.converter.exception;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.converter.field.FieldConverter;

public class FieldConversionException extends ConversionException {

	/**
	 * Serialization Version Id
	 */
	private static final long serialVersionUID = 1L;

	private FieldModel attemptedConversionFieldModel;

	private FieldConverter attemptedConverter;

	private Exception attemptedConversionException;

	public FieldConversionException(FieldModel attemptedConversionFieldModel, FieldConverter attemptedConverter, Exception attemptedConversionException){
		super();
		this.attemptedConversionFieldModel = attemptedConversionFieldModel;
		this.attemptedConverter = attemptedConverter;
		this.attemptedConversionException = attemptedConversionException;
	}

	public FieldModel getAttemptedConversionFieldModel(){
		return attemptedConversionFieldModel;
	}

	public FieldConverter getAttemptedConverter(){
		return attemptedConverter;
	}

	public Exception getAttemptedConversionException(){
		return attemptedConversionException;
	}

	@Override
	public String getMessage(){
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Field conversion failed. Attempted to convert field ");
		messageBuilder.append(getAttemptedConversionFieldModel().getName());
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
