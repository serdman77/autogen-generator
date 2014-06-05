package com.sedisys.autogen.generator.model.java.clazz.repository;

import java.util.ArrayList;
import java.util.List;

import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;
import com.sedisys.autogen.generator.model.java.interfaze.SimpleInterfaceModel;

public class SimpleRepositoryModel extends SimpleInterfaceModel implements RepositoryModel {
	protected DomainModel entityType;
	protected List<FieldGrouping> fieldGroupings;
	protected boolean useQueryDSQL = true;

	public SimpleRepositoryModel(){
		super();
		setFieldGroupings(new ArrayList<FieldGrouping>());
	}

	@Override
	public DomainModel getDomainModel(){
		return entityType;
	}

	public void setEntityType(DomainModel entityType){
		this.entityType = entityType;
	}

	@Override
	public List<FieldGrouping> getFieldGroupings(){
		return fieldGroupings;
	}

	public void setFieldGroupings(List<FieldGrouping> fieldGroupings){
		this.fieldGroupings = fieldGroupings;
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

	@Override
	public boolean isUseQueryDSL(){
		return useQueryDSQL;
	}

	public void setUseQueryDSQL(boolean useQueryDSQL){
		this.useQueryDSQL = useQueryDSQL;
	}

}
