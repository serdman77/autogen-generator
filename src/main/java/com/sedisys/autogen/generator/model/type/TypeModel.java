package com.sedisys.autogen.generator.model.type;

import java.util.List;
import java.util.Set;

import com.sedisys.autogen.generator.model.Named;

public interface TypeModel extends Named, References{

	public String getNameSpace();

	public String getQualifiedName();

	public List<? extends TypeModel> getContainedTypes();

	public Set<? extends TypeModel> getContainedTypeReferences();
}
