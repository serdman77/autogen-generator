package com.sedisys.autogen.generator.model.java.type;

import java.util.List;

import com.sedisys.autogen.generator.model.type.TypeModel;

public interface ClassTypeModel extends TypeModel{
	public Class<? extends Object> getTypeClass();

	public List<? extends TypeModel> getGenerics();
}
