package com.sedisys.autogen.generator.model.domain;

import java.util.List;

import com.sedisys.autogen.generator.model.Named;
import com.sedisys.autogen.generator.model.domain.field.DomainFieldModel;
import com.sedisys.autogen.generator.model.domain.field.FieldGrouping;

public interface DomainModel extends Named{

	public String getNameSpace();

	public String getQualifiedName();

	public List<? extends DomainFieldModel> getFields();

	public List<FieldGrouping> getFieldGroupings();

}


