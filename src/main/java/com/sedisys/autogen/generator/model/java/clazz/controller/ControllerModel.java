package com.sedisys.autogen.generator.model.java.clazz.controller;

import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.java.clazz.ClassModel;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;

public interface ControllerModel extends ClassModel<ClassFieldModel>{
	public DomainModel getEntityType();
}
