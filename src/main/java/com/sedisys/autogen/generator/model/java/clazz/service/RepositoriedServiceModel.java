package com.sedisys.autogen.generator.model.java.clazz.service;

import com.sedisys.autogen.generator.model.domain.Domained;
import com.sedisys.autogen.generator.model.java.clazz.ClassModel;
import com.sedisys.autogen.generator.model.java.clazz.repository.RepositoryModel;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;

public interface RepositoriedServiceModel extends ClassModel<ClassFieldModel>, Domained{

	public RepositoryModel getRepositoryModel();
}
