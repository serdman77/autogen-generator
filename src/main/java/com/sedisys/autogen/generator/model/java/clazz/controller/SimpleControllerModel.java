package com.sedisys.autogen.generator.model.java.clazz.controller;

import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.java.clazz.SimpleClassModel;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;

public class SimpleControllerModel extends SimpleClassModel<ClassFieldModel> implements ControllerModel{
	private DomainModel entityType;

	public SimpleControllerModel(){
		super();
	}

	@Override
	public DomainModel getEntityType(){
		return entityType;
	}

	public void setEntityType(DomainModel entityType){
		this.entityType = entityType;
	}
}
