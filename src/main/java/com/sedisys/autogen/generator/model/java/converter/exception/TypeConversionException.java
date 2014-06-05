package com.sedisys.autogen.generator.model.java.converter.exception;

import com.sedisys.autogen.generator.model.java.converter.type.TypeConverter;
import com.sedisys.autogen.generator.model.type.TypeModel;

public class TypeConversionException extends ConversionException {

	/**
	 * Serialization Version Id
	 */
	private static final long serialVersionUID = 1L;

	private TypeModel attemptedConversionTypeModel;

	private TypeConverter attemptedConverter;

	private Exception attemptedConversionException;

	public TypeConversionException(TypeModel attemptedConversionTypeModel, TypeConverter attemptedConverter, Exception attemptedConversionException){
		super();
		this.attemptedConversionTypeModel = attemptedConversionTypeModel;
		this.attemptedConverter = attemptedConverter;
		this.attemptedConversionException = attemptedConversionException;
	}

	public TypeModel getAttemptedConversionTypeModel(){
		return attemptedConversionTypeModel;
	}

	public TypeConverter getAttemptedConverter(){
		return attemptedConverter;
	}

	public Exception getAttemptedConversionException(){
		return attemptedConversionException;
	}

	@Override
	public String getMessage(){
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Type conversion failed. Attempted to convert type ");
		messageBuilder.append(getAttemptedConversionTypeModel().getQualifiedName());
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
