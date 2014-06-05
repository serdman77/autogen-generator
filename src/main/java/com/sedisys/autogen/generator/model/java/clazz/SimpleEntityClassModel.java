package com.sedisys.autogen.generator.model.java.clazz;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;
import com.sedisys.autogen.generator.model.java.field.EntityClassFieldModel;

public class SimpleEntityClassModel extends AbstractClassModel<EntityClassFieldModel> implements EntityClassModel{
	private List<EntityClassFieldModel> fields;
	private List<FieldGrouping> fieldGroupings;

	public SimpleEntityClassModel(){
		super();
		fields = new ArrayList<>();
		fieldGroupings = new ArrayList<>();
	}

	@Override
	public List<EntityClassFieldModel> getFields(){
		return fields;
	}

	@Override
	public List<String> getFieldNames(){
		List<String> fieldNames = new ArrayList<>();
		for (EntityClassFieldModel field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	@Override
	public EntityClassFieldModel getField(String fieldName){
		for (EntityClassFieldModel field : fields){
			if (field.getName().equals(fieldName)){
				return field;
			}
		}
		return null;
	}

	@Override
	public EntityClassFieldModel getField(int index){
		return getFields().get(index);
	}

	@Override
	public boolean containsField(EntityClassFieldModel field){
		return getFields().contains(field);
	}

	@Override
	public void addField(EntityClassFieldModel field){
		getFields().add(field);
	}

	@Override
	public void insertField(int insertAt, EntityClassFieldModel field){
		getFields().add(insertAt, field);
	}

	@Override
	public void replaceField(int replaceAt, EntityClassFieldModel field){
		getFields().set(replaceAt, field);
	}

	@Override
	public EntityClassFieldModel removeField(int removeAt){
		return getFields().remove(removeAt);
	}

	@Override
	public void removeField(EntityClassFieldModel field){
		getFields().remove(field);
	}

	@Override
	public int getFieldCount(){
		return getFields().size();
	}

	@Override
	public List<FieldGrouping> getFieldGroupings(){
		return fieldGroupings;
	}

	public void setFieldGroupings(List<FieldGrouping> fieldGroupings){
		this.fieldGroupings = fieldGroupings;
	}
}
