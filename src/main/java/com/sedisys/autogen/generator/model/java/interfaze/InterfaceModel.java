package com.sedisys.autogen.generator.model.java.interfaze;

import java.util.List;

import com.sedisys.autogen.generator.model.Named;
import com.sedisys.autogen.generator.model.java.annotation.Annotated;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.type.References;
import com.sedisys.autogen.generator.model.type.TypeModel;

public interface InterfaceModel extends Named, Annotated, References, TypeModel{
	public List<TypeModel> getGenerics();

	public List<TypeModel> getInterfaces();

	public List<MethodModel> getMethods();
}
