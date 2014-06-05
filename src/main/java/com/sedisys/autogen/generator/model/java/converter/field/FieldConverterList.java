package com.sedisys.autogen.generator.model.java.converter.field;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.field.FieldModel;
import com.sedisys.autogen.generator.model.java.converter.exception.FieldConversionException;
import com.sedisys.autogen.generator.model.java.field.MutableAnnotationsClassFieldModel;

public class FieldConverterList implements FieldConverter {
	private List<FieldConverter> converterList;

	public FieldConverterList(){
		setConverterList(new ArrayList<FieldConverter>());
	}

	public List<FieldConverter> getConverterList(){
		return converterList;
	}

	public void setConverterList(List<FieldConverter> converterList){
		this.converterList = converterList;
	}

	public FieldConverter getConverter(int index){
		return getConverterList().get(index);
	}

	public boolean containsConverter(FieldConverter converter){
		return getConverterList().contains(converter);
	}

	public void addConverter(FieldConverter converter){
		getConverterList().add(converter);
	}

	public void insertConverter(int insertAt, FieldConverter converter){
		getConverterList().add(insertAt, converter);
	}

	public void replaceConverter(int replaceAt, FieldConverter converter){
		getConverterList().set(replaceAt, converter);
	}

	public FieldConverter removeConverter(int removeAt){
		return getConverterList().remove(removeAt);
	}

	public void removeConverter(FieldConverter converter){
		getConverterList().remove(converter);
	}

	public int getConverterCount(){
		return getConverterList().size();
	}

	@Override
	public MutableAnnotationsClassFieldModel convertField(FieldModel field) throws FieldConversionException{
		MutableAnnotationsClassFieldModel returnField = null;
		for (FieldConverter converter : getConverterList()){
			returnField = converter.convertField(field);
			if (returnField!=null){
				break;
			}
		}
		return returnField;
	}
}
