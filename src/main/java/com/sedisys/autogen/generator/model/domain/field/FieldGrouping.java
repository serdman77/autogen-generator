package com.sedisys.autogen.generator.model.domain.field;

import java.util.List;

import com.sedisys.autogen.generator.model.Named;
import com.sedisys.autogen.generator.model.field.FieldModel;

public interface FieldGrouping extends Named{
	public GroupingType getType();

	public List<FieldModel> getFields();

	public boolean containsField(String fieldName);

	public enum GroupingType{
		NATURALID, UNIQUE, INDEXED
	}
}
