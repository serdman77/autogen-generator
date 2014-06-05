package com.sedisys.autogen.generator.model.java.clazz;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;

public class SimpleClassModel<TFieldType extends ClassFieldModel> extends AbstractClassModel<TFieldType> {

	private List<TFieldType> fields;

	@Override
	public List<String> getFieldNames(){
		List<String> fieldNames = new ArrayList<>();
		for (ClassFieldModel field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	@Override
	public ClassFieldModel getField(String fieldName){
		for (ClassFieldModel field : fields){
			if (field.getName().equals(fieldName)){
				return field;
			}
		}
		return null;
	}

	@Override
	public List<TFieldType> getFields(){
		return fields;
	}

	@Override
	public TFieldType getField(int index){
		return getFields().get(index);
	}

	@Override
	public boolean containsField(TFieldType Field){
		return getFields().contains(Field);
	}

	@Override
	public void addField(TFieldType Field){
		getFields().add(Field);
	}

	@Override
	public void insertField(int insertAt, TFieldType Field){
		getFields().add(insertAt, Field);
	}

	@Override
	public void replaceField(int replaceAt, TFieldType Field){
		getFields().set(replaceAt, Field);
	}

	@Override
	public TFieldType removeField(int removeAt){
		return getFields().remove(removeAt);
	}

	@Override
	public void removeField(TFieldType Field){
		getFields().remove(Field);
	}

	@Override
	public int getFieldCount(){
		return getFields().size();
	}
}
