package com.sedisys.autogen.generator.model.java.clazz;

import java.util.List;

import com.sedisys.autogen.generator.model.java.annotation.Annotated;
import com.sedisys.autogen.generator.model.java.field.ClassFieldModel;
import com.sedisys.autogen.generator.model.method.MethodModel;
import com.sedisys.autogen.generator.model.type.References;
import com.sedisys.autogen.generator.model.type.TypeModel;

public interface ClassModel<TFieldType extends ClassFieldModel> extends Annotated, References, TypeModel{
	public List<TypeModel> getInterfaces();

	public List<TFieldType> getFields();

	public List<MethodModel> getMethods();
}
