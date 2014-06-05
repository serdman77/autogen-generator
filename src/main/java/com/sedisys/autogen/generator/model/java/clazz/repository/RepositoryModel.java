package com.sedisys.autogen.generator.model.java.clazz.repository;

import java.util.List;

import com.sedisys.autogen.generator.model.domain.DomainModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;
import com.sedisys.autogen.generator.model.java.annotation.Annotated;
import com.sedisys.autogen.generator.model.java.interfaze.InterfaceModel;

public interface RepositoryModel extends InterfaceModel, Annotated{
	public DomainModel getDomainModel();

	public List<FieldGrouping> getFieldGroupings();

	public boolean isUseQueryDSL();
}
