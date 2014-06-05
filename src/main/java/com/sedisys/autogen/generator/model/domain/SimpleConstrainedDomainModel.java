package com.sedisys.autogen.generator.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.constraint.Constraint;
import com.sedisys.autogen.generator.model.domain.field.DomainFieldModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;

public class SimpleConstrainedDomainModel implements ConstrainedDomainModel{
	private String name;
	private String nameSpace;
	private List<DomainFieldModel> fields;
	private List<FieldGrouping> fieldGroupings;
	private List<Constraint> constraints;

	public SimpleConstrainedDomainModel(){
		fields = new ArrayList<>();
		fieldGroupings = new ArrayList<>();
		constraints = new ArrayList<>();
	}

	@Override
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setNameSpace(String nameSpace){
		this.nameSpace = nameSpace;
	}

	@Override
	public String getNameSpace(){
		return nameSpace;
	}

	@Override
	public String getQualifiedName(){
		return getNameSpace() + '.' + getName();
	}

	@Override
	public List<DomainFieldModel> getFields(){
		return fields;
	}

	public void setFields(List<DomainFieldModel> fields){
		this.fields = fields;
	}

	public DomainFieldModel getField(int index){
		return getFields().get(index);
	}

	public DomainFieldModel getField(String fieldName){
		for (DomainFieldModel field : getFields()){
			if (field.getName().equals(fieldName)){
				return field;
			}
		}
		return null;
	}

	public List<String> getFieldNames(){
		List<String> returnFieldNames = new ArrayList<>();
		for (DomainFieldModel field : getFields()){
			returnFieldNames.add(field.getName());
		}
		return returnFieldNames;
	}

	public boolean containsField(DomainFieldModel field){
		return getFields().contains(field);
	}

	public void addField(DomainFieldModel field){
		getFields().add(field);
	}

	public void insertField(int insertAt, DomainFieldModel field){
		getFields().add(insertAt, field);
	}

	public void replaceField(int replaceAt, DomainFieldModel field){
		getFields().set(replaceAt, field);
	}

	public DomainFieldModel removeField(int removeAt){
		return getFields().remove(removeAt);
	}

	public void removeField(DomainFieldModel field){
		getFields().remove(field);
	}

	public int getFieldCount(){
		return getFields().size();
	}


	public void setFieldGroupings(List<FieldGrouping> fieldGroupings){
		this.fieldGroupings = fieldGroupings;
	}

	@Override
	public List<FieldGrouping> getFieldGroupings(){
		return fieldGroupings;
	}

	public FieldGrouping getFieldGrouping(int index){
		return getFieldGroupings().get(index);
	}

	public boolean containsFieldGrouping(FieldGrouping fieldGrouping){
		return getFieldGroupings().contains(fieldGrouping);
	}

	public void addFieldGrouping(FieldGrouping fieldGrouping){
		getFieldGroupings().add(fieldGrouping);
	}

	public void insertFieldGrouping(int insertAt, FieldGrouping fieldGrouping){
		getFieldGroupings().add(insertAt, fieldGrouping);
	}

	public void replaceFieldGrouping(int replaceAt, FieldGrouping fieldGrouping){
		getFieldGroupings().set(replaceAt, fieldGrouping);
	}

	public FieldGrouping removeFieldGrouping(int removeAt){
		return getFieldGroupings().remove(removeAt);
	}

	public void removeFieldGrouping(FieldGrouping fieldGrouping){
		getFieldGroupings().remove(fieldGrouping);
	}

	public int getFieldGroupingCount(){
		return getFieldGroupings().size();
	}


	public void setConstraints(List<Constraint> constraints){
		this.constraints = constraints;
	}

	@Override
	public List<Constraint> getConstraints(){
		return constraints;
	}

	public Constraint getConstraint(int index){
		return getConstraints().get(index);
	}

	public boolean containsConstraint(Constraint constraint){
		return getConstraints().contains(constraint);
	}

	public void addConstraint(Constraint constraint){
		getConstraints().add(constraint);
	}

	public void insertConstraint(int insertAt, Constraint constraint){
		getConstraints().add(insertAt, constraint);
	}

	public void replaceConstraint(int replaceAt, Constraint constraint){
		getConstraints().set(replaceAt, constraint);
	}

	public Constraint removeConstraint(int removeAt){
		return getConstraints().remove(removeAt);
	}

	public void removeConstraint(Constraint constraint){
		getConstraints().remove(constraint);
	}

	public int getConstraintCount(){
		return getConstraints().size();
	}
}
